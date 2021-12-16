<%@page import="java.math.BigInteger"%>
<%@page import="java.security.SecureRandom"%>
<%@page import="java.net.URLEncoder"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="ui center aligned container">

	<h3 class="ui header" id="login_text">로그인</h3>

	<div class="ui input">
		<input type="text" id="login_id" placeholder="아이디를 입력해 주세요" maxlength="12"/>
	</div>

	<div class="ui input">
		<input type="password" id="login_password" placeholder="비밀번호를 입력해 주세요" style="width: 296px;" maxlength="12"/>
	</div>

	<div class="ui center aligned">
	<button class="ui gray button " id="login_btn">로그인</button>
	</div>

	<div class="ui center aligned">
	<a href="http://localhost:8181/auth/NAVER"><img src="/assets//img/login/naver_login.png" alt="Naver Login" id="naverlogin_btn"></a>
	</div>

	<div class="ui center aligned">
	<a href="http://localhost:8181/auth/KAKAO"><img src="/assets//img/login/kakao_login.png" alt="Kakao Login" id="kakaologin_btn"></a>
	</div>

	<div class="ui center aligned">
	<a href="http://localhost:8181/auth/GOOGLE"><img src="/assets//img/login/google_login.png" alt="Google Login" id="googlelogin_btn"></a>
	</div>

	<div class="ui center aligned">
	<a href="http://localhost:8181/auth/GITHUB"><img src="/assets//img/login/github_login.png" alt="Github Login" id="gitlogin_btn"></a>
	</div>

	<div class="ui center aligned">
	<button class="ui gray button" id="signin_btn">회원가입</button>
	</div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/views/login/login.js"></script>
