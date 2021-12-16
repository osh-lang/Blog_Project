<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<div class="ui left aligned container">
	<h3>관리자 페이지</h3>
</div>


<div class="ui left aligned container" style="width: 1400px; margin-bottom: 50px; margin-top: 50px;">
	<div class="ui internally celled grid">
	    <div class="three wide column">
	    	<div class="ui center aligned container" style="margin-bottom: 35px;">
	    	<img class="ui medium circular image" src="/assets/img/apple.jpg">
	    	<h3>관리자 : ${userMap.user_id}</h3>
			<a href="${pageContext.request.contextPath}/main" class="ui blue button"><i class="home icon"></i>블로그로</a>
	    	</div>
	      	<ul style="padding-left: 0px;">
				<li class="ui list">
					<div class="item">
						<i class="folder icon"></i>
						<div class="content">
							<div class="header">콘텐츠</div>
							<div class="list">
								<div class="item">
									<div class="content">
										<div class="description">
											<a href="${pageContext.request.contextPath}/admin/postManage" class="ui blue button"><i class="file icon"></i>글 관리</a>
										</div>
									</div>
								</div>
								<div class="item">
									<div class="content">
										<div class="description">
											<a href="#" class="ui blue button"><i class="file icon"></i>카테고리 관리</a>
										</div>
									</div>
								</div>
								<div class="item">
									<div class="content">
										<div class="description">
											<a href="#" class="ui blue button"><i class="file icon"></i>태그 관리</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<i class="folder icon"></i>
						<div class="content">
							<div class="header">댓글 관리</div>
							<div class="list">
								<div class="item">
									<div class="content">
										<div class="description">
											<a href="#" class="ui blue button"><i class="file icon"></i>댓글 관리</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="item">
						<i class="folder icon"></i>
						<div class="content">
							<div class="header">사용자 관리</div>
							<div class="list">
								<div class="item">
									<div class="content">
										<div class="description">
											<a href="${pageContext.request.contextPath}/admin/userManage" class="ui blue button"><i class="file icon"></i>사용자 관리</a>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</li>
			</ul>
	    </div>
	    <div class="thirteen wide column">
	    </div>
	</div>
</div>
