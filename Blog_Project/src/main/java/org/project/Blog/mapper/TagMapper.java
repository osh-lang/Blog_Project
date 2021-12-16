package org.project.Blog.mapper;

import java.util.List;
import java.util.Map;

public interface TagMapper {

	List<Map<String, Object>> getTagList();

	int insertTag(Map<String, Object> requsetMap);

	Map<String, Object> selectTag(int index);

	int updateTag(Map<String, Object> requsetMap);

	int deleteTag(Map<String, Object> requsetMap);
}
