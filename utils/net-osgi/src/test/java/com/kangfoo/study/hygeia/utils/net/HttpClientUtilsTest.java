package com.kangfoo.study.hygeia.utils.net;

import junit.framework.TestCase;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-1
 * Time: 下午3:16
 * To change this template use File | Settings | File Templates.
 */
public class HttpClientUtilsTest extends TestCase {

    /**
     * 使用 ResponseHandler 推荐做法。
     * @throws java.io.IOException
     */
    public void testGet() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try{
            HttpGet httpGet = new HttpGet("http://www.baidu.com");

            System.out.println("Executing request " + httpGet.getRequestLine());

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {

                    int status = response.getStatusLine().getStatusCode();
                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };

            String responseBody = httpClient.execute(httpGet, responseHandler);
            System.out.println("----------------------------------------");
            System.out.println(responseBody);
        } finally {
            httpClient.close();
        }


    }

}
