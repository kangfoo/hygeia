Camel Router Project for Blueprint (OSGi)
=========================================

To build this project use

    mvn install

To run the project you can execute the following Maven goal

    mvn camel:run

To deploy the project in OSGi. For example using Apache ServiceMix
or Apache Karaf. You can run the following command from its shell:

    osgi:install -s mvn:com.kangfoo.study.hygeia/pm25in.import2local.getpm25data/1.0-SNAPSHOT

For more help see the Apache Camel documentation

    http://camel.apache.org/

本示例程序重点使用 Camel EIP Spring DSL

DSL 名词解释：
Camel使用一种Java领域特定的语言（Domain Specific Language，DSL）来创建企业集成模式（Enterprise Integration Patterns）或路由。
Camel同时支持一种基于Spring的 XML 配置，以及 Scala DSL。
    使用Java DSL的优点是你使用的IDE可以在你开始编写时，智能的实例代码，而不需要在大量的XML上浪费时间。Java DSL非常有表现能力，
    因为你可以在语言中混合搭配你自己的代码，来进行表达式或断言评估，或容易地添加自定义的处理模块。

DSL的主要入口有
    CamelContext 用于创建一个Camel 路由规则库（routing rule base）；
    RouteBuilder 用于创建路由集合。

