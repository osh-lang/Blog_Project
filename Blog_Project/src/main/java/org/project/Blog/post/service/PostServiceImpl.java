package org.project.Blog.post.service;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.project.Blog.mapper.AnswerMapper;
import org.project.Blog.mapper.PostMapper;
import org.project.Blog.mapper.TagMapper;
import org.project.common.response.CommonResponse;
import org.project.common.response.ResponseCode;
import org.project.common.util.CommonFileUtil;
import org.project.common.util.CommonFunction;
import org.project.common.util.CommonResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Service("PostService")
public class PostServiceImpl implements PostService {

	@Autowired
	PostMapper postMapper;

	@Autowired
	TagMapper tagMapper;

	@Autowired
	AnswerMapper answerMapper;

	@Override
	public CommonResponse getPostList(Map<String, Object> requestMap) throws Exception {
		// 필수값 체크
		if (CommonFunction.checkRequiredValue(requestMap, "search_count", "search_page")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		int search_count = Integer.parseInt(requestMap.get("search_count").toString());
		int search_page = Integer.parseInt(requestMap.get("search_page").toString());

		requestMap.put("limit_start", (search_page-1)*search_count);
		requestMap.put("limit_end", search_count);

		List<Map<String, Object>> dataList = postMapper.getPostList(requestMap);

//		for (Map<String, Object> requestData : dataList) {
//			int countData = illegalParkingHistMapper.getCountData(requestData);
//			requestData.put("cntMonth",countData);
//		}

		if(dataList.size() > 0) {
			int total_count = postMapper.getPostTotalCount(requestMap);

			return CommonResponseUtil.getListResponseWithPage(CommonFunction.nullToBlankInList(dataList), total_count, dataList.size(), search_page);
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

	@Override
	public CommonResponse getAnswerList(Map<String, Object> requestMap) throws Exception {
		// 필수값 체크
		if (CommonFunction.checkRequiredValue(requestMap, "post_no")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		List<Map<String, Object>> dataList = answerMapper.getAnswerList(requestMap);

		if(dataList.size() > 0) {
			int total_count = answerMapper.getAnswerTotalCount(requestMap);

			return CommonResponseUtil.getListResponse(CommonFunction.nullToBlankInList(dataList), total_count);
		}else {
			return CommonResponseUtil.getErrorResponse(ResponseCode.DATA_NOT_FOUND);
		}
	}

	@Override
	public CommonResponse insertAnswer(Map<String, Object> requestMap) throws Exception {
		// 필수값 체크
		if (CommonFunction.checkRequiredValue(requestMap, "post_no", "answer_register", "answer")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		int chkMapper = answerMapper.insertAnswer(requestMap);

		if(chkMapper > 0) {
			return CommonResponseUtil.getSuccessResponse();
		} else {
			return CommonResponseUtil.getErrorResponse(ResponseCode.INVALID_ERROR);
		}
	}

	@Override
	public CommonResponse registerPost(MultipartHttpServletRequest multiRequest) throws Exception {
		Map<String, Object> requestMap = CommonFunction.requestParameterToMap(multiRequest);

		// 필수값 체크
		if (CommonFunction.checkRequiredValue(requestMap, "post_sj", "post_cn", "tag_value", "frst_register")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		String subDir = "files/";
		postMapper.insertFile(requestMap);
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
			postMapper.insertFileDetail(fileMap);
			file_index++;
		}

		int checkRegister = postMapper.insertPost(requestMap);

		if(checkRegister > 0) {
			return CommonResponseUtil.getSuccessResponse();
		} else {
			return CommonResponseUtil.getErrorResponse(ResponseCode.INVALID_ERROR);
		}
	}

	@Override
	public Map<String, Object> getPost(int index) {
		Map<String, Object> post = postMapper.selectPost(index);

		return post;
	}

	@Override
	public boolean deletePost(int index) {
		return false;
	}

	@Override
	public CommonResponse insertLike(Map<String, Object> requestMap) throws Exception {
		// 필수값 체크
		if (CommonFunction.checkRequiredValue(requestMap, "answer_no", "user_id")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		int chkMapper = answerMapper.insertLike(requestMap);

		if(chkMapper > 0) {
			return CommonResponseUtil.getSuccessResponse();
		} else {
			return CommonResponseUtil.getErrorResponse(ResponseCode.INVALID_ERROR);
		}
	}
	@Override
	public CommonResponse deleteLike(Map<String, Object> requestMap) throws Exception {
		if(CommonFunction.checkRequiredValue(requestMap, "answer_no", "user_id")) {
			return CommonResponseUtil.getErrorResponse(ResponseCode.PARAMETER_REQUIRED);
		}

		int chkMapper = answerMapper.deleteLike(requestMap);

		if(chkMapper > 0) {
			return CommonResponseUtil.getSuccessResponse();
		} else {
			return CommonResponseUtil.getErrorResponse(ResponseCode.INVALID_ERROR);
		}
	}
}
