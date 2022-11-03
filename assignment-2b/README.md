# Assignment-2b

In this assignment you are creating a project that builds a Java Authentication package. 

Assignment objectives:
- Build Java package locally once you have cloned the repository.
- Configure a Gitlab pipeline to automatically build the application.
- Inspect the source code and correct the following security issues:
   - Weak Password policy
   - Information leakage
   - Lack of audit and logging
   - Exception handling
   
Grading will be based on commit history and pipeline execution history reflecting completing the steps below.

# About the code base

This Java code base builds a package (.jar) that defines a method for authenticating users.  It could be used within a web application.  However it contains several security flaws that need to be mitigated.  Using the common security areas described from the lectures you are asked to fix these issues using the source code inspection and control practies you have been learning.

The code base uses the Java [Factory Pattern](https://www.tutorialspoint.com/design_pattern/factory_pattern.htm) to provide a concrete class (**UsernamePasswordAuthenticator**) that implements a simple login **AuthLoginInterface**.  The database of users is stored in a simple  **users.json** file.  Logging is provided by SLF4J logging framework with configuration in the **simplelogger.properties** file.


# Create Gitlab build pipeline for Assignment 1b and correct fail tests

1. Create a **.gitlab-ci.yml** to build the java application

2. Setup **test** to the [stages](https://docs.gitlab.com/ee/ci/yaml/#stages) section
    ```yaml
    stages:         
      - test
    ```

4. Add **login-test** stage specifically to build a Maven structure.  Things to note:
   - Specifying a new Docker [image](https://docs.gitlab.com/ee/ci/yaml/#image) within the stage definition for Maven  
   - In the [script](https://docs.gitlab.com/ee/ci/yaml/#script) we are running the same maven command you run locally.
   - New [only](https://docs.gitlab.com/ee/ci/yaml/#only--except) section specifies the job is triggered only on main branch
    ```yaml
      login-test:
      image: maven:3.8-openjdk-11
      stage: test
      only:
        - main
      script:
        - mvn test
    ```

5. Git Add/Commit to your local repo.


5. Push the changes to the remote repo.


6. Verify the Gitlab pipeline was triggered and caused errors.
  ```
  Tests run: 6, Failures: 2, Errors: 0, Skipped: 0
  [INFO] ------------------------------------------------------------------------
  [INFO] BUILD FAILURE
  [INFO] ------------------------------------------------------------------------
  [INFO] Total time:  4.526 s
  [INFO] Finished at: 2022-06-01T01:16:39Z
  [INFO] ------------------------------------------------------------------------
   ``` 

8. Correct the errors failing in the pipeline on a local **bug-check-null** branch.  To correct the errors you will need to throw the appropriate **AULoginException** with the correct message.  You should examine the test class to see the validation.

9. Push the **bug-check-null** branch to the remote repo.  The pipeline should not be triggered.


10. Create a merge request and process it checking the commit diff and add a comment before merging to main.


11. Check that the pipeline execute and passed.

# Add build stage to your pipeline

12. Checkout to **main** branch


13. Add a "**build**" stage to the [stages](https://docs.gitlab.com/ee/ci/yaml/#stages) section of your pipeline config after the test stage


13. Add a stage definition
    ```yaml
    login-build:
      image: maven:3.8-openjdk-11
      stage: build
      only:
        - main
      script:
        - mvn package
    ``` 

14. At the end of the file add the ability to cache maven dependency's between job/pipeline execution.  This enables Maven to only download dependencies on the first pipeline execution by enabling a local [cache](https://docs.gitlab.com/ee/ci/yaml/#cache) to be kept in your project. "[variables](https://docs.gitlab.com/ee/ci/yaml/#variables)" are values based to all jobs.  The MAVEN_OPTS tells maven where the local dependencies are located.
    ```yaml
    variables:
      MAVEN_OPTS: "-Dmaven.repo.local=$CI_PROJECT_DIR/.m2/repository"
    
    # Cache downloaded dependencies and plugins between builds.
    # To keep cache across branches add 'key: "$CI_JOB_NAME"'
    cache:
      paths:
        - .m2/repository
    ```
    
15 Add/Commit/Push the **.gitlab-ci.yml** file and verify pipeline executed both stages successfully.

  ```yaml
      [INFO] Building jar: /builds/cpsc4970-sum-a/aubie/assignment-2b/target/AuthProvider-1.0-SNAPSHOT-jar-with-dependencies.jar
      [INFO] ------------------------------------------------------------------------
      [INFO] BUILD SUCCESS
      [INFO] ------------------------------------------------------------------------
      [INFO] Total time:  7.895 s
      [INFO] Finished at: 2022-06-01T02:08:21Z
      [INFO] ------------------------------------------------------------------------
  ```

# Source code inspection and correction for security issues.

16. Create 4 branches:
- **bug-info-leak**
- **bug-exception-leak**
- **feature-password-complexity**  - any additional complextiy of your choosing
- **feature-log-login-success**


17. On each specific branch fix the security vulnerability it indicates.


18. Run maven locally to make sure all tests still pass.


19. You can all run the java application local to see some of the security issues and check your changes by running the **LoginApp" as it has a **"public static void main( String[] args )"**.  You can change any code in this class.  With maven you can use the follow command:
    ```yaml
    mvn exec:java
    ```
    This Maven target knows which class by this **pom.xml** entry:
    ```xml
    <exec.mainClass>edu.auburn.cpsc4970.auth.LoginApp</exec.mainClass>
    ```
    
20. Push each branch to the remote repository.  Create a merge request and process it **with a comment** on a description of what the code does.


21. Check to make sure the pipeline completes successfully

