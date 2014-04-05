package org.apache.camel.example.reportincident;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.properties.PropertiesComponent;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.language.bean.BeanLanguage;
import org.apache.camel.language.ognl.OgnlExpression;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-5
 * Time: 上午12:58
 *
 */
public class ReportIncidentPart6Routes extends RouteBuilder {

    private boolean usingServletTransport = true;

    public void setUsingServletTransport(boolean usingServletTransport) {
        this.usingServletTransport = usingServletTransport;
    }

    @Override
    public void configure() throws Exception {

        OutputReportIncident ok = new OutputReportIncident();
        ok.setCode("0");

        // endpoint to our CXF webservice
        // We should use the related path to publish the service, when using the ServletTransport
        // so we need to configure set the bus which is configured to use the ServletTransport
        String cxfEndpointAddress = "cxf:/incident?bus=#cxf&";
        // Using the full http address for stand alone running
        if (!usingServletTransport) {
            cxfEndpointAddress = "cxf://http://localhost:{{port}}/camel-example-reportincident/webservices/incident?";
        }
        String cxfEndpoint = cxfEndpointAddress
                + "serviceClass=org.apache.camel.example.reportincident.ReportIncidentEndpoint"
                + "&wsdlURL=etc/report_incident.wsdl";

        // first part from the webservice -> file backup
        from(cxfEndpoint)
                // we need to convert the CXF payload to InputReportIncident that FilenameGenerator and velocity expects
                .convertBodyTo(InputReportIncident.class)
                        // then set the file name using the FilenameGenerator bean
                .setHeader(Exchange.FILE_NAME, BeanLanguage.bean(FilenameGenerator.class, "generateFilename"))
                        // and create the mail body using velocity template
                .to("velocity:etc/MailBody.vm")
                        // and store the file
                .to("file://target/subfolder")
                        // return OK as response
                .log("Wrote ${file:name} and returning OK response")
                .transform(constant(ok));

        // second part from the file backup -> send email
        from("file://target/subfolder")
                // set the subject of the email
                .setHeader("subject", constant("new incident reported"))
                        // send the email
                .log("Sending email to incident@mycompany.com:\n${body}")
                .to("smtp://someone@localhost?password=secret&to=incident@mycompany.com");
    }

}
