<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="ui left aligned container">
	<h3>사용자 관리</h3>
	<a href="${pageContext.request.contextPath}/admin/main" class="ui blue button"><i class="home icon"></i>관리자 홈</a>
</div>

<div class="ui right aligned container" style="padding-bottom: 15px">
	<select class="ui compact selection dropdown" name="search_type" id="search_type" style="padding-top: 9px;padding-bottom: 9px;top: 1px;">
		<option value="all">전체</option>
		<option value="user_id">아이디</option>
		<option value="user_nm">이름</option>
		<option value="user_mail">이메일</option>
	</select>
	<div class="ui input">
		<input type="text" id="search_keyword" name="search_keyword" placeholder="Search" style="padding-top: 9px; padding-bottom: 9px;">
	</div>
	<div class="ui blue button" id="searchuser_btn">검색</div>


</div>

<div class="row" style="margin-bottom: 30px;">
	<div class="ui right aligned container">
		<h4 class="ui horizontal divider header">
			<i class="bar note icon"></i> 회원 리스트
		</h4>

		<div id="order_type">
			<button class="ui green basic left attached button" id="frst_regist_btn" value="frst_regist">가입 날짜순</button>
			<button class="ui blue basic right attached button" id="last_update_btn" value="last_update">수정 날짜순</button>
		</div>


		<table class="ui celled table">

			<thead>
				<tr>
					<th><input type='checkbox' id='select_all'/></th>
					<th>아이디</th>
					<th>이름</th>
					<th>가입구분</th>
					<th>전화번호</th>
					<th>이메일</th>
					<th>가입날짜</th>
					<th>최종수정날짜</th>
					<th></th>
				</tr>
			</thead>

			<tbody id="user_list_tbody"> </tbody>

			<tfoot>
				<tr>
					<th colspan="9">
						<div class="ui right floated pagination menu">
							<ul class="pagination"></ul>
						</div>
						<input type="button" class="ui gray button" id="button_${user_id}" name="button_${user_id}" value="삭제">
					</th>
				</tr>
			</tfoot>

		</table>
	</div>
</div>


<script src="${pageContext.request.contextPath}/assets/js/pagination_main.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/views/admin/userManage.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>