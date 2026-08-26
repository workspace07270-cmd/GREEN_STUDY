<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 - 회원가입</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<script>
	/** 모든 필드가 올바르게 입력되었는지 확인하는 함수입니다. */
	async function checkField(){
		let result = true;
		
		// 1. 아이디 입력 확인 및 중복 여부 체크 (서버 통신)
		let id = document.querySelector("#id").value;
		if(id.trim() == '') return false;
		
		let response = await fetch('./checkId.do?id='+id);
		let data = await response.json();

		if(data.result == 0) {
			alert('이미 사용 중인 아이디입니다.');
			result = false;
		}

		// 2. 모든 input 태그에 값이 입력되었는지 확인
		let input = document.querySelectorAll("input");
		input.forEach(item => {	
			if(item.value.trim() == '')
				result = false;
		});
		return result;
	}

	window.onload = () => {
		// --- 아이디 중복확인 버튼 클릭 이벤트 ---
		document.querySelector("#btnCheck").onclick = (e) => {
			let id = document.querySelector("#id").value;
			if(id.trim() == '') {
				alert('아이디를 먼저 입력하세요.');
				return;
			}
			
			// 비동기 통신(fetch)으로 중복 여부 확인
			fetch('./checkId.do?id='+id)
			.then(reponse => reponse.json())
			.then(data => {
				console.log(data);
				let checkArea = document.querySelector("#idCheckResult");
				if(data.result == 1){
					// 사용 가능한 경우 (파란색)
					checkArea.style.fontWeight = 'bold';
					checkArea.style.color = 'blue';
				}else{
					// 중복된 경우 (빨간색)
					checkArea.style.fontWeight = 'bold';
					checkArea.style.color = 'red';
				}
				checkArea.innerHTML = data.msg;
			})
		}

		// --- 폼 제출(가입하기 버튼) 이벤트 ---
		document.querySelector('form').onsubmit = async (e) => {
			// 기본 제출 기능을 막고 검증을 수행합니다.
			e.preventDefault();
			
			let result = await checkField();
			
			if(result) {
				// 검증 통과 시 실제 데이터를 전송합니다.
				e.target.submit();
			} else {
				alert('입력 항목을 다시 확인해 주세요.');
			}
		}
		
		// --- 취소 버튼 클릭 이벤트 ---
		document.querySelector("#btnCancel").onclick = () => {
			history.back(); // 이전 페이지(로그인 등)로 돌아감
		}
	}
</script>
</head>
<body>
	<div class="form-card">
		<h2>회원가입</h2>
		<form action="./register.do" method="post">
			<div class="form-group">
				<label for="id">아이디</label>
				<div class="input-with-btn">
					<input type="text" id="id" name="id" placeholder="아이디를 입력해주세요">
					<button type="button" id="btnCheck">중복확인</button>
				</div>
				<div id="idCheckResult"></div>
			</div>
			<div class="form-group">
				<label>비밀번호</label>
				<input type="text" name="passwd" placeholder="암호를 입력해주세요">
			</div>
			<div class="form-group">
				<label>이름</label>
				<input type="text" name="name" placeholder="이름을 입력해주세요">
			</div>
			<div class="form-group">
				<label>닉네임</label>
				<input type="text" name="nick" placeholder="닉네임 입력해주세요">
			</div>
			<div class="form-group" style="margin-top: 8px; flex-direction: row; gap: 10px;">
				<button class="btn-full">가입하기</button>
				<button type="button" id="btnCancel" class="btn-full">취소</button>
			</div>
		</form>
	</div>
</body>
</html>