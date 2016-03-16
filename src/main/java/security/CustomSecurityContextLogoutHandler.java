package security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;

import services.OpenAMAuthenticationService;

public class CustomSecurityContextLogoutHandler extends SecurityContextLogoutHandler {

    public CustomSecurityContextLogoutHandler() {
        // TODO Auto-generated constructor stub
        super();
    }
    
    @Autowired
    OpenAMAuthenticationService openAMAuthenticationService;
    
    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) {
        System.out.println("CustomSecurityContextLogoutHandler logout");
        
        CustomUsernamePasswordAuthenticationToken userAuthentication = (CustomUsernamePasswordAuthenticationToken)authentication;
        
        String openAMURL = userAuthentication.getOpenAMUrl();
        String openAMToken = userAuthentication.getOpenAMToken();
        super.logout(request, response, authentication);
        openAMAuthenticationService.logout(openAMURL, openAMToken);
    }
}
