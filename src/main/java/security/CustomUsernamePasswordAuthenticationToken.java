package security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class CustomUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {

    private String openAMToken;
    private String openAMUrl;
    
    
    
    
    public String getOpenAMUrl() {
        return openAMUrl;
    }

    public void setOpenAMUrl(String openAMUrl) {
        this.openAMUrl = openAMUrl;
    }

    public String getOpenAMToken() {
        return openAMToken;
    }


    public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials)
    /*     */ {
        /* 54 */ super(principal, credentials);
        /*     */ }


   
    public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials,
            Collection<? extends GrantedAuthority> authorities) {
        //openAMToken = token;
        super(principal, credentials, authorities);
        // TODO Auto-generated constructor stub
    }


    public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials,
            Collection<? extends GrantedAuthority> authorities, String openAMToken) {
        //openAMToken = token;
        super(principal, credentials, authorities);
        this.openAMToken = openAMToken;
        // TODO Auto-generated constructor stub
    }

}
