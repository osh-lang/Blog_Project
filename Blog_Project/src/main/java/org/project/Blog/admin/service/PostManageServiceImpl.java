package org.project.Blog.admin.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.project.Blog.mapper.PostManageMapper;
import org.project.Blog.mapper.TagMapper;
import org.project.common.response.CommonResponse;
import org.project.common.response.ResponseCode;
import org.project.common.util.CommonFileUtil;
import org.project.common.util.CommonFunction;
import org.project.common.util.CommonResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@EnableAsync
@Service("PostListServiceImpl")
public class PostManageServiceImpl implements PostManageService {

	private static final Logger logger = LoggerFactory.getLogger(PostManageServiceImpl.class);

	@Autowired
	PostManageMapper postManageMapper;

	@Autowired
	TagMapper tagMapper;

	// 글 조회
	@Override
	public CommonResponse selectPost(Map<String, Object> requestMap) throws Exception {

		// 필수값 체크
		if (CommonFunction.checkRequiredValue(requestMap, "search_count", "search_page")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		int search_count = Integer.parseInt(requestMap.get("search_count").toString());
		int search_page = Integer.parseInt(requestMap.get("search_page").toString());

		requestMap.put("limit_start", (search_page-1)*search_count);
		requestMap.put("limit_end", search_count);

		List<Map<String, Object>> postList = postManageMapper.selectPost(requestMap);

		if(postList.size() > 0) {
			int total_count = postManageMapper.countPostList(requestMap);

			return CommonResponseUtil.getListResponseWithPage(CommonFunction.nullToBlankInList(postList), total_count, postList.size(), search_page);
		}else {
			return CommonResponseUtil.getErrorResponse(ResponseCode.DATA_NOT_FOUND);
		}
	}

	@Override
	public CommonResponse getTagList() throws Exception {

		List<Map<String, Object>> dataList = tagMapper.getTagList();

		if(dataList.size() > 0) {
			return CommonResponseUtil.getListResponse(dataList);
		}else {
			return CommonResponseUtil.getErrorResponse(ResponseCode.DATA_NOT_FOUND);
		}
	}

	// 글 수정
	@Override
	public CommonResponse updatePost(MultipartHttpServletRequest multiRequest) throws Exception {

		Map<String, Object> requestMap = CommonFunction.requestParameterToMap(multiRequest);

		// 필수값 체크
		if (CommonFunction.checkRequiredValue(requestMap, "post_sj", "post_cn", "last_updusr")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		String subDir = "files/";
		postManageMapper.updateFile(requestMap);
		Long file_no = (Long) requestMap.get("file_no");

		int file_index = 1;
		//파일저장.
		Iterator<?> fileNameIter = multiRequest.getFileNames();
		while (fileNameIter.hasNext()) {
			String key = (String)fileNameIter.next();
			MultipartFile file = multiRequest.getFile(key);
			Map<String, Object> fileMap = CommonFileUtil.uploadFile(file, subDir);
			fileMap.put("file_no", file_no);
			fileMap.put("file_detail_sn", file_index);
			postManageMapper.updateFileDetail(fileMap);
			file_index++;
		}

		int checkupdate = postManageMapper.updatePost(requestMap);

		if(checkupdate > 0) {
			return CommonResponseUtil.getSuccessResponse();
		} else {
			return CommonResponseUtil.getErrorResponse(ResponseCode.INVALID_ERROR);
		}
	}

	@Override
	public Map<String, Object> viewPost(int post_no) throws Exception {
		return postManageMapper.viewPost(post_no);
	}

	// 글 삭제
	@SuppressWarnings("unchecked")
	@Override
	public CommonResponse deletePost(Map<String, Object> requestMap) throws Exception {
		// 필수값 체크
		if (CommonFunction.checkRequiredValue(requestMap, "post_no")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		ArrayList<String> str = (ArrayList<String>) requestMap.get("post_no");
		for(int i = 0; i<str.size(); i++) {
			String temp = str.get(i);
			postManageMapper.deletePost(temp);
		}

		return CommonResponseUtil.getSuccessResponse();
	}
}
