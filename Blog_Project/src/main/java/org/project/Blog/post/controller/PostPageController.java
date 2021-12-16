package org.project.Blog.post.controller;

import java.util.Map;

import org.project.Blog.post.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/post")
public class PostPageController {
	private static final Logger logger = LoggerFactory.getLogger(PostPageController.class);

	@Autowired
	PostService postService;

	@RequestMapping(value = "/{post_no}", method = RequestMethod.GET)
	public ModelAndView postDetail(@PathVariable(name = "post_no") int post_no) {
		ModelAndView mv = new ModelAndView();

		Map<String, Object> postDetail = postService.getPost(post_no);
		mv.addObject("postDetail", postDetail);

		mv.setViewName("page.main.detail");

		return mv;
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView postRegister() {
		ModelAndView mv = new ModelAndView();

		mv.setViewName("page.main.register");

		return mv;
	}
}