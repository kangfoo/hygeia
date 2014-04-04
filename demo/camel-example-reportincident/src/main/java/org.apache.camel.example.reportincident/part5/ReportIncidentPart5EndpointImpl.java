package org.apache.camel.example.reportincident.part5;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.example.reportincident.FilenameGenerator;
import org.apache.camel.example.reportincident.*;
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
public class ReportIncidentPart5EndpointImpl implements ReportIncidentEndpoint {

    private CamelContext context;
    private ProducerTemplate template;

    // endpoint to our CXF webservice
    String cxfEndpoint = "cxf://http://localhost:8080/part-five/webservices/incident"
            + "?serviceClass=org.apache.camel.example.reportincident.ReportIncidentEndpoint"
            + "&wsdlURL=report_incident.wsdl";

    public ReportIncidentPart5EndpointImpl() throws Exception {
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



        Object mailBody = template.requestBodyAndHeader("direct:start", parameters, Exchange.FILE_NAME, BeanLanguage.bean(FilenameGenerator.class, "generateFilename"));
        System.out.println("Body:" + mailBody);

        // return an OK reply
        OutputReportIncident out = new OutputReportIncident();
        out.setCode("OK");
        return out;
    }
}
