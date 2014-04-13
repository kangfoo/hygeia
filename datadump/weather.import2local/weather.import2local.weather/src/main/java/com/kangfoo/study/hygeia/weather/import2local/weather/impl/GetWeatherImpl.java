package com.kangfoo.study.hygeia.weather.import2local.weather.impl;

import com.kangfoo.study.hygeia.weather.import2local.weather.GetWeather;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-3-27
 * Time: 下午9:16
 * To change this template use File | Settings | File Templates.
 */
public class GetWeatherImpl implements GetWeather {

    private final static Logger logger = LoggerFactory
            .getLogger(GetWeatherImpl.class);

    private String getWeather(String url) {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            return getData(httpclient, url);

        } catch (ClientProtocolException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            try {
                httpclient.close();
            } catch (IOException e) {
                logger.error("", e);
            }
        }
        return null;
    }


    public void importData() {
        String timeStamp = timeStamp();
        String urlTemp = GetWeather.URL;
        String url = null;
        for (String city : GetWeather.citys){
            url = String.format(urlTemp, city);
            print2log(timeStamp +"|"+ getWeather(url) );
        }


    }

    private String getData(CloseableHttpClient httpclient, String urlstr)
            throws IOException, ClientProtocolException {
        HttpGet httpget = new HttpGet(urlstr);

        ResponseHandler<String> responseHandler = new ResponseHandler<String>() {

            public String handleResponse(final HttpResponse response)
                    throws ClientProtocolException, IOException {
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity entity = response.getEntity();
                    return entity != null ? EntityUtils.toString(entity) : null;
                } else {
                    throw new ClientProtocolException(
                            "Unexpected response status: " + status);
                }
            }
        };
        String responseBody = httpclient.execute(httpget, responseHandler);

        return responseBody;
    }

    private void print2log(String log){
        logger.info(log);
    }

//
//    public static void main(String[] args) {
//        GetWeatherImpl getWeather = new GetWeatherImpl();
//        getWeather.importData();
//    }

    DateTimeFormatter yyyyMMddHHmmssformat = DateTimeFormat.forPattern("yyyyMMddHHmmss");

    public String timeStamp() {
        return DateTime.now().toString(yyyyMMddHHmmssformat);
    }
}
