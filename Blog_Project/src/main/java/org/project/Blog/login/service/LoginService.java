package org.project.Blog.login.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.project.common.response.CommonResponse;

public interface LoginService {
	CommonResponse userLogin(Map<String, Object> requestMap, HttpServletRequest request) throws Exception;
}
