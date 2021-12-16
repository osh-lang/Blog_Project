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
import com.google.gson.JsonParser;

@Component
public class GitHubOauth implements SocialOauth {

    private String GITHUB_SNS_BASE_URL="https://github.com/login/oauth/authorize?";
    private String GITHUB_SNS_CLIENT_ID = "9ed908b747dc1c818c1c";
    private String GITHUB_SNS_CLIENT_SECRET = "ff22b53af83dd3a0a2deaf1b10bb9a6980390e94";
    private String GITHUB_SNS_CALLBACK_URL = "http://localhost:8181/auth/github/callback";
    private String GITHUB_SNS_TOKEN_BASE_URL = "https://github.com/login/oauth/access_token";

	@Override
	public String getOauthRedirectURL() throws Exception {
    	String clientId = GITHUB_SNS_CLIENT_ID;//애플리케이션 클라이언트 아이디값";
		String redirectURI = URLEncoder.encode(GITHUB_SNS_CALLBACK_URL, "UTF-8");
		String apiURL = GITHUB_SNS_BASE_URL;
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
		String reqURL = GITHUB_SNS_TOKEN_BASE_URL;

		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // 서버와 클라이언트 간 통신에 사용되는 규약, 자바에서 웹페이지에 접속하기위해 사용
			conn.setRequestMethod("GET"); // HttpConnection을 이용하여 Get방식과 Post방식으로 내용을 가져온다.

			conn.setDoOutput(true); // URL 연결을 출력용으로 사용

			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream())); // 버퍼를 이용해서 씀, 바이트 입력 스트림에 연결되어 문자 출력 스트림인 Writer로 변환시키는 보조 스트림, 출력 스트림
			StringBuilder sb = new StringBuilder(); // String과 문자열을 더할 때 새로운 객체를 생성하는 것이 아니라 기존의 데이터에 더하는 방식, 속도가 빠르며 상대적으로 부하가 적다.
			sb.append("&code="+code);
			sb.append("&client_id=" + GITHUB_SNS_CLIENT_ID);
			sb.append("&client_secret=" + GITHUB_SNS_CLIENT_SECRET);
			sb.append("&redirect_uri=" + GITHUB_SNS_CALLBACK_URL);

			bw.write(sb.toString());
			bw.flush(); // 출력 스트림을 비우고 버퍼 된 출력 바이트를 강제로 쓴다.

			int responseCode = conn.getResponseCode(); // http 응답의 상태코드
			System.out.println("response code = " + responseCode);

			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); // 버퍼를 이용해서 읽음, 바이트 입력 스트림에 연결되어 문자 입력 스트림인 Reader로 변환시키는 보조 스트림, 입력 스트림

			String line = "";
			String result = "";
			while((line = br.readLine())!=null) {
				result += line;
			}
			System.out.println("response body = " + result);

			String[] strAry1 = result.split("&");
			String accessToken2 = strAry1[0];

			String[] strAry2 = accessToken2.split("=");
			accessToken = strAry2[1];

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
		String reqUrl = "https://api.github.com/user";
		try {
			URL url = new URL(reqUrl);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("AuthorizaTion", "token " + access_Token); // Request Header값 세팅 (String key, String value)
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

			String name = null;
			String email = null;

			name = element.getAsJsonObject().get("name").toString();
			email = element.getAsJsonObject().get("email").toString();
			String id = element.getAsJsonObject().get("id").getAsString();

			userInfo.put("name", name);
			userInfo.put("email", email);
			userInfo.put("id", id);
			userInfo.put("join_type", "github");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return userInfo;
	}

}