<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원 관리 - 회원정보 수정</title>
<script>
	/** 모든 필드가 올바르게 입력되었는지 확인하는 함수입니다. */
	async function checkField(){
		let result = true;
	
		let input = document.querySelectorAll("input");
		input.forEach(item => {	
			if(item.value.trim() == '')
				result = false;
		});
		return result;
	}

	window.onload = () => {
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
	<h2>회원정보수정</h2>
	<form action="./update.do" method="post">
		<input type="hidden" name="no" value="${dto.no }"> 
		<input type="text" id="id" readonly value="${dto.id }">
		<input type="text" name="passwd" placeholder="암호를 입력해주세요"><br>
		<input type="text" name="name" placeholder="이름을 입력해주세요" value="${dto.userName }"><br>
		<input type="text" name="nick" placeholder="닉네임 입력해주세요"value=" ${dto.nickName }"><br>
		
		<button>수정</button>
		<button type="button" id="btnCancel">취소</button>
	</form>
</body>
</html>