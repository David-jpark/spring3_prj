//package com.lec14.auth.google;
//
//
////import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
////import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
////import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
////import com.google.api.client.json.JsonFactory;
////import com.google.api.client.json.jackson2.JacksonFactory;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.fasterxml.jackson.core.JsonFactory;
//
//import java.util.Collections;
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//public class GoogleOAuthRestController {
//
//	private static final String CLIENT_ID = "YOUR_GOOGLE_CLIENT_ID";
//	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
//
//	@PostMapping("/api/authenticate")
//	public Map<String, Object> authenticate(@RequestBody Map<String, String> payload) {
//		String idTokenString = payload.get("token");
//
//		try {
//			GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(GoogleNetHttpTransport.newTrustedTransport(), JSON_FACTORY)
//					.setAudience(Collections.singletonList(CLIENT_ID))
//					.build();
//
//			GoogleIdToken idToken = verifier.verify(idTokenString);
//			if (idToken != null) {
//				GoogleIdToken.Payload tokenPayload = idToken.getPayload();
//
//				// 사용자 정보 추출
//				String userId = tokenPayload.getSubject();
//				String email = tokenPayload.getEmail();
//				boolean emailVerified = Boolean.valueOf(tokenPayload.getEmailVerified());
//				String name = (String) tokenPayload.get("name");
//				String pictureUrl = (String) tokenPayload.get("picture");
//
//				// 사용자 인증 로직 추가
//				// 예: 데이터베이스에 사용자 정보 저장 또는 세션 설정
//
//				Map<String, Object> response = new HashMap<>();
//				response.put("success", true);
//				response.put("userId", userId);
//				response.put("email", email);
//				response.put("name", name);
//				response.put("pictureUrl", pictureUrl);
//				return response;
//			} else {
//				Map<String, Object> response = new HashMap<>();
//				response.put("success", false);
//				response.put("message", "Invalid ID token.");
//				return response;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			Map<String, Object> response = new HashMap<>();
//			response.put("success", false);
//			response.put("message", "Token verification failed.");
//			return response;
//		}
//	}
//}
