# CPSC4970-Assignment-1b

This assignment has you setting up and using Apache Maven to build a basic Java Hello World application.  The Java application was created by using the Maven archetype [maven-archetype-quickstart](https://maven.apache.org/archetypes/maven-archetype-quickstart/), which provides a ready to build Java application with a single Java class and associate test.  This is the simplest type of Maven configuration.
```
mvn archetype:generate -DgroupId=edu.auburn.cpsc4970 -DartifactId=assign1b -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```

Your are welcome to start using an IDE as you edit source code files.  But it is encouraged to still use git and maven at the command line to re-enforce the concepts.  IDE's essentially run these commands for you.

Do the follow:
# Basic Maven Commands

1. Clone the following repository for the assignment. Suggest copying from Gitlab under the "Clone" button
    ```
    git clone https://gitlab.com/cpsc4970-sum-a/<your project>/CPSC4970-Assignment-1b.git
    ```
2. Examine the .gitignore file
   - It contains files that should be ignored by git and should be edited for any files any IDE configuration files or directories.  The existing **.idea** directory is for Intellij IDE.
   - The **target** directory is where Maven outputs all of it's working files and build artifacts. 


3. Make sure you are running Java JDK 11
    ```
    java -version
    ```
   ```
   java version "11.0.8" 2020-07-14 LTS
   Java(TM) SE Runtime Environment 18.9 (build 11.0.8+10-LTS)
   Java HotSpot(TM) 64-Bit Server VM 18.9 (build 11.0.8+10-LTS, mixed mode)
   ```
4. Check Maven version
   ```
   mvn -version
   ```   
   ```
   Apache Maven 3.6.0 (97c98ec64a1fdfee7767ce5ffb20918da4f719f3 2018-10-24T20:41:47+02:00)
   Maven home: /usr/local/Cellar/maven/3.6.0/libexec
   Java version: 11.0.8, vendor: Oracle Corporation, runtime: /Library/Java/JavaVirtualMachines/jdk-11.0.8.jdk/Contents/Home
   ```
5. Run Maven compile:
    ```
    mvn compile
    ```
   **target** directory should have been created on successful build.  Examine it's contents.  The compiled classes can be found under the **classes** directory


7. Run Maven test:
    ```
    mvn test
    ```
   Examine **target** directory and examine it's contents.  You will notice additional directories related to running automated tests.

8. Run the main class:
   ```
   mvn exec:java
   ```

9. Run Maven clean:
    ```
    mvn clean
    ```
   The **target** directory is now removed and all working files are deleted.


7. Package your Java application into a jar file.
   ```
   mvn package
   ```
   The **target** directory will not contain the following file:
   ```
   assign1b-1.0-SNAPSHOT.jar
   ```
   Run the following to examine the contents:
   ```
   tar -tf target/assign1b-1.0-SNAPSHOT.jar
   ```
8. Install the package in your local repository
   ```
   mvn install
   ```
   Examine the **~/.m2/repository/** directory and look for the directoy and files related to the **assign1-1b.0-SNAPSHOT.jar** package.

# Modifying the Maven Build

9. Create and checkout a branch called "feature-add"


10. Change the artifact id (assign1-1b) in the pom.xml. Run mvn package and notice artifact name change in target directory.


11. Add a method to the App class that adds two integers


12. Add an addition junit test to the test class that tests the method.


13. Build and validate your code works.


14. Push the branch to the remote repository.


15. Create and process merge request to **main** on Gitlab


Grade will be based on review of commit history and branch on Gitlab
