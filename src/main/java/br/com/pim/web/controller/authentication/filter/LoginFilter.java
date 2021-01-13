package br.com.pim.web.controller.authentication.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.pim.web.controller.authentication.LoginController;
import org.apache.log4j.Logger;

public class LoginFilter implements Filter {

    private static final Logger LOG = Logger.getLogger(LoginFilter.class);
 
    /**
     * Checks if user is logged in. If not it redirects to the index.xhtml page, which
     * is currently the login page.
     * 
     * @param request
     * @param response
     * @param chain
     * @throws java.io.IOException
     * @throws javax.servlet.ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // Get the loginBean from session attribute
        LoginController loginController = (LoginController)((HttpServletRequest)request).getSession().getAttribute("loginController");
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        // For the first application request there is no loginBean in the session so user needs to log in
        // For other requests loginBean is present but we need to check if user has logged in successfully
        if (loginController == null || !loginController.isLoggedIn()) {
            String contextPath = httpRequest.getContextPath();
            String fromPath = httpRequest.getRequestURI();
            String redirect = "";

            // Fetches the path from where the user is arriving so we can perform
            // the proper redirect later
            for (String path : fromPath.split("/")){
                redirect += "/" + path;
            }
            
            // if it is not logged in and is a protected page, performs the
            // proper redirect to the login page
            if (fromPath.contains("/p/")){
                ((HttpServletResponse)response).sendRedirect(contextPath + "/index");
            }
        }
         
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            LOG.error("Não foi possível restaurar view.");
        }
             
    }
 
    @Override
    public void init(FilterConfig config) throws ServletException {
        // Nothing to do here!
    }
 
    @Override
    public void destroy() {
        // Nothing to do here!
    }   
     
}
