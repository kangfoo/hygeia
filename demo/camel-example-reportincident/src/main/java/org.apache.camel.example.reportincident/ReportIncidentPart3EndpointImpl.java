package org.apache.camel.example.reportincident;

import org.apache.camel.*;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jws.WebParam;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-3
 * Time: 下午10:44
 * Brew a cup of coffee, tug the kids and kiss the wife, for now we will have us some fun with the Camel. See you in part 3.
 */
public class ReportIncidentPart3EndpointImpl implements ReportIncidentEndpoint {

    private CamelContext camel;
    private ProducerTemplate template;

    public ReportIncidentPart3EndpointImpl() throws Exception {
        init(true);
    }

    public ReportIncidentPart3EndpointImpl(boolean enableConsumer) throws Exception {
        init(enableConsumer);
    }

    private void init(boolean enableConsumer) throws Exception {
        // create the camel context that is the "heart" of Camel
        camel = new DefaultCamelContext();

        // get the ProducerTemplate thst is a Spring'ish xxxTemplate based producer for very
        // easy sending exchanges to Camel.
        template = camel.createProducerTemplate();

        // add the event driven consumer that will listen for mail files and process them
        if (enableConsumer) {
            addMailSendConsumer();
        }

        // start Camel
        camel.start();
    }

    private void addMailSendConsumer() throws Exception {
        // Grab the endpoint where we should consume. Option - the first poll starts after 2 seconds
        Endpoint endpint = camel.getEndpoint("file://target/subfolder?consumer.initialDelay=2000");

        // create the event driven consumer
        // the Processor is the code what should happen when there is an event
        // (think it as the onMessage method)
        Consumer consumer = endpint.createConsumer(new Processor() {
            public void process(Exchange exchange) throws Exception {
                // get the mail body as a String
                String mailBody = exchange.getIn().getBody(String.class);

                // okay now we are read to send it as an email
                System.out.println("Sending email..." + mailBody);
                sendEmail(mailBody);
                System.out.println("Email sent");
            }
        });

        // star the consumer, it will listen for files
        consumer.start();
    }

    private void sendEmail(String body) {
        // send the email to your mail server
        String url = "smtp://someone@localhost?password=secret&to=incident@mycompany.com";
        template.sendBodyAndHeader(url, body, "subject", "New incident reported");
    }

    protected ProducerTemplate getTemplate() {
        return template;
    }

    @Override
    public OutputReportIncident reportIncident(@WebParam(partName = "parameters", name = "inputReportIncident", targetNamespace = "http://reportincident.example.camel.apache.org") InputReportIncident parameters) {
        // transform the request into a mail body
        Object mailBody = template.requestBody("velocity:MailBody.vm", parameters);

        // store the mail body in a file
        String filename = "mail-incident-" + parameters.getIncidentId() + ".txt";
        template.sendBodyAndHeader("file://target/subfolder", mailBody, Exchange.FILE_NAME, filename);

        // return an OK reply
        OutputReportIncident out = new OutputReportIncident();
        out.setCode("OK");
        return out;
    }
}
