package com.kangfoo.study.hygeia.pm25.import2local.route;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-5
 * Time: 下午6:32
 * To change this template use File | Settings | File Templates.
 */
public class APIRankingRoutes extends RouteBuilder {

    private final static Logger logger = LoggerFactory
            .getLogger(APIRankingRoutes.class);

    private final static String all_citiesUrl = "http://www.pm25.in/api/querys/aqi_ranking.json?token=%s";

    private final static String token = "5j1znBVAsnSf5xQyNQyq";

    @Override
    public void configure() throws Exception {
//        <from uri="quartz2://groupName/timerName?cron=0/5+*+*+?+*+*" />
//        <to uri="http://www.pm25.in/api/querys/all_cities.json?token=${pm25Token}" />
//        <bean ref="apiRankingTransform" method="writeLog"/>
//        <to uri="log:apiRankingLog" />



    }
}
