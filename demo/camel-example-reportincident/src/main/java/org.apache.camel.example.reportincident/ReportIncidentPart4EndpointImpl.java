package org.apache.camel.example.reportincident;

import org.apache.camel.*;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.language.bean.BeanLanguage;

import javax.jws.WebParam;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-3
 * Time: 下午10:44
 * Brew a cup of coffee, tug the kids and kiss the wife, for now we will have us some fun with the Camel. See you in part 3.
 */
public class ReportIncidentPart4EndpointImpl implements ReportIncidentEndpoint {

    private CamelContext context;
    private ProducerTemplate template;

    public ReportIncidentPart4EndpointImpl() throws Exception {
        // create the context
        context = new DefaultCamelContext();
        template = context.createProducerTemplate();

        // append the routes to the context
        context.addRoutes(new ReportIncidentPart4Routes());

        // at the end start the camel context
        context.start();
    }


    @Override
    public OutputReportIncident reportIncident(@WebParam(partName = "parameters", name = "inputReportIncident", targetNamespace = "http://reportincident.example.camel.apache.org") InputReportIncident parameters) {
        /**
         * Notice that we get the producer template using the createProducerTemplate method on the CamelContext.
         * Then we send the input parameters to the direct:start endpoint and it will route it to the velocity
         * endpoint that will generate the mail body. Since we use direct as the consumer endpoint (=from) and
         * its a synchronous exchange we will get the response back from the route. And the response is of course
         * the output from the velocity endpoint.
         */
        // re-use the  ProducerTemplate . Why ? ref:  https://camel.apache.org/why-does-camel-use-too-many-threads-with-producertemplate.html
        //  Object mailBody = context.createProducerTemplate().requestBody("direct:start", parameters);
        Object mailBody = template.requestBodyAndHeader("direct:start", parameters, Exchange.FILE_NAME, BeanLanguage.bean(FilenameGenerator.class, "generateFilename"));
        System.out.println("Body:" + mailBody);

        // return an OK reply
        OutputReportIncident out = new OutputReportIncident();
        out.setCode("OK");
        return out;
    }
}
