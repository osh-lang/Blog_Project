package org.project.common.socialOauth;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class GoogleOauth implements SocialOauth {
    private String GOOGLE_SNS_BASE_URL = "https://accounts.google.com/o/oauth2/v2/auth";
    private String GOOGLE_SNS_CLIENT_ID = "708713972604-647qi8v3e9vt64r0v4jo5lg3kfu9lp8o.apps.googleusercontent.com";
    private String GOOGLE_SNS_CLIENT_SECRET = "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";
    private String GOOGLE_SNS_CALLBACK_URL = "http://localhost:8181/auth/google/callback";
    private String GOOGLE_SNS_TOKEN_BASE_URL = "https://www.googleapis.com/oauth2/v4/token";

    @Override
    public String getOauthRedirectURL() {
        Map<String, Object> params = new HashMap<>();
        params.put("scope", "profile email");
        params.put("response_type", "code");
        params.put("client_id", GOOGLE_SNS_CLIENT_ID);
        params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);

        String parameterString = params.entrySet().stream()
                .map(x -> x.getKey() + "=" + x.getValue())
                .collect(Collectors.joining("&"));

        return GOOGLE_SNS_BASE_URL + "?" + parameterString;
    }


    @Override
    public String requestAccessToken(HttpServletRequest request) {
    	String code = request.getParameter("code");
        try {
            URL url = new URL(GOOGLE_SNS_TOKEN_BASE_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setDoOutput(true);

            Map<String, Object> params = new HashMap<>();
            params.put("code", code);
            params.put("client_id", GOOGLE_SNS_CLIENT_ID);
            params.put("client_secret", GOOGLE_SNS_CLIENT_SECRET);
            params.put("redirect_uri", GOOGLE_SNS_CALLBACK_URL);
            params.put("grant_type", "authorization_code");

            String parameterString = params.entrySet().stream()
                    .map(x -> x.getKey() + "=" + x.getValue())
                    .collect(Collectors.joining("&"));

            BufferedOutputStream bous = new BufferedOutputStream(conn.getOutputStream());
            bous.write(parameterString.getBytes());
            bous.flush();
            bous.close();

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;

            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

            if (conn.getResponseCode() == 200) {
            	JsonParser parser = new JsonParser();
            	JsonObject jsonObj = (JsonObject) parser.parse(sb.toString());
            	String access_token = jsonObj.get("access_token").toString();
                return access_token;
            }
            return "구글 로그인 요청 처리 실패";
        } catch (IOException e) {
            throw new IllegalArgumentException("알 수 없는 구글 로그인 Access Token 요청 URL 입니다 :: " + GOOGLE_SNS_TOKEN_BASE_URL);
        }
    }

    @Override
    public HashMap<String, Object> getUserInfo(String access_Token) {
    	//요청하는 클라이언트마다 가진 정보가 다를 수 있기에 Map타입으로 선언
		HashMap<String, Object> googleUserInfo = new HashMap<>();
		String reqURL = "https://www.googleapis.com/userinfo/v2/me?access_token="+access_Token;

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Authorization", "Bearer " + access_Token);
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : "+responseCode);
			if(responseCode == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				String line = "";
				String result = "";

				while((line = br.readLine()) != null) {
					result += line;
				}
				JsonParser parser = new JsonParser();
				JsonElement element = parser.parse(result);

				System.out.println("result : "+result);

				String email = element.getAsJsonObject().get("email").getAsString();
				String id = element.getAsJsonObject().get("id").getAsString();
				String name = element.getAsJsonObject().get("name").getAsString();

				googleUserInfo.put("email", email);
				googleUserInfo.put("name", name);
				googleUserInfo.put("id", id);
				googleUserInfo.put("join_type", "google");
				System.out.println("login Controller : " + googleUserInfo);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return googleUserInfo;
    }

}
