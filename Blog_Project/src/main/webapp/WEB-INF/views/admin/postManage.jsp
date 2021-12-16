<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="ui left aligned container">
	<h3>사용자 글 관리</h3>
	<a href="${pageContext.request.contextPath}/admin/main" class="ui blue button"><i class="home icon"></i>관리자 홈</a>
</div>

<div class="ui right aligned container" style="padding-bottom: 15px">
	<select class="ui compact selection dropdown" name="search_type" id="search_type" style="padding-top: 9px;padding-bottom: 9px;top: 1px;">
		<option value="all">전체</option>
		<option value="frst_register">작성자</option>
		<option value="post_sj">글 제목</option>
		<option value="post_cn">내용</option>
	</select>
	<div class="ui input">
		<input type="text" id="search_keyword" name="search_keyword" placeholder="Search" style="padding-top: 9px; padding-bottom: 9px;">
	</div>
	<div class="ui blue button" id="searchpost_btn">검색</div>
</div>

<div class="row" style="margin-bottom: 30px;">
	<div class="ui right aligned container">
		<h4 class="ui horizontal divider header">
			<i class="bar note icon"></i> 글 리스트
		</h4>
		<table class="ui celled table">

			<thead>
				<tr>
					<th><input type='checkbox' id='select_all'/></th>
					<th style="width: 20px;">no.</th>
					<th style="width: 270px;">제목</th>
					<th style="width: 98px;"></th>
				</tr>
			</thead>

			<tbody>
				<tbody id="post_list_tbody"> </tbody>
			</tbody>

			<tfoot>
				<tr>
					<th colspan="4">
						<div class="ui right floated pagination menu">
							<ul class="pagination"></ul>
						</div>
						<input type="button" class="ui gray button" id="button_${post_no}" name="button_${post_no}" value="삭제">
						<input type="hidden" id="last_updusr" value="${userMap.user_id}" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" readonly="readonly">
						<input type="hidden" id="deleter" value="${userMap.user_id}" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" readonly="readonly">
					</th>
				</tr>
			</tfoot>

		</table>
	</div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/pagination_main.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/views/admin/postManage.js"></script>