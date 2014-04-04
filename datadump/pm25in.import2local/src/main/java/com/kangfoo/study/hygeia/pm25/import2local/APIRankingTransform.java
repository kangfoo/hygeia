package com.kangfoo.study.hygeia.pm25.import2local;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-2
 * Time: 下午3:54
 * To change this template use File | Settings | File Templates.
 */
public class APIRankingTransform {

    private static final transient Logger LOG = LoggerFactory.getLogger(APIRankingTransform.class);

    private final static String all_citiesUrl = "http://www.pm25.in/api/querys/all_cities.json?token=%s";

    private String pm25Token = "5j1znBVAsnSf5xQyNQyq";

    public Object writeLog(Object body) {
        LOG.info(body.toString());
        return body;
    }

    public Object transform(Object body) {
        return getUrl();
    }

    public String getPm25Token() {
        return pm25Token;
    }

    public void setPm25Token(String pm25Token) {
        this.pm25Token = pm25Token;
    }

//    @Override
//    public void configure() throws Exception {
//        from("timer://myTimer?fixedRate=true?period=2000").to(getUrl())
//              //  .setHeader(Exchange.HTTP_METHOD,constant("GET"));    // HTTP STATUS 502
//        ;
//
//        log.info(outBody().convertToString().toString());
//
//    }

    private String getUrl() {
        return String.format(all_citiesUrl, pm25Token);
    }
}
