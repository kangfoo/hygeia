package org.apache.camel.example.reportincident;

import org.apache.camel.Exchange;
import org.jvnet.mock_javamail.Mailbox;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-3
 * Time: 下午11:17
 * 使用命令行方式运行。
 * $ mvn clean test -Dtest=org.apache.camel.example.reportincident.ReportIncidentPart3EndpointImplTest  test
 *
 * <!-- 发邮件需要，否则报错是没有日志的。只能调试查看。这是不是导致我在 serviceMix 中安装 bundle 时，有些 bundle 是无法启动但又很难定位的一个原因呢。TODO. -->
     <dependency>
         <groupId>org.apache.camel</groupId>
         <artifactId>camel-mail</artifactId>
         <version>${camel-version}</version>
     </dependency>
 *
 */
public class ReportIncidentPart3EndpointImplTest extends BaseTest {

    private ReportIncidentPart3EndpointImpl endpoint;

    public void testConsumer() throws Exception {
        // we run this unit test with the consumer, hence the true parameter
        endpoint = new ReportIncidentPart3EndpointImpl(true);

        // get the mailbox
        Mailbox box = Mailbox.get("incident@mycompany.com");
        assertEquals("Should not have mails", 0, box.size());

        // drop a file in the folder that the consumer listen
        // here is a trick to reuse Camel! so we get the producer template and just
        // fire a message that will create the file for us
        endpoint.getTemplate().sendBodyAndHeader("file://target/subfolder", "Hello World",
                Exchange.FILE_NAME, "mail-incident-test.txt");

        // let the consumer have time to run
        Thread.sleep(3 * 1000);

        // get the mock mailbox and check if we got mail ;)
        assertEquals("Should have got 1 mail", 1, box.size());
        assertEquals("Subject wrong", "New incident reported", box.get(0).getSubject());
        assertEquals("Mail body wrong", "Hello World", box.get(0).getContent());
    }
}
