<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions"%>
<c:set var="path1" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>공지사항 목록</title>
    <!-- 헤드 부분 인클루드 -->
    <jsp:include page="../include/head.jsp"></jsp:include>
</head>
<body>
	<header id="header">
		<!-- 헤더 부분 인클루드 -->
	 	<jsp:include page="../include/hd.jsp"></jsp:include>
    </header>
	
	<div class="content" id="content">
	    <div class="row column text-center">
	      <h2 class="h1">공지사항 글 수정</h2>
	      <hr>
	      <div class="container">
				<table class="table">
					<thead>
						<tr><th>연번</th><th>제목</th><th>작성자</th></tr>
					</thead>
					<tbody>
						<c:forEach var="notice" items="${noticeList }" varStatus="status">
						<tr><td>${notice.no }</td>
						<td><a href="${path1 }/notice/detail.do?no=${notice.no }">${notice.title }</a></td>
						<td>${notice.author }</td></tr>
						</c:forEach>
						<tr>
							<td colspan="3">
								<a href="javascript:history.go(-1)" class="button" >뒤로 가기</a>
								<a href="${path1 }/notice/insert.do" class="button" >글 등록</a>
							</td>
						</tr>
					</tbody>
				</table>		 
			</div>
	    </div>
	</div>
    <footer id="footer" class="footer-nav row expanded collapse">
    	<!-- 푸터 부분 인클루드 -->
    	<jsp:include page="../include/ft.jsp"></jsp:include>
    </footer>
</body>
</html>