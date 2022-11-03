package edu.auburn.cpsc4970.auth;

import org.apache.commons.io.IOUtils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import java.lang.String;
//import java.lang.Character;

/**
 * Simple Authenticator using a json file based database
 */
public class UsernamePasswordAuthenticator implements AuthProviderInterface {
    /**
     * Logging class
     */
    private final Logger logger = LoggerFactory.getLogger(UsernamePasswordAuthenticator.class);

    /**
     * Login handler taking a simple username and password to validate
     *
     * @param username
     * @param password
     * @return User session context
     * @throws AULoginException
     * @throws IOException
     */
    @Override
    public UserSession login(String username, String password) throws AULoginException, IOException {

        logger.info("Checking username with password");
       // logger.info("Checking \"" + username + "\" with password \"" + password + "\"");

        // Find the user database file on the classpath
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("users.json");

        // Read in json database of
        String result = IOUtils.toString(in, StandardCharsets.UTF_8);
        HashMap<String, String> userList = Utils.parseUserJson(result);


        if (username == null) {
            throw new AULoginException("Username can not be null"); // Placed first because user  does not exist triggers the "user does not exist".
        if(!userList.containsKey(username))
            throw new AULoginException("User \"" + username + "\" does not exist.");
        if (password == null)
            throw new AULoginException("Password can not be null"); // Placed first because user  does not exist triggers the "password is invalid".
        if (!password.equals(userList.get(username)))
            throw new AULoginException("Password for \"" + username + "\" is invalid");

        UserSession userSession = new UserSession(username, password);

        return userSession;
    }

    /**
     * Passwords need to be at least 2 characters long
     *
     * @param password to be checked.
     * @return
     */
    public boolean checkPasswordComplexity(String password) {

        //if (password.length() <= 2) return false;

        if (password.length() <= 2) {
            return false;
        } else {
            char c;
            int count = 1;
            for (int i = 0; i < password.length() - 1; i++) {
                c = password.charAt(i);
                if (!Character.isLetterOrDigit(c)) {
                    return false;
                } else if (Character.isDigit(c)) {
                    count++;
                    if (count < 1) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

}
