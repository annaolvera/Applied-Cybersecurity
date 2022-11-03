# Assignment 4a

In this assignment you are configuring dependencies for the AuthProvider package, incorporating vulnerabilitiy scanning, and resolving vulnerabilities found as part of the scanning.  

### Assignment objectives:
- Modify maven project object model (pom.xml) dependencies
- Configure build pipeline to perform dependency scanning and vulnerabiltiy checking
- Resolve dependency issues and vulnerability issues.


Grading will be based on commit history, pipeline execution, and dependency report  reflecting completing the steps below.

# Adding missing dependencies

1. Clone the repository to your local development environment.


2. Review the pom.xml file for the <dependencies> and <plugin> xml sections

3. Run maven compile and make sure it successfully compiles.


4. The classic "**java.lang.NoClassDefFoundError**" error.  If you examine this error you will notice it can not find our database driver class.  The JVM has searched the class path (-cp command line option) at run time and did not find the class org.postgres.Driver. We need to add this dependency in our pom.xml.


4. Create a branch “**bug-missing-dependency**” and checkout branch


5. Add the org.postgressql.Driver dependency in the pom.xml by searching on [Maven Central](https://mvnrepository.com/) for version 4.2.31.


6. Run the maven test again to see if the test passes.


7. Add, Commit, Push, and Merge the changes into the **main** branch3. Create a branch “**feature-log4j**” and checkout branch.

# Change Logging Framework


8. We will be switching to [Log4J]() open source library for logging.  Make the following dependency changes to your project object model (pom.xml): 

    - Add the following log4j dependencies (**version 2.14.1**) by looking them up on Maven central.

     - [**log4j-api**](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api)
     - [**log4j-core**](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core)
     - [**log4j-slf4j-impl**](https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-slf4j-impl)
 

Note: Since we use [slf4j](https://logging.apache.org/log4j/2.x/) switching out logging frameworks is seamless without any configuration.  At runtime slf4j framework will search the class path for a logging framework to use.,

9. Remove the slf4j-simple dependency since it is not needed.


10. Run mvn test to see the new logging statements. You can change the format of the statements in the **log4j.properties** file.


11. A **log4j.properties** files needs to be added for configuring log4j.  Add the following file in the **resources** directory of the project:
    ```properties
    # Extra logging related to initialization of Log4j
    # Set to debug or trace if log4j initialization is failing
    status = warn
    # Name of the configuration
    name = ConsoleLogConfigDemo
    
    # Console appender configuration
    appender.console.type = Console
    appender.console.name = consoleLogger
    appender.console.layout.type = PatternLayout
    appender.console.layout.pattern = %d{yyyy-MM-dd HH:mm:ss} %-5p %c{1}:%L - %m%n
    
    # Root logger level
    rootLogger.level = debug
    # Root logger referring to console appender
    rootLogger.appenderRef.stdout.ref = consoleLogger
    ``` 
12. You can remove the **simplelogger.properties** file in the **resources** directory using the the following git remove command:
```
git rm <filename>
```  

13. Run maven **test** to make sure your configuration changes are correct.  You should see maven downloading the log4j dependency files the first run after adding the dependencies


14. Run the following command to see a report of your dependencies.  This is accomplished with the maven dependency plugin in the **<plugins>** section
```yaml
mvn dependency:tree
```

Make sure the slf4j-simple is not being reported.

15. Add, Commit, Push, and Merge the pom.xml and log4j properties files to remote repo and merge into **main**.


# Add Dependency Vulnerability Scanning

16. Create a branch **build_pipeline** and checkout


17. Create a gitlab-ci.yml file to compile and test 

18. Add stage **depend-scan** to the **stages** section after **test** in your **gitlab-ci.yml** file

19. Add the following line to pipeline file to add vulnerability scanning
    ```yaml
    include: '/templates/Dependency-Scanning.gitlab-ci.yml'
    ```
    

20. Add, Commit, Push, and Merge changes into **main**


21. Make sure pipeline executes successfully


22. Your Gitlab project should have **Security and Compliance** section on the lefthand navigation.  Select the **Dependency List** from the menu to view the Dependency report


23. Review the vulnerabilities reported.  


24. Create a branch **depend-updates** and checkout


25. For each dependency with vulnerabilities update your pom.xml with a more current version.  You can query [Maven Central](https://mvnrepository.com/) for a list of versions.


26. Add, Commit, Push, and Merge changes into **main**


27. Make sure pipeline executes successfully and dependency  report shows no vulnerabilities present.


# Publish AuthProvider to Gitlab package registry

28. Create a branch **pipeline-publish** and checkout


29. Add the following to the **pom.xml** within the **<project>** tag to add pointers to the Gitlab repository where the AuthProvider package will be published.
```yaml

    <repositories>
        <repository>
            <id>gitlab-maven</id>
            <url>https://gitlab.com/api/v4/projects/${CI_PROJECT_ID}/packages/maven</url>
        </repository>
    </repositories>

    <distributionManagement>
        <repository>
            <id>gitlab-maven</id>
            <url>https://gitlab.com/api/v4/projects/${CI_PROJECT_ID}/packages/maven</url>
        </repository>
        <snapshotRepository>
            <id>gitlab-maven</id>
            <url>https://gitlab.com/api/v4/projects/${CI_PROJECT_ID}/packages/maven</url>
        </snapshotRepository>
    </distributionManagement>
```
30. Add the stage "publish" to gitlab-ci.yml


31. Add an instance of "publish" with the following config:
    ```yaml
    deploy_to_gitlab_package_registry:
      image: maven:3.6-jdk-11
      stage: publish
      script:
        - 'mvn deploy -s ci_settings.xml'
      only:
        - main
    ```
    

32. Add, Commit, Push, and Merge changes into **main**


33. Your Gitlab project should have **Packages & Registries** section on the lefthand navigation.  Select the **Package Registry** from the menu to check your AuthProvider artifact is present


34. Click on the artifact to view information about it.


35. Create a branch **update-version** and checkout


36. Change the **<version>** in your **pom.xml** to 1.1.  

37. Add, Commit, Push, and Merge changes into **main**


38. Select the **Package Registry** from the menu to check your new AuthProvider artifact is present
