var fnObj = {};

fnObj.initEvent = function() {
	$("#login_btn").on("click", function(){
		fnObj.userLogin();
	});

	$("#signin_btn").on("click", function(){
		location.href = contextPath + '/join/join';
	});

	$("#login_id").keyup(function(event) {
		if(event.keyCode == 13) {
			fnObj.userLogin();
		}
	});

	$("#login_password").keyup(function(event) {
		if(event.keyCode == 13) {
			fnObj.userLogin();
		}
	});
}

fnObj.userLogin = function(){
	var login_id = $('#login_id').val();
	var login_password = $('#login_password').val();

	if(login_id == '') {
		alert("아이디를 입력해 주십시요.");
		$('#login_id').focus();
		return;
	}

	if(login_password == '') {
		alert("비밀번호를 입력해 주십시요.");
		$('#login_password').focus();
		return;
	}

	var _url = contextPath + '/api/v1/login/userLogin';
	var _postData = {
		'login_id' : login_id,
		'login_password' : login_password
	};

	commonObj.ajaxCall(_url, _postData, fnObj.userLoginCallback);
};

fnObj.userLoginCallback = function(response, status, xhr) {
	try {
		responseCode = response.response_code;
		responseMessage = response.response_message;

		if(responseCode == "000") {
			alert("로그인 성공");
			location.href = contextPath + '/main';
		}else {
			alert(responseMessage);
		}
	}catch(e) {
		console.log(e);
		console.log(response);
	}
}

$(document).ready(function(){
	fnObj.initEvent();
});
