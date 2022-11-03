# CPSC4970-Assignment-2a - SonarQube Setup and Simple Analysis

In this assignment you are creating a Gitlab build pipeline to connect to and analyze source code in SonarQube. The objective is to understand the basics of setting up a static code scan with SonarQube.  This asignments contains less step by step instructions and sample configuration text as you should be familar with these from previous assignments.  See reference material if you need help.

The following information is for logining into SonarQube server:
- URL: http://sonarqube.au-csse-cpsc4970.com:9000/
- User: AU email without "@aubun.edu"
- Default Password: "Auburn"+last digits in email.   E.g.  pwb0016@auburn.edu -> "Auburn16".  Change after login.

Grading will be based on commit history, pipeline execution, and analysis in SonarQube.

# Create A Gitlab Pipeline

1. Examine the repository on Gitlab.  It contains a simple maven project containing a single class and associated test class.


2. Clone the repository to your local development environment.


3. Run maven to build and test the application.


4. Create a branch called "pipeline-1" and checkout branch.


5. Create a **.gitlab-ci.yml** file with a **build** and **test** stage to run the same appropriate mvn targets/command.  Ensure it runs successfully


6. Define a **sast** stage


7. Edit your **pom.xml** and replace **\<AUemail\>** with your AU email name such as "pwb0016".  This will become the name of your SonarQube project
    ```
    <name>HelloSonarQube-<AUemail></name>
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
      - mvn verify sonar:sonar -Dsonar.projectKey=cpsc4970-sum-a-<AUemail>  -Dsonar.qualitygate.wait=true
      allow_failure: true
    ```
   - Replace **\<AUemail\>** in the mvn command with your AU email name such as "pwb0016".  This is the pointer to your SonarQube project


10. Add, Commit, Push, and process a merge request to **main**.


11. Check to validate the pipeline failed the sast stage.  This is because of the Quality Gate metrics


12. Login to SonarQube and examine the project created.  You should have a failing analysis.

    1. Examine the failing metrics dashboard


13. Examine the 3a Quality Gate to understand failing metrics.

    - Review the "Overall Code" tab for metrics


14. You should have received 4 issues:

    - 5 Code smells
    - 1 Bug


15. Resolve the Code Smells on a branch called "refactor-1"

    1. Reduce the severity of System.out.  Resolve as "Wont Fix", add comment why.
    2. Fix constants & comments issues
    3. Update issues in Sonarqube with resolution and comment
    4. Add, Commit, Push, Merge to. **main** 
    5. Check pipeline run
    6. Resolve issues in Sonarqube.


16. Examine new code scan between New code and overall Code


17. Resolve the null point exception on a branch called "bug-1"

    1. Raise severity level
    2. Add a comment on how you intent to resolve
    3. Add, Commit, Push, Merge to. **main** 


18.  Check SonarQube that the scans are now passing and pipeline is passing or make changes as necesary to resolve.


