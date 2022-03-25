package io.muzoo.ssc.webapp.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection initializeDatabase() {
        try {
            String dbDriver = "com.mysql.cj.jdbc.Driver";
            String dbURL = "jdbc:mysql:// localhost:3306/";
            // Database name to access
            String dbName = "ssc";
            String dbUsername = "root";
            String dbPassword = "";

            Class.forName(dbDriver);
            Connection con = DriverManager.getConnection(dbURL + dbName,
                    dbUsername,
                    dbPassword);
            return con;
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }

        return null;
    }
}
