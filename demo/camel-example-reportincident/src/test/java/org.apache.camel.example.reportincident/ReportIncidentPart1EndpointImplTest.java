package org.apache.camel.example.reportincident;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-3
 * Time: 下午4:32
 * To change this template use File | Settings | File Templates.
 */
public class ReportIncidentPart1EndpointImplTest extends BaseTest {

    public void testRendportIncident() throws Exception {
        super.launch(new ReportIncidentPart1EndpointImpl());
    }
}
