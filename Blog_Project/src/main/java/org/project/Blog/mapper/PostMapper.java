package org.project.Blog.mapper;

import java.util.List;
import java.util.Map;

public interface PostMapper {
	List<Map<String, Object>> getPostList(Map<String, Object> requsetMap);
	int getPostTotalCount(Map<String, Object> requsetMap);

	int insertFile(Map<String, Object> requsetMap);

	int insertFileDetail(Map<String, Object> requsetMap);

	int insertPost(Map<String, Object> requsetMap);

	Map<String, Object> selectPost(int index);

	int updatePost(Map<String, Object> requsetMap);

	int deletePost(Map<String, Object> requsetMap);
}
