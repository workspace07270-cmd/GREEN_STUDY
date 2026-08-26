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
	<a href="./insertView.do">학생정보 추가</a>
	<a href="./updateView.do">학생정보 수정</a>
	<hr>
	<h2>학생 목록</h2>
	<main>
		<section>
			<table>
				<thead>
					<tr>
						<td colspan="5">
							<form action="./search.do" method="get">
								<input type="text" name="name" placeholder="이름 일부 입력">
								<button> 검색 </button>
							</form>
						</td>
					</tr>
					<tr>
						<th>학번</th>	
						<th>이름</th>
						<th>학과명</th>
						<th>평점</th>
						<th>비고</th>
					</tr>
				</thead>
				<tbody>
				<!-- jstl을 이용해서 request 영역에 저장된 list를 표 형태로 출력 -->
				<c:forEach var="dto" items="${requestScope.list }">
					<tr>
						<td>${dto.no }</td>
						<td>${dto.name }</td>
						<td>${dto.majorName }</td>
						<td>${dto.score }</td>
						<td>
						    <a href="./delete.do?no=${dto.no }">삭제</a>
						    /
						    <a href="./updateView.do?no=${dto.no }">수정</a>
						</td>
					</tr>
				</c:forEach>
				</tbody>
			</table>
		</section>
	</main>		
</body>
</html>






