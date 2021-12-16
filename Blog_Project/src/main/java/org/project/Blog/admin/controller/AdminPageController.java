package org.project.Blog.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.Blog.admin.service.PostManageService;
import org.project.Blog.admin.service.UserManageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin")
public class AdminPageController {
	private static final Logger logger = LoggerFactory.getLogger(AdminPageController.class);

	@Autowired
	UserManageService userlistService;

	@Autowired
	PostManageService postlistService;

	//메인
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("page.admin.main");
		return mv;
	}

	// 유저 목록
	@RequestMapping(value = "/userManage", method = RequestMethod.GET)
	public ModelAndView UserList() throws Exception {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("page.admin.userManage");
		return mv;
	}

	// 유저 수정
	@RequestMapping(value = "/userUpdate", method = RequestMethod.GET)
	public ModelAndView UserUpdate(String user_id) throws Exception {
		ModelAndView mv = new ModelAndView();

		Map<String, Object> viewuser = userlistService.viewUser(user_id);

		mv.addObject("viewUser", viewuser);

		mv.setViewName("page.admin.userUpdate");
		return mv;
	}

	// 글 목록
	@RequestMapping(value = "/postManage", method = RequestMethod.GET)
	public ModelAndView PostList() throws Exception {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("page.admin.postManage");
		return mv;
	}

	// 글 수정
	@RequestMapping(value = "/postUpdate", method = RequestMethod.GET)
	public ModelAndView PostUpdate(int post_no) throws Exception {
		ModelAndView mv = new ModelAndView();

		Map<String, Object> viewpost = postlistService.viewPost(post_no);

		mv.addObject("viewPost", viewpost);

		mv.setViewName("page.admin.postUpdate");
		return mv;
	}

	// 글 보기
	@RequestMapping(value = "/postDetail", method = RequestMethod.GET)
	public ModelAndView postDetail(int post_no) throws Exception {
		ModelAndView mv = new ModelAndView();

		Map<String, Object> viewpost = postlistService.viewPost(post_no);

		mv.addObject("viewPost", viewpost);

		mv.setViewName("page.admin.postDetail");
		return mv;
	}

}