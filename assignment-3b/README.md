# Assignment 3b

In this assignment you are using SonarQube to identify and resolve security bugs and hotspots.  This assignment introduces database access and SQL, which are common areas for vulnerabilities to occur.

### Assignment objectives:
- Build Java package locally once you have cloned the repository.
- Configure a Gitlab pipeline to automatically build the application.
- Inspect the source code and correct the following security issues:
   - DB credentials located in source code
   - SQL Injection
   - Remove debug code
   - Weak Encryption Algorithm

### Configuration
- Postgres Database is setup on AWS for executing SQL queries against.


Grading will be based on commit history and pipeline execution history reflecting completing the steps below.

# Configure The Gitlab Pipeline

1. Examine the repository on Gitlab.  


2. Clone the repository to your local development environment.


3. Run maven to build and test the application.  It should complete successfully.


4. Create a branch called "pipeline-1" and checkout branch.


5. Define a **sast** stage after test


7. Edit your **pom.xml** and replace **AUemail** in the <name> tag with your AU email initials such as "pwb0016" would be "pwb".  This will become the name of your SonarQube project
    ```
    <name>3b-AuthProvider-AUemail</name>
    ```

8. Create a **sast** stage to execute our SonarQube analysis


9. Add a **sast** instance stage.  Make sure you adhere to [YAML syntax](https://docs.ansible.com/ansible/latest/reference_appendices/YAMLSyntax.html)
    ```yaml
    sonarqube-check:
      image: maven:3.6.3-jdk-11
      variables:
        SONAR_USER_HOME: "${CI_PROJECT_DIR}/.sonar"
        GIT_DEPTH: '0'
      cache:
        key: "${CI_JOB_NAME}"
        paths:
        - ".sonar/cache"
      script:
      - mvn verify sonar:sonar -Dsonar.projectKey=cpsc4970-sum-a-3b-<AUemail>  -Dsonar.qualitygate.wait=true
      allow_failure: true
    ```
    - For then sonar.projectKey replace **\<AUemail\>** in the mvn command with your AU email initials, "pwb0016" would be "cpsc4970-sum-a-3b-pwb".  This is the pointer to your SonarQube project and must match.


10. Add, Commit, Push, and process a merge request to **main**.


11. Check to validate the pipeline executed.  


12. Login to SonarQube and examine the project created. You will see many more issues since the code base is larger and has not been reviewed.


13. Examine the 3b Quality Gate to understand failing metrics.

    - Review the "Overall Code" tab for metrics.


14. Select the "Security Hotspots" tab.  You should have received 7 Security Hotspots:

- 2 High
- 5 Low
     

# Database credentials in source code

Connection credentials in source code is a critical vulnerability since it can easily be seen by anyone with access to the repository (think public Github repos).  Credentials should be moved external to the source code.  Move the credentials into a Java properties file.  Although this properties file is still in source code control it should ultimately be moved externally and added at runtime (not for this assignment).  A better approach is setting them as environment variables or as we will see in a later lecture looked up realtime from an external secrets management tool.

15. Create "bug-db-credentials" branch and checkout branch.

16. Use the Utils.getProperties() method to lookup the properties at runtime from the system.properties files in the **resource** directory.  Move the values to the lookup key.  See [Java Properties](https://docs.oracle.com/javase/tutorial/essential/environment/properties.html) for more details.


18  Add, Commit, Push, Merge.  Ensure pipeline runs and passes tests.

# SQL Injection

SQL statements should use parameterized statements vs. direct strings.  This protects against SQL injection attacks by preventing escape characters such as single quotes from being inserted.  

19. Create "bug-db-sql" branch and checkout branch.

20. Using parameterized prepared statements is a common method to prevent vulnerabilities.  Replace the SQL statement with the following:

    ```
    // Setup query to execute
    preparedStatement = connection.prepareStatement("select name, password from users where username= ?");
    preparedStatement.setString(1,searchUser);
    ```

21.  Add, Commit, Push, Merge.  Ensure pipeline runs and passes tests.

# Remove debug code

Not handling exceptions properly can leak internal code information that could be seen users or unauthorized internal personal.  Correct an code flagged as "debug feature is deactivated".

22. Create "bug-debug-code" branch and checkout branch.


23. Remove lines that are printing stack traces

24. Add, Commit, Push, Merge.  Ensure pipeline runs and passes tests.


# Fix Weak Encryption Algorithms

Encryption algorithms are continuously being updated to be more secure and increase complexity necessary to protect against brute force attacks. Hashaing algorithms, used to encrypt password and verify files should use current algorithms.  Fix the weak hash algorithm with a more current algorithm by inspecting the recommended fixes.

25. Create "bug-weak-hash" branch and checkout branch.


26. Update "SHA1" with a more current algorithm


27.  Add, Commit, Push, Merge.  Ensure pipeline runs and passes tests.
