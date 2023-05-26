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
<title>글 등록</title>
</head>
<body>
	<form action="${path1 }/notice/insert.do" method="post">
		<table class="table">
			<tbody>
				<tr>
					<th>글 제목</th>
					<td><input type="text" name="title" id="title">${notice.title }</td>
				</tr>
				<tr>
					<th>글 내용</th>
					<td><textarea name="content" id="content" cols="100" rows="8">${notice.content }</textarea></td>
				</tr>
				<tr>
					<td colspan="2"><input type="submit" value="글 등록"></td>
			</tbody>
		</table>
		</form>
	<a href="${path1 }/notice/list.do">글 목록</a>
	<a href="${path1 }">메인으로</a>
</body>
</html>