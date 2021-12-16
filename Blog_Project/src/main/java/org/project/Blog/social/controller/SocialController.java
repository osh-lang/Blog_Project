package org.project.Blog.social.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.project.Blog.social.service.SocialService;
import org.project.common.response.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/v1/snsJoin")
public class SocialController {

 	@Autowired
 	private SocialService Service;

	@RequestMapping("/userJoin")
	public @ResponseBody CommonResponse userJoin(HttpServletRequest request, @RequestBody Map<String, Object> requestMap) throws Exception {
		return Service.socialJoin(requestMap);
	}
}
