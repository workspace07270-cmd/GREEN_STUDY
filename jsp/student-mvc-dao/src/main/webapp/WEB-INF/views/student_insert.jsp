<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>학생정보 등록 페이지</h2>
	<!-- 학생정보 입력받는 폼  -->
	<form action="./insert.do" method="post">
		<input type="text" name="no"><br>	
		<input type="text" name="name"><br>	
		<input type="text" name="majorName"><br>	
		<input type="text" name="score"><br>
		<button>추가</button>
		<button type="button">취소</button>	
	</form>
</body>
</html>





