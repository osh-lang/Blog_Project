var joinObj = {};
var sns_id = $('#sns_id').val();
var _url = '';

joinObj.initEvent = function() {

	$("#save_btn").on("click", function() {
		if(sns_id == ''){
			_url = contextPath + '/api/v1/join/userJoin'
		} else {
			_url = contextPath + '/api/v1/snsJoin/userJoin'
		}
		joinObj.userJoin();
	});
}

joinObj.validateUserInfo = function(v) {
	v.user_id = $("#user_id").val();
	v.user_pw = $("#user_pw").val();
	v.user_nm = $("#user_nm").val();
	v.user_mail = $("#user_mail").val();
	v.user_tel = $("#user_tel").val();
	v.join_type = $("#join_type").val();
	v.sns_id = $('#sns_id').val();

	var regExpId = /^[a-zA-Z0-9]{4,12}$/;
	var regExpName = /^[가-힣]{2,4}$/;
	var regExpPW = /^(?=.*[a-zA-z])(?=.*[0-9])(?=.*[$`~!@$!%*#^?&\\(\\)\-_=+])(?!.*[^a-zA-z0-9$`~!@$!%*#^?&\\(\\)\-_=+]).{8,12}$/;
	var regExpTel = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
	var regExpMail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})$/;

	if (regExpId.test(v.user_id) === false) {
		alert("아이디 형식이 올바르지 않습니다.");
		$("#user_id").focus();
		return false;
	}

	if(v.user_id == null || v.user_id === "") {
		alert("아이디를 입력해 주세요.");
		$("#user_id").focus();
		return false;
	}

	if (regExpName.test(v.user_nm) === false) {
		alert("이름 형식이 올바르지 않습니다.");
		$("#user_nm").focus();
		return false;
	}

	if(v.user_nm == null || v.user_nm === "") {
		alert("이름을 입력해 주세요.");
		$("#user_nm").focus();
		return false;
	}

	if (regExpPW.test(v.user_pw) === false) {
		alert("비밀번호 형식이 올바르지 않습니다.");
		$("#user_pw").focus();
		return false;
	}

	if (v.user_pw == null || v.user_pw === '') {
		alert("비밀번호를 입력해주세요.");
		return false;
	}

	if (regExpMail.test(v.user_mail) === false) {
		alert("메일 형식이 올바르지 않습니다.");
		$("#user_mail").focus();
		return false;
	}

	if(v.user_mail == null || v.user_mail === "") {
		alert("메일을 입력해 주세요.");
		$("#user_mail").focus();
		return false;
	}

	if (regExpTel.test(v.user_tel) === false) {
		alert("전화번호 형식이 올바르지 않습니다.");
		$("#user_tel").focus();
		return false;
	}

	if(v.user_tel == null || v.user_tel === "") {
		alert("전화번호를 입력해 주세요.");
		$("#user_tel").focus();
		return false;
	}

	if(v.join_type == null || v.join_type === "") {
		v.join_type = "normal";
		return true;
	}

	return true;
};

// 회원가입
joinObj.userJoin = function() {

	var _data = {};
	if(!joinObj.validateUserInfo(_data)) return;

	var _postData = {
		'user_id': _data.user_id,
		'user_pw': _data.user_pw,
		'user_mail': _data.user_mail,
		'user_nm': _data.user_nm,
		'user_tel': _data.user_tel,
		'join_type' : _data.join_type,
		'sns_id' : _data.sns_id
	};

	commonObj.ajaxCall(_url, _postData, joinObj.userJoinCallback);
};

joinObj.userJoinCallback = function(response, status, xhr) {
	try {
		responseCode = response.response_code;
		responseMessage = response.response_message;
		if (responseCode == "000") {
			alert("회원가입이 완료되었습니다.");
			location.href = contextPath + '/main';
		} else {
			alert(responseMessage);
		}
	} catch (e) {
		console.log(e);
		console.log(response);
	}
}

$(document).ready(function() {
	joinObj.initEvent();
});