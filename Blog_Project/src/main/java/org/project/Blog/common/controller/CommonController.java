package org.project.Blog.common.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.project.Blog.common.service.CommonService;
import org.project.common.response.CommonResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/common")
public class CommonController {
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@Autowired
	private CommonService commonService;

	@RequestMapping("/selectMap")
	public @ResponseBody CommonResponse selectMap(HttpServletRequest request, @RequestBody Map<String, Object> requestMap) throws Exception {
		return commonService.selectMap(requestMap);
	}

	@RequestMapping("/selectList")
	public @ResponseBody CommonResponse selectList(HttpServletRequest request, @RequestBody Map<String, Object> requestMap) throws Exception {
		return commonService.selectList(requestMap);
	}

	@RequestMapping("/insert")
	public @ResponseBody CommonResponse insert(HttpServletRequest request, @RequestBody Map<String, Object> requestMap) throws Exception {
		return commonService.insert(requestMap);
	}

	@RequestMapping("/update")
	public @ResponseBody CommonResponse update(HttpServletRequest request, @RequestBody Map<String, Object> requestMap) throws Exception {
		return commonService.update(requestMap);
	}

	@RequestMapping("/delete")
	public @ResponseBody CommonResponse delete(HttpServletRequest request, @RequestBody Map<String, Object> requestMap) throws Exception {
		return commonService.delete(requestMap);
	}
}

