1. 此工程使用 mvn archetype:generate 生成
mvn archetype:generate \
    -DarchetypeGroupId=com.dtzq \
    -DarchetypeArtifactId=maven-springbatch-archetype \
    -DarchetypeVersion=1.4-SNAPSHOT \
    -DgroupId=com.kangfoo.study.hygeia \
    -DartifactId=springbatch.test \
    -Dversion=1.0-SNAPSHOT \
    -Dpackage=com.kangfoo.study.hygeia.springbatch.test


2. 此示例代码主要参见于
http://www.ibm.com/developerworks/cn/java/j-lo-springbatch1/

3. 参见步骤

3.1. 对象定义
User 类
Message 类

3.2. 读写及处理接口
所有 Spring Batch 的读操作均需要实现 ItemReader 接口
而且 Spring Batch 为我们提供了多种默认实现，尤其是基于 ORM 框架的读接口，
同时支持基于游标和分页两类操作。因此，多数情况下我们并不需要手动编写 ItemReader 类，而是直接使用相应实现类即可。
此处使用 org.springframework.batch.item.file.FlatFileItemReader 类从文件中进行信息读入
