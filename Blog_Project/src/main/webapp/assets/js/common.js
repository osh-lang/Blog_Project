var commonObj = (function () {

	function ajaxCall(url, postData, callback, option) {
		var option = option;

		if (option === undefined){
			option = {}
		}
		if(option.async == null){
			option.async = true;
		}
		if(option.loading == null){
			option.loading = false;
		}

		$.ajax({
			type	: "POST",
			url		: url,
			async : option.async,
	 		data  : JSON.stringify(postData),
			processData : true,
			cache : true,
	 		contentType : "application/json",
			dataType	: "json",
			beforeSend  : function(){
				if(option.loading){
					document.getElementById('ajax_pageLoading').classList.remove('pageLoading_off');
				}
			},
			success	: function(response, status, xhr) {
				callback(response, status, xhr);
			},
			error	: function(request, status, error){
				console.log(error);
//				console.log("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
//				alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);

//				popup.alert("에러가 발생했습니다. 문제가 지속될 경우 관리자에게 문의해 주세요.");
	 		},
			complete : function(xhr, status){
				if(option.loading){
					document.getElementById('ajax_pageLoading').classList.add('pageLoading_off');
				}
			}
		});
	}

	function ajaxCallWithFormData(url, formData, callback) {
		$.ajax({
	        type		:"POST",
	        url			:url,
			data 		:formData,
			async		:true,
			processData	:false,
	        contentType	:false,
	        beforeSend  :function(){
//	        	popup.loading();
			},
	        success		: function(response, status, xhr) {
				callback(response, status, xhr);
			},
	        error:function(request,status,error){
//	        	popup.alert("에러가 발생했습니다. 문제가 지속될 경우 관리자에게 문의해 주세요.");
	        },
			complete:function(xhr, status){
//				popup.closeLoading();
			}
	    });
	}

	/**
	* 코드 콤보박스 or 체크박스 생성을 위한 함수
	* @param objType : 생성할 컨트롤의 종류 ('combo', 'radio')
	* @param makeObj : 생성할 object ID
	* @param cdGrp : 상위 코드값
	* @param firstItem : combo에 보여질 첫번째 값(선택, 전체, 등), null 로 보내면 첫번째 값이 없이 생성
	* @param selectItem : 생성 후 초기 선택되어지는 값
	* @example CodeUtil.setCodeList('combo', 'cmbCode2', '2', '001', '');
	*/
	function setCodeList(objType, makeObj, codeGroup, firstItem, selectItem) {
		if (isNull(objType)) {
			console.log("commonObj.setCodeList : objType is null");
	        return;
	    }

		if (isNull(makeObj)) {
			console.log("commonObj.setCodeList : makeObj is null");
	        return;
	    }

		if (isNull(codeGroup)) {
			console.log("commonObj.setCodeList : codeGroup is null");
	        return;
	    }

		var _url = contextPath + '/api/v1/common/getCodeList';

		var _postData = {
			"code_group" : codeGroup
		};

		var _option = {
				async : "false"
		}

		ajaxCall(_url, _postData, function(response, status, xhr) {
			try {
				var responseCode = response.response_code;
				var responseMessage = response.response_message;
				if(responseCode == "000") {
					var _param = {
						"makeObj" : makeObj,
						"firstItem" : firstItem,
						"selectItem" : selectItem
					}

					makeComboObj(_param, response.data);
				}else {
					alert(responseMessage);
				}
			}catch(e) {
				console.log(e);
				console.log(response);
				alert("에러가 발생했습니다. 관리자에게 문의해 주세요.");
			}
		}, _option);

	}

	function makeComboObj (param, data){
		$("#"+param.makeObj).empty();

		if(param.firstItem != null) {
	        $("#"+param.makeObj).append("<option value=''>"+param.firstItem+"</option>");
	    }

		for(var i=0; i < data.length; i++) {
	        $("#"+param.makeObj).append("<option value="+data[i].code_id+">"+data[i].code_nm+"</option>");
	    }

		if (param.selectItem != null && param.selectItem != '') {
	        $("#"+param.makeObj).val(param.selectItem);
	    }
	}


	function isNull(value){
		if (value == undefined) {
			return true;
		} else {
			if (typeof value == "boolean" && value == false) {
				return false;
			} else if (typeof value == "number" && value == 0) {
				return false;
			} else if (value == null || value == "") {
				return true;
			} else {
				return false;
			}
		}
	}


	function initDatePicker(obj, onSelectCallback) {
	    $("#" + obj).datepicker({
	        language : 'ko',
	        dateFormat : 'yyyy.mm.dd',
	        autoClose : true,
	        maxDate : new Date(),
	        onSelect : onSelectCallback
	    }).data('datepicker');
	}

	function initDatePicker2(obj, onSelectCallback) {
	    $("#" + obj).datepicker({
	        language : 'ko',
	        dateFormat : 'yyyy.mm.dd',
	        autoClose : true,
	        onSelect : onSelectCallback
	    }).data('datepicker');
	}

	function initDatePickerTime(obj, onSelectCallback) {
	    $("#" + obj).datepicker({
	        language : 'ko',
	        dateFormat : 'yyyy.mm.dd',
	        autoClose : true,
	        onSelect : onSelectCallback,
	        timepicker: true,
	        timeFormat : "hh:ii:00"
	    }).data('datepicker');
	}

	function setDateInputValue(obj, date) {
		$('#' + obj).datepicker().data('datepicker').selectDate(date);
	}

	function numberWithCommas(x) {
		return x.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	}

	function getFormatDate(date){
	    var year = date.getFullYear();
	    var month = (1 + date.getMonth());
	    month = month >= 10 ? month : '0' + month;
	    var day = date.getDate();
	    day = day >= 10 ? day : '0' + day;
	    return  year + '.' + month + '.' + day;
	}

	function submitForm(url, keyArray, dataArray, method){
		var form = document.createElement("form");
		form.setAttribute("method", method);
		form.setAttribute("action", url);

		for (var i=0; i < keyArray.length; i++) {
			var parm = document.createElement("input");
			parm.setAttribute("type", "hidden");
			parm.setAttribute("name", keyArray[i]);
			parm.setAttribute("value", dataArray[i]);
			form.appendChild(parm);
        }

		document.body.appendChild(form);
		form.submit();
	}

	function objectToForm(object) {
		var formData = new FormData();
		if(object != null) {
			for(var key in object) {
				formData.append(key, object[key]);
			}
		}
		return formData;
	}



	function fillZero(width, str){
		str += "";
	    return str.length >= width ? str:new Array(width-str.length+1).join('0')+str;
	}

	function getOs() {
		var currentOS;
		var mobile = (/iphone|ipad|ipod|android/i.test(navigator.userAgent.toLowerCase()));

		if(mobile) {
			// 유저에이전트를 불러와서 OS를 구분합니다.
			var userAgent = navigator.userAgent.toLowerCase();
			if (userAgent.search("android") > -1)
				currentOS = "android";
			else if ((userAgent.search("iphone") > -1) || (userAgent.search("ipod") > -1) || (userAgent.search("ipad") > -1))
				currentOS = "ios";
			else
				currentOS = "else";
		} else {
			// 모바일이 아닐 때
			currentOS = "nomobile";
		}

		return currentOS;
	}

	//현재 URL 자르기
	function getUrlSeg(number){
		var urlArray = (location.pathname).split("/");
		return urlArray[number];
	}

	//코드값 조회
	function getCmnCdNm(cmnCd){
		var _url = contextPath + '/api/v1/common/getCmnCdNm';
		var _postData = {
			"cmn_cd" : cmnCd
		};
		var _option = {
				"async" : false
		};

		var cmnNm = "";

		commonObj.ajaxCall(_url, _postData, function(response, status, xhr) {
			var responseCode = response.response_code;
			var data = response.data;
			if(responseCode == "000"){
		    	cmnNm = data.cd_nm;
			}else{
				cmnNm = "";
			}
	  	}, _option);

	  	return cmnNm;
	}

	//상위 코드로 코드 리스트 조회
	function getCmnCdList(code_group){
		var _url = contextPath + '/api/v1/common/getCodeList';
		var _postData = {
			"code_group" : code_group
		};
		var _option = {
				"async" : false
		};

		var codeList;

		commonObj.ajaxCall(_url, _postData, function(response, status, xhr) {
			var responseCode = response.response_code;
			var data = response.data;

			if(responseCode == "000"){
			    codeList = data;
			}else{
				codeList = [];
			}
	  	}, _option);

	  	return codeList;
	}

	//폼 데이터 JSON형식으로 return
	function formserializeToJson(id){
		var data = '';
		$.each( $("#" + id).serializeArray(), function(key, val){
			data += ',"'+val['name']+'":"'+val['value']+'"';
		});
		data = '{'+ data.substr(1) +'}';

		return  JSON.parse(data)
	}

	return {
		ajaxCall : ajaxCall,
		ajaxCallWithFormData : ajaxCallWithFormData,
		setCodeList : setCodeList,
		isNull : isNull,
		initDatePicker : initDatePicker,
		initDatePicker2 : initDatePicker2,
		initDatePickerTime : initDatePickerTime,
		setDateInputValue : setDateInputValue,
		numberWithCommas : numberWithCommas,
		getFormatDate : getFormatDate,
		submitForm : submitForm,
		objectToForm : objectToForm,
		fillZero : fillZero,
		getOs : getOs,
		getUrlSeg : getUrlSeg,
		getCmnCdNm : getCmnCdNm,
		getCmnCdList : getCmnCdList,
		formserializeToJson : formserializeToJson
	}
})();