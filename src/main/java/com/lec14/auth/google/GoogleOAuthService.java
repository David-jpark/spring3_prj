package com.lec14.auth.google;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class GoogleOAuthService {

    private final String clientId 			= "132153140522-46cvv4nbdb8gi5soug53972g2vaa9hgn.apps.googleusercontent.com";
    private final String clientSecret 		= "1SHdnEEJChFeerfO8lRV6rBJ";
    private final String redirectUri 		= "http://localhost:8089/oauth2callback";
    private final String tokenEndpoint 		= "https://oauth2.googleapis.com/token";
    private final String userInfoEndpoint 	= "https://www.googleapis.com/userinfo/v2/me";
    										   
    public String getGoogleLoginUrl() {
    	
    	//https://accounts.google.com/o/oauth2/v2/auth?client_id=132153140522-46cvv4nbdb8gi5soug53972g2vaa9hgn.apps.googleusercontent.com&redirect_uri=http://localhost:8089/oauth2callback&response_type=code&scope=email profile
    	String scope = "https://www.googleapis.com/auth/userinfo.profile";
    	String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + clientId
                + "&redirect_uri="+redirectUri+"&response_type=code&scope=email%20profile%20openid&access_type=offline";
        return reqUrl;
    }

    public String getAccessToken(String authorizationCode) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String, String> params = new HashMap<>();
        params.put("code"			, authorizationCode);
        params.put("client_id"		, clientId);
        params.put("client_secret"	, clientSecret);
        params.put("redirect_uri"	, redirectUri);
        params.put("grant_type"		, "authorization_code");

        //"https://oauth2.googleapis.com/token";
        Map<String, String> response = restTemplate.postForObject(tokenEndpoint, params, Map.class);
        return response.get("access_token");
    }

    public String getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        //"https://www.googleapis.com/userinfo/v2/me";
        String url = userInfoEndpoint + "?access_token=" + accessToken;
        return restTemplate.getForObject(url, String.class);
    }
}

