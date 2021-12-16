package org.project.Blog.common.service;

import java.util.Map;

import org.project.common.response.CommonResponse;

public interface CommonService {
	public CommonResponse selectMap(Map<String, Object> requestMap) throws Exception;
	public CommonResponse selectList(Map<String, Object> requestMap) throws Exception;
	public CommonResponse insert(Map<String, Object> requestMap) throws Exception;
	public CommonResponse update(Map<String, Object> requestMap) throws Exception;
	public CommonResponse delete(Map<String, Object> requestMap) throws Exception;
}
