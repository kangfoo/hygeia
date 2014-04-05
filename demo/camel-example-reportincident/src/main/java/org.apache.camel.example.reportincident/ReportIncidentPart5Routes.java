package org.apache.camel.example.reportincident;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.example.reportincident.InputReportIncident;
import org.apache.camel.example.reportincident.OutputReportIncident;
import org.apache.camel.language.ognl.OgnlExpression;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-4
 * Time: 上午12:58
 * To change this template use File | Settings | File Templates.
 */
public class ReportIncidentPart5Routes extends RouteBuilder {

    // endpoint to our CXF webservice
    String cxfEndpoint = "cxf://http://localhost:8080/part-five/webservices/incident"
            + "?serviceClass=org.apache.camel.example.reportincident.ReportIncidentEndpoint"
            + "&wsdlURL=report_incident.wsdl";


    @Override
    public void configure() throws Exception {

        // webservice response for OK
        OutputReportIncident OK = new OutputReportIncident();
        OK.setCode("0");

        from(cxfEndpoint)
        // we need to convert the CXF payload to InputReportIncident that FilenameGenerator and velocity expects
        .convertBodyTo(InputReportIncident.class)
        // we need to set the filename and uses OGNL for this
            .setHeader(Exchange.FILE_NAME, OgnlExpression.ognl("'mail-incident-' + request.body.incidentId + '.txt'"))
        // using pipes-and-filters we send the output from the previous to the next
            .pipeline("velocity:MailBody.vm", "file://target/subfolder")
        // return OK as response
        //    .process(new OKResponseProcessor());
        .transform(constant(OK));

        // second part from the file backup -> send email
        from("file://target/subfolder")
                // set the subject of the email
                .setHeader("subject", constant("New incident reported"))
                // send the email
                .to("smtp://someone@localhost?password=secret&to=incident@mycompany.com");
    }


//    private static class OKResponseProcessor implements Processor {
//        public void process(Exchange exchange) throws Exception {
//            // the response we want to send
//            OutputReportIncident OK = new OutputReportIncident();
//            OK.setCode("0");
//
//            // set the response on the OUT message as we use InOut
//            exchange.getOut().setBody(OK);
//        }
//    }

    // CxfExchange stores the payload in a CXF holder class org.apache.cxf.message.MessageContentsList.
//    public void process(final Exchange exchange) {
//        // Get the parameter list
//        List parameter = exchange.getIn().getBody(List.class);
//        // Get the first object in the list that is our InputReportIncident
//        Object input = parameter.get(0);
//        // replace with our input
//        exchange.getOut().setBody(input);
//    }

}
