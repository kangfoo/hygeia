package org.apache.camel.example.reportincident.part5;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.language.ognl.OgnlExpression;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-4
 * Time: 上午12:58
 * To change this template use File | Settings | File Templates.
 */
public class ReportIncidentPart5Routes extends RouteBuilder {
    @Override
    public void configure() throws Exception {
//        // direct:start is a internal queue to kick-start the routing in our example
//        // we use this as the starting point where you can send messages to direct:start
//        from("direct:start")
//
//                .setHeader(Exchange.FILE_NAME, BeanLanguage.bean(FilenameGenerator.class, "generateFilename"))
//                // to is the destination we send the message to our velocity endpoint
//                // where we transform the mail body
//                .to("velocity:MailBody.vm")
//                // set the filename to a constant before the file producer receives the message
//                // .setHeader(Exchange.FILE_NAME, constant("incident.txt"))
//
//                // set the filename using the bean language and call the FilenameGenerator class.
//                // the 2nd null parameter is optional methodname, to be used to avoid ambiguity.
//                // if not provided Camel will try to figure out the best method to invoke, as we
//                // only have one method this is very simple
//
//
//                .to("file://target/subfolder");

// using pipes-and-filters we send the output from the previous to the next
// 或者 使用管线, ognl
        from("direct:start")
                // we need to set the filename and uses OGNL for this
                .setHeader(Exchange.FILE_NAME, OgnlExpression.ognl("'mail-incident-' + request.body.incidentId + '.txt'"))
                        // using pipes-and-filters we send the output from the previous to the next
                .pipeline("velocity:MailBody.vm", "file://target/subfolder");


        // second part from the file backup -> send email
        from("file://target/subfolder")
                // set the subject of the email
                .setHeader("subject", constant("New incident reported"))
                // send the email
                .to("smtp://someone@localhost?password=secret&to=incident@mycompany.com");
    }
}
