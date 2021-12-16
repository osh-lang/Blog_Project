package org.project.common.response;

public enum ResponseCode {
	SUCCESS("000", "정상"),
	PARAMETER_REQUIRED("101", "필수 파라미터가 입력되지 않았습니다."),
	INVALID_CODE("102", "정의되지 않은 코드입니다."),
	DUPLICATE_INSPECTION("103", "중복된 로그인 아이디입니다."),
	DATA_NOT_FOUND("201", "조회된 데이터가 없습니다."),
	WRONG_ID_OR_PASSWORD("301", "현재 입력하신 아이디가 등록되어 있지 않거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다."),
	UNAUTHORIZED_ID("302", "사용 승인되지 않은 아이디 입니다."),
	INVALID_ERROR("901", "에러가 발생했습니다. 시스템 관리자에게 문의해 주세요."),

	;

	private String code;
	private String message;

	ResponseCode(String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

}
