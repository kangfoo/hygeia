# Summary
Maven archetype providing a pre-configured and basic Spring Batch setup.

This setup provides the most common starting point I have seen which is reading from a flat file and writing to a database.

# Features
Some features that work out of the box are:

* Basic reader, processor and writer example.
* Uses hsqldb as an in memory data source.
* Ability to run via maven or a deployment package.
* Example test coverage.

# Local Source Install
$ git clone https://github.com/chrisjs/maven-springbatch-archetype.git

$ cd maven-springbatch-archetype

$ mvn install

# Use
mvn archetype:generate -DarchetypeGroupId=com.dtzq -DarchetypeArtifactId=maven-springbatch-archetype -DarchetypeVersion=1.3

At the current time, the latest release version is 1.3

Answer any questions and the build will end up in a directory named after your artifact ID.

cd $artifactId ; mvn package

Besides running the tests that will occur during the package phase, you can:

1. Use the Maven assembled package:
   * After running "mvn package" a file with the naming convention of "theArtifactId-version-distribution.tar.gz" will be located in the target/ directory of the project.
   * Copy and extract this archive to a directory where you want to run the batch job and invoke via the ./bin/runJob.sh script. You may need to make this script executable (chmod +x ./bin/runJob.sh).

2. Execute the job via Maven:
   * Run: mvn exec:java
   * See the exec-maven-plugin section of the pom for more info on changing job parameters.

# Contribute
Contributions are more then welcome. Please fork the repository and create pull requests!
