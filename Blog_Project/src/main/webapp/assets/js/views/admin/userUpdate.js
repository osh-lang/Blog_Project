var userUpdateObj = {};

userUpdateObj.initEvent = function() {

	var user_mail = $("#user_mail").val();
	var user_tel = $("#user_tel").val();

	// 유저 업데이트에서 수정 버튼 눌렀을 때
	$("#update_btn").on("click", function() {
		if($("#user_mail").val() !== user_mail && $("#user_tel").val() !== user_tel) {
			userUpdateObj.updateUser();
		} else if ($("#user_mail").val() !== user_mail) {
			userUpdateObj.updateUser();
		} else if ($("#user_tel").val() !== user_tel) {
			userUpdateObj.updateUser();
		} else {
			alert("수정사항이 없습니다.");
		}
	});

	// 유저 업데이트에서 취소 버튼 눌렀을 때
	$("#cancle_btn").on("click", function() {

		if (confirm("취소 하시겠습니까??") == true){

			location.href = contextPath + '/admin/userManage';
		}else{
			return ;
		}
	});

}

$(document).ready(function() {
	userUpdateObj.initEvent();
});

userUpdateObj.validateUserInfo = function(v) {
	v.user_nm = $('#user_nm').text();
	v.user_id = $('#user_id').text();
	v.user_pw = $('#user_pw').text();
	v.user_mail = $("#user_mail").val();
	v.user_tel = $("#user_tel").val();

	var regExpTel = /^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}$/;
	var regExpMail = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})$/;


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

	return true;
};

userUpdateObj.updateUser = function() {

	var _data = {};
	if(!userUpdateObj.validateUserInfo(_data)) return;

	var _url = contextPath + '/api/v1/admin/userManage/updateUser';
	var _postData = {
		'user_nm': _data.user_nm,
		'user_id': _data.user_id,
		'user_pw': _data.user_pw,
		'user_mail': _data.user_mail,
		'user_tel': _data.user_tel
	};

	commonObj.ajaxCall(_url, _postData, userUpdateObj.updateUserCallback);
};

userUpdateObj.updateUserCallback = function(response, status, xhr) {
	try {
		responseCode = response.response_code;
		responseMessage = response.response_message;
		if (responseCode == "000") {
			alert("수정이 완료되었습니다.");
			location.href = contextPath + '/admin/userManage';
		} else {
			alert(responseMessage);
		}
	} catch (e) {
		console.log(e);
		console.log(response);
	}
}
