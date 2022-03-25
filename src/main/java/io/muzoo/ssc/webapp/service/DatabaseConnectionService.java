package io.muzoo.ssc.webapp.service;
import com.zaxxer.hikari.HikariDataSource;
import io.muzoo.ssc.webapp.config.ConfigProperties;
import io.muzoo.ssc.webapp.config.ConfigurationLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnectionService {
    String dbDriver = "com.mysql.cj.jdbc.Driver";
    String dbURL = "jdbc:mysql:// localhost:13306/";
    String dbName = "login_webapp";
    String dbUsername = "root";
    String dbPassword = "securedpassword";
    private final HikariDataSource ds;

    public DatabaseConnectionService(){
        ds = new HikariDataSource();
        ConfigProperties configProperties = ConfigurationLoader.load();
        if(configProperties == null){
            throw new RuntimeException("Unable to read config.properties");
        }
        ds.setDriverClassName(configProperties.getDatabaseDriverClassName());
        ds.setJdbcUrl(configProperties.getDatabaseConnectionUrl());
        ds.addDataSourceProperty("user", configProperties.getDatabaseUsername());
        ds.addDataSourceProperty("password", configProperties.getDatabasePassword());
        ds.setAutoCommit(false);
    }

    public Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

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
