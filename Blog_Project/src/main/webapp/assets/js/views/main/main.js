var fnObj = {};
var tag_no = 0;

fnObj.initEvent = function() {
	$("#logout_btn").on("click", function() {
		location.href=contextPath + "/login/userLogout";
	});
}

$(document).ready(function(){

	fnObj.initEvent();
	fnObj.getPostList();
	fnObj.getTagList();

	pagination.init($(".pagination"), 1, 10);

});

// 페이징리스트 불러오기
fnObj.getPostList = function(){
	var _url = contextPath + '/api/v1/post/getPostList';
	var _postData = {
		"search_page" : pagination.getCurrPage(),
		"search_count" : 10,
		"tag_no" : tag_no
	};

	commonObj.ajaxCall(_url, _postData, fnObj.getPostListCallback, true);
}

fnObj.getPostListCallback = function(response, status, xhr) {
	try {
		var responseCode = response.response_code;
		var responseMessage = response.response_message;

		$("#post_tbody").empty();


		if(responseCode == "000") {
			var search_count = response.search_count;
			var search_page = response.search_page;
			var data = response.data;


			$.each(data, function(index, item) {
				var num = (search_page-1)*10 + index + 1;

				var html = '';

					html += '<h3 class="ui header">';
					html += '	<a href="' + contextPath +'/post/' + item.post_no + '">' + item.post_sj + '</a></h3>';
					html += '		<p class="text max">' + item.post_cn + '</p>';
					html += '		<p>' + item.frst_regist_dt + '&nbsp;작성자 : ' + item.frst_register + '</p><hr>';

				$("#post_tbody").append(html);
			});

			pagination.drawPage(response.total_count);

		}else if(responseCode == "201"){
			var html = '';
			html += '<tr>';
			html += '	<td colspan="7" style="text-align:center;">조회된 데이터가 없습니다.</td>';
			html += '</tr>';
			$("#post_tbody").append(html);

			pagination.drawPage(0);

		}else {
			var html = '';
			html += '<tr>';
			html += '	<td colspan="7">' + responseMessage + '</td>';
			html += '</tr>';
			$("#post_tbody").append(html);

			pagination.drawPage(0);
		}

	}catch(e) {
		console.log(e);
		console.log(response);
	}
}

// 태그리스트 불러오기
fnObj.getTagList = function(){
	var _url = contextPath + '/api/v1/post/getTagList';

	var _postData = {
	};

	commonObj.ajaxCall(_url, _postData, fnObj.getTagListCallback, true);
}

fnObj.getTagListCallback = function(response, status, xhr) {
	try {
		var responseCode = response.response_code;
		var responseMessage = response.response_message;

		$("#tag_tbody").empty();
		$("#tag_tbody").append('<table>');

		if(responseCode == "000") {
			var data = response.data;

			$.each(data, function(index, item) {
				var html = '';
					html += '<tr>';
					html += '  <td>';
					html += '	<button type="button" class="ui button ' + (item.tag_no==tag_no?"active":"") + '" onclick="fn_go_page(1,' + item.tag_no + ')">' + item.tag_nm + '(' + item.tag_count + ')</button>';
					html += '  </td>';
					html += '</tr>';
				$("#tag_tbody").append(html);

			});

		}else if(responseCode == "201"){
			var html = '';
			html += '<tr>';
			html += '	<td colspan="7" style="text-align:center;">조회된 데이터가 없습니다.</td>';
			html += '</tr>';
			$("#tag_tbody").append(html);

		}else {
			var html = '';
			html += '<tr>';
			html += '	<td colspan="7">' + responseMessage + '</td>';
			html += '</tr>';
			$("#tag_tbody").append(html);
		}

		$("#tag_tbody").append('</table>');

	}catch(e) {
		console.log(e);
		console.log(response);
	}
}


var pagination = new PageObject();

function fn_go_page(page, tag_no) {

	if(tag_no > 0) {
		this.tag_no = tag_no;
	}

	pagination.setCurrPage(page);
	fnObj.getPostList();
	fnObj.getTagList();
}
