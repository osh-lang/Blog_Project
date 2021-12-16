package org.project.Blog.admin.controller;

import java.util.Map;

import org.project.Blog.admin.service.UserManageService;
import org.project.common.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/admin/userManage")
public class UserManageController {
	private static final Logger logger = LoggerFactory.getLogger(UserManageController.class);

	@Autowired
	private UserManageService userlistService;

	// 유저 조회
	@RequestMapping("/selectUser")
	public @ResponseBody CommonResponse selectUser(@RequestBody Map<String, Object> requestMap) throws Exception {
		return userlistService.selectUser(requestMap);
	}

	// 유저 수정
	@RequestMapping("/updateUser")
	public @ResponseBody CommonResponse updateUser(@RequestBody Map<String, Object> requestMap) throws Exception {
		return userlistService.updateUser(requestMap);
	}

	@RequestMapping("/viewUser")
	public @ResponseBody Map<String, Object> viewUser(@RequestParam("user_id") String user_id) throws Exception {
		return userlistService.viewUser(user_id);
	}

	// 유저 삭제
	@RequestMapping("/deleteUser")
	public @ResponseBody CommonResponse deleteUser(@RequestBody Map<String, Object> requestMap) throws Exception {
		return userlistService.deleteUser(requestMap);
	}
}

