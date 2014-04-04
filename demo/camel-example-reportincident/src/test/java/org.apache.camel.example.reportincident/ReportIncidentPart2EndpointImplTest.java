package org.apache.camel.example.reportincident;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-3
 * Time: 下午6:12
 *
 * 测试命令
 * mvn -Dtest=org.apache.camel.example.reportincident.ReportIncidentPart2EndpointImplTest  test
 */
public class ReportIncidentPart2EndpointImplTest extends BaseTest {

    /**
     *
     * @throws Exception
     */
    public void testRendportIncident() throws Exception {
        super.launch(new ReportIncidentPart2EndpointImpl());
    }
}
