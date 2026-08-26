<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%-- JSTL의 핵심 기능을 사용하기 위한 태그 선언입니다. --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 - 메인</title>
<style type="text/css">
	table{
		width:800px;
		margin : 0 auto;
		table-layout: fixed;
		border-collapse: collapse;
	}
	td,th{
		border :1px solid black;
	}
	.passwd {
		width: 400px;
		text-overflow: ellipsis;
		overflow: hidden;
		white-space: nowrap;
	}
</style>
<script>
	function addDeleteEvent(){
		// --- 회원 삭제 버튼 클릭 이벤트 ---
		document.querySelectorAll('.btnDelete').forEach((item) => {
			item.addEventListener('click',() => {
				// 클릭한 버튼이 속한 행의 첫 번째 열(번호)을 가져옵니다.
				let no = item.parentElement.parentElement.firstElementChild.innerText;
				// 확인 후 삭제 경로로 이동합니다.
				if(confirm(no + '번 회원을 정말 삭제할까요?')){
					location.href="./delete.do?no="+no;
				}
			});
		});
	}

	window.onload = () => {
		addDeleteEvent();

		// --- 검색 버튼 클릭 이벤트 (AJAX 사용 예시) ---
		document.querySelector('#btnSearch').onclick = async (e) => {
			let btn = e.target;
			let tags = btn.closest('td'); // 클릭한 버튼이 포함된 td 태그 찾기

			const param = new URLSearchParams();
			// td 내부의 input과 select 태그들의 값을 파라미터로 만듭니다.
			tags.querySelectorAll('input,select').forEach(item => {
				param.append(item.id, item.value);
			});
			
			let url = `./search.do?\${param.toString()}`;
			console.log("요청 URL:", url);
			
			// 서버로 비동기 요청을 보냅니다.
			let response = await fetch(url);
			let data = await response.json();
			
			// 서버로부터 받은 JSON 응답을 콘솔에 출력합니다.
			console.log("서버 응답:", data);
			//alert(data.msg);
			
			
			const area = document.querySelector("#list_area");
			area.innerHTML = '';
			//data를 tbody에 출력
			let tag = '';
			data.list.forEach(item => {
				//태그 조립하고 화면에 출력 
				tag += '<tr>';
				tag += `<td>\${item.no}</td>`;
				tag += `<td>\${item.id}</td>`;
				tag += `<td class="passwd">\${item.passwd}</td>`;
				tag += `<td>\${item.userName}</td>`;
				tag += `<td>\${item.nickName}</td>`;
				tag += `<td><button class="btnDelete">삭제</button></td>`;
				tag += '</tr>';
			})
			area.innerHTML = tag;
			
			addDeleteEvent();
			
		}
	}
</script>
</head>
<body>
	<nav>
		<%-- 세션에 로그인 정보(member)가 없는 경우 --%>
		<c:if test="${sessionScope.member == null }">
			<a href="./loginView.do">로그인</a>
			<a href="./registerView.do">회원 가입</a>
		</c:if>
		<%-- 세션에 로그인 정보(member)가 있는 경우 --%>
		<c:if test="${sessionScope.member != null }">
			<a href="./loginOut.do">로그아웃</a>
			<span><strong>${sessionScope.member.nickName }</strong>님이 로그인하셨습니다.</span>
		</c:if>
	</nav>
	<hr>
	
	<table>
		<thead>
			<!-- 검색 영역 -->
			<tr>
				<td colspan="6">
					<select id="kind">
						<option value="id">아이디</option>
						<option value="username">이름</option>
						<option value="nickname">닉네임</option>
					</select>					
					<input type="text" id="search" placeholder="검색어를 입력하세요">
					<button id="btnSearch">검색</button>
				</td>
			</tr>
			<!-- 테이블 제목 -->
			<tr>
				<th>no</th>
				<th>id</th>
				<th>passwd (암호화됨)</th>
				<th>userName</th>
				<th>nickName</th>
				<th>비고</th>
			</tr>		
		</thead>
		<tbody id="list_area">
			<%-- 컨트롤러에서 보낸 'list'를 순회하며 회원 정보를 출력합니다. --%>
			<c:forEach items="${list }" var="member">
				<tr>
					<td>${member.no }</td>
					<td>${member.id }</td>
					<td class="passwd">${member.passwd }</td>
					<td>${member.userName }</td>
					<td>${member.nickName }</td>
					<td>
						<button class="btnDelete">삭제</button>
						<a class="btnUpdate" href="./updateView.do?no=${member.no }">수정</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>