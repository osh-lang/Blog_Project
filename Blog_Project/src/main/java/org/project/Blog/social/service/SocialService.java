package org.project.Blog.social.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.project.common.response.CommonResponse;
import org.project.common.response.SocialLoginType;

public interface SocialService {

	void request(SocialLoginType socialLoginType) throws Exception;

	String requestAccessToken(SocialLoginType socialLoginType, HttpServletRequest request) throws Exception;

	Map<String, Object> personalInfo(SocialLoginType socialLoginType, String accessToken) throws Exception;

	Map<String, Object> loginCheck(Map<String, Object> requestMap) throws Exception;

	CommonResponse socialJoin(Map<String, Object> requestMap) throws Exception;

	public Map<String, Object> getUserMap(Map<String, Object> requestMap, HttpServletRequest request) throws Exception;
}