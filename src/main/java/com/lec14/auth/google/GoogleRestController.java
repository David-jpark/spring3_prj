//package com.lec14.auth;
//
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//
//@RestController
////@CrossOrigin("*")
//public class GoogleRestController {
//	 //@Value("${google.client.id}")
//    private String googleClientId = "132153140522-46cvv4nbdb8gi5soug53972g2vaa9hgn.apps.googleusercontent.com";
//    //@Value("${google.client.pw}")
//    private String googleClientPw = "1SHdnEEJChFeerfO8lRV6rBJ";
//    
//    private String authCode="";
//    private String redirectUri = "http://localhost:8089/oauth2callback";
//    
//    
//    /**
//     * POST --> [사용자]로그인을 위한 [구글로그인창] 요청 : (client_id,redirect_uri,response_type,scope등) 
//     *      --> [구글]  로그인창 및 사용자가 지정한 정보공개,서비스,약관 안내   
//     * GET  --> [구글]  정상회원인 경우 사용자지정 callback URI로 cridential code 전송 
//     */
//    @RequestMapping(value="/getPopupURL", method = RequestMethod.POST)
//    public String loginUrlGoogle(){
//    	//https://accounts.google.com/o/oauth2/v2/auth?client_id=132153140522-46cvv4nbdb8gi5soug53972g2vaa9hgn.apps.googleusercontent.com&redirect_uri=http://localhost:8089/oauth2callback&response_type=code&scope=email profile
//        String reqUrl = "https://accounts.google.com/o/oauth2/v2/auth?client_id=" + googleClientId
//                + "&redirect_uri="+redirectUri+"&response_type=code&scope=email%20profile%20openid&access_type=offline";
//        return reqUrl;
//    }
//    
//    /**
//     * access_token 토큰 획득
//     */
//    @RequestMapping(value="/oauth2callback", method = RequestMethod.GET)
//    public String loginGoogle(@RequestParam(value = "code") String code, HttpServletResponse response){
//    	
//    	//http://localhost:8089/oauth2callback?code=4%2F0ATx3LY7bzr5ySpFPKF7yGe7117S5RPzmKL98OKC7MakXbpXyGtp2j3InngCLE-nsJ6Wp-A&scope=email+profile+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&authuser=0&prompt=consent
//    	this.authCode = code;
//    	System.out.println("authCode:" + code);
//    	
//    	
//    	//-------------------------------------------------------------------------
//        //요청 : clientId/clientSecret, code, grantType, redirectUri 
//        //-------------------------------------------------------------------------
//        GoogleRequest googleRequest = GoogleRequest
//                .builder()
//                .clientId(googleClientId)
//                .clientSecret(googleClientPw)
//                .code(authCode)
//                .redirectUri(redirectUri)
//                .grantType("authorization_code").build();
//        System.out.println(googleRequest.toString());
//        /**
//         GoogleRequest(
//         * clientId=132153140522-46cvv4nbdb8gi5soug53972g2vaa9hgn.apps.googleusercontent.com, 
//         * redirectUri=http://localhost:8089/oauth2callback, 
//         * clientSecret=1SHdnEEJChFeerfO8lRV6rBJ, 
//           responseType=null, 
//           scope=null, 
//         * code=4/0ATx3LY5Zv-hVDO-XY68Y1-xZOPs7Us6Soju8HBsEmWELGFZGPr9I-SdaeX1R4AJMvOThuA, 
//           accessType=null, 
//         * grantType=authorization_code,  //고정값(인가code를 통한 로그인 방식)
//           state=null, 
//           includeGrantedScopes=null, 
//           loginHint=null, 
//           prompt=null)
//         */
//        	
//        
//        //-------------------------------------------------------------------------
//        //응답 : access_token, expires_in, refresh_token, scope, token_type, id_token
//        //-------------------------------------------------------------------------
//        RestTemplate restTemplate = new RestTemplate();
//        ResponseEntity<GoogleResponse> googleResponse = restTemplate.postForEntity("https://oauth2.googleapis.com/token", googleRequest, GoogleResponse.class);
//        System.out.println(googleResponse.toString());
//        /**
//         <200 OK,GoogleResponse(
//         * access_token=ya29.a0AXooCgvDOaSSsTcWHQvPxodXuQ4LZnp3whkg_79CWrURjZ9VGVXNu9NxRa-D1jZl_Yt-VQVrggx17xmXArImhdZ3QVammOX0eHx9NcthRjMeskd7RG2jpFpwPrVg_T1iwP3VwSSjfi_-VHG7gdFFeBmskkXFvvUgpYzQaCgYKASUSARMSFQHGX2MiJK_y_-OGPHbN7clZS9IYfQ0171, 
//           expires_in=3599, 
//           refresh_token=null, 
//           scope=https://www.googleapis.com/auth/userinfo.profile openid https://www.googleapis.com/auth/userinfo.email, 
//           token_type=Bearer,   //고정값
//         * id_token=eyJhbGciOiJSUzI1NiIsImtpZCI6IjJhZjkwZTg3YmUxNDBjMjAwMzg4OThhNmVmYTExMjgzZGFiNjAzMWQiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJhenAiOiIxMzIxNTMxNDA1MjItNDZjdnY0bmJkYjhnaTVzb3VnNTM5NzJnMnZhYTloZ24uYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJhdWQiOiIxMzIxNTMxNDA1MjItNDZjdnY0bmJkYjhnaTVzb3VnNTM5NzJnMnZhYTloZ24uYXBwcy5nb29nbGV1c2VyY29udGVudC5jb20iLCJzdWIiOiIxMTY3ODI5NTY4MzUwMTQ5MzY2MTYiLCJlbWFpbCI6ImNvbXMua29yZWFAZ21haWwuY29tIiwiZW1haWxfdmVyaWZpZWQiOnRydWUsImF0X2hhc2giOiJLZ3NLN05LQ1BUZVkwRWdFTFRvZE1RIiwibmFtZSI6IuydtOqyve2ZlCIsInBpY3R1cmUiOiJodHRwczovL2xoMy5nb29nbGV1c2VyY29udGVudC5jb20vYS9BQ2c4b2NMYlRiWTlGVWdCeURybUgxQ21RUlF5VV9iMURGRVdjdWJjNlNqUjR1WmZEV1hWYU4wPXM5Ni1jIiwiZ2l2ZW5fbmFtZSI6Iuqyve2ZlCIsImZhbWlseV9uYW1lIjoi7J20IiwiaWF0IjoxNzE5OTYyODc5LCJleHAiOjE3MTk5NjY0Nzl9.YhG5yHG2yfmA_I5kWNIaiPax-VpN0GqdyomwkGItAFJKJ40O2jVbFY7jPTBRQM3kfwtaqbeqo0vdKrdKrrfZv1m9sAMtav82P2jZgPnoweSexGJ_SVtFVwI_Mlz312V2AaRyh7qr9JhbSDl2-JzzZIwKmIYIL34Vh92iBYy8i7-EQYqukRYzEtD15StGNUSqUWE-FpQ86KLZQw8k7UwdbVLU4dK0kFWcvQrnCAhOdsHyCqB7E9mwevkrBKKEKIcBsQraHcFe6xbtXTBvBjWn8BuMZjwi-_ElrusVkfJINUc24JFctLKrpE0e2Uzt_FdvP6YfHudcZFWGj5wgGPM1Ug)
//           ,{Date=[Tue, 02 Jul 2024 23:27:59 GMT], Expires=[Mon, 01 Jan 1990 00:00:00 GMT], Cache-Control=[no-cache, no-store, max-age=0, must-revalidate], Pragma=[no-cache], Content-Type=[application/json; charset=utf-8], Vary=[X-Origin, Referer, Origin,Accept-Encoding], Server=[scaffolding on HTTPServer2], X-XSS-Protection=[0], X-Frame-Options=[SAMEORIGIN], X-Content-Type-Options=[nosniff], Alt-Svc=[h3=":443"; ma=2592000,h3-29=":443"; ma=2592000], Accept-Ranges=[none], Transfer-Encoding=[chunked]}>
//         */
//        
//        String jwtToken = googleResponse.getBody().getId_token();
//        Map<String, String> map=new HashMap<>();
//        map.put("id_token",jwtToken);
//        
//        //유효성 검사
//        ResponseEntity<GoogleInfResponse> googleInfResponse = restTemplate.postForEntity("https://oauth2.googleapis.com/tokeninfo", map, GoogleInfResponse.class);
//        System.out.println(googleInfResponse.toString());
//        /**
//         <200 OK,GoogleInfResponse(
//           iss=https://accounts.google.com, 
//           azp=132153140522-46cvv4nbdb8gi5soug53972g2vaa9hgn.apps.googleusercontent.com, 
//           aud=132153140522-46cvv4nbdb8gi5soug53972g2vaa9hgn.apps.googleusercontent.com, 
//           sub=116782956835014936616, 
//         * email=coms.korea@gmail.com, 
//           email_verified=true, 
//           at_hash=PeKpLSK-laItl2GayJ01lw, 
//         * name=이경화, 
//           picture=https://lh3.googleusercontent.com/a/ACg8ocLbTbY9FUgByDrmH1CmQRQyU_b1DFEWcubc6SjR4uZfDWXVaN0=s96-c, 
//           given_name=경화, family_name=이, 
//           locale=null, 
//           iat=1719963501,
//           exp=1719967101, 
//           alg=RS256, 
//           kid=2af90e87be140c20038898a6efa11283dab6031d, typ=JWT),
//           {Pragma=[no-cache], Expires=[Mon, 01 Jan 1990 00:00:00 GMT], Date=[Tue, 02 Jul 2024 23:38:21 GMT], Cache-Control=[no-cache, no-store, max-age=0, must-revalidate], Content-Type=[application/json; charset=UTF-8], Vary=[X-Origin, Referer, Origin,Accept-Encoding], Server=[ESF], X-XSS-Protection=[0], X-Frame-Options=[SAMEORIGIN], X-Content-Type-Options=[nosniff], Alt-Svc=[h3=":443"; ma=2592000,h3-29=":443"; ma=2592000], Accept-Ranges=[none], Transfer-Encoding=[chunked]}>
//         */
//        String email=googleInfResponse.getBody().getEmail();       
//        return email;
//        
//    }
//
//}
