package org.project.Blog.admin.service;

import java.util.Map;

import org.project.common.response.CommonResponse;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface PostManageService {

	// 글 조회
	public CommonResponse selectPost(Map<String, Object> requestMap) throws Exception;

	// 글 수정
	public CommonResponse updatePost(MultipartHttpServletRequest multiRequest) throws Exception;
	public Map<String, Object> viewPost(int post_no) throws Exception;

	// 글 삭제
	public CommonResponse deletePost(Map<String, Object> requestMap) throws Exception;

	// 태그 조회
	public CommonResponse getTagList() throws Exception;
}
