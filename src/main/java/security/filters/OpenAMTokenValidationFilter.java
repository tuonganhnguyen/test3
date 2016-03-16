package security.filters;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hamcrest.generator.qdox.tools.QDoxTester.Reporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.GenericFilterBean;

import security.CustomUsernamePasswordAuthenticationToken;
import services.OpenAMAuthenticationService;

public class OpenAMTokenValidationFilter extends GenericFilterBean {

    @Autowired
    OpenAMAuthenticationService openAMAuthenticationService;
    
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // TODO Auto-generated method stub
        System.out.println("OpenAMTokenValidationFilter doFilter");
        CustomUsernamePasswordAuthenticationToken userAuthentication = (CustomUsernamePasswordAuthenticationToken)SecurityContextHolder.getContext().getAuthentication();
        if(userAuthentication != null && userAuthentication.isAuthenticated())
        {
            System.out.println("OpenAMTokenValidationFilter doFilter authenticated");
            System.out.println(userAuthentication.getOpenAMToken());
            Boolean validationResult = openAMAuthenticationService.validateToken(userAuthentication.getOpenAMUrl(), userAuthentication.getOpenAMToken());
            HttpServletRequest httpRequest = (HttpServletRequest)request;
            HttpServletResponse httpResponse = (HttpServletResponse)response;
            if(validationResult == null || !validationResult) {
                //httpResponse.
                new SecurityContextLogoutHandler().logout(httpRequest, httpResponse, userAuthentication);
                httpResponse.sendRedirect("logout");
                return;
            }
        }
        chain.doFilter(request, response);

    }

}
