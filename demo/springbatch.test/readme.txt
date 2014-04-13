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

4. 数据库表设计文档
http://docs.spring.io/spring-batch/reference/html/metaDataSchema.html

5. 多次运行程序出现错误
com.mysql.jdbc.exceptions.jdbc4.MySQLSyntaxErrorException: Table 'batch_job_instance' already exists
解决方案参考于：
  http://forum.spring.io/forum/spring-projects/batch/122278-table-batch-job-instance-already-exists-error-when-trying-to-execute-spring-batch

在 jdbc:initialize-database 节点下配置 enabled="false"
<jdbc:initialize-database data-source="dataSource" enabled="false">
    <jdbc:script location="${batch.schema.script}" />
</jdbc:initialize-database>

具体到生产机还需要处理。

6. 使用spring batch 进行多数据源读取
参见：
http://stackoverflow.com/questions/16816116/using-2-different-datasources-spring-batch

