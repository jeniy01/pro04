<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>다온 쇼핑몰</title>
	<!-- 헤드 부분 인클루드 -->
    <jsp:include page="./include/head.jsp"></jsp:include>
</head>
<body>
<header id="header">
	<!-- 헤더 부분 인클루드 -->
 	<jsp:include page="./include/hd.jsp"></jsp:include>
</header>
<div class="content container" id="content" style="min-height:500px;">
	<h2 class="title">메인</h2>
</div>
<footer id="footer" class="footer-nav row expanded collapse">
	<!-- 푸터 부분 인클루드 -->
	<jsp:include page="./include/ft.jsp"></jsp:include>
</footer>
</body>
</html>
