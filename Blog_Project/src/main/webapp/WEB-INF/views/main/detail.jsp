<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<link rel="stylesheet" href="https://uicdn.toast.com/editor/latest/toastui-editor-viewer.min.css"/>
<!-- TOAST UI Editor CDN URL(CSS)-->
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/codemirror/5.48.4/codemirror.min.css"/>
<link rel="stylesheet" href="https://uicdn.toast.com/editor/2.0.0/toastui-editor.min.css"/>

<style type="text/css">

* {
	text-align: left;

}
#viewer {
/* border : 1px solid; */
  width : 100%;
  margin : 0 auto;
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

<div class="ui borderless main menu" >
  <div class="ui text container" style="max-width: 1050px;">
    <div class="header item">
      <img class="logo" src="${pageContext.request.contextPath}/assets/img/apple.jpg">
      Blog
    </div>
    <a href="${pageContext.request.contextPath}/main" class="item">Home</a>
  </div>
</div>

<div class="ui main text container" style="max-width: 1000px;">
  <h1 class="ui header">${postDetail.post_sj}</h1>
    <hr>
</div>



<div class="ui text container" style="max-width: 1000px;">
  <span>${postDetail.frst_regist_dt}</span>
  <span>${postDetail.frst_register}</span>
<!--   <a href="#">#태그</a>
  <a href="#">#태그2</a> -->
<!--   <i class="heart icon"></i>
  <i class="heart outline icon"></i> -->
</div>

<div class="ui text container" style="max-width: 1000px;">
  <div id="viewer">
    ${postDetail.post_cn}
  </div>
  <hr>
</div>

<input type="hidden" id="post_no" value="${postDetail.post_no}">
<input type="hidden" id="user_id" value="${userMap.user_id}">
<input type="hidden" id="admin" value="${userMap.admin_at}">

<div class="ui text container" style="max-width: 1000px;">
<form class="ui form">
  <textarea placeholder="내용을 입력하세요" rows="3" id="comments"
  style="resize:none; margin: 0px 1% 1% 0px; height: 100%; width: 100%;"></textarea>

  <div class="ui blue button" id="insert_cmt_btn" style="float: right;">댓글등록</div>
</form><br><br>

<div style="float: right;" class="dropdown">
  <button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
    정렬기준
  </button>
  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
    <a class="dropdown-item" href="#">인기 댓글순</a>
    <a class="dropdown-item" href="#">최근 날짜순</a>

  </div>
</div>


<div class="ui comments" style="max-width: 970px;">
  <div id="answer_tbody"></div>
</div>
</div>

<script src="https://uicdn.toast.com/editor/2.0.0/toastui-editor-all.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/views/main/detail.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
<script>
   $(document).ready(function() {

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
  });
</script>
