<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="ui left aligned container">
 	<div class="ui header">
		<h3>회원가입</h3>
	</div>

		<input type="hidden" id="sns_id" value="${snsUserMap.id}" readonly="readonly"/>
		<input type="hidden" id="join_type" value="${snsUserMap.join_type}" readonly="readonly"/>

		<div>
			<p>아이디</p>
		</div>
		<div class="ui input">
			<input type="text" class="username_input" id="user_id" placeholder="아이디를 입력해 주세요" maxlength='12'/>4~12자의 영문 대소문자와 숫자로 입력
		</div>

		<div>
			<p>비밀번호</p>
		</div>
		<div class="ui input">
			<input type="password" id="user_pw" placeholder="비밀번호를 입력해 주세요"/>ex) 숫자, 영문, 특수문자 각 1자리 이상 사용하여 8자 이상 16자 이하
		</div>

		<div>
			<p>메일</p>
		</div>
		<div class="ui input">
			<input type="text" id="user_mail" value="${snsUserMap.email}" placeholder="이메일을 입력해 주세요" maxlength='16'/>ex) mysite@ourearth.com, my.ownsite@ourearth.org, mysite@you.me.net
		</div>

		<div>
			<p>이름</p>
		</div>
		<div class="ui input">
			<input type="text" id="user_nm" value="${snsUserMap.name}" placeholder="이름을 입력해 주세요" maxlength='4'/>ex)한글 2글자 이상 4글자 이하
		</div>

		<div>
			<p>전화번호</p>
		</div>
		<div class="ui input">
			<input type="tel" id="user_tel" placeholder="전화번호를 입력해 주세요" maxlength='13'/>ex) 02-000-0000, 053-000-0000, 010-0000-0000
		</div>

	<div>
	<button class="ui primary button" id="save_btn">제출</button>
	</div>

</div>

<script src="${pageContext.request.contextPath}/assets/js/views/join/join.js"></script>
