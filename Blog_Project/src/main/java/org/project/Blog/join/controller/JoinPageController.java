package org.project.Blog.join.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/join")
public class JoinPageController {
	private static final Logger logger = LoggerFactory.getLogger(JoinPageController.class);

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView join(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();

		mv.addObject("userMap", getSessionUserMap(request)); // 가입 후 바로 홈화면으로 유저정보 보냄
		mv.setViewName("page.join.join");
		return mv;
	}

	@SuppressWarnings("unchecked")
    private Map<String, Object> getSessionUserMap(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Map<String, Object>)session.getAttribute("userMap");
    }
}
