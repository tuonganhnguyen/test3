package security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.util.StringUtils;

import services.OpenAMAuthenticationService;

//import OpenAMAuthenticationService;

public class CustomAuthenticationProvider implements AuthenticationProvider {

    
    @Autowired
    OpenAMAuthenticationService openAMAuthService;
    
    public CustomAuthenticationProvider() {
    }
    
    public Authentication authenticate(Authentication paramAuthentication) throws AuthenticationException {
        // TODO Auto-generated method stub
        CustomUsernamePasswordAuthenticationToken userToken = (CustomUsernamePasswordAuthenticationToken)paramAuthentication;
        
        String name = paramAuthentication.getName();
        String password = paramAuthentication.getCredentials().toString();
        String openAMToken = openAMAuthService.login(userToken.getOpenAMUrl(), name, password);
        
        
        if (!StringUtils.isEmpty(openAMToken)) {
            List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();
            grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));
            CustomUsernamePasswordAuthenticationToken auth = new CustomUsernamePasswordAuthenticationToken(name, password, grantedAuths, openAMToken);
            auth.setOpenAMUrl(userToken.getOpenAMUrl());
            return auth;
        }
        
        return null;
    }

    public boolean supports(Class<?> paramClass) {
        // TODO Auto-generated method stub
        return true;
    }

}
