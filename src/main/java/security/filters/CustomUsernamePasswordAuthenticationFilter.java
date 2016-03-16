package security.filters;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import security.CustomUsernamePasswordAuthenticationToken;

public class CustomUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private boolean postOnly = true;
    
    public CustomUsernamePasswordAuthenticationFilter() {
        super();
    }
    
 // ~ Methods
    // ========================================================================================================

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                    HttpServletResponse response) throws AuthenticationException {
            System.out.println("CustomUsernamePasswordAuthenticationFilter attemptAuthentication");
            if (postOnly && !request.getMethod().equals("POST")) {
                    throw new AuthenticationServiceException(
                                    "Authentication method not supported: " + request.getMethod());
            }

            String username = obtainUsername(request);
            String password = obtainPassword(request);
            String openAMUrl = request.getParameter("openAMURL");

            if (username == null) {
                    username = "";
            }

            if (password == null) {
                    password = "";
            }

            username = username.trim();

            CustomUsernamePasswordAuthenticationToken authRequest = new CustomUsernamePasswordAuthenticationToken(
                            username, password);
            authRequest.setOpenAMUrl(openAMUrl);

            // Allow subclasses to set the "details" property
            setDetails(request, authRequest);

            return this.getAuthenticationManager().authenticate(authRequest);
    }
}
