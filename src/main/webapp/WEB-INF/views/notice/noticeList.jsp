<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="path1" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 목록</title>
</head>
<body>
	<table class="table">
		<thead>
			<tr><th>연번</th><th>제목</th><th>작성자</th></tr>
		</thead>
		<tbody>
			<c:forEach var="notice" items="${noticeList }" varStatus="status">
			<tr><td>${notice.no }</td>
			<td><a href="${path1 }/notice/detail.do?no=${notice.no}">${notice.title }</a></td>
			<td>${notice.author }</td></tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="javascript:history.go(-1)">뒤로가기</a>
	<a href="${path1 }/notice/insert.do">글 등록</a>
</body>
</html>