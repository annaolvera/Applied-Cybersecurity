# CPSC4970-Assignment-1a

This assignment has you performing basic git commands for adding, committing, branching, and working with remote repositories.  The grading will be based on the commit history and branches that are pushed to your Gitlab assignment 1a repository.

Do the follow:
# Basic Git Commands

1. Install git
   - Configure your username and email
   - Setup alias in .gitconfig for pretty print
    ```
    [alias]
       lg1 = log --graph --abbrev-commit --decorate --format=format:'%C(bold blue)%h%C(reset) - %C(bold green)(%ar)%C(reset) %C(white)%s%C(reset) %C(dim white)- %an%C(reset)%C(bold yellow)%d%C(reset)' --all
       lg2 = log --graph --abbrev-commit --decorate --format=format:'%C(bold blue)%h%C(reset) - %C(bold cyan)%aD%C(reset) %C(bold green)(%ar)%C(reset)%C(bold yellow)%d%C(reset)%n''          %C(white)%s%C(reset) %C(dim white)- %an%C(reset)' --all
       lg = !"git lg1"
    ```

2. Setup Gitlab Account
  - Generate ssh key on local system.  [How to](https://docs.gitlab.com/ee/user/ssh.html)
  - Add ssh key to account
3. Initialize a new git local repository by cloning assignment 1a repository to lcoal git environment. You can copy the url from git lab "Clone" button on the repoistory page. 
    ```
    git clone https://gitlab.com/cpsc4970-sum-a/<your project>/CPSC4970-Assignment-1a.git
    ```

4. Add and commit a .gitignore file.  Push changes to main
    ```
    git push
    ```

5. Create two branches name **feature-1** and **bug-1**

6. Add and commit a text file to each branch containing a quote from a movie.  File name can be the name of the movie.

7. Examine the git log after each commit.  Watch how the tree changes after every commit and how the HEAD changes.
    ```
    git lg
    git lg2
    ```
8. Switch to **main** branch and merge the branches
    ```
    git merge feature-1
    git merge bug-1
    ```
9. Push the changes to the Gitlab repository (origin).
    ```
    git push origin main
    ```
10. Examine the git log to notice how the origin/main has changed

# Working with Remotes

11. Create **feature-2** and **bug-2** branches.  Push **feature-2** and **bug-2**  branches to the origin.  For branches you need first specify the remote tracking branches. Do the following for both branches
    ```
    git push -u origin <branch name>
             or
    git push --set-upstream origin <branch name>
    ```
12. Review on Gitlab that all branches are now present.
13. Add a new movie file/quote or modify your existing movie files for each branch.  Push changes from each branch to remote repository
    ```
    git push origin <branch>
         or
    git push origin   # for current branch
    ```
14. Create a pull request in Gitlab to merge branch into the main branch.
    - Assign yourself as the processor
15. Process the pull request
    - Review the changes by selecting the "Changes" tab at the top of the screen
    - Add a comment about the change
    - Merge the change in to main by selecting "Merge" button.
16. Pull merged changes into your local repository since it has changed.
    - Review git log
    - Switch to **main**
    - Pull remote changes down
    ```
    git pull origin
    ```
17. Create a branch of your choosing.  Add a few text files, modifying files after initial commits, thus ending have multiple commits. Push the branch to the remote repository.  Create a pull request and process it to have changes merged into **main** branch

Grading will be based on commit history reflecting completing the steps above and completed quiz.
