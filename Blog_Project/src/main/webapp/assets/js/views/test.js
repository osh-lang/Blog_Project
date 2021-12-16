var fnObj = {};

fnObj.initEvent = function() {
}

fnObj.selectMap = function(){
	
	var _url = contextPath + '/api/v1/common/selectMap';
	var _postData = {
		"id" : "1"
	};
	
	commonObj.ajaxCall(_url, _postData, function(response, status, xhr) {
		try {
			var responseCode = response.response_code;
			var responseMessage = response.response_message;
			if(responseCode == "000") {
				var data = response.data;
				alert(data);
			}else {
				alert(responseMessage);
			}
		}catch(e) {
			console.log(e);
			console.log(response);
		}
	});
};

$(document).ready(function(){
	fnObj.initEvent();
	//fnObj.selectMap();
});

