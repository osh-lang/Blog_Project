package org.project.Blog.post.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.Blog.post.service.PostService;
import org.project.common.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1/post")
public class PostController {
 	private static final Logger logger = LoggerFactory.getLogger(PostController.class);

 	@Autowired
 	private PostService postservice;

 	@RequestMapping(value = "/getPostList", method = RequestMethod.POST)
	public @ResponseBody CommonResponse getPostList(@RequestBody Map<String, Object> requestMap) throws Exception {
		return postservice.getPostList(requestMap);
	}

 	@RequestMapping(value = "/getTagList", method = RequestMethod.POST)
	public @ResponseBody CommonResponse getTagList(@RequestBody Map<String, Object> requestMap) throws Exception {
		return postservice.getTagList();
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public @ResponseBody CommonResponse register(MultipartHttpServletRequest multiRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return postservice.registerPost(multiRequest);
	}

	@RequestMapping(value = "/insertAnswer", method = RequestMethod.POST)
	public @ResponseBody CommonResponse insertAnswer(@RequestBody Map<String, Object> requestMap) throws Exception {
		return postservice.insertAnswer(requestMap);
	}

    @RequestMapping(value = "/answerList", method = RequestMethod.POST)
    public @ResponseBody CommonResponse getAnswerList(@RequestBody Map<String, Object> requestMap) throws Exception {
        return postservice.getAnswerList(requestMap);
    }

    @RequestMapping(value = "/insertLike", method = RequestMethod.POST)
    public @ResponseBody CommonResponse insertLike(@RequestBody Map<String, Object> requestMap) throws Exception {
        return postservice.insertLike(requestMap);
    }

    @RequestMapping(value = "/deleteLike", method = RequestMethod.POST)
    public @ResponseBody CommonResponse deleteLike(@RequestBody Map<String, Object> requestMap) throws Exception {
    	return postservice.deleteLike(requestMap);
    }
}
