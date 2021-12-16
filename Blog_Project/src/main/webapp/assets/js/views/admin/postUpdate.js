var postUpdateObj = {};
var filesTempArr = [];
const editor = new toastui.Editor({
	el: document.querySelector('#editor'),
	previewStyle: 'vertical',
	initialEditType: "wysiwyg",
	height: '500px'
});

postUpdateObj.initEvent = function() {

	$("#fileupload").on("change", function(e){
		postUpdateObj.addFiles(e);
	});

	$("#upload_btn").on("click", function(){
		postUpdateObj.uploadFile();
	});

	$("#update_btn").on("click", function() {
		postUpdateObj.updatePost();
	});

	$("#cancel_btn").on("click", function() {
		location.href = contextPath + '/admin/postList';
	});
}

// 태그리스트 불러오기
postUpdateObj.getTagList = function(){
	var _url = contextPath + '/api/v1/admin/postList/getTagList';

	var _postData = {
	};

	commonObj.ajaxCall(_url, _postData, postUpdateObj.getTagListCallback, true);
}

postUpdateObj.getTagListCallback = function(response, status, xhr) {
	try {
		var responseCode = response.response_code;
		var responseMessage = response.response_message;

		$("#tag_value").empty();
		$("#tag_value").append('<option value="">Tag</option>');

		if(responseCode == "000") {
			var data = response.data;
			$.each(data, function(index, item) {
				var html = '';
					html += '<option value="'+ item.tag_no +'">'+ item.tag_nm +'</option>';
				$("#tag_value").append(html);
			});
		}

	}catch(e) {
		console.log(e);
		console.log(response);
	}
}

// 파일 추가
postUpdateObj.addFiles = function(e) {
    var files = e.target.files;
    var filesArr = Array.prototype.slice.call(files);
    var filesArrLen = filesArr.length;
    var filesTempArrLen = filesTempArr.length;

    for( var i=0; i<filesArrLen; i++ ) {
        filesTempArr.push(filesArr[i]);
        $("#fileList").append('<div>' + filesArr[i].name + '<img src="'+ contextPath +'/assets/img/minus.png" onclick="postUpdateObj.deleteFile(event, ' + (filesTempArrLen+i) + ');"></div>');
    }
//    $(this).val('');
}

// 파일 삭제
postUpdateObj.deleteFile = function(event, orderParam) {
    filesTempArr.splice(orderParam, 1);
    var html = "";
    var filesTempArrLen = filesTempArr.length;

    for(var i=0; i<filesTempArrLen; i++) {
        html += '<div>' + filesTempArr[i].name + '<img src="'+ contextPath +'/assets/img/minus.png" onclick="postUpdateObj.deleteFile(event, ' + i + ');"></div>'
    }

    $("#fileList").html(html);
    $("#fileupload").val('');
}

// 글 수정
postUpdateObj.updatePost = function() {
	var post_sj = $('#post_sj').val();
	var post_cn = editor.getHtml();
	var tag_value = $('#tag_value').val();
	var last_updusr = $('#last_updusr').val();

	if (tag_value == '') {
		alert("태그를 선택해 주세요");
		$('#tag_value').focus();
		return;
	}

	if (post_sj == '') {
		alert("제목을 입력해 주십시요.");
		$('#post_sj').focus();
		return;
	}

	if (post_cn == '') {
		alert("내용을 입력해 주십시요.");
		$('#post_cn').focus();
		return;
	}

	var _url = contextPath + '/api/v1/admin/postList/updatePost';
	var _postData = {
		'post_sj': post_sj,
		'post_cn': post_cn,
		'tag_value': tag_value,
		'last_updusr': last_updusr
	};
	var formData = commonObj.objectToForm(_postData);

	// 파일 데이터(없으면 그냥 지나감)
	for(var i=0, filesTempArrLen = filesTempArr.length; i<filesTempArrLen; i++) {
	   formData.append("files" + i , filesTempArr[i]);
	}

	commonObj.ajaxCallWithFormData(_url, formData, function(response, status, xhr){
		try {
			var responseCode = response.response_code;
			var responseMessage = response.response_message;
			if(responseCode == "000") {
				alert("등록 되었습니다.");
				location.href = contextPath + '/admin/postList';
			}else {
				alert(responseMessage);
			}
		}catch(e) {
			console.log(e);
			console.log(response);
			alert("에러가 발생했습니다. 관리자에게 문의해 주세요.");
		}
	});
}

$(document).ready(function() {
	postUpdateObj.initEvent();
	postUpdateObj.getTagList();
});