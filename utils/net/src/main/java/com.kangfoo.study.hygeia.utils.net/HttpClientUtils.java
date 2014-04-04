package com.kangfoo.study.hygeia.utils.net;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-1
 * Time: 下午2:24
 * To change this template use File | Settings | File Templates.
 */
public class HttpClientUtils {

    private final static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    /**
     * 默认转为 string 类型输出
     * @return
     * @throws IOException
     */
    public String get(String url) throws IOException {
        String result = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try{
            HttpGet httpGet = new HttpGet(url);

            logger.debug("Executing url : {}, request : {} ", url, httpGet.getRequestLine());

            ResponseHandler<String> responseHandler = new ResponseHandler<String>() {
                @Override
                public String handleResponse(HttpResponse response) throws ClientProtocolException, IOException {

                    int status = response.getStatusLine().getStatusCode();

                    logger.debug("status : {} ", status);

                    if (status >= 200 && status < 300) {
                        HttpEntity entity = response.getEntity();
                        return entity != null ? EntityUtils.toString(entity) : null;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                }
            };

            result = httpClient.execute(httpGet, responseHandler);
        } finally {
            httpClient.close();
        }
        return result;
    }



}
