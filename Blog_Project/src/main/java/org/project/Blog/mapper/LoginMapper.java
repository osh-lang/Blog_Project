package org.project.Blog.mapper;

import java.util.Map;

public interface LoginMapper {
	Map<String, Object> userLogin(Map<String, Object> requestMap) throws Exception;
	Map<String, Object> userLoginCheck(Map<String, Object> requestMap) throws Exception;
	Map<String, Object> naverLogin(Map<String, Object> requestMap) throws Exception;
	Map<String, Object> naverRegister(Map<String, Object> requestMap) throws Exception;

}
