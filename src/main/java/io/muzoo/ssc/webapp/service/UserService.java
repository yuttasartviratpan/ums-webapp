package io.muzoo.ssc.webapp.service;

public class UserService {
    /*
    public static void main(String[] args) {
        String dbDriver = "com.mysql.cj.jdbc.Driver";
        String dbURL = "jdbc:mysql:// localhost:13306/";
        // Database name to access
        String dbName = "login_webapp";
        String dbUsername = "root";
        String dbPassword = "securedpassword";

        try(Connection con = DriverManager.getConnection(dbURL + dbName,
                dbUsername,
                dbPassword);){
            String query = " INSERT INTO tbl_user(username, password, display_name)"
                    + " VALUES (?, ?, ?)";
            PreparedStatement preparedStmt = con.prepareStatement(query);
            preparedStmt.setString (1, "my_username");
            preparedStmt.setString (2, "my_password");
            preparedStmt.setString (3, "my_display_name");
            preparedStmt.execute();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
     */
}
