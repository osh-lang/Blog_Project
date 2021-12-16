package org.project.Blog.admin.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.project.Blog.admin.service.PostManageService;
import org.project.common.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@RestController
@RequestMapping(value = "/api/v1/admin/postManage")
public class PostManageController {
	private static final Logger logger = LoggerFactory.getLogger(PostManageController.class);

	@Autowired
	private PostManageService postManageService;

	// 글 조회
	@RequestMapping("/selectPost")
	public @ResponseBody CommonResponse selectUser(@RequestBody Map<String, Object> requestMap) throws Exception {
		return postManageService.selectPost(requestMap);
	}

	// 글 수정
	@RequestMapping("/updatePost")
	public @ResponseBody CommonResponse updateUser(MultipartHttpServletRequest multiRequest, HttpServletRequest request, HttpServletResponse response) throws Exception {
		return postManageService.updatePost(multiRequest);
	}

	@RequestMapping("/viewPost")
	public @ResponseBody Map<String, Object> viewPost(@RequestParam("post_no") int post_no) throws Exception {
		return postManageService.viewPost(post_no);
	}

	// 글 삭제
	@RequestMapping("/deletePost")
	public @ResponseBody CommonResponse deleteUser(@RequestBody Map<String, Object> requestMap) throws Exception {
		return postManageService.deletePost(requestMap);
	}

	// 글 조회
 	@RequestMapping("/getTagList")
	public @ResponseBody CommonResponse getTagList(@RequestBody Map<String, Object> requestMap) throws Exception {
		return postManageService.getTagList();
	}
}

