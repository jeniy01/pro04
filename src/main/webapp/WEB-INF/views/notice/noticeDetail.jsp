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
	<title>글 상세보기</title>
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
					<tbody>
						<tr>
							<th>글 번호</th>
							<td>${notice.no }</td>
						</tr>
						<tr>
							<th>글 제목</th>
							<td>${notice.title }</td>
						</tr>
						<tr>
							<th>글 내용</th>
							<td>${notice.content }</td>
						</tr>
						<tr>
							<th>글 작성자</th>
							<td>${notice.author }</td>
						</tr>
						<tr>
							<th>작성일시</th>
							<td>${notice.resdate }</td>
						</tr>
						<tr>
							<th>조회수</th>
							<td>${notice.visited }</td>
						</tr>
						<tr>
							<td colspan="2">
								<a href="${path1 }/notice/list.do" class="button" >글 목록</a>
								<a href="${path1 }/notice/edit.do?no=${notice.no }" class="button" >글 수정</a>
								<a href="${path1 }/notice/delete.do?no=${notice.no }" class="button" >글 삭제</a>
								<a href="${path1 }" class="button" >메인으로</a>
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