package org.project.Blog.common.service;

import java.util.List;
import java.util.Map;

import org.project.Blog.mapper.CommonMapper;
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
@Service("commonServiceImpl")
public class CommonServiceImpl implements CommonService {
	private static final Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

	@Autowired
	CommonMapper commonMapper;

	@Override
	public CommonResponse selectMap(Map<String, Object> requestMap) throws Exception {
		if (CommonFunction.checkRequiredValue(requestMap, "id")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		Map<String, Object> dataMap = commonMapper.selectMap(requestMap);

		if(dataMap != null && dataMap.size() > 0) {
			return CommonResponseUtil.getSingleResponse(dataMap);
		}else {
			return CommonResponseUtil.getErrorResponse(ResponseCode.DATA_NOT_FOUND);
		}
	}

	@Override
	public CommonResponse selectList(Map<String, Object> requestMap) throws Exception {
		List<Map<String, Object>> dataList = commonMapper.selectList(requestMap);

		if(dataList.size() > 0) {
			return CommonResponseUtil.getListResponse(CommonFunction.nullToBlankInList(dataList));
		}else {
			return CommonResponseUtil.getErrorResponse(ResponseCode.DATA_NOT_FOUND);
		}
	}

	@Override
	public CommonResponse insert(Map<String, Object> requestMap) throws Exception {
		if (CommonFunction.checkRequiredValue(requestMap, "id", "name")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		try {
			commonMapper.insert(requestMap);
		} catch (Exception e) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.INVALID_ERROR);
		}

		return CommonResponseUtil.getSuccessResponse();
	}

	@Override
	public CommonResponse update(Map<String, Object> requestMap) throws Exception {
		if (CommonFunction.checkRequiredValue(requestMap, "id", "name")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		try {
			commonMapper.update(requestMap);
		} catch (Exception e) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.INVALID_ERROR);
		}

		return CommonResponseUtil.getSuccessResponse();
	}

	@Override
	public CommonResponse delete(Map<String, Object> requestMap) throws Exception {
		if (CommonFunction.checkRequiredValue(requestMap, "id")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		try {
			commonMapper.delete(requestMap);
		} catch (Exception e) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.INVALID_ERROR);
		}

		return CommonResponseUtil.getSuccessResponse();
	}
}
