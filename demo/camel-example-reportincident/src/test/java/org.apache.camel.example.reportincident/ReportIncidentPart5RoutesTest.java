package org.apache.camel.example.reportincident;

import junit.framework.TestCase;
import org.apache.camel.CamelContext;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.jvnet.mock_javamail.Mailbox;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-4
 * Time: 下午11:38
 * $ mvn clean test -Dtest=org.apache.camel.example.reportincident.ReportIncidentPart5RoutesTest  test
 */
public class ReportIncidentPart5RoutesTest extends TestCase {

    private CamelContext camel;

    // should be the same address as we have in our route
    private static String ADDRESS = "http://localhost:8080/part-five/webservices/incident";

    protected void startCamel() throws Exception {
        camel = new DefaultCamelContext();
        camel.addRoutes(new ReportIncidentPart5Routes());
        camel.start();
    }

    protected static ReportIncidentEndpoint createCXFClient(){
        JaxWsProxyFactoryBean factoryBean = new JaxWsProxyFactoryBean();
        factoryBean.setServiceClass(ReportIncidentEndpoint.class);
        factoryBean.setWsdlURL("report_incident.wsdl");
        factoryBean.setAddress(ADDRESS);
        return (ReportIncidentEndpoint) factoryBean.create();
    }

    public void testRendportIncident() throws Exception {
// start camel
        startCamel();

        // assert mailbox is empty before starting
        Mailbox inbox = Mailbox.get("incident@mycompany.com");
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
        ReportIncidentEndpoint client = createCXFClient();
        OutputReportIncident out = client.reportIncident(input);

        // assert we got a OK back
        assertEquals("0", out.getCode());

        // let some time pass to allow Camel to pickup the file and send it as an email
        Thread.sleep(3000);

        // assert mail box
        assertEquals("Should have got 1 mail", 1, inbox.size());

        // stop camel
        camel.stop();

    }
}
