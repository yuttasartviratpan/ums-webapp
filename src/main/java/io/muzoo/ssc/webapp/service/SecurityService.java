/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.muzoo.ssc.webapp.service;
import javax.servlet.http.HttpServletRequest;

import io.muzoo.ssc.webapp.model.User;
import lombok.Setter;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author gigadot
 */
public class SecurityService {

    @Setter
    private UserService userService;

    public boolean isAuthorized(HttpServletRequest request) {
        String username = (String) request.getSession()
                .getAttribute("username");

        return (username != null && userService.findByUsername(username) != null);
    }

    public boolean authenticate(String username, String password, HttpServletRequest request) {
        User user = userService.findByUsername(username);
        if(user != null && BCrypt.checkpw(password, user.getPassword())){
            request.getSession().setAttribute("username", username);
            return true;
        }
        else{
            return false;
        }
    }

    public void logout(HttpServletRequest request) {
        request.getSession().invalidate();
    }

}
