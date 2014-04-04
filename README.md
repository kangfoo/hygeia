hygeia
======

基于ServiceMix OSGi 容器实现的 有关 PM2.5数据的采集和相关数据分析的学习程序。

开发过程纪要：

1. 使用 maven commnad 建立 Create an OSGi blueprint bundle (karaf-blueprint-archetype) 类型的项目
命令如下：

mvn archetype:generate \
    -DarchetypeGroupId=org.apache.karaf.archetypes \
    -DarchetypeArtifactId=karaf-blueprint-archetype \
    -DarchetypeVersion=2.3.4 \
    -DgroupId=com.kangfoo.study.hygeia \
    -DartifactId=pm25in.import2local \
    -Dversion=1.0-SNAPSHOT \
    -Dpackage=com.kangfoo.study.pm25.import2local


2. 调整日志输出
$ cd ${SERVICEMIX_HOME}/etc/org.ops4j.pax.logging.cfg
添加 sift
Root logger
log4j.rootLogger=INFO, out, osgi:VmLogAppender, sift
并注释掉
log4j.appender.sift.default=servicemix

3. 安装 jms 组件
这个错误怎么回事
 * NoClassDefFoundError: org/apache/camel/component/jms/JmsBinding
 * 先安装 features:install camel-jms 。

4. 出现 OSGi 容器异常，空指针，
 我目前只能重启，不行再 rm -rf ${SERVICEMIX_HOME}/data/* 下的所有文件，全部重新开始。
 bundle 才可以正常安装运行。问题待分析。


5. 错误：
2014-04-02 17:57:18,970 | INFO | mix-5.0.0/deploy | BlueprintContainerImpl | ? ? | 8 - org.apache.aries.blueprint.core - 1.4.0 | Bundle pm25in.import2local is waiting for dependencies [(&(component=quartz2)(objectClass=org.apache.camel.spi.ComponentResolver)), (&(component=http)(objectClass=org.apache.camel.spi.ComponentResolver))]

------>
 karaf@root> features:install camel-quartz2
 karaf@root> features:install camel-http4

6.
e pm25in.import2local is waiting for dependencies [(&(component=http)(objectClass=org.apache.camel.spi.ComponentResolver))]
2014-04-02 17:58:03,668 | WARN | Thread-42 | BndUtils | ? ? | 2 - org.ops4j.pax.url.wrap - 1.3.7 | Bundle cannot be generated
java.io.IOException: Pipe closed
at java.io.PipedInputStream.checkStateForReceive(PipedInputStream.java:261)[:1.7.0_45]
at java.io.PipedInputStream.receive(PipedInputStream.java:202)[:1.7.0_45]
------>
karaf@root> features:install camel-http
