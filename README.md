<b>Project name:</b> # connectedcityservice

<b>Description:</b> This project is a webservice that describes the two given cities (origin, destination) connected by routes or not.

<b>Table of Contents:</b>

   1. **Project Tools or Technologies**
   			   <br>	&nbsp;	&nbsp; Used #Spring boot, #Java, #TestNG, #ExtentReports, #Swagger, #Some Tools.<br>
   2. **Restful Endpoints**
	 				 <br>	&nbsp;	&nbsp; It supports 2 endpoints one is to addCities, and other is to check if the cities get connected.<br>
   3. **Code Coverage Tests**
	 			 	<br>	&nbsp;	&nbsp; Unit tests added for these 2 endpoints, runs as part of the maven build.<br>
	 4. **Reports**
	 				<br>	&nbsp;	&nbsp; Genarated test reports using #ExtentReports, which generates using html. Here is the sample file : <br>	&nbsp;	&nbsp; ConnectedRoutes_Sun_Sep_27_14_41_41_EDT_2020.html, under the root folder of this project repository

<b>Installation:</b>
	<br>	&nbsp;	&nbsp; Prerequisites : Install #Java 8+ and maven 3+ versions, Also download the project from the repo. With eclipse import the project as existing maven project.
	<br> &nbsp; &nbsp; <br><b>Run:</b></br> goto CityServiceApplication class and run as java application, which bootstraps the spring app and starts the embedded tomcat webserver.<br>
	Next, goto pom.xml and right click run as a maven build will do clean install by triggering all tests automatically. Also generates test report as i mentioned above.<br>
	
	Run Through Command Line: goto root of the project and run using this comand ->  mvn spring-boot:run
	Please make sure set up MAVEN_HOME locally
