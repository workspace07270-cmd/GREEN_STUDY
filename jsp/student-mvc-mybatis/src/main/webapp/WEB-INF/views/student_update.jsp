<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>학생정보 수정 페이지</h2>
	<!-- 학생정보 수정하는 폼  -->
	<form action="./update.do" method="post">
		<span>${vo.no }</span>
		<input type="hidden" name="no" value="${vo.no }"><br>	
		<input type="text" name="name" value="${vo.name }"><br>	
		<input type="text" name="majorName" value="${vo.majorName }"><br>	
		<input type="text" name="score" value="${vo.score }"><br>
		<button>수정</button>
		<button type="button">취소</button>	
	</form>
</body>
</html>