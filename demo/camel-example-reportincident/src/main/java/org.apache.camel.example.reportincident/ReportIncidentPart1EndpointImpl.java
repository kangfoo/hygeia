package org.apache.camel.example.reportincident;

import javax.jws.WebParam;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-3
 * Time: 上午1:29
 * To change this template use File | Settings | File Templates.
 */
public class ReportIncidentPart1EndpointImpl implements ReportIncidentEndpoint {
    @Override
    public OutputReportIncident reportIncident(@WebParam(partName = "parameters", name = "inputReportIncident", targetNamespace = "http://reportincident.example.camel.apache.org") InputReportIncident parameters) {
        System.out.println("Hello ReportIncidentPart1EndpointImpl is called from " + parameters.getGivenName());

        OutputReportIncident out = new OutputReportIncident();
        out.setCode("OK");
        return out;
    }
}
