package com.kangfoo.study.hygeia.weather.import2local.impl;

import com.kangfoo.study.hygeia.weather.import2local.GetWeather;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-3-27
 * Time: 下午9:16
 *
 * 1. 本例子犯得错误
 *  a. 想当然的使用了接口设计，导致 CAMEL 有些诡异的异常。
 *  b. 多个 url（Endpoint）如何处理
 *  c. http 默认是POST 提交吗， 那么要改成 GET 方式
 *  d. 需要 feature:install camel-http4, 同时 camel-http 也要有的。报错就知道了
 *  e. 再则需要安装 feature:install camel-quartz2。 （我主要想使用高版本，和spring, quartz 更好的支持。）
 *
 * 2. 这个错误怎么回事
 * NoClassDefFoundError: org/apache/camel/component/jms/JmsBinding
 * 我根本就没有使用JMS服务呀。 先安装 features:install camel-jms 。
 *
 * 3. 日志的问题，我还没规划好。
 */
public class GetWeatherImpl extends RouteBuilder {

    private final static Logger logger = LoggerFactory
            .getLogger(GetWeatherImpl.class);

    @Override
    public void configure() throws Exception {

//        <from uri="quartz2://groupName/timerName?cron=0/5+*+*+?+*+*" /> <!-- 0+30+*/1+*+*+? 0 30 */1 * * ? 暂不配置集群-->
//        <!--<bean ref="getWeather" method="dump"/>-->
//        <process ref="getWeather"/>


       // from("quartz2://groupName/timerName?cron=0/5+*+*+?+*+*").to(endpoints()).setHeader(Exchange.HTTP_METHOD,constant("GET"));
       // log("kangfoo, ${body}");
//       from("quartz2://groupName/timerName?cron=0/5+*+*+?+*+*")
//                .to("http://www.weather.com.cn/data/cityinfo/101020100.html")
//                .setHeader(Exchange.HTTP_METHOD,constant("GET"))
//                .log("kangfoo, ${body}");

        from("quartz2://groupName/timerName?cron=0/5+*+*+?+*+*").to(endpoints())
                //.setHeader(Exchange.HTTP_METHOD,constant("GET"));
         .log("kangfoo, ${body}");


    }

//
//   // @Override
//    public String getWeather(String url) {
//        String msg = null;
//        try {
//          //  msg = HttpClientUtils.get(url);
//
//        } catch (Exception e) {
//            logger.error(e.getMessage(), e);
//        }
//        return msg;
//    }

//    //@Override
//    public void dump() {
//        String urlTemp = GetWeather.URL;
//        String url = null;
//        for (String city : GetWeather.citys){
//            url = String.format(urlTemp, city);
//            logger.info(getWeather(url));
//        }
//    }

    public String[] endpoints() {

        List<String> endpointslist = new ArrayList(GetWeather.citys.size());

        String urlTemp = GetWeather.URL;
        String url = null;

        for (String city : GetWeather.citys){
            url = String.format(urlTemp, city);
            endpointslist.add(url);
        }

        return endpointslist.toArray(new String[]{});
    }


}
