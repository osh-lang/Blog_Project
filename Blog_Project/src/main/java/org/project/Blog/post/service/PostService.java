package org.project.Blog.post.service;

import java.util.Map;

import org.project.common.response.CommonResponse;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface PostService {

	CommonResponse getPostList(Map<String, Object> requestMap) throws Exception;

	CommonResponse getTagList() throws Exception;

	Map<String, Object> getPost(int index);

	boolean deletePost(int index);

	CommonResponse registerPost(MultipartHttpServletRequest multiRequest) throws Exception;

	CommonResponse getAnswerList(Map<String, Object> requestMap) throws Exception;

	CommonResponse insertAnswer(Map<String, Object> requestMap) throws Exception;

	CommonResponse insertLike(Map<String, Object> requestMap) throws Exception;
	CommonResponse deleteLike(Map<String, Object> requestMap) throws Exception;
}