package org.project.Blog.admin.service;

import java.util.Map;

import org.project.common.response.CommonResponse;

public interface UserManageService {
	// 유저 조회
	public CommonResponse selectUser(Map<String, Object> requestMap) throws Exception;

	// 유저 수정
	public CommonResponse updateUser(Map<String, Object> requestMap) throws Exception;
	public Map<String, Object> viewUser(String user_id) throws Exception;

	// 유저 삭제
	public CommonResponse deleteUser(Map<String, Object> requestMap) throws Exception;
}
