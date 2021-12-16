package org.project.Blog.mapper;

import java.util.Map;

public interface JoinMapper {
	public int userJoin(Map<String, Object> requestMap) throws Exception;
	public int idChk(Map<String, Object> requestMap) throws Exception;
}

