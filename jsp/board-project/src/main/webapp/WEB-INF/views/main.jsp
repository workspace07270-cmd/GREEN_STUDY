<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
</head>
<body>
	<jsp:include page="./template/header.jsp"></jsp:include>
	<hr>
	<div class="page-content">
		<table class="board_table">
			<thead>
				<tr>
					<th>글번호</th>
					<th>제목</th>
					<th>작성일</th>
					<th>닉네임</th>
					<th>조회수</th>
					<th>좋아요</th>
					<th>싫어요</th>
				</tr>

			</thead>
			<tbody class="board_content_list">
				<c:forEach var="board" items="${requestScope.list }">
					<tr>
						<td>${board.bno }</td>
						<td>
							<a href="./boardView.do?bno=${board.bno }">
								${board.title }[${board.ccount }]</a>
						</td>
						<td>${board.writeDate }</td>
						<td>${board.nickName }</td>
						<td>${board.bcount }</td>
						<td>${board.blike }</td>
						<td>${board.bhate }</td>
					</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="7">
						<div class="pagination">
							<!--  이전 페이지 그룹 이동 ◀
										이전 페이지 그룹이 있을때만 링크를 출력
							 -->
							 <c:if test="${pagging.priviousPageGroup }">
								<a href="./main.do?page=${pagging.startPageOfPageGroup - 1}">◀</a>
							</c:if>
							<!-- 페이징 처리 영역 -->
							<c:forEach var="i" begin="${requestScope.pagging.startPageOfPageGroup }" end="${requestScope.pagging.endPageOfPageGroup }">
								<a href="./main.do?page=${i}" <c:if test="${i == pagging.currentPage }">class="active"</c:if>>${i }</a>							
							</c:forEach>
							<!-- 다음 페이지 그룹 이동 ▶ 
									다음 페이지 그룹이 있을때만 링크를 출력
							-->
							<c:if test="${pagging.nextPageGroup }">
								<a href="./main.do?page=${pagging.endPageOfPageGroup + 1}">▶</a>
							</c:if>
						</div>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</body>
</html>