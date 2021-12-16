package org.project.Blog.login.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.project.Blog.login.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/login")
public class LoginPageController {
	private static final Logger logger = LoggerFactory.getLogger(LoginPageController.class);

	@Autowired
	LoginService service;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginPage(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws Exception {

		ModelAndView mv = new ModelAndView();

		mv.setViewName("page.login.login");
		mv.addObject("userMap", getSessionUserMap(request));
		return mv;
	}

	@RequestMapping(value = "/userLogout", method = RequestMethod.GET)
	public ModelAndView userLogout(HttpServletRequest request) throws Exception {
		request.getSession().removeAttribute("userMap");

		ModelAndView mv = new ModelAndView();

		mv.setViewName("page.login.login");
		return mv;
	}

	@SuppressWarnings("unchecked")
    private Map<String, Object> getSessionUserMap(HttpServletRequest request) {
        HttpSession session = request.getSession();
        return (Map<String, Object>)session.getAttribute("userMap");
    }
}