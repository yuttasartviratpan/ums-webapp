package io.muzoo.ssc.webapp.service;

import io.muzoo.ssc.webapp.model.User;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

public class UserService {

    private static final String INSERT_USER_SQL = "INSERT INTO tbl_user(username, password, display_name) VALUES (?, ?, ?);";
    private static final String SELECT_USER_SQL = "SELECT * FROM tbl_user WHERE username = ?;";

    @Setter
    private DatabaseConnectionService databaseConnectionService;

    public void createUser(String username, String password, String displayName) throws UserServiceException {
        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        try(Connection connection = databaseConnectionService.getConnection()){
            PreparedStatement preparedStmt = connection.prepareStatement(INSERT_USER_SQL);
            preparedStmt.setString (1, username);
            preparedStmt.setString (2, hashedPassword);
            preparedStmt.setString (3, displayName);
            preparedStmt.execute();
            connection.commit();
        }
        catch (SQLIntegrityConstraintViolationException e){
            throw new UsernameNotUniqueException(String.format("Username %s has already been taken.", username));
        }
        catch (SQLException throwables){
            throw new UserServiceException(throwables.getMessage());
        }
    }

    public User findByUsername(String username){
        try(Connection connection = databaseConnectionService.getConnection()){
            PreparedStatement preparedStmt = connection.prepareStatement(SELECT_USER_SQL);
            preparedStmt.setString (1, username);
            ResultSet result = preparedStmt.executeQuery();
            result.next();
            return new User(
                    result.getLong("id"),
                    result.getString("username"),
                    result.getString("password"),
                    result.getString("display_name")
            );
        }
        catch (SQLException throwables){
            return null;
        }
    }

    public static void main(String[] args) {
        UserService userService = new UserService();
        userService.setDatabaseConnectionService(new DatabaseConnectionService());
        User user = userService.findByUsername("domo");
        System.out.println(user.getUsername());
    }
}
