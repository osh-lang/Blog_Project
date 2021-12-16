package org.project.Blog.join.controller;

import java.util.Map;

import org.project.Blog.join.service.JoinService;
import org.project.common.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/v1/join")
public class JoinController {
	private static final Logger logger = LoggerFactory.getLogger(JoinController.class);

 	@Autowired
 	private JoinService joinService;

	@RequestMapping(value = "/userJoin", method = RequestMethod.POST)
	public @ResponseBody CommonResponse userJoin(@RequestBody Map<String, Object> requestMap) throws Exception {
		return joinService.userJoin(requestMap);
	}
}
