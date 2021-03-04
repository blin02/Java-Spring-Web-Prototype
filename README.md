Java-Spring-Web-Prototype
=========================

A Prototype of Java Web Application Uses Spring and Spring MVC Framework

- Eclipse IDE Setup
	-> Install the following plug-ins:
	Maven Integration for Eclipse 
	Update site: http://m2eclipse.sonatype.org/sites/m2e

	Maven Integration for WTP (this is required for the web module)
	Update Site: http://m2eclipse.sonatype.org/sites/m2e-extras

	-> Import all projects as Mave projects.
	
	-> Configure to run the web app within Eclipse
	To add a Tomcat server, open the "Servers" view, select "New" to add a new server. 
    From the "Select the server type" list box, select "Apache->Tomcat v6.0 Server".
    click "Next". Enter the Tomcat installation directory in the "Tomcat installation directory" textbox. Click "Finish"
    
    To add the web project to the server, right click on the Tomcat server and select "Add and Remove". 
    Add the "web" project from the "Available" list to the "Configured" list.	

- MVC framework: Spring 3 MVC
	-> Domain model objects are in: core\src\main\java\com.mycompany.model
	If needed, create web model objects if domain model objects doesn't meet the UI requirement and use auto-class-mapper
	to map between web model and domain model
	-> Controllers are in: web\src\main\java\com.mycompany.web.controller
	-> Views are in: web\src\main\webapp\WEB-INF\views
	
- Configuration:
	-> Most configuration files are located at project's src\main\resources directory.
	-> Environment specific configuration variables are in the "environment.properties" file in the "config\[environment_name]" direcotry in each project, for example core\config\dev. 
	   Maven will read the properties from the corresponding "environment.properties" file based on the "environment" property, 
	   and set the values for the corresponding variables in the resource files. 
	   To build for different environments, set environment value, for example, "mvn package -Denvironment=qa". 
	   By default, the "environment" property is set to "dev".

- Database: 
	-> Use hsqldb file database 
	-> Data source is created in Spring context xml: core\src\main\resources\spring-core-context.xml
	-> Settings for MySQL are commented out in core\pom.xml

- ORM: Hibernate 3.6.1
	-> Session factory bean are created in Spring context xml: core\src\main\resources\spring-core-context.xml
	-> Other Hibernate properties are in Config file: core\src\main\resources\hibernate.cfg.xml
	-> Hibernate properties can be moved to app-context.xml.  
	
- Transaction Management:
	-> Use of Spring Trsanction Manager.
	-> Bean is defined in core's Spring context xml: core\src\main\resources\spring-core-context.xml
	
- Import/Export Data
	-> Use Dbunit plugin to import test/predefined data from dataset
	-> Sample data is in core\src\test\resources\sample-data.xml

- Security/User Authentication, Authorization
	-> Security is handled by Spring security framework, config file is: web\src\main\resources\spring-security.xml
	-> In order to utilize Spring's security mechanism and use custom authenticate service:
		- "User" needs to implement "UserDetails" interface
		- "Role" needs to implement "GrantedAuthority" interface
		- "UserServiceImpl" needs to implement "UserDetailsService" interface
		- Set "user-service-ref" attribute to point to "userDao"
	-> To use Spring's encryption to encrypt the password:
		- Define beans for PasswordEncoder and SaltSource in core's app context file: core\src\main\resources\spring-core-context.xml
		- Define PasswordEncoder and SaltSource in "UserServiceImpl" and "Autowired" them
		- Set "password-encoder" and "salt-source" in web project's security config file spring-security.xml.		
	-> If "use-expression" is false, to change the default role name prefix ("ROLE_"), need to create a "RoleVoter" bean in security config file.

- Web Service (SOAP and REST): cxf 2.4.0
	-> Annotation is at the service interface level
	-> Config file: core\src\main\resources\web-service-cxf.xml
	-> To see the list of available web services, http://localhost:8080/web/services/
	-> For RESTful service example, try 
	http://localhost:8080/web/services/rest/weather/getCityForecastByZip/11223?_type=xml
	or
	http://localhost:8080/web/services/rest/weather/getCityForecastByZip/11223?_type=json

- Generate Client Stub from web service wsdl
	-> Use cxf codegen maven plugin to generate the client stub source code for a web service that you want to consume. 
	-> The configuration is in the "core\pom.xml" file. Look for the "codegen" profile and "cxf-codegen-plugin" plugin. 
	There can be more than one wsdl files.
	-> Usage: in command console window, cd to the "core" project folder, run: mvn generate-sources -Pcodegen
	By default, the source files would be generated to the default target folder, which is "core/target/generated-source/cxf".
	The source files can also be generated to any specific folder if the "sourceRoot" element is specified in the pom file.
	-> Note, when generate the source files for the first time or when the web services get updated, 
	need to manually copy the generated source files and paste to the "core/src/main/java" folder.

- UI Layout and template: sitemesh
	-> Specify the sitemesh filter in web.xml
	-> config file: web\src\main\resources\sitemesh.xml
	-> template files: web\src\main\webapp\WEB-INF\templates\

- Logging: SLF4J (use log4j binding)
	-> Log4 Config file: web\src\main\resources\log4.xml

- Exception:
	-> use Spring Aspect to log the exception at the service level (in com.mycompany.aspect package).

- Auto Class Mapper: Dozer

- Form and form validation: 
	-> In the views, use Spring form tag library (http://www.springframework.org/tags/form)
	-> Form validation can be done using Spring Annotation-based Bean Validation Framework (or manual validation).
	-> the annotation-based validator bean is created in Spring context xml: web\src\main\resources\spring-context.xml
	-> With the annotation-based validator, the form validation error messages can be in the model class or are in the web-app-resources.properties file. 
	The error message in the properties file should follow this format: bean-class.bean-propery[validation-rule]=Validation Error message
	
- Email:
	-> For Spring Email support, use EmailService. "mailSender" is defined in core\src\resources\spring-core-context.xml 	
	-> For JavaMail API, use JavaMailUtil in com.mycompany.util package

- Resource Bundle
	-> Create a Spring bean "messageSource" in spring-context.xml file.
	-> Use Spring message tag library in view (http://java.sun.com/jsp/jstl/fmt), look for genericError.jsp as example.
	
- Tag Library:
	-> JSTL 1.1.2
	-> Display tag
	-> Spring form tag library
	-> Spring security tag library
	-> Spring message tag library 

- Unit/Integration Testing:
	-> Use Maven plugins (Surefire and Failsafe) to run unit testing 
	-> Unit testing provider is JUnit
	-> Use EasyMock in unit testing for Service layer.

- Comments/Documents

- Maven Plug-ins
	- - Maven compiler plugin
	- - Maven resource plugin
	- - Hibernate Maven plugin (use to create database schema)
	- - Dbunit plugin (use to import test/predefined data from dataset)
	- - Document: javadoc
	- - Unit test: Surefire for unit test, Failsafe for integration test (JUnit is the provider) 
	- - CXF
	- - Properties plugin (properties-maven-plugin), read properties from external files.

- Maven Version 3.0 
