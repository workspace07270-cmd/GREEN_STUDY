<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
async function checkField(){
		let result = true;
		//아이디 중복 여부
		let id = document.querySelector("#id").value;
		let response = await fetch('./checkId.do?id='+id);
		let data = await response.json();

		if(data.result == 0)
			result = false;
		
		//각 항목을 전부 입력했는지
		let input = document.querySelectorAll("input");
		input.forEach(item => {	
			if(item.value.trim() == '')
				result = false;
		});
		return result;
	}
	window.onload = () => {
		document.querySelector("#btnCheck").onclick = (e) => {
			let id = document.querySelector("#id").value;
			//alert(id); //입력 아이디값 확인
			
			fetch('./checkId.do?id='+id)
			.then(reponse => reponse.json())
			.then(data => {
				// 서버에서 받은 데이터를 확인 후 처리
				console.log(data);
				let checkArea = document.querySelector("#idCheckResult");
				if(data.result == 1){
					checkArea.style.fontWeight = 'bold';
					checkArea.style.color = 'blue';
				}else{
					checkArea.style.fontWeight = 'bold';
					checkArea.style.color = 'red';
				}
				checkArea.innerHTML = data.msg;
			})
		}
		//form submit 이벤트 처리
		document.querySelector('form').onsubmit = async(e) => {
			//alert('submit 이벤트 발생');
			// 태그는 기본적으로 설정된 이벤트
			// 하이퍼링크는 페이지 이동
			// submit 버튼 누르면 해당 경로로 데이터 전송
			// preventDefault는 그 기본적으로 설정되어 있는 이벤트를 무력화 시킴
			// e.preventDefault();
			
			e.preventDefault();
			let result = await checkField();
			
			if(result)
				e.target.submit();
			else
				alert('입력 항목을 체크하세요');
		}
	}
</script>
</head>
<body>
	<h2>회원가입</h2>
	<form action="./register.do" method="post">
		<input type="text" id="id" name="id" placeholder="아이디를 입력해주세요">
		<button type="button" id="btnCheck">중복확인</button><br>
		<div id="idCheckResult"></div>
		<input type="text" name="passwd" placeholder="암호를 입력해주세요"><br>
		<input type="text" name="name" placeholder="이름을 입력해주세요"><br>
		<input type="text" name="nick" placeholder="닉네임 입력해주세요"><br>
		<button>가입하기</button>
		<button type="button" id="btnCancel">취소</button>
	</form>
</body>
</html>