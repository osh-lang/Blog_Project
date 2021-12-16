var mainObj = {};

mainObj.initEvent = function() {

	// 메인에서 사용자 관리를 눌렀을때

	// 메인에서 글 관리를 눌렀을때

	// 유저리스트에서 삭제 버튼 눌렀을때
	 $("input[name^='button']").on("click", function() {
		if (confirm("삭제 하시겠습니까??") == true){

		    mainObj.deleteUser();
		}else{
		    alert("취소되었습니다.");
		}
  	});

 	// 유저리스트에서 검색 버튼 눌렀을때
	$("#searchuser_btn").on("click", function() {
		pagination._currPage = 1;
		mainObj.getUserList();
	});

}

$(document).ready(function() {
	mainObj.initEvent();
	mainObj.getUserList();

	pagination.init($(".pagination"), 1, 10);
});

// 페이징 리스트 불러오기
mainObj.getUserList = function(){
	var _url = contextPath + '/api/v1/admin/userList/selectUser';

	var _postData = {
		"search_page" : pagination.getCurrPage(),
		"search_count" : 10,
		"search_type" : $("#search_type option:selected").val(),
		"search_keyword" : $("#search_keyword").val(),
	};

	commonObj.ajaxCall(_url, _postData, mainObj.getUserListCallback, true);
};

mainObj.getUserListCallback = function(response, status, xhr) {
	try {
		var responseCode = response.response_code;
		var responseMessage = response.response_message;

		$("#user_list_tbody").empty();

		if(responseCode == "000") {
			var search_count = response.search_count;
			var search_page = response.search_page;

			var data = response.data;

			$.each(data, function(index, item) {
				var num = (search_page-1)*10 + index + 1;

				var html = '';
					html += '<tr>';
					html += '	<td style="width: 35px">' + '<input type="checkbox" class="chk" id="cks" value="' +  item.user_id + '">' + '</td>';
					html += '	<td>' + item.user_nm + '</td>';
					html += '	<td>' + item.user_id + '</td>';
					html += '	<td>' + item.join_type + '</td>';
					html += '	<td>' + item.tel + '</td>';
					html += '	<td>' + item.user_mail + '</td>';
					html += '	<td>' + item.frst_regist_dt + '</td>';
					html += '	<td>' + item.last_updt_dt + '</td>';
					html += '	<td>' + '<a href="/admin/userUpdate?user_id=' + item.user_id + '" class="ui gray button" id="userupdt_btn">수정</a>' + '</td>';
					html += '</tr>';

				$("#user_list_tbody").append(html);

			});

			pagination.drawPage(response.total_count);

		}else if(responseCode == "201"){
			var html = '';
			html += '<tr>';
			html += '	<td class="pdt1_1" colspan="7" style="text-align:center;">조회된 데이터가 없습니다.</td>';
			html += '</tr>';
			$("#user_list_tbody").append(html);

			pagination.drawPage(0);

		}else {
			var html = '';
			html += '<tr>';
			html += '	<td class="pdt1_1" colspan="8">' + responseMessage + '</td>';
			html += '</tr>';
			$("#user_list_tbody").append(html);

			pagination.drawPage(0);
		}
	}catch(e) {
		console.log(e);
		console.log(response);
	}
};

mainObj.deleteUser = function() {

	var chk = [];

	$('.chk:checked').each(function(){
        chk.push($(this).val());
        user_id = chk[0];
    });

	var _url = contextPath + '/api/v1/admin/userList/deleteUser';
	var _postData = {
		'user_id': user_id,
	};

	commonObj.ajaxCall(_url, _postData, mainObj.deleteUserCallback);
};

mainObj.deleteUserCallback = function(response, status, xhr) {

	try {
		responseCode = response.response_code;
		responseMessage = response.response_message;
		if (responseCode == "000") {
			alert("삭제가 완료되었습니다.");
			mainObj.getUserList();
		} else {
			alert(responseMessage);
		}
	} catch (e) {
		console.log(e);
		console.log(response);
	}
}


var pagination = new PageObject2();

function fn_go_page(page) {
	pagination.setCurrPage(page);
	mainObj.getUserList();
}