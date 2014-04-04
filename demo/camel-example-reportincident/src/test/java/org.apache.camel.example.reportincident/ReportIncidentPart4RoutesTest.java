package org.apache.camel.example.reportincident;

import org.apache.camel.ContextTestSupport;
import org.apache.camel.builder.RouteBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-4
 * Time: 上午1:13
 * $ mvn clean test -Dtest=org.apache.camel.example.reportincident.ReportIncidentPart4RoutesTest  test
 */
public class ReportIncidentPart4RoutesTest extends ContextTestSupport {

    @Override
    protected RouteBuilder createRouteBuilder() throws Exception {
        return new ReportIncidentPart4Routes();
    }

    public void testTransformMailBody() throws Exception {
        // create a dummy input with some input data
        InputReportIncident parameters = createInput();

        // send the message (using the sendBody method that takes a parameters as the input body)
        // to "direct:start" that kick-starts the route
        // the response is returned as the out object, and its also the body of the response
        Object out = context.createProducerTemplate().requestBody("direct:start", parameters);

        // convert the response to a string using camel converters. However we could also have casted it to
        // a string directly but using the type converters ensure that Camel can convert it if it wasn't a string
        // in the first place. The type converters in Camel is really powerful and you will later learn to
        // appreciate them and wonder why its not build in Java out-of-the-box
        String body = context.getTypeConverter().convertTo(String.class, out);

        // do some simple assertions of the mail body
        assertTrue(body.startsWith("Incident 123 has been reported on the 2008-07-16 by Claus Ibsen."));
    }

    /**
     * Creates a dummy request to be used for input
     */
    protected InputReportIncident createInput() {
        InputReportIncident input = new InputReportIncident();
        input.setIncidentId("123");
        input.setIncidentDate("2008-07-16");
        input.setGivenName("Claus");
        input.setFamilyName("Ibsen");
        input.setSummary("bla bla");
        input.setDetails("more bla bla");
        input.setEmail("davsclaus@apache.org");
        input.setPhone("+45 2962 7576");
        return input;
    }

}
