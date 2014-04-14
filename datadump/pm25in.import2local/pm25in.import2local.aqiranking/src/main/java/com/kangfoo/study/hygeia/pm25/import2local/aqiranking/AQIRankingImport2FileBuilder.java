package com.kangfoo.study.hygeia.pm25.import2local.aqiranking;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.builder.SimpleBuilder;
import org.apache.camel.component.http4.HttpMethods;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: kangfoo-mac
 * Date: 14-4-5
 * Time: 下午11:02
 * To change this template use File | Settings | File Templates.
 */
public class AQIRankingImport2FileBuilder extends RouteBuilder {

    private String pm25Token;  // 测试数据有次数限制

    private String httpURL;
    private String flag; //当前 bundle 的简称标识符。
    private String quartz2;
    private FileNameGenerator fileNameGenerator;

    @Override
    public void configure() throws Exception {

        from(quartz2)
                .setHeader(Exchange.HTTP_METHOD, constant(HttpMethods.GET))
                .setHeader(Exchange.HTTP_QUERY, constant(pm25Token))
                .to(this.getHttpURL()) // http4
                .convertBodyTo(String.class)  // 字节流转 string 要优化(不添加次转换启动运行明显缓慢)。 参见 https://camel.apache.org/type-converter.html
                .setHeader(Exchange.FILE_NAME, SimpleBuilder.simple(fileNameGenerator.generateFileName(flag)))
                .log("获取API")
                .to("file://target/" + flag + File.separator + fileNameGenerator.generateDirNameByTime())
        ;

    }


    public String getPm25Token() {
        return pm25Token;
    }

    public void setPm25Token(String pm25Token) {
        this.pm25Token = pm25Token;
    }

    public FileNameGenerator getFileNameGenerator() {
        return fileNameGenerator;
    }

    public void setFileNameGenerator(FileNameGenerator fileNameGenerator) {
        this.fileNameGenerator = fileNameGenerator;
    }

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

    public String getHttpURL() {
        return httpURL;
    }

    public void setHttpURL(String httpURL) {
        this.httpURL = httpURL;
    }
}
