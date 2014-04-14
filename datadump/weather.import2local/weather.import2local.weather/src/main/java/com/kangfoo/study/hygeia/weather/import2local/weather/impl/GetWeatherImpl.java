package com.kangfoo.study.hygeia.weather.import2local.weather.impl;

import com.kangfoo.study.hygeia.weather.import2local.weather.GetWeather;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
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
 * camel-http4 使用的是 http 4.2.5 版本，我之前用的是 4.3.2
 * 参见文档：
 * https://hc.apache.org/httpcomponents-client-4.2.x/examples.html
 */
public class GetWeatherImpl implements GetWeather {

    private final static Logger logger = LoggerFactory
            .getLogger(GetWeatherImpl.class);

    private String getWeather(String url) {
        HttpClient httpClient = new DefaultHttpClient();
        try{
            HttpGet httpGet = new HttpGet(url);
            return httpClient.execute(httpGet, new BasicResponseHandler());
        } catch (ClientProtocolException e) {
            logger.error("", e);
        } catch (IOException e) {
            logger.error("", e);
        } finally {
            httpClient.getConnectionManager().shutdown();

        }
        return  null;
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

    private void print2log(String log){
        logger.info(log);
    }


    public static void main(String[] args) {
        GetWeatherImpl getWeather = new GetWeatherImpl();
        getWeather.importData();
    }

    DateTimeFormatter yyyyMMddHHmmssformat = DateTimeFormat.forPattern("yyyyMMddHHmmss");

    public String timeStamp() {
        return DateTime.now().toString(yyyyMMddHHmmssformat);
    }
}
