package org.project.Blog.social.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.Blog.mapper.SocialMapper;
import org.project.common.response.CommonResponse;
import org.project.common.response.ResponseCode;
import org.project.common.response.SocialLoginType;
import org.project.common.socialOauth.SocialOauth;
import org.project.common.util.CommonFunction;
import org.project.common.util.CommonResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class SocialServiceImpl implements SocialService {
    private final List<SocialOauth> socialOauthList;
    private final HttpServletResponse response;

    @Autowired
    SocialMapper socialMapper;

    @Override
	public void request(SocialLoginType socialLoginType) throws Exception {
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
        String redirectURL = socialOauth.getOauthRedirectURL();
        try {
            response.sendRedirect(redirectURL);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
	public String requestAccessToken(SocialLoginType socialLoginType, HttpServletRequest request) throws Exception{
        SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);//NAVER

        return socialOauth.requestAccessToken(request);
    }

    @Override
	public Map<String, Object> personalInfo(SocialLoginType socialLoginType, String accessToken) throws Exception {
    	SocialOauth socialOauth = this.findSocialOauthByType(socialLoginType);
    	Map<String, Object> map = socialOauth.getUserInfo(accessToken);

    	return map;
    }

	@Override
	public Map<String, Object> loginCheck(Map<String, Object> requestMap) throws Exception {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		if (CommonFunction.checkRequiredValue(requestMap, "id", "join_type")) {
			resultMap.put("code", "201");
			resultMap.put("data", "");
			return resultMap;
		}

		try {
			resultMap.put("code", "000");
			resultMap.put("data", socialMapper.SocialCheck(requestMap).get("login_at"));
		} catch (Exception e) {
			resultMap.put("code", "201");
			resultMap.put("data", "");
			return resultMap;
		}

		return resultMap;
	}

	@Override
	public CommonResponse socialJoin(Map<String, Object> requestMap) throws Exception {
		if (CommonFunction.checkRequiredValue(requestMap, "user_id", "user_pw", "user_mail", "user_nm", "user_tel", "sns_id", "join_type")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		try {
			socialMapper.Register(requestMap);
			socialMapper.RegisterInfo(requestMap);

		} catch (Exception e) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.INVALID_ERROR);
		}

		return CommonResponseUtil.getSuccessResponse();
	}

	@Override
	public Map<String, Object> getUserMap(Map<String, Object> requestMap, HttpServletRequest request) throws Exception {
		Map<String, Object> userMap = socialMapper.userMap(requestMap);

		request.getSession().setAttribute("userMap", userMap);
		request.getSession().setMaxInactiveInterval(30*60); // 30분

		return userMap;
	}

    private SocialOauth findSocialOauthByType(SocialLoginType socialLoginType) {
        return socialOauthList.stream()
                .filter(x -> x.type() == socialLoginType)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("알 수 없는 SocialLoginType 입니다."));
    }
}
