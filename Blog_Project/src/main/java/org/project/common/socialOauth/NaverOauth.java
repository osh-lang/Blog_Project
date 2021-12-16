package org.project.common.socialOauth;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Component
public class NaverOauth implements SocialOauth {

    private String NAVER_SNS_BASE_URL="https://nid.naver.com/oauth2.0/authorize?response_type=code";
    private String NAVER_SNS_CLIENT_ID = "Ofaq59YYQ1wFmQx4hCIa";
    private String NAVER_SNS_CLIENT_SECRET = "ysQ67phYrM";
    private String NAVER_SNS_CALLBACK_URL = "http://localhost:8181/auth/naver/callback";
    private String NAVER_SNS_TOKEN_BASE_URL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";

    @Override
	public String getOauthRedirectURL() throws Exception {
    	String clientId = NAVER_SNS_CLIENT_ID;
		String redirectURI = URLEncoder.encode(NAVER_SNS_CALLBACK_URL, "UTF-8");
		SecureRandom random = new SecureRandom();
		String state = new BigInteger(130, random).toString();
		String apiURL = NAVER_SNS_BASE_URL;
		apiURL += "&client_id=" + clientId;
		apiURL += "&redirect_uri=" + redirectURI;
		apiURL += "&state=" + state;

		return apiURL;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String requestAccessToken(HttpServletRequest request) throws Exception {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
    	String redirectURI = URLEncoder.encode(NAVER_SNS_CALLBACK_URL, "UTF-8");
	    String apiURL;
	    apiURL = NAVER_SNS_TOKEN_BASE_URL;
	    apiURL += "client_id=" + NAVER_SNS_CLIENT_ID;
	    apiURL += "&client_secret=" + NAVER_SNS_CLIENT_SECRET;
	    apiURL += "&redirect_uri=" + redirectURI;
	    apiURL += "&code=" + code;
	    apiURL += "&state=" + state;
	    URL url = new URL(apiURL);
	    HttpURLConnection con = (HttpURLConnection)url.openConnection();
	    con.setRequestMethod("GET");
	    int responseCode = con.getResponseCode();
	    BufferedReader br;
	    if(responseCode==200) { // 정상 호출
	    	br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    } else {  // 에러 발생
	    	br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	    }
	    String inputLine;
	    StringBuffer res = new StringBuffer();
	    while ((inputLine = br.readLine()) != null) {
	    	res.append(inputLine);
	    }
	    br.close();

	    Map<String, Object> map = null;

	    if(responseCode==200) {
		    JsonParser Parser = new JsonParser();
	    	JsonObject jsonObj = (JsonObject) Parser.parse(res.toString());
	    	map = new ObjectMapper().readValue(jsonObj.toString(), Map.class);
	    }

	    return map.get("access_token").toString();
	}

	@SuppressWarnings("unchecked")
	@Override
	public HashMap<String, Object> getUserInfo(String access_Token) throws Exception {

        String header = "Bearer " + access_Token; // Bearer 다음에 공백 추가
        String apiURL = "https://openapi.naver.com/v1/nid/me";
        URL url = new URL(apiURL);
        HttpURLConnection con = (HttpURLConnection)url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Authorization", header);
        int responseCode = con.getResponseCode();
        BufferedReader br;
        if(responseCode==200) { // 정상 호출
            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
        } else {  // 에러 발생
            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
        }
        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();

        HashMap<String, Object> map = null;
        JsonParser Parser = new JsonParser();
    	JsonObject jsonObj = (JsonObject) Parser.parse(response.toString());
    	JsonObject user = (JsonObject) jsonObj.get("response");
    	map = (HashMap<String, Object>) new ObjectMapper().readValue(user.toString(), Map.class);
    	map.put("join_type", "naver");

		return map;
	}
}