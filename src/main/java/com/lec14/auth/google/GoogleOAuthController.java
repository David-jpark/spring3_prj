package com.lec14.auth.google;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.JsonProcessingException;

@Controller
public class GoogleOAuthController {

    @Autowired
    private GoogleOAuthService googleOAuthService;

    @RequestMapping(value = "/loginForm", method = RequestMethod.GET)
    public String loginForm(Model model) {
        String googleUrl = googleOAuthService.getGoogleLoginUrl();
        model.addAttribute("MY_GOOGLE_LOGIN_URL", googleUrl);
        return "/login_page";
    }

    @RequestMapping(value = "/oauth2callback", method = RequestMethod.GET)
    public String loginGoogle(@RequestParam(value = "code") String code, Model model) {
        String accessToken = googleOAuthService.getAccessToken(code);
        String userInfo = googleOAuthService.getUserInfo(accessToken);
        model.addAttribute("MY_userInfo", userInfo);
        return "/mypage";
    }
    
    
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String revokeToken(@RequestParam(value = "token") String token, HttpServletResponse response) throws JsonProcessingException {

		RestTemplate restTemplate = new RestTemplate();
		final String requestUrl = UriComponentsBuilder.fromHttpUrl("https://oauth2.googleapis.com/revoke").queryParam("token", token).toString();
		
		String resultJson = restTemplate.postForObject(requestUrl, null, String.class);
		Map<String, String> result = new HashMap<>();
		result.put("result", "success");
		result.put("resultJson", resultJson);

		
		 Cookie cookie = new Cookie("refreshToken", "");
         cookie.setMaxAge(0);
         response.addCookie(cookie);
         
		System.out.println(result.toString());
		return "/login_page";

	}
    
}
