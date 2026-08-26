<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 보기</title>
<style>
	button.btn-comment-like *,
	button.btn-comment-hate *{
		pointer-events: none;
	}

</style>
<script>
	window.onload = (e) => {
		// 좋아요 버튼을 클릭 경고창 출력
		// 경고창 출력시 글번호도 출력
		const bno = '${board.bno}';
		document.querySelector('.btn-like').onclick = async (e) => {
			// alert(`좋아요 버튼 클릭 : \${bno}`);
			// boardLike.do 호출해서 좋아요 처리 -> board_like 추가
			// 전달할 데이터 : 글번호
			// 받는 데이터 : 결과메세지 msg
			// 					'이 게시글에 좋아요를 하셨습니다.'
			//					'이 게시글에 좋아요를 취소하셨습니다.'
			//			    좋아요 개수 count
			// 데이터를 응답 받으면 좋아요 개수를 최신화, 결과 메세지는 경고창으로 출력
			const response = await fetch(`./boardLike.do?bno=\${bno}`);
			const data = await response.json();

			
			alert(data.msg);
			//로그인 안했을 떄 경고창 확인 후 로그인 페이지로 이동
			if(data.resultCode == 1)
				location.href="./loginView.do";

			document.querySelector('.btn-like span').innerHTML = data.count;
		}

		document.querySelector('.btn-hate').onclick = async (e) => {
		 	//1. ajax 호출할 url 완성
			const url = `./boardHate.do?bno=\${bno}`;
			//2. ajax 호출
			const response = await fetch(url);
			const data = await response.json();
			//3. 결과 받아서 출력
			// resultCode에 따라서 하는 일을 구분
			// 1 : 로그인 안했을 떄
			// 0 : 싫어요 했거나/취소했거나
			console.log(data);
			alert(data.msg);
			if(data.resultCode == 1)
				location.href = "./loginView.do";

			//싫어요 개수 최신화
			document.querySelector('.btn-hate span').innerHTML = data.count;
			
		}

		// 댓글 좋아요/싫어요 버튼 클릭 이벤트 등록
		document.querySelectorAll("button.btn-comment-like,button.btn-comment-hate").forEach(item => {
			item.onclick = async (e) => {
				console.log(e.target.className);
				const cno = e.target.closest(".comment-item").querySelector("input[name='cno']").value;
				let url = `./boardCommentLikeHate.do?cno=\${cno}&mode=`
				if(e.target.className.indexOf('like') != -1){
					//좋아요 처리
					url += "like";					
				}else{
					//싫어요 처리
					url += "hate";					
				}
				console.log(url);
				// ajax
				const response = await fetch(url);
				const data = await response.json();
				
				console.log(data);
				
				alert(data.msg);
				e.target.querySelector('span').innerHTML = data.count;
				
			}
		})
	}

</script>
</head>
<body>
	<jsp:include page="./template/header.jsp"></jsp:include>
	<hr>
	<main>
		<article>
			<h3 class="board-title">제목 : ${board.title }</h3>
			<p class="board-meta-info">글번호 : <span id="bno">${board.bno}</span></p>
			<p class="board-meta-info">작성자 : ${board.nickName }, 
				작성일 : ${board.writeDate }(${board.updateDate })</p>
			<p class="board-meta-info">조회수 : ${board.bcount}</p>
			<div class="board-content">
				${board.content }
			</div>
			<div class="board-actions">
				<button class="btn btn-like" type="button">
					<img src="./resources/img/like_icon.png">
					<span>${board.blike }</span>
				</button>
				<button class="btn btn-hate" type="button">
					<img src="./resources/img/like_icon.png">
					<span>${board.bhate }</span>
				</button>
			</div>
			<div class="board-actions">
				<a href="javascript:history.back();" class="btn btn-back">뒤로가기</a>
				<c:if test="${sessionScope.user.no == board.mno }">
					<a href="./boardDelete.do?bno=${board.bno }" class="btn btn-delete">삭제</a>
					<a href="./boardUpdateView.do?bno=${board.bno }" class="btn btn-update">수정</a>
				</c:if>
			</div>
			<div class="board-file-list">
				<c:forEach var="file" items="${flist }">
					<p><a href="./fileDown.do?fno=${file.fno }">${file.fileName }</a></p>
				</c:forEach>
			</div>
		</article>
		<hr>
		<section class="comment-area">
			<!-- 댓글 폼 
					1. 로그인 했을 때만 댓글 입력 폼을 작성
						- 댓글, 글번호
					2. 로그인 안했을 때는 '댓글을 입력하실려면 로그인 하세요' 
			-->
			<c:choose>
				<c:when test="${user != null}">
					<form action="./boardCommentInsert.do" method="post" class="comment-form">
						<textarea name="content" required></textarea>
						<button>댓글달기</button>
						<input type="hidden" name="bno" value="${board.bno}">
					</form>
				</c:when>
				<c:otherwise>
					<p class="comment-non-login">댓글을 입력하실려면 로그인 하세요</p>
				</c:otherwise>
			</c:choose>

			<!-- 댓글 목록 -->
			<div class="comment-list">
				<c:forEach var="comment" items="${clist }">
					<div class="comment-item">
						<input type="hidden" name="cno" value="${comment.cno }">
						<p class="comment-info">
							작성자 : ${comment.nickName }, 작성일 : ${comment.cdate }
						</p>
						<p class="comment-content">${comment.content }</p>
						<p class="comment-actions">
							<button type="button" class="btn btn-comment-like">
								<img src="./resources/img/like_icon.png">
								<span>${comment.clike }</span>
							</button>
							<button type="button" class="btn btn-comment-hate">
								<img src="./resources/img/like_icon.png">
								<span>${comment.chate }</span>
							</button>
							<c:if test="${session.user.no == comment.mno }">
								<a href="./commentDelete.do" class="btn btn-comment-delete">삭제</a>
							</c:if>
						</p>
					</div>
				</c:forEach>
			</div>
			<!-- 댓글 더보기 버튼 -->
			<button type="button" class="btn btn-comment-more">댓글 더보기</button>
		</section>
	</main>

</body>
</html>
