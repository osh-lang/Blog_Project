var postlistObj = {};

postlistObj.initEvent = function() {

	// 글 리스트에서 전체 체크박스 눌렀을때
	$("#select_all").on("click", function() {
		if($("#select_all").is(":checked")) {
			$("input[name=cks]").prop("checked", true);
		} else {
			$("input[name=cks]").prop("checked", false);
		}
	});

	// 글 리스트에서 삭제 버튼 눌렀을때
	 $("input[name^='button']").on("click", function() {
		if (confirm("삭제 하시겠습니까??") == true){

		    postlistObj.deletePost();
		}else{
		    alert("취소되었습니다.");
		}
  	});

 	// 글 리스트에서 검색 버튼 눌렀을때
	$("#searchpost_btn").on("click", function() {
		pagination._currPage = 1;
		postlistObj.getPostList();
	});

	// 글 리스트에서 검색창에 검색어 입력하고 엔터 눌렀을때
	$("#search_keyword").keyup(function(event) {
		if (event.keyCode == 13) {
			pagination._currPage = 1;
			userlistObj.getUserList();
		}
	});
}

postlistObj.validateUserInfo = function(v) {
	v.search_keyword = $("#search_keyword").val();

	var regExpSearch_keyword = /^[가-힣a-zA-Z0-9]+$/;

	if (regExpSearch_keyword.test(v.search_keyword) === false) {
		alert("검색 형식이 올바르지 않습니다. 한글(가-힣), 영대소문자, 숫자만 가능");
		$("#search_keyword").focus();
		return false;
	}

	if(v.search_keyword == null || v.search_keyword === "") {
		alert("검색어를 입력해 주세요.");
		$("#search_keyword").focus();
		return false;
	}

	return true;
};

// 페이징 리스트 불러오기
postlistObj.getPostList = function(){

	var _data = {};
	var search_keyword = $("#search_keyword").val();

	if(search_keyword == "") {
		_data.search_keyword == "";
	} else {
		if(!postlistObj.validateUserInfo(_data)) return;
	}

	var _url = contextPath + '/api/v1/admin/postManage/selectPost';

	var _postData = {
		"search_page" : pagination.getCurrPage(),
		"search_count" : 10,
		"search_type" : $("#search_type option:selected").val(),
		"search_keyword" : _data.search_keyword
	};

	commonObj.ajaxCall(_url, _postData, postlistObj.getPostListCallback, true);
};

postlistObj.getPostListCallback = function(response, status, xhr) {
	try {
		var responseCode = response.response_code;
		var responseMessage = response.response_message;

		$("#post_list_tbody").empty();

		if(responseCode == "000") {
			var data = response.data;

			$.each(data, function(index, item) {

				var html = '';
					html += '<tr>';
					html += '	<td style="width: 35px">' + '<input type="checkbox" class="chk" id="cks" name="cks" value="' +  item.post_no + '">' + '</td>';
					html += '	<td>' + item.post_no + '</td>';
					html += '	<td>' + '<a href="/admin/postDetail?post_no=' + item.post_no + '">' + item.post_sj + '</a><br>' + '작성자 : ' + item.frst_register + '&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;작성일 : ' + item.frst_regist_dt + '</td>';
					html += '	<td>' + '<a href="/admin/postUpdate?post_no=' + item.post_no + '" class="ui gray button" style="margin-right: 0px;" id="postupdt_btn">수정</a>' + '</td>';
					html += '</tr>';

				$("#post_list_tbody").append(html);
				$("#select_all").prop("checked", false);

			});

			pagination.drawPage(response.total_count);

		}else if(responseCode == "201"){
			var html = '';
			html += '<tr>';
			html += '	<td class="pdt1_1" colspan="7" style="text-align:center;">조회된 데이터가 없습니다.</td>';
			html += '</tr>';
			$("#post_list_tbody").append(html);

			pagination.drawPage(0);

		}else {
			var html = '';
			html += '<tr>';
			html += '	<td class="pdt1_1" colspan="8">' + responseMessage + '</td>';
			html += '</tr>';
			$("#post_list_tbody").append(html);

			pagination.drawPage(0);
		}
	}catch(e) {
		console.log(e);
		console.log(response);
	}
};

postlistObj.deletePost = function() {

	var deleter = $('#deleter').val();
	var chk = [];

	$('.chk:checked').each(function(){
        chk.push($(this).val());
    });

	var _url = contextPath + '/api/v1/admin/postManage/deletePost';
	var _postData = {
		'post_no': chk,
		'deleter': deleter
	};

	commonObj.ajaxCall(_url, _postData, postlistObj.deletePostCallback);
};

postlistObj.deletePostCallback = function(response, status, xhr) {

	try {
		responseCode = response.response_code;
		responseMessage = response.response_message;
		if (responseCode == "000") {
			alert("삭제가 완료되었습니다.");
			fn_go_page(1);
			postlistObj.getPostList();
			$("#select_all").prop("checked", false);
		} else {
			alert(responseMessage);
		}
	} catch (e) {
		console.log(e);
		console.log(response);
	}
}

$(document).ready(function() {
	postlistObj.initEvent();
	postlistObj.getPostList();2

	pagination.init($(".pagination"), 1, 10);
});

var pagination = new PageObject2();

function fn_go_page(page) {
	pagination.setCurrPage(page);
	postlistObj.getPostList();
}