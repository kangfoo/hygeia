package com.kangfoo.study.hygeia.weather.import2local.weather;

import org.apache.camel.builder.RouteBuilder;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-3-27
 * Time: 下午9:16
 * <p/>
 * 我自定义的 RouteDefinition 不起效呀。
 * 中央气象台3个接口有时数据并不是同步的。
 * http://www.weather.com.cn/data/sk/101010100.html  （这貌似还行）
 * http://www.weather.com.cn/data/cityinfo/101010100.html
 * http://m.weather.com.cn/data/101010100.html
 *
 * 此类，无实际意义，仅记录开发过程中的问题。多个 http url 请求 无法使用 自定义 的 RouteDefinition 转而使用自己的原生实现。
 */
public class GetWeatherBuilder extends RouteBuilder {

    private String flag; //当前 bundle 的简称标识符。
    private String quartz2;

    @Override
    public void configure() throws Exception {
        from(quartz2)
           .beanRef("getWeather","importData");
    }

//    /**
//     * 不起效呀。
//     */
//    private class MultiRouteDefinition extends RouteDefinition{
//
//        public RouteDefinition doBiz(){
//            Map<String, String> map = endpointsMap();
//            for (String ep : map.keySet()){
//                from(map.get(ep))
//                        .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
//                        .convertBodyTo(String.class)  // 字节流转 string 要优化(不添加次转换启动运行明显缓慢)。 参见 https://camel.apache.org/type-converter.html
//                        .setHeader(Exchange.FILE_NAME, SimpleBuilder.simple(fileNameGenerator.generateFileName(flag +"-"+ep)))
//                        .log("获取 weather")
//                        .to("file://target/" + flag + File.separator + fileNameGenerator.generateDirNameByTime());
//            }
//           return this;
//        }
//    }


//    private Map<String, String> endpointsMap() {
//
//        Map<String, String> map = new HashMap<String, String>(GetWeather.citys.size());
//
//        String urlTemp = GetWeather.URL;
//        String url;
//
//        for (String city : GetWeather.citys) {
//            url = String.format(urlTemp, city);
//            map.put(city, url);
//        }
//
//        return map;
//    }
//
//    private String endpoints() {
//        return StringUtils.join(endpointsMap().values(), ",");
//    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getQuartz2() {
        return quartz2;
    }

    public void setQuartz2(String quartz2) {
        this.quartz2 = quartz2;
    }

}
