<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<style type="text/css">

  .text.max{
    width        : 20em;     /* 너비는 변경될수 있습니다. */
    text-overflow: ellipsis;  /* 위에 설정한 20em 보다 길면 말줄임표처럼 표시합니다. */
    white-space  : nowrap;    /* 줄바꿈을 하지 않습니다. */
    overflow     : hidden;    /* 내용이 길면 감춤니다 */
    display      : block;     /* ie6이상 현재요소를 블럭처리합니다. */
  }

  .hidden.menu {
    display: none;
  }

  .masthead.segment {
    min-height: 500px;
    padding: 1em 0em;
  }
  .masthead .logo.item img {
    margin-right: 1em;
  }
  .masthead .ui.menu .ui.button {
    margin-left: 0.5em;
  }
  .masthead h1.ui.header {
    margin-top: 3em;
    margin-bottom: 0em;
    font-size: 4em;
    font-weight: normal;
  }
  .masthead h2 {
    font-size: 1.7em;
    font-weight: normal;
  }

  .ui.vertical.stripe {
    padding: 8em 0em;
  }
  .ui.vertical.stripe h3 {
    font-size: 2em;
  }
  .ui.vertical.stripe .button + h3,
  .ui.vertical.stripe p + h3 {
    margin-top: 3em;
  }
  .ui.vertical.stripe .floated.image {
    clear: both;
  }
  .ui.vertical.stripe p {
    font-size: 1.33em;
  }
  .ui.vertical.stripe .horizontal.divider {
    margin: 3em 0em;
  }

  .quote.stripe.segment {
    padding: 0em;
  }
  .quote.stripe.segment .grid .column {
    padding-top: 5em;
    padding-bottom: 5em;
  }

  .footer.segment {
    padding: 5em 0em;
  }

  .secondary.pointing.menu .toc.item {
    display: none;
  }

  @media only screen and (max-width: 700px) {
    .ui.fixed.menu {
      display: none !important;
    }
    .secondary.pointing.menu .item,
    .secondary.pointing.menu .menu {
      display: none;
    }
    .secondary.pointing.menu .toc.item {
      display: block;
    }
    .masthead.segment {
      min-height: 350px;
    }
    .masthead h1.ui.header {
      font-size: 2em;
      margin-top: 1.5em;
    }
    .masthead h2 {
      margin-top: 0.5em;
      font-size: 1.5em;
    }
  }

</style>

<!-- Following Menu -->
<div class="ui large top fixed hidden menu">
  <div class="ui container">
    <a class="active item">Home</a>
    <div class="right menu">
      <div class="item">
        <a class="ui button" href="${pageContext.request.contextPath}/admin/main">관리자 페이지</a>
      </div>
      <div class="item">
        <a class="ui primary button">${userMap.user_id}</a>
      </div>
      <div class="item">
        <a class="ui red button" id="logout_btn">로그아웃</a>
      </div>
    </div>
  </div>
</div>

<!-- Sidebar Menu -->
<div class="ui vertical inverted sidebar menu">
  <a class="active item">Home</a>
</div>

<!-- Page Contents -->
<div class="pusher">
  <div class="ui inverted vertical masthead center aligned segment">

    <div class="ui container">
      <div class="ui large secondary inverted pointing menu">
        <a class="toc item">
          <i class="sidebar icon"></i>
        </a>
        <a class="active item">Home</a>
        <div class="right item">
          <a class="ui inverted button" id="admin_page" href="${pageContext.request.contextPath}/admin/main">관리자 페이지</a>
          <a class="ui inverted button">${userMap.user_id}</a>
          <a class="ui red button" id="logout_btn">로그아웃</a>
        </div>
      </div>
    </div>

    <div class="ui text container">
      <h1 class="ui inverted header">
       Blog
      </h1>
    </div>
  </div>

  <div class="ui vertical stripe segment">
    <div class="ui middle aligned stackable grid container">
        <!-- 카테고리 -->
      <div class="row">
        <a href="#">#카테</a>
        <a href="#">#고리</a>
        <a href="#">#카테</a>
        <a href="#">#고리</a>
      </div>

      <div class="row">
        <div class="eight wide column">

          <div id="post_tbody"></div>

          <!-- paging -->
		  <div class="pull-right">
		    <nav aria-label="Page navigation example">
		      <ul class="pagination"></ul>
		    </nav>
		  </div>
		  <!-- paging -->

        </div>
        <!-- <div class="six wide right floated column"> -->
        <div class="six wide right" style="padding-left: 10em">
          <h4 class="ui header" style="color: green;">Tag</h4>
          <!-- Tag -->
          <div id="tag_tbody"></div>
          <!-- Tag -->

          <div><br>
          <h4 class="ui header" style="color: green;">Notice</h4>
		     <table>
		     	<tr>
		     		<td><a href="#">공지사항</a></td>
		     	</tr>
		     </table>
		  </div><br>
		  <div>
		     <h4 class="ui header" style="color: green;">write</h4>
		     <table>
		     	<tr>
		     		<td><a href="${pageContext.request.contextPath}/post/register">글쓰기</a></td>
		     	</tr>
		     </table>
		   </div>
        </div>
      </div>
    </div>
  </div>
  </div>

<script src="${pageContext.request.contextPath}/assets/js/pagination_main.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/views/main/main.js"></script>
<script>
$(document)
  .ready(function() {

    // fix menu when passed
    $('.masthead')
      .visibility({
        once: false,
        onBottomPassed: function() {
          $('.fixed.menu').transition('fade in');
        },
        onBottomPassedReverse: function() {
          $('.fixed.menu').transition('fade out');
        }
      })
    ;

    // create sidebar and attach to menu open
    $('.ui.sidebar')
      .sidebar('attach events', '.toc.item')
    ;

  })
;
</script>