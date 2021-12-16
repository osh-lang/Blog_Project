package org.project.Blog.join.service;

import java.util.Map;

import org.project.Blog.mapper.JoinMapper;
import org.project.common.response.CommonResponse;
import org.project.common.response.ResponseCode;
import org.project.common.util.CommonFunction;
import org.project.common.util.CommonResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@EnableAsync
@Service("JoinServiceImpl")
public class JoinServiceImpl implements JoinService {
	private static final Logger logger = LoggerFactory.getLogger(JoinServiceImpl.class);

	@Autowired
	JoinMapper joinMapper;

	@Override
	public CommonResponse userJoin(Map<String, Object> requestMap) throws Exception {
		if (CommonFunction.checkRequiredValue(requestMap, "user_id", "user_pw", "user_mail", "user_nm", "user_tel")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		//로그인 아이디 중복검사
		int duplCount = joinMapper.idChk(requestMap);
		if(duplCount > 0){
			return CommonResponseUtil.getErrorResponse(ResponseCode.DUPLICATE_INSPECTION);
		}
			joinMapper.userJoin(requestMap);

		return CommonResponseUtil.getSuccessResponse();
	}
}
