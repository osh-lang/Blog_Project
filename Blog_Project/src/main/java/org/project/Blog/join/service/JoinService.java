package org.project.Blog.join.service;

import java.util.Map;

import org.project.common.response.CommonResponse;

public interface JoinService {
	public CommonResponse userJoin(Map<String, Object> requestMap) throws Exception;
}
