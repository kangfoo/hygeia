开发过程纪要：

1. 使用 maven commnad 建立 Create an OSGi blueprint bundle
karaf-blueprint-archetype 类型的项目
命令如下：
mvn archetype:generate \
    -DarchetypeGroupId=org.apache.karaf.archetypes \
    -DarchetypeArtifactId=karaf-blueprint-archetype \
    -DarchetypeVersion=2.3.4 \
    -DgroupId=com.kangfoo.study.hygeia \
    -DartifactId=pm25in.import2local \
    -Dversion=1.0-SNAPSHOT \
    -Dpackage=com.kangfoo.study.pm25.import2local

camel-archetype-blueprint 类型的项目(这个更适合我)。可参阅于：
http://camel.apache.org/camel-maven-archetypes.html
命令如下：
mvn archetype:generate \
    -DarchetypeGroupId=org.apache.camel.archetypes \
    -DarchetypeArtifactId=camel-archetype-blueprint \
    -DarchetypeVersion=2.12.3 \
    -DgroupId=com.kangfoo.study.hygeia \
    -DartifactId=pm25in.import2local.getpm25data.test \
    -Dversion=1.0-SNAPSHOT \
    -Dpackage=com.kangfoo.study.hygeia.pm25.import2local.getpm25data.test


2. 调整日志输出
$ cd ${SERVICEMIX_HOME}/etc/org.ops4j.pax.logging.cfg
添加 sift
# Root logger
log4j.rootLogger=INFO, out, osgi:VmLogAppender, sift
并注释掉
# log4j.appender.sift.default=servicemix


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


6.错误
pm25in.import2local is waiting for dependencies [(&(component=http)(objectClass=org.apache.camel.spi.ComponentResolver))]
2014-04-02 17:58:03,668 | WARN | Thread-42 | BndUtils | ? ? | 2 - org.ops4j.pax.url.wrap - 1.3.7 | Bundle cannot be generated
java.io.IOException: Pipe closed
at java.io.PipedInputStream.checkStateForReceive(PipedInputStream.java:261)[:1.7.0_45]
at java.io.PipedInputStream.receive(PipedInputStream.java:202)[:1.7.0_45]

------>
karaf@root> features:install camel-http


7. 开发过程中关于 blueprint 示例可参考
http://www.ibm.com/developerworks/cn/opensource/os-osgiblueprint/

8. 无意中谷歌到的一个关于OSGi, SOA, Camel, Blueprint 全面的中文博客
http://thinkinside.tk/pages/tags.html#OSGi-ref

9. HTML字符实体（Character Entities），转义字符串（Escape Sequence）
http://114.xixik.com/character/

10. properties
参见 http://camel.apache.org/properties.html
camel 读取配置使用语法 {{key}}
spring/blueprint 读取配置使用语法 ${key}
特殊提醒：${} 有动态表达式的意义。

11. pm2.5 数据主要来自于
http://www.pm25.in/api_doc

12. 项目管理工具
trello  维基百科
Trello 是由Fog Creek Software开发的一款免费的基于网络应用程序 项目管理应用。
Trello使用1980年代流行的丰田供应链管理方式看板管理作为管理项目的样式。

Teambition
https://www.teambition.com/projects
http://today.ai/past
teambition是一款高效、易用、优雅的协作化项目管理工具，它能帮你随时把握项目动态，高效地管理项目任务，以及方便地分享和存档项目文件。
在teambition中你可以创建多个项目，并邀请你的同事或是朋友来协同工作。

支持国内90后创业小伙的 teambition
我的 hygeia 就建在
https://www.teambition.com/project/534a4899a5d1044d378afd8a/home
下。