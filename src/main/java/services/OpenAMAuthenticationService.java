package services;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import security.openam.messages.LoginMessage;
import security.openam.messages.LogoutMessage;
import security.openam.messages.TokenValidationMessage;

@Service
public class OpenAMAuthenticationService {

    RestTemplate restTemplate;
    
    public OpenAMAuthenticationService() {
        restTemplate = new RestTemplate();
    }
    
    public String login(String openAMURL, String username, String password)
    {
        //restTemplate = new RestTemplate();
        StringBuilder loginUrlBuilder = new StringBuilder(openAMURL);
        loginUrlBuilder.append("/json/authenticate");
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("X-OpenAM-Username", username);
        headers.add("X-OpenAM-Password", password);
        headers.add("Accept-API-Version", "resource=1.0, protocol=1.0");
        
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<LoginMessage> entity = restTemplate.postForEntity(loginUrlBuilder.toString(), 
                request, LoginMessage.class);
        if(entity.getStatusCode() == HttpStatus.OK)
        {
            return entity.getBody().getTokenId();
//            return "Test";
        }
        return null;
    }
    
    public Boolean validateToken(String openAMURL, String openAMToken) {
        
        StringBuilder validateUrlBuilder = new StringBuilder(openAMURL);
        validateUrlBuilder.append("/json/sessions");
        validateUrlBuilder.append("/");
        validateUrlBuilder.append(openAMToken);
        validateUrlBuilder.append("?");
        validateUrlBuilder.append("_action=validate");
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        
        HttpEntity<String> request = new HttpEntity<String>(headers);
        ResponseEntity<TokenValidationMessage> entity = restTemplate.postForEntity(validateUrlBuilder.toString(), 
                request, TokenValidationMessage.class);
        return entity.getBody().getValid();
    }
    
    public void logout(String openAMURL, String openAMToken) {
        StringBuilder logoutUrlBuilder = new StringBuilder(openAMURL);
        logoutUrlBuilder.append("/json/sessions");
        logoutUrlBuilder.append("/");
        logoutUrlBuilder.append("?");
        logoutUrlBuilder.append("_action=logout");
        
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.add("iplanetDirectoryPro", openAMToken);
        HttpEntity<String> request = new HttpEntity<String>(headers);
//        MultiValueMap<String,String> parameters = new LinkedMultiValueMap<String,String>();
//        parameters.add("subjectid", openAMToken);
        
     // Create the http entity for the request
//        HttpEntity<MultiValueMap<String,String>> entity =
//                    new HttpEntity<MultiValueMap<String, String>>(parameters, headers);

        // Get the response as a string
        ResponseEntity<LogoutMessage> logoutResult = restTemplate.postForEntity(logoutUrlBuilder.toString(), 
                request, LogoutMessage.class);
        
    }
}
