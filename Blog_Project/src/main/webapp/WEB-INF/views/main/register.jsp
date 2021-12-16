<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style type="text/css">

.ck-content { /* ckeditor 높이 */
    height: 20em;
}

body {
  background-color: #FFFFFF;
}
.main.container {
  margin-top: 2em;
}

.main.menu {
  margin-top: 4em;
  border-radius: 0;
  border: none;
  box-shadow: none;
  transition:
    box-shadow 0.5s ease,
    padding 0.5s ease
  ;
}
.main.menu .item img.logo {
  margin-right: 1.5em;
}

.overlay {
  float: left;
  margin: 0em 3em 1em 0em;
}
.overlay .menu {
  position: relative;
  left: 0;
  transition: left 0.5s ease;
}

.main.menu.fixed {
  background-color: #FFFFFF;
  border: 1px solid #DDD;
  box-shadow: 0px 3px 5px rgba(0, 0, 0, 0.2);
}
.overlay.fixed .menu {
  left: 800px;
}

.text.container .left.floated.image {
  margin: 2em 2em 2em -4em;
}
.text.container .right.floated.image {
  margin: 2em -4em 2em 2em;
}

.ui.footer.segment {
  margin: 5em 0em 0em;
  padding: 5em 0em;
}
</style>

<style>
* {
text-align: left;
}
#editor {
/* border : 1px solid; */
width : 100%;
margin : 0 auto;
}
</style>

<!-- TOAST UI Editor CDN URL(CSS)-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css"/>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/2.0.0/toastui-editor.min.css" />

<div class="ui borderless main menu">
  <div class="ui text container" style="max-width: 1050px;">
    <div class="header item">
      <img class="logo" src="${pageContext.request.contextPath}/assets/img/apple.jpg">
      Blog
    </div>
    <a href="${pageContext.request.contextPath}/main" class="item">Home</a>
  </div>
</div>

<div class="ui main text container" style="max-width: 1000px;">
  <h1 class="ui header">
	<input type="text" id="post_sj" maxlength="20" placeholder="제목을 입력하세요" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;">

  </h1>
    <hr>
</div>

<div class="ui text container" style="max-width: 1000px;">

  <input type="hidden" id="frst_register" value="${userMap.user_id}" style="border:none;border-right:0px; border-top:0px; boder-left:0px; boder-bottom:0px;" readonly="readonly">
  <select class="ui dropdown" id="tag_value"></select>

<!-- TOAST UI Editor가 들어갈 div태그 -->
<div id="editor"></div>
<!-- 에디터 내용을 받을 div태그-->
<div id="contents"></div>

  <!-- file 전송 -->
  <div class="filebox">
	<input name="files" id="fileupload" type="file" multiple />
	<div id="fileList"></div>
  </div>

  <button class="ui blue button" id="save_btn" type="button">작성</button>
  <button class="ui red button" id="cancel_btn" type="button">취소</button>
  <!-- file 전송 -->

</div>

<!-- TOAST UI Editor 생성 JavaScript 코드 -->
<script src="https://uicdn.toast.com/editor/2.0.0/toastui-editor-all.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/views/main/register.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/31.0.0/classic/ckeditor.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

<!-- <script>
ClassicEditor
.create(document.querySelector('#post_cn'))
.catch(error=>{
	console.error(error);
});

$(document)
  .ready(function() {
    // fix main menu to page on passing
    $('.main.menu').visibility({
      type: 'fixed'
    });
    $('.overlay').visibility({
      type: 'fixed',
      offset: 80
    });

    // lazy load images
    $('.image').visibility({
      type: 'image',
      transition: 'vertical flip in',
      duration: 500
    });

    // show dropdown on hover
    $('.main.menu  .ui.dropdown').dropdown({
      on: 'hover'
    });
  })
;
</script> -->