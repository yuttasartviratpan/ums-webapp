package io.muzoo.ssc.webapp.servlet;

import io.muzoo.ssc.webapp.Routable;
import io.muzoo.ssc.webapp.model.User;
import io.muzoo.ssc.webapp.service.SecurityService;
import io.muzoo.ssc.webapp.service.UserService;
import org.apache.commons.lang.StringUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author gigadot
 */
public class EditUserServlet extends HttpServlet implements Routable {

    private SecurityService securityService;

    @Override
    public String getMapping() {
        return "/user/edit";
    }

    @Override
    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if (authorized) {
            String username = StringUtils.trim(request.getParameter("username"));
            UserService userService = UserService.getInstance();
            User user = userService.findByUsername(username);
            if(user == null){
                response.sendRedirect("/");
            }
            else{
                request.setAttribute("user", user);
                request.setAttribute("username", user.getUsername());
                request.setAttribute("displayName", user.getDisplayName());
                RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/edit.jsp");
                rd.include(request, response);
            }

            request.getSession().removeAttribute("hasError");
            request.getSession().removeAttribute("message");
        } else {
            request.removeAttribute("hasError");
            request.removeAttribute("message");
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authorized = securityService.isAuthorized(request);
        if (authorized) {

            String username = StringUtils.trim(request.getParameter("username"));
            String displayName = StringUtils.trim(request.getParameter("displayName"));

            UserService userService = UserService.getInstance();
            User user = userService.findByUsername(username);

            String errorMessage = null;
            if(user == null){
                errorMessage = "Username does not exist";
            }
            else if(StringUtils.isBlank(username)){
                errorMessage = "Username cannot be blank";
            }
            else if(StringUtils.isBlank(displayName)){
                errorMessage = "Display name cannot be blank";
            }
            if(errorMessage != null){
                request.getSession().setAttribute("hasError", true);
                request.getSession().setAttribute("message", errorMessage);
            }
            else{
                try{
                    userService.updateUserByUsername(username, displayName);
                    request.getSession().setAttribute("hasError", false);
                    request.getSession().setAttribute("message", "User has been updated");
                    response.sendRedirect("/");
                    return;
                }
                catch (Exception e){
                    request.getSession().setAttribute("hasError", true);
                    request.getSession().setAttribute("message", e.getMessage());
                }
            }
            request.setAttribute("username", username);
            request.setAttribute("displayName", displayName);

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/edit.jsp");
            rd.include(request, response);
            request.getSession().removeAttribute("hasError");
            request.getSession().removeAttribute("message");
        } else {
            request.removeAttribute("hasError");
            request.removeAttribute("message");
            response.sendRedirect("/login");
        }
    }

}