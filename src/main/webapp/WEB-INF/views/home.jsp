<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>건강제약</title>
	<!-- 헤드 부분 인클루드 -->
    <jsp:include page="./include/head.jsp"></jsp:include>
</head>
<body>
<header id="header">
	<!-- 헤더 부분 인클루드 -->
 	<jsp:include page="./include/hd.jsp"></jsp:include>
</header>
<div class="content container" id="content" style="min-height:500px;">
	<h2 class="title" style="text-align:center; padding-top:100px;">Greater Vaule For Your Life</h2>
	<p style="text-align:center;">끊임없는 연구개발을 통해 고객의 건강한 삶을 위한 더 나은 가치를 만들어갑니다.</p>
</div>
<footer id="footer" class="footer-nav row expanded collapse">
	<!-- 푸터 부분 인클루드 -->
	<jsp:include page="./include/ft.jsp"></jsp:include>
</footer>
</body>
</html>
