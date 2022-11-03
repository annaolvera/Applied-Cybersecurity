package edu.auburn.cpsc4970.database;

import edu.auburn.cpsc4970.auth.Utils;
import org.postgresql.Driver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;



public class DBUtils {

    /**
     * Logging class
     */
    private static final Logger logger = LoggerFactory.getLogger(DBUtils.class);


    public static String getNameForUser (String searchUser) {

        String userValue = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {

            // Load the database driver
            Class.forName("org.postgresql.Driver");

            // Registering drivers
            DriverManager.registerDriver(new Driver());

            // Get connection to the remote database
            String userName = Utils.getProperty("db.user");
            String passWord = Utils.getProperty("db.password");
            connection = DriverManager.getConnection("jdbc:postgresql://cpsc4970-sum-a-db.cemps9spg241.us-east-2.rds.amazonaws.com:5432/cpsc-sum-a", userName, passWord);



            // Setup query to execute
            preparedStatement = connection.prepareStatement("select name, password from users where username= ?");
            preparedStatement.setString(1,searchUser);

            logger.info("Looking up name for user: " + searchUser );

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                userValue = resultSet.getString(1);

                System.out.println("Found: "+userValue);

            }

            // Closing the connection
            preparedStatement.close();
            connection.close();
        }

        // Catch block to handle exceptions
        catch (Exception ex) {
            // Display message when exceptions occurs
            java.util.logging.Logger.getLogger(String.valueOf(ex));
            System.err.println(ex);
        }
        finally {
            // Quietly try to close connections.
            try { resultSet.close(); } catch (Exception e) { /* Ignored */ }
            try { preparedStatement.close(); } catch (Exception e) { /* Ignored */ }
            try { connection.close(); } catch (Exception e) { /* Ignored */ }
        }

        return userValue;
    }
}
