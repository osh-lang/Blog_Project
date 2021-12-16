package org.project.common.socialOauth;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class KakaoOauth implements SocialOauth {

    private String KAKAO_SNS_BASE_URL="https://kauth.kakao.com/oauth/authorize?response_type=code";
    private String KAKAO_SNS_CLIENT_ID = "119e7228c63f5df1de2a8f62d99f7f73";
    private String KAKAO_SNS_CLIENT_SECRET = "HIckQ5u8HRQkRQg4owMFcFMZcG0nCOCq";
    private String KAKAO_SNS_CALLBACK_URL = "http://localhost:8181/auth/kakao/callback";
    private String KAKAO_SNS_TOKEN_BASE_URL = "https://kauth.kakao.com/oauth/token";

	@Override
	public String getOauthRedirectURL() throws Exception {
    	String clientId = KAKAO_SNS_CLIENT_ID;//애플리케이션 클라이언트 아이디값";
		String redirectURI = URLEncoder.encode(KAKAO_SNS_CALLBACK_URL, "UTF-8");
		String apiURL = KAKAO_SNS_BASE_URL;
		apiURL += "&client_id=" + clientId;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&response_type=code";

		return apiURL;
	}

	@Override
	public String requestAccessToken(HttpServletRequest request) {
		String code = request.getParameter("code");
		String accessToken = "";
		String refreshToken = "";
		String reqURL = KAKAO_SNS_TOKEN_BASE_URL;

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=" + KAKAO_SNS_CLIENT_ID);
			sb.append("&redirect_uri=" + KAKAO_SNS_CALLBACK_URL);
			sb.append("&code="+code);

			bw.write(sb.toString());
			bw.flush();

			int responseCode = conn.getResponseCode();
			System.out.println("response code = " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";
			while((line = br.readLine())!=null) {
				result += line;
			}
			System.out.println("response body = " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			accessToken = element.getAsJsonObject().get("access_token").getAsString();
			refreshToken = element.getAsJsonObject().get("refresh_token").getAsString();

			br.close();
			bw.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return accessToken;
	}

	@Override
	public HashMap<String, Object> getUserInfo(String access_Token) {
		HashMap<String, Object> userInfo = new HashMap<String, Object>();
		String reqUrl = "https://kapi.kakao.com/v2/user/me";
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("AuthorizaTion", "Bearer " + access_Token);
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode = " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			String result = "";

			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body = " + result);

			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);

			JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
			JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

			String nickname = properties.getAsJsonObject().get("nickname").getAsString();
			String email = kakaoAccount.getAsJsonObject().get("email").getAsString();
			String id = element.getAsJsonObject().get("id").getAsString();

			userInfo.put("name", nickname);
			userInfo.put("email", email);
			userInfo.put("id", id);
			userInfo.put("join_type", "kakao");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userInfo;
	}

}