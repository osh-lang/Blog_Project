<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html lang="ko">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
	<title>admin</title>
	<link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/fomantic-ui/2.8.7/components/icon.min.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/fomantic-ui@2.8.7/dist/semantic.min.css">
	<link href="${pageContext.request.contextPath}/assets/css/style.css" rel="stylesheet" type="text/css" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    <script src="${pageContext.request.contextPath}/assets/js/jquery.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/common.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/semantic.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/semantic.js"></script>

	<script>
		var contextPath = "${pageContext.request.contextPath}";
	</script>
</head>

<body>
	<tiles:insertAttribute name="header" />

	<tiles:insertAttribute name="sidebar" />

	<tiles:insertAttribute name="content" />

	<tiles:insertAttribute name="footer" />

	<tiles:insertAttribute name="popup" />

</body>
</html>