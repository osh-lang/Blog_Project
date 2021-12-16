package org.project.Blog.mapper;

import java.util.List;
import java.util.Map;

public interface PostManageMapper {

	// 글 조회
	public List<Map<String, Object>> selectPost(Map<String, Object> requestMap) throws Exception;

	// 글 수 조회
	public int countPostList(Map<String, Object> requestMap);

	// 글 수정
	public int updatePost(Map<String, Object> requestMap) throws Exception;
	public Map<String, Object> viewPost(int post_no) throws Exception;

	// 글 삭제
	public int deletePost(String temp) throws Exception;

	// 태그 조회
	public List<Map<String, Object>> getTagList() throws Exception;

	// 파일 수정
	public int updateFile(Map<String, Object> requsetMap);
	public int updateFileDetail(Map<String, Object> requsetMap);

	public List<Map<String, Object>> selectFile(Map<String, Object> requestMap) throws Exception;
}
