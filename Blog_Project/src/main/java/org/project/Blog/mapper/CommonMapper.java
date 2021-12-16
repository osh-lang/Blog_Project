package org.project.Blog.mapper;

import java.util.List;
import java.util.Map;

public interface CommonMapper {
	public Map<String, Object> selectMap(Map<String, Object> requestMap) throws Exception;
	public List<Map<String, Object>> selectList(Map<String, Object> requestMap) throws Exception;
	public int insert(Map<String, Object> requestMap) throws Exception;
	public int update(Map<String, Object> requestMap) throws Exception;
	public int delete(Map<String, Object> requestMap) throws Exception;
}

