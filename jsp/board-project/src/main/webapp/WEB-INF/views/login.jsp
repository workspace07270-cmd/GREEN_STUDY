<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 - 로그인</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<div class="form-card">
		<h2>로그인</h2>
		<form action="./login.do" method="post">
			<div class="form-group">
				<label for="loginId">아이디</label>
				<input type="text" id="loginId" name="id" placeholder="아이디를 입력해주세요">
			</div>
			<div class="form-group">
				<label for="loginPw">비밀번호</label>
				<input type="text" id="loginPw" name="passwd" placeholder="암호를 입력해주세요">
			</div>
			<div class="form-group" style="margin-top: 8px;">
				<button class="btn-full">로그인</button>
			</div>
			<div class="form-group">
				<button type="button" id="btnRegister" class="btn-full" onclick="location.href='./registerView.do'">회원가입</button>
			</div>
		</form>
	</div>
</body>
</html>