# CPSC4970-Assignment-2a

This assignment you are creating a simple Gitlab build pipeline. The objective is to give you an understanding of the configuration and execution of a multi-stage pipeline.  Gitlab follows the **convention** method of configuring pipelines.  If it sees a **gitlab-ci.yml** in the root directoy of a respository then it understands how to trigger and execute pipelines,

Grading will be based on commit history and pipeline execution history reflecting completing the steps below.

# Create A Basic Gitlab Pipeline

1. Examine the repository on Gitlab.  You should only see a **README.md** file present.


2. Clone the repository to your local development environment.  This command can be copied from the **Clone** button on your project repository page.
   ```
   git clone https://gitlab.com/cpsc4970-sum-a/<your project id>/cpsc4970-assignment-2b.git
   ```
3. Create a **.gitlab-ci.yml** file. 
4. Add a single pipeline stage
   ```yaml
   stages:
      - build
   ```
5. Specify the Docker [image](https://docs.gitlab.com/ee/ci/yaml/#image) to use.  We are using the basic [Alpine](https://hub.docker.com/_/alpine) linux Docker image.
   ```yaml
   image: alpine
   ```
6. For this pipeline we are only printing messages using the [script](https://docs.gitlab.com/ee/ci/yaml/#script).
   ```yaml
   <name of your stage>:
      stage: build
      script:
         - echo: "Running <name of your stage>"
         - exit 0  # Return Success
   ```
7. Completed file contents should look similar to this.  Make sure you adhere to [YAML syntax](https://docs.ansible.com/ansible/latest/reference_appendices/YAMLSyntax.html)
    ```yaml
    stages:
      - build
    
    image: alpine
    
    myBuildStage:
      stage: build
      script:
        - echo "Running myBuildStage"
        - exit 0  # Return Success
    ```
8. Using the git commands learned in the previous module. Add, Commit, and Push the **.gitlab-ci.yml** to the remote repo.
   ```
   git add .gitlab-ci.yml
   git commit -m "<commit message>"
   git push origin main
   ```
9. Check  pipeline successfully executed in your Gitlab project.  Drill down in to the pipeline stage to see your message printed out.  Pipelines are automatically executed on commits to the **main** branch.
    ```
    https://gitlab.com/cpsc4970-sum-a/<your project name>/cpsc4970-assignment-2b
    ```
10. Have the script return an error code to indicate it failed
    ```yaml
    <name of your stage>:
      stage: build
      script:
        - echo "Running <name of your stage>"
        - exit -1  # Return Error - non zero value
    ```

11. Check that your pipeline failed in your Gitlab project. Drill down into pipeline stage to see the error code you specified printed out.

#Creating Multi Stage Pipelines

11. Add a **test** stage to your pipeline to run after your **build** stage using the script to echo a message that the stage is running.

12. Commit the changes and push them to the remote repo **main** branch


13. Check the pipeline graph and it has successfully executed with your message being echo'd in the stage execution.


14. Add a second **test** stage in your **.gitlab-ci.yml**.  This is done by adding another section to the file:
   ```yaml
    <new stage name>:
      stage: test
      script:
        - echo "Running <new stage name>"
        - exit 0  # Return Success 
```

15. Commit the changes and push them to the remote repo **main** branch


16. Check the pipeline graph and that it has successfully executed with your message being echo'd in the stage execution.  Also check that now two stages are shown in parallel in the **pipelines** section.

17. Add a **deploy** stage in your **.gitlab-ci.yml**.  Add the stage entry and associate stage section similar to **test** above. Remember stage names are up to you.
```yaml
stages:
   - build
   - test
   - deploy
```

19. Commit the changes and push them to the remote repo **main** branch


19. Check the pipeline graph and that it has successfully executed with your message being echo'd in the stage execution.

#Adding Custom Stages

20. Add a "**\<your project name\>Stage**" stage in your **.gitlab-ci.yml**.  
```yaml
stages:
   - build
   - test
   - <your project name>Stage
   - deploy
```

21. Add a stage instance associate with your new stage
   ```yaml
   <stage name>:
      stage: <your project name>Stage
      script:
         echo: "Running <name of your stage>"
         exit 0  # Success 
   ```

22. Commit the changes and push them to the remote repo **main** branch


23. Check the pipeline graph and that it has successfully executed with your message being echo'd in the stage execution.


24. Add a second **\<your project name\>Stage** stage in your **.gitlab-ci.yml** with a new stage name.


25. Commit the changes and push them to the remote repo **main** branch


23. Check the pipeline graph and that it has successfully executed with your message being echo'd in the stage execution and the pipeline view shows the new stage executing in parallel.
