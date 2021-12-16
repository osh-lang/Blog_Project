package org.project.Blog.login.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.project.Blog.mapper.LoginMapper;
import org.project.common.response.CommonResponse;
import org.project.common.response.ResponseCode;
import org.project.common.util.CommonFunction;
import org.project.common.util.CommonResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@EnableAsync
@Service("loginServiceImpl")
public class LoginServiceImpl implements LoginService {

	@Autowired
	LoginMapper loginMapper;

	@Override
	public CommonResponse userLogin(Map<String, Object> requestMap, HttpServletRequest request) throws Exception {
		if (CommonFunction.checkRequiredValue(requestMap, "login_id", "login_password")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		Map<String, Object> userCheck = loginMapper.userLoginCheck(requestMap);

		if("Y".equals(userCheck.get("login_at"))) {
			Map<String, Object> userMap = loginMapper.userLogin(requestMap);
			request.getSession().setAttribute("userMap", userMap);
			request.getSession().setMaxInactiveInterval(30*60); // 30분

			return CommonResponseUtil.getSuccessResponse();
		}

		return CommonResponseUtil.getErrorResponse(ResponseCode.WRONG_ID_OR_PASSWORD);
	}
}
