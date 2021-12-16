package org.project.Blog.login.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.project.Blog.login.service.LoginService;
import org.project.common.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/login")
public class LoginController {
 	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

 	@Autowired
 	private LoginService loginService;

 	@RequestMapping("/userLogin")
 	public @ResponseBody CommonResponse userLogin(@RequestBody Map<String, Object> requestMap, HttpServletRequest request) throws Exception {
 		return loginService.userLogin(requestMap, request);
 	}
}
