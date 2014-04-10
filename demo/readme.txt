1. spring batch 示例
    1.1 batch_sample 出自于
http://www.ibm.com/developerworks/cn/java/j-lo-springbatch1/

    1.2 maven-springbatch-archetype
springbatch 2.2.5 稳定版的 maven archetype 插件。其默认生成的示例和 spring batch 官方文档上提供的数据完全一致。
如有疑问可直接参见 http://spring.io/guides/gs/batch-processing/ 官方介绍。
同时 maven-springbatch-archetype 可直接访问 https://github.com/chrisjs/maven-springbatch-archetype 进行参阅。


2. osgi
    2.1 osgi in action 出自于 google code
https://code.google.com/p/osgi-in-action/downloads/detail?name=osgi-in-action-20110401.zip

    2.2 camel-example-reportincident 出自于
https://camel.apache.org/tutorial-example-reportincident.html


3. test 我自己使用的样板程序
    3.1 spring-batch-test
mvn archetype:generate \
    -DarchetypeGroupId=com.dtzq \
    -DarchetypeArtifactId=maven-springbatch-archetype \
    -DarchetypeVersion=1.4-SNAPSHOT \
    -DgroupId=com.kangfoo.study.hygeia \
    -DartifactId=springbatch.test \
    -Dversion=1.0-SNAPSHOT \
    -Dpackage=com.kangfoo.study.hygeia.springbatch.test
