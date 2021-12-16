<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- TOAST UI Editor CDN URL(CSS)-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css"/>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/2.0.0/toastui-editor.min.css" />

<div class="ui left aligned container">
	<h3>글 수정</h3>
</div>

<div class="ui right aligned container">
	<h4 class="ui horizontal divider header">
	  <i class="bar note icon"></i>
	  글 정보
	</h4>

	<div class="ui main text container">
	  <h1 class="ui header">
		<input type="text" id="post_sj" maxlength="20" value="${viewPost.post_sj}" placeholder="제목을 입력하세요" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;margin-right: 400px;">
	  </h1>
	  <hr>
	</div>

	<div class="ui text container">
		<input type="hidden" id="frst_register" value="${userMap.user_id}" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" readonly="readonly">
		<input type="hidden" id="last_updusr" value="${userMap.user_id}" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" readonly="readonly">
		<select class="ui dropdown" id="tag_value" style=" margin-bottom: 10px; padding-bottom: 6px; padding-top: 6px; padding-right: 6px; padding-left: 6px;"></select>

		<!-- TOAST UI Editor가 들어갈 div태그 -->
		<div id="editor">${viewPost.post_cn}</div>
		<!-- 에디터 내용을 받을 div태그-->
		<div id="contents"></div>

		<!-- file 전송 -->
		<div class="filebox">
		<input name="files" id="fileupload" type="file" multiple />
		<div id="fileList"></div>
		</div>

		<button class="ui blue button" id="update_btn" type="button">수정</button>
		<button class="ui red button" id="cancel_btn" type="button">취소</button>
		<!-- file 전송 -->
	</div>

</div>

<!-- TOAST UI Editor 생성 JavaScript 코드 -->
<script src="https://uicdn.toast.com/editor/2.0.0/toastui-editor-all.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/views/admin/postUpdate.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/31.0.0/classic/ckeditor.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
