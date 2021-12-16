<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="ui left aligned container">
	<h3>사용자 수정</h3>
</div>

<div class="ui right aligned container" style="margin-bottom: 15px;">
<h4 class="ui horizontal divider header">
  <i class="bar note icon"></i>
  회원 정보
</h4>
<table class="ui definition table">
  <tbody>
    <tr>
      <td class="two wide column">이름</td>
      <td><p id="user_nm">${viewUser.user_nm}</p></td>
    </tr>
    <tr>
      <td>아이디</td>
      <td><p id="user_id">${viewUser.user_id}</p></td>
    </tr>
    <tr>
      <td>비밀번호</td>
	  <td><p id="user_pw">${viewUser.user_pw}</p></td>
    </tr>
    <tr>
      <td>이메일</td>
      <td><div class="ui input"><input type="text" class="input_change" style="width: 280px;" id="user_mail" value="${viewUser.user_mail}" placeholder="이메일을 입력해 주세요."/> ex) mysite@ourearth.com, my.ownsite@ourearth.org, mysite@you.me.net</div></td>
    </tr>
    <tr>
      <td>전화번호</td>
      <td><div class="ui input"><input type="tel" class="input_change" style="width: 280px;" id="user_tel" value="${viewUser.tel}" placeholder="전화번호를 입력해 주세요."/> ex) 02-000-0000, 053-000-0000, 010-0000-0000</div></td>
    </tr>
  </tbody>
</table>
<div class="ui blue button" id="update_btn">수정</div>
<div class="ui red button" id="cancle_btn">취소</div>
</div>


<script src="${pageContext.request.contextPath}/assets/js/views/admin/userUpdate.js"></script>