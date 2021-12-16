package org.project.Blog.mapper;

import java.util.List;
import java.util.Map;

public interface UserManageMapper {

	// 유저 조회
	public List<Map<String, Object>> selectUser(Map<String, Object> requestMap) throws Exception;

	// 유저 수 조회
	public int selectUserCount(Map<String, Object> requestMap) throws Exception;

	// 유저 수정
	public int updateUser(Map<String, Object> requestMap) throws Exception;
	public Map<String, Object> viewUser(String user_id) throws Exception;

	// 유저 삭제
	public int deleteUser(String temp) throws Exception;
}


