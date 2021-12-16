package org.project.Blog.mapper;

import java.util.Map;

public interface SocialMapper {
	Map<String, Object> userMap(Map<String, Object> requestMap) throws Exception;
	Map<String, Object> LoginCheck(Map<String, Object> requestMap) throws Exception;
	Map<String, Object> SocialCheck(Map<String, Object> requestMap) throws Exception;
	int Register(Map<String, Object> requestMap) throws Exception;
	int RegisterInfo(Map<String, Object> requestMap) throws Exception;
}
