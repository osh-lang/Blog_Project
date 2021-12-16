var fnObj = {};
const viewer = new toastui.Editor.factory({
  el: document.querySelector('#viewer'),
  height: '500px',
  viewer: true
});

var idx = $('#post_no').val();
var userid = $('#user_id').val();
var admin = $('#admin').val();

fnObj.initEvent = function() {
	$("#save_btn").on("click", function() {
		fnObj.userJoin();
	});

	$("#insert_cmt_btn").on("click", function() {
		fnObj.insertAnswer();
	});
}

// 댓글추가
fnObj.insertAnswer = function(comments) {

	var post_no = $('#post_no').val();
	var answer_register = $('#user_id').val();
	var answer = $('#comments').val();

	if (answer == '') {
		alert("내용을 입력해 주세요");
		$('#comments').focus();
		return;
	}

	var _url = contextPath + "/api/v1/post/insertAnswer";
	var _postData = {
		'answer_register': answer_register,
		'post_no': post_no,
		'answer': answer
	};

	commonObj.ajaxCall(_url, _postData, fnObj.insertAnswerCallback);
};

fnObj.insertAnswerCallback = function(response, status, xhr) {
	try {
		responseCode = response.response_code;
		responseMessage = response.response_message;
		if (responseCode == "000") {
			$("#comments").val('');
			fnObj.answerList();
			alert("댓글 등록이 완료되었습니다.");

		} else {
			alert(responseMessage);
		}
	} catch (e) {
		console.log(e);
		console.log(response);
	}
}

// 대댓글추가
fnObj.insertReplyData = function(answer_no) {

	var post_no = $('#post_no').val();
	var answer_register = $('#user_id').val();
	var answer = $('#comments'+ answer_no).val();

	if (answer == '') {
		alert("내용을 입력해 주세요");
		$('#comments'+ answer_no).focus();
		return;
	}

	var _url = contextPath + "/api/v1/post/insertAnswer";
	var _postData = {
		'answer_register': answer_register,
		'post_no': post_no,
		'answer': answer,
		'upper_answer_no' : answer_no,
	};

	commonObj.ajaxCall(_url, _postData, fnObj.insertReplyDataCallback);
};

fnObj.insertReplyDataCallback = function(response, status, xhr) {
	try {
		responseCode = response.response_code;
		responseMessage = response.response_message;
		if (responseCode == "000") {
			fnObj.answerList();
			$("#reply"+ answer_no).empty();
			alert("댓글 등록이 완료되었습니다.");
		} else {
			alert(responseMessage);
		}
	} catch (e) {
		console.log(e);
		console.log(response);
	}
}

// 좋아요 추가
fnObj.insertLike = function(answer_no) {
	// 테이블안에 user_id가 있으면 Y, 없으면 N을 받은 다음에 리스트로 보냄
	var user_id = $('#user_id').val();

	var _url = contextPath + "/api/v1/post/insertLike";
	var _postData = {
		'answer_no': answer_no,
		'user_id': user_id
	};

	commonObj.ajaxCall(_url, _postData, fnObj.insertLikeCallback);
};

fnObj.insertLikeCallback = function(response, status, xhr) {
	try {
		responseCode = response.response_code;
		responseMessage = response.response_message;
		if (responseCode == "000") {
			fnObj.answerList();
		} else {
			alert(responseMessage);
		}
	} catch (e) {
		console.log(e);
		console.log(response);
	}
}

// 좋아요 삭제
fnObj.deleteLike = function(answer_no) {
	// 테이블안에 user_id가 있으면 Y, 없으면 N을 받은 다음에 리스트로 보냄
	var user_id = $('#user_id').val();

	var _url = contextPath + "/api/v1/post/deleteLike";
	var _postData = {
		'answer_no': answer_no,
		'user_id': user_id
	};

	commonObj.ajaxCall(_url, _postData, fnObj.deleteLikeCallback);
};

fnObj.deleteLikeCallback = function(response, status, xhr) {
	try {
		responseCode = response.response_code;
		responseMessage = response.response_message;
		if (responseCode == "000") {
			fnObj.answerList();
		} else {
			alert(responseMessage);
		}
	} catch (e) {
		console.log(e);
		console.log(response);
	}
}

// 댓글 리스트
fnObj.answerList = function() {

	var post_no = $('#post_no').val();
	var user_id = $('#user_id').val();
	var _url = contextPath + "/api/v1/post/answerList";
	var _postData = {
		'post_no': post_no,
		'user_id': user_id
	};

	commonObj.ajaxCall(_url, _postData, fnObj.answerListCallback);
};

fnObj.answerListCallback = function(response, status, xhr) {
	try {
		responseCode = response.response_code;
		responseMessage = response.response_message;
		var responseTotal_count = response.total_count;
		if(responseTotal_count == null){
			responseTotal_count = 0;
		}
		$("#answer_tbody").empty();
		$("#answer_tbody").append('<h3 class="ui dividing header" >Comments '+ responseTotal_count +'</h3>');

		if(responseCode == "000") {
			var data = response.data;
			$.each(data, function(index, item){
				var html = '';
				var style = '';
				if(item.level>1){ // 4층 이상은 300px고정
				    style = 'style="padding-left: '+ 100 +'px"';
				} else {
//					style = 'style="padding-left: '+ (item.level-1)*100 +'px"';
				}

					html += '<div class="ui comments" '+ style +'>';
					html += '<div class="comment">';
					html += '	<div class="avatar"><img src="https://react.semantic-ui.com/images/avatar/small/elliot.jpg"/></div>';
					html += '	<div class="content">';
					html += '	  <a class="author">'+ item.answer_register +'</a>';
					html += '	  <div class="metadata">';
					html += '	    <div>'+ item.answer_dt +'</div>';
					html += '	  </div>';
					html += '	  <div class="text">';
					html += '	    <p>'+ item.answer +'</p>';
					html += '	  </div>';
					html += '	  <div class="actions">';

					if(item.level === 1){
						html += '	    <a class="" onclick="fnObj.addReply('+ item.answer_no +')">Reply</a>';
					} else {
//						html += '	    <a class="" id="reply_btn'+ item.answer_no +'" onclick="fnObj.addReply('+ item.answer_no +')">Reply</a>';
					}

					if(item.user_like_at === 'Y'){
						html += '	    <a onclick="fnObj.deleteLike(' + item.answer_no + ')"><i class="heart icon"></i></a>';
					} else {
						html += '	    <a onclick="fnObj.insertLike(' + item.answer_no + ')"><i class="heart outline icon"></i></a>';
					}
					html += '	    <a class="">'+ item.answer_like+'</a>';
					html += '	  </div>';
					// 댓글창
					html += '	  <div id="reply'+ item.answer_no +'"></div>';
					html += '	  </div>';
				    html += '</div>';
				$("#answer_tbody").append(html);
			});

		}else if(responseCode == "201"){
			var html = '';
			html += '<tr>';
			html += '	<td colspan="7" style="text-align:center;">댓글이 없습니다.</td>';
			html += '</tr>';
			$("#answer_tbody").append(html);

		}else {
			var html = '';
			html += '<tr>';
			html += '	<td colspan="7">' + responseMessage + '</td>';
			html += '</tr>';
			$("#answer_tbody").append(html);
		}
	}catch(e) {
		console.log(e);
		console.log(response);
	}

}

fnObj.insertReply = function(answer_no) {
	fnObj.insertReplyData(answer_no);
}

fnObj.cancelReply = function(answer_no) {
	$("#reply"+ answer_no).empty();
	fnObj.answerList();
}

fnObj.addReply = function(answer_no) {
	$("[id^=reply]").empty();

	var replyHtml  = '';
		replyHtml += '    <form class="ui reply form">';
		replyHtml += '      <div class="field">';
		replyHtml += '        <textarea id="comments'+ answer_no +'"></textarea>';
		replyHtml += '      </div>';
		replyHtml += '      <div class="ui blue button" onclick="fnObj.insertReply(' + answer_no + ')">';
		replyHtml += '        댓글등록';
		replyHtml += '      </div>';
		replyHtml += '      <div class="ui red button" onclick="fnObj.cancelReply(' + answer_no + ')">';
		replyHtml += '        취소';
		replyHtml += '      </div>';
		replyHtml += '    </form>';

	$("#reply"+ answer_no).append(replyHtml);
}

$(document).ready(function() {
	fnObj.initEvent();
	fnObj.answerList();
	viewer.setMarkdown(document.getElementById('viewer'));
});
