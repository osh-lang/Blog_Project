package org.project.Blog.admin.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.project.Blog.mapper.UserManageMapper;
import org.project.common.response.CommonResponse;
import org.project.common.response.ResponseCode;
import org.project.common.util.CommonFunction;
import org.project.common.util.CommonResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

@EnableAsync
@Service("UserManageServiceImpl")
public class UserManageServiceImpl implements UserManageService {
	private static final Logger logger = LoggerFactory.getLogger(UserManageServiceImpl.class);

	@Autowired
	UserManageMapper userManageMapper;

	// �쑀�� 議고쉶
	@Override
	public CommonResponse selectUser(Map<String, Object> requestMap) throws Exception {

		// �븘�닔媛� 泥댄겕
		if (CommonFunction.checkRequiredValue(requestMap, "search_count", "search_page")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		int search_count = Integer.parseInt(requestMap.get("search_count").toString());
		int search_page = Integer.parseInt(requestMap.get("search_page").toString());

		requestMap.put("limit_start", (search_page - 1) * search_count);
		requestMap.put("limit_end", search_count);

		List<Map<String, Object>> userList = userManageMapper.selectUser(requestMap);

		if (userList.size() > 0) {
			int total_count = userManageMapper.selectUserCount(requestMap);

			return CommonResponseUtil.getListResponseWithPage(CommonFunction.nullToBlankInList(userList), total_count,
					userList.size(), search_page);
		} else {
			return CommonResponseUtil.getErrorResponse(ResponseCode.DATA_NOT_FOUND);
		}
	}

	// �쑀�� �닔�젙
	@Override
	public CommonResponse updateUser(Map<String, Object> requestMap) throws Exception {

		// �븘�닔媛� 泥댄겕
		if (CommonFunction.checkRequiredValue(requestMap, "user_id")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		userManageMapper.updateUser(requestMap);

		return CommonResponseUtil.getSuccessResponse();
	}

	@Override
	public Map<String, Object> viewUser(String user_id) throws Exception {
		return userManageMapper.viewUser(user_id);
	}

	// �쑀�� �궘�젣
	@SuppressWarnings("unchecked")
	@Override
	public CommonResponse deleteUser(Map<String, Object> requestMap) throws Exception {
		// �븘�닔媛� 泥댄겕
		if (CommonFunction.checkRequiredValue(requestMap, "user_id")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		ArrayList<String> str = (ArrayList<String>) requestMap.get("user_id");
		for (int i = 0; i < str.size(); i++) {
			String temp = str.get(i);
			userManageMapper.deleteUser(temp);
		}

		return CommonResponseUtil.getSuccessResponse();
	}
}
