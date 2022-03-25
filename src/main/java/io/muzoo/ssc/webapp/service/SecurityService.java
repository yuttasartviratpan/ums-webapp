/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muzoo.ssc.webapp.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import io.muzoo.ssc.webapp.database.DatabaseConnection;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author gigadot
 */
public class SecurityService {
    
    public boolean isAuthorized(HttpServletRequest request) {
        String username = (String) request.getSession()
                .getAttribute("username");

        // do checking

       return (username != null && isUserInDB(username));
    }

    private boolean isUserInDB(String username){
        try (Connection con = new DatabaseConnection().initializeDatabase();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM hw4usercredentials " +
                     "WHERE username = " + "'" + username + "'")){
            if (rs.next()) {
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }


    public boolean authenticate(String username, String password, HttpServletRequest request) {
        try (Connection con = new DatabaseConnection().initializeDatabase();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM hw4usercredentials " +
                     "WHERE username = " + "'" + username + "'" + " AND password = " + "'" + password + "'")){
            if (rs.next()) {
                return true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

/*
    public boolean authenticate(String username, String password, HttpServletRequest request) {
        String passwordInDB = userCredentials.get(username);
        boolean isMatched = StringUtils.equals(password, passwordInDB);
        if (isMatched) {
            request.getSession().setAttribute("username", username);
            return true;
        } else {
            return false;
        }
    }
    
    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }
   */

}
