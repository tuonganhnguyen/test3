package security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        // TODO Auto-generated method stub
        if(authentication.isAuthenticated())
        {
            System.out.println("CustomAuthenticationSuccessHandler onAuthenticationSuccess");
            CustomUsernamePasswordAuthenticationToken token = (CustomUsernamePasswordAuthenticationToken)authentication;
            
            Cookie openAMCookie = new Cookie("iPlanetDirectoryPro", token.getOpenAMToken()); 
            System.out.println("CustomAuthenticationSuccessHandler onAuthenticationSuccess:" + token.getOpenAMToken());
            openAMCookie.setPath("/");
            //openAMCookie.setDomain("");
            response.addCookie(openAMCookie);
            response.sendRedirect("page/test");
           
        }
        
    }

}
