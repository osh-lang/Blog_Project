package org.project.Blog.main.controller;

import org.project.Blog.post.service.PostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "")
public class MainPageController {

 	private static final Logger logger = LoggerFactory.getLogger(MainPageController.class);

 	@Autowired
 	PostService postService;

 	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main() {
 		ModelAndView mv = new ModelAndView();
		mv.setViewName("page.main.main");
		return mv;
	}
}

