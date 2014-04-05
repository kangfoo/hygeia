package org.apache.camel.example.reportincident;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.test.AvailablePortFinder;
import org.apache.camel.test.junit4.CamelTestSupport;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.junit.BeforeClass;
import org.junit.Test;
import org.jvnet.mock_javamail.Mailbox;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-5
 * Time: 下午3:36
 * $ mvn clean test -Dtest=org.apache.camel.example.reportincident.ReportIncidentPart6RoutesTest  test
 */
public class ReportIncidentPart6RoutesTest extends CamelTestSupport{

    // should be the same address as we have in our route
    private static final String URL = "http://localhost:{{port}}/camel-example-reportincident/webservices/incident";

    @BeforeClass
    public static void setupFreePort() throws Exception {
        // find a free port number, and write that in the custom.properties file
        // which we will use for the unit tests, to avoid port number in use problems
        int port = AvailablePortFinder.getNextAvailable();
        String s = "port=" + port;
        File custom = new File("target/custom.properties");
        FileOutputStream fos = new FileOutputStream(custom);
        fos.write(s.getBytes());
        fos.close();
    }

    @Override
    protected CamelContext createCamelContext() throws Exception {
        CamelContext camel = super.createCamelContext();
        camel.addComponent("properties", new PropertiesComponent("classpath:incident.properties,file:target/custom.properties"));
        return camel;
    }

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        ReportIncidentPart6Routes routes = new ReportIncidentPart6Routes();
        routes.setUsingServletTransport(false);
        return routes;
    }

    protected static ReportIncidentEndpoint createCXFClient(String url) {
        // we use CXF to create a client for us as its easier than JAXWS and works
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
        factory.setServiceClass(ReportIncidentEndpoint.class);
        factory.setAddress(url);
        return (ReportIncidentEndpoint) factory.create();
    }

    @Test
    public void testReportIncident() throws Exception {
        // assert mailbox is empty before starting
        Mailbox inbox = Mailbox.get("incident@mycompany.com");
        inbox.clear();
        assertEquals("Should not have mails", 0, inbox.size());

        // create input parameter
        InputReportIncident input = new InputReportIncident();
        input.setIncidentId("123");
        input.setIncidentDate("2008-08-18");
        input.setGivenName("Claus");
        input.setFamilyName("Ibsen");
        input.setSummary("Bla");
        input.setDetails("Bla bla");
        input.setEmail("davsclaus@apache.org");
        input.setPhone("0045 2962 7576");

        // create the webservice client and send the request
        String url = context.resolvePropertyPlaceholders(URL);
        ReportIncidentEndpoint client = createCXFClient(url);
        OutputReportIncident out = client.reportIncident(input);

        // assert we got a OK back
        assertEquals("0", out.getCode());

        // let some time pass to allow Camel to pickup the file and send it as an email
        Thread.sleep(3000);

        // assert mail box
        assertEquals("Should have got 1 mail", 1, inbox.size());
    }

}
