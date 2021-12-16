package org.project.Blog.mapper;

import java.util.List;
import java.util.Map;

public interface AnswerMapper {
	List<Map<String, Object>> getAnswerList(Map<String, Object> requsetMap);
	int getAnswerTotalCount(Map<String, Object> requsetMap);
	int insertAnswer(Map<String, Object> requsetMap);
	int insertLike(Map<String, Object> requsetMap);
	int deleteLike(Map<String, Object> requsetMap);
	int updateLike(Map<String, Object> requsetMap);
}
