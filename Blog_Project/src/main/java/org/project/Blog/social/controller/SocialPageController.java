package org.project.Blog.social.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.Blog.login.service.LoginService;
import org.project.Blog.social.service.SocialService;
import org.project.common.response.SocialLoginType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;

@Controller
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(value = "/auth")
public class SocialPageController {

    private final SocialService socialService;
    private final LoginService loginService;

    /**
     * 사용자로부터 SNS 로그인 요청을 Social Login Type 을 받아 처리
     * @param socialLoginType (GOOGLE, GITHUB, NAVER, KAKAO)
     */
    @RequestMapping(value = "/{socialLoginType}", method = RequestMethod.GET)
    public void socialLoginType(
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType) throws Exception{
    	socialService.request(socialLoginType);
    }

    /**
     * Social Login API Server 요청에 의한 callback 을 처리
     * @param socialLoginType (GOOGLE, GITHUB, NAVER, KAKAO)
     */
    @RequestMapping(value = "/{socialLoginType}/callback", method = RequestMethod.GET)
    public ModelAndView callback(
    		HttpServletRequest request, HttpServletResponse response,
            @PathVariable(name = "socialLoginType") SocialLoginType socialLoginType) throws Exception{
    	ModelAndView mv = new ModelAndView();

    	String access_token = socialService.requestAccessToken(socialLoginType, request);
        Map<String, Object> userInfo = socialService.personalInfo(socialLoginType, access_token);
        Map<String, Object> loginCheck = socialService.loginCheck(userInfo);

        if("000".equals(loginCheck.get("code"))) {
			if("Y".equals(loginCheck.get("data"))) {
				socialService.getUserMap(userInfo, request);
				response.sendRedirect(request.getContextPath() + "/main");
			}else {
				mv.addObject("snsUserMap", userInfo);
				mv.setViewName("page.join.join");
			}
		}else {
			mv.setViewName("page.login.login");
		}

        return mv;
    }
}
