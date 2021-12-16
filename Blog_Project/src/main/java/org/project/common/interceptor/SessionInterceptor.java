package org.project.common.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

/**
* @Class Name : SessionInterceptor.java
* @Description : 세션 처리를 위한 interceptor 클래스를 정의한다.
* @since 2020.12.21
* @version 1.0
* @see
*/
public class SessionInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(SessionInterceptor.class);

	@SuppressWarnings("unchecked")
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
		Map<String, Object> loginInfo = (Map<String, Object>)session.getAttribute("userMap");

		if(loginInfo == null || "".equals(loginInfo.get("user_id"))) {
			response.setStatus(401);
			response.sendRedirect(request.getContextPath() + "/login/login");
			return false;
		} else {

		    return true;
		}
	}
}
