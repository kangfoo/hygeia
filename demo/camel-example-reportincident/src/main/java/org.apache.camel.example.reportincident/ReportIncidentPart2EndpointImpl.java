package org.apache.camel.example.reportincident;

import org.apache.camel.*;
import org.apache.camel.impl.DefaultCamelContext;

import javax.jws.WebParam;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-3
 * Time: 下午6:07
 * To change this template use File | Settings | File Templates.
 */
public class ReportIncidentPart2EndpointImpl implements ReportIncidentEndpoint {

    private CamelContext camel;

    // Reducing code lines 1.
    private ProducerTemplate template;

    public ReportIncidentPart2EndpointImpl() throws Exception {
//        // create the camel context that is the "heart" of Camel
        camel = new DefaultCamelContext();
//
//        // add the log component
//        camel.addComponent("log", new LogComponent());
//
//        // add the file component
//        camel.addComponent("file", new FileComponent());
//
//        // start Camel
//        camel.start();

        // Reducing code lines 2.
        // get the ProducerTemplate thst is a Spring'ish xxxTemplate based producer for very
        // easy sending exchanges to Camel.
        template = camel.createProducerTemplate();

        // start Camel
        camel.start();
    }

    @Override
    public OutputReportIncident reportIncident(@WebParam(partName = "parameters", name = "inputReportIncident", targetNamespace = "http://reportincident.example.camel.apache.org") InputReportIncident parameters) {
        String name = parameters.getGivenName() + " " + parameters.getFamilyName();

//        Reducing code lines 3.
//        // let Camel do something with the name
//        sendToCamelLog(name);
//        sendToCamelFile(parameters.getIncidentId(), name);
        generateEmailBodyAndStoreAsFile(parameters);

        OutputReportIncident out = new OutputReportIncident();
        out.setCode("OK");
        return out;
    }

    private void sendToCamelLog(String name) {
        try {
            // get the log component
            Component component = camel.getComponent("log");

            // create an endpoint and configure it.
            // Notice the URI parameters this is a common practice in Camel to configure
            // endpoints based on URI.
            // com.mycompany.part2 = the log category used. Will log at INFO level as default
            Endpoint endpoint = component.createEndpoint("log:com.mycompany.part2");

            // create an Exchange that we want to send to the endpoint
            Exchange exchange = endpoint.createExchange();
            // set the in message payload (=body) with the name parameter
            exchange.getIn().setBody(name);

            // now we want to send the exchange to this endpoint and we then need a producer
            // for this, so we create and start the producer.
            Producer producer = endpoint.createProducer();
            producer.start();
            // process the exchange will send the exchange to the log component, that will process
            // the exchange and yes log the payload
            producer.process(exchange);

            // stop the producer, we want to be nice and cleanup
            producer.stop();

        } catch (Exception e) {
            // we ignore any exceptions and just rethrow as runtime
            throw new RuntimeException(e);

        }
    }

    private void sendToCamelFile(String incidentId, String name) {
        try {
            // get the file component
            Component component = camel.getComponent("file");

            // create an endpoint and configure it.
            // Notice the URI parameters this is a common practice in Camel to configure
            // endpoints based on URI.
            // file://target instructs the base folder to output the files. We put in the target folder
            // then its automatically cleaned by mvn clean

            Endpoint endpoint = component.createEndpoint("file://target");

//            // create the file endpoint, we cast to FileEndpoint because then we can do
//            // 100% java settter based configuration instead of the URI sting based
//            // must pass in an empty string, or part of the URI configuration if wanted
//            FileEndpoint endpoint = (FileEndpoint)component.createEndpoint("");
//            endpoint.setFile(new File("target/subfolder"));
//            endpoint.setAutoCreate(true);

            // create an Exchange that we want to send to the endpoint
            Exchange exchange = endpoint.createExchange();
            // set the in message payload (=body) with the name parameter
            exchange.getIn().setBody(name);

            // now a special header is set to instruct the file component what the output filename
            // should be
            // exchange.getIn().setHeader(FileComponent.HEADER_FILE_NAME, "incident-" + incidentId + ".txt");
            // 文件生成的名字好像 ID-kang-mac-local-49648-1396521424732-0-1 是个有序的值，不是指定的 前缀 + ${value} + .txt
            // exchange.getIn().setHeader("FileComponent.HEADER_FILE_NAME", "incident-" + incidentId + ".txt");

            exchange.getIn().setHeader(Exchange.FILE_NAME, "incident-" + incidentId + ".txt");

            // now we want to send the exchange to this endpoint and we then need a producer
            // for this, so we create and start the producer.
            Producer producer = endpoint.createProducer();
            producer.start();
            // process the exchange will send the exchange to the file component, that will process
            // the exchange and yes write the payload to the given filename
            producer.process(exchange);

            // stop the producer, we want to be nice and cleanup
            producer.stop();
        } catch (Exception e) {
            // we ignore any exceptions and just rethrow as runtime
            throw new RuntimeException(e);
        }
    }

    private String createMailBody(InputReportIncident parameters) {
        StringBuilder sb = new StringBuilder();
        sb.append("Incident ").append(parameters.getIncidentId());
        sb.append(" has been reported on the ").append(parameters.getIncidentDate());
        sb.append(" by ").append(parameters.getGivenName());
        sb.append(" ").append(parameters.getFamilyName());

        // and the rest of the mail body with more appends to the string builder

        return sb.toString();
    }

    private void generateEmailBodyAndStoreAsFile(InputReportIncident parameters) {
        // generate the mail body using velocity template
        // notice that we just pass in our POJO (= InputReportIncident) that we
        // got from Apache CXF to Velocity.
        String response = template.requestBody("velocity:MailBody.vm", parameters).toString();

        // Note: the response is a String and can be cast to String if needed

        // store the mail in a file
        String filename = "mail-incident-" + parameters.getIncidentId() + ".txt";
        template.sendBodyAndHeader("file://target/subfolder", response, Exchange.FILE_NAME, filename);
    }
}
