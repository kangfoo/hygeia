package org.apache.camel.example.reportincident;

import org.jvnet.mock_javamail.Mailbox;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-4
 * Time: 上午10:47
 * mvn clean test -Dtest=org.apache.camel.example.reportincident.ReportIncidentPart4EndpointImplTest  test
 */
public class ReportIncidentPart4EndpointImplTest extends BaseTest {

    /**
     *
     * @throws Exception
     */
    public void testRendportIncident() throws Exception {

        // get the mailbox
        Mailbox box = Mailbox.get("incident@mycompany.com");
        assertEquals("Should not have mails", 0, box.size());

        super.launch(new ReportIncidentPart4EndpointImpl());

        // let the consumer have time to run
        Thread.sleep(3 * 1000);

        // get the mock mailbox and check if we got mail ;)
        assertEquals("Should have got 1 mail", 1, box.size());
        assertEquals("Mail body right", true, box.get(0).getContent().toString().contains("Incident 123 has been reported on the 2008-07-16 by Claus Ibsen."));

    }
}
