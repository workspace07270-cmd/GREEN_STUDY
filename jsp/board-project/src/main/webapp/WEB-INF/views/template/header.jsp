<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<style>

</style>
<!-- 메뉴 
		로그인 안한 상태
			홈, 로그인, 회원가입
		로그인 한 상태
			홈, 글쓰기, OOO님이 로그인 하셨습니다, 로그아웃
			
	사용자 정보는 session에 user로 저장되어있음.
-->
<nav class="menu_bar">
	<ul>
		<li>
			<a href="./main.do">홈</a>
			<c:choose>
				<c:when test="${sessionScope.user != null }">
				<!-- 로그인 했을때 -->
					<li><a href="./boardWriteView.do">글쓰기</a></li>
					<li>${sessionScope.user.nickName}님이 로그인 하셨습니다.</li>
					<li><a href="./logout.do">로그아웃</a></li>
				</c:when>
				<c:otherwise>
				<!-- 로그인 안했을때 -->
					<li><a href="./loginView.do">로그인</a></li>
					<li><a href="./registerView.do">회원가입</a></li>
				</c:otherwise>
			</c:choose>
			
		</li>
	</ul>
</nav>
