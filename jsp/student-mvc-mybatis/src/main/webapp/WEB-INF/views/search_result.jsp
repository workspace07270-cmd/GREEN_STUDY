<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>검색 결과</h2>
	<c:choose>
		<c:when test="${vo != null }">
			<ul>
				<li>학번 : ${vo.no }</li>
				<li>이름 : ${vo.name }</li>
				<li>학과명 : ${vo.majorName }</li>
				<li>평점 : ${vo.score }</li>
			</ul>
		</c:when>	
		<c:otherwise>
			<p>검색결과가 없습니다.</p>
		</c:otherwise>
	</c:choose>
</body>
</html>




