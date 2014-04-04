1. 请参阅
https://camel.apache.org/tutorial-example-reportincident.html

2. cxf webservice
http://localhost:9080/webservices/incident?wsdl

请求：
http://localhost:9080/webservices/incident
POST
REQUEST:
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:rep="http://reportincident.example.camel.apache.org">
   <soapenv:Header/>
   <soapenv:Body>
      <rep:inputReportIncident>
         <incidentId>incidentId</incidentId>
         <incidentDate>incidentDate</incidentDate>
         <givenName>givenName</givenName>
         <familyName>familyName</familyName>
         <summary>summary</summary>
         <details>details</details>
         <email>email</email>
         <phone>phone</phone>
      </rep:inputReportIncident>
   </soapenv:Body>
</soapenv:Envelope>

RESPONSE:
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
   <soap:Body>
      <ns2:outputReportIncident xmlns:ns2="http://reportincident.example.camel.apache.org">
         <code>OK</code>
      </ns2:outputReportIncident>
   </soap:Body>
</soap:Envelope>

3. 调试
    a. 一般的测试用例
        $ mvn clean install -Dmaven.surefire.debug
    b. jetty 等容器调试
        $ MAVEN_OPTS="-Xmx512m -XX:MaxPermSize=128m -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005"
        $ export MAVEN_OPTS
        $ mvn jetty:run
