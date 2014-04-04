package org.apache.camel.example.reportincident;

import junit.framework.TestCase;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import javax.xml.ws.Endpoint;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-3
 * Time: 下午6:16
 * To change this template use File | Settings | File Templates.
 */
public class BaseTest extends TestCase {

    private static String ADDRESS = "http://localhost:9090/unittest";

    /**
     *
     *  publish 错误。肯能版本有差异。
     * 1.
     * javax.xml.ws.WebServiceException: java.lang.ArrayIndexOutOfBoundsException: 2
     at org.apache.cxf.jaxws.EndpointImpl.doPublish(EndpointImpl.java:357)
     at org.apache.cxf.jaxws.EndpointImpl.publish(EndpointImpl.java:246)
     at org.apache.cxf.jaxws.spi.ProviderImpl.createAndPublishEndpoint(ProviderImpl.java:151)
     at javax.xml.ws.Endpoint.publish(Endpoint.java:240)
     at org.apache.camel.example.reportincident.ReportIncidentPart1EndpointImplTest.startServer(ReportIncidentPart1EndpointImplTest.java:25)
     at org.apache.camel.example.reportincident.ReportIncidentPart1EndpointImplTest.testRendportIncident(ReportIncidentPart1EndpointImplTest.java:37)
     at sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)
     at sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)
     at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)
     at com.intellij.junit3.JUnit3IdeaTestRunner.doRun(JUnit3IdeaTestRunner.java:139)
     at com.intellij.rt.execution.junit.JUnitStarter.main(JUnitStarter.java:63)
     Caused by: java.lang.ArrayIndexOutOfBoundsException: 2
     at org.apache.cxf.ws.policy.PolicyAnnotationListener.handleEvent(PolicyAnnotationListener.java:112)
     at org.apache.cxf.service.factory.AbstractServiceFactoryBean.sendEvent(AbstractServiceFactoryBean.java:72)
     at org.apache.cxf.frontend.AbstractWSDLBasedEndpointFactory.createBindingInfo(AbstractWSDLBasedEndpointFactory.java:345)
     at org.apache.cxf.jaxws.JaxWsServerFactoryBean.createBindingInfo(JaxWsServerFactoryBean.java:181)
     at org.apache.cxf.frontend.AbstractWSDLBasedEndpointFactory.createEndpointInfo(AbstractWSDLBasedEndpointFactory.java:258)
     at org.apache.cxf.frontend.AbstractWSDLBasedEndpointFactory.createEndpoint(AbstractWSDLBasedEndpointFactory.java:143)
     at org.apache.cxf.frontend.ServerFactoryBean.create(ServerFactoryBean.java:159)
     at org.apache.cxf.jaxws.JaxWsServerFactoryBean.create(JaxWsServerFactoryBean.java:207)
     at org.apache.cxf.jaxws.EndpointImpl.getServer(EndpointImpl.java:442)
     at org.apache.cxf.jaxws.EndpointImpl.doPublish(EndpointImpl.java:329)
     ... 23 more

     ------> 解决方案： 使用cxf版本 2.7.1 替换 2.6.1

     * @throws Exception
     */
    protected void startServer(ReportIncidentEndpoint server ) throws Exception {
        // We need to start a server that exposes or webservice during the unit testing
        // We use jaxws to do this pretty simple
        Endpoint.publish(ADDRESS, server);
    }

    protected ReportIncidentEndpoint createCXFClient() {
        // we use CXF to create a client for us as its easier than JAXWS and works
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ReportIncidentEndpoint.class);
        factory.setAddress(ADDRESS);
        return (ReportIncidentEndpoint) factory.create();
    }
    
    public OutputReportIncident launch(ReportIncidentEndpoint reportIncidentEndpoint) throws Exception {
        startServer(reportIncidentEndpoint);

        ReportIncidentEndpoint client = createCXFClient();

        InputReportIncident input = new InputReportIncident();
        input.setIncidentId("123");
        input.setIncidentDate("2008-07-16");
        input.setGivenName("Claus");
        input.setFamilyName("Ibsen");
        input.setSummary("bla bla");
        input.setDetails("more bla bla");
        input.setEmail("davsclaus@apache.org");
        input.setPhone("+45 2962 7576");

        OutputReportIncident out = client.reportIncident(input);
        assertEquals("Response code is wrong", "OK", out.getCode());
        return out;
    }

}
