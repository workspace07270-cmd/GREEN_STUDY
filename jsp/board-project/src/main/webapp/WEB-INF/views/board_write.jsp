<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글쓰기</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">
<link href="https://cdn.jsdelivr.net/npm/quill@2.0.3/dist/quill.snow.css" rel="stylesheet" />
<script src="https://cdn.jsdelivr.net/npm/quill@2.0.3/dist/quill.js"></script>
<script>
	const toolbarOptions = [
		  ['bold', 'italic', 'underline', 'strike'],        // toggled buttons
		  ['blockquote', 'code-block'],
		  ['link', 'image', 'video', 'formula'],
	
		  [{ 'header': 1 }, { 'header': 2 }],               // custom button values
		  [{ 'list': 'ordered'}, { 'list': 'bullet' }, { 'list': 'check' }],
		  [{ 'script': 'sub'}, { 'script': 'super' }],      // superscript/subscript
		  [{ 'indent': '-1'}, { 'indent': '+1' }],          // outdent/indent
		  [{ 'direction': 'rtl' }],                         // text direction
	
		  [{ 'size': ['small', false, 'large', 'huge'] }],  // custom dropdown
		  [{ 'header': [1, 2, 3, 4, 5, 6, false] }],
	
		  [{ 'color': [] }, { 'background': [] }],          // dropdown with defaults from theme
		  [{ 'font': [] }],
		  [{ 'align': [] }],
	
		  ['clean']                                         // remove formatting button
		];

	window.onload = () => {
		const quill = new Quill('#editor', {
			modules: {
			    toolbar: toolbarOptions
			  },
		    theme: 'snow'
		  });
		document.querySelector("form").onsubmit = (e) => {
			//e.preventDefault();
			console.log(quill.getContents());
			console.log(quill.getText());
			console.log(quill.getSemanticHTML());
			document.querySelector("#content").value = quill.getSemanticHTML();  
		}
	}
</script>
<style>
	#editor {
	  height: 500px;
	}
</style>
</head>
<body>
	<jsp:include page="./template/header.jsp"></jsp:include>
	<hr>
	<div class="write-card">
		<h1>글쓰기</h1>
		<form action="./boardWrite.do" method="post" enctype="multipart/form-data">
			<div class="write-title-group">
				<label for="writeTitle">제목</label>
				<input type="text" id="writeTitle" name="title" placeholder="제목을 입력해주세요">
			</div>
			<div id="editor"></div>
			<input type="hidden" name="content" id="content">
			<div class="write-file-group">
				<input type="file" name="file">
				<input type="file" name="file">
				<input type="file" name="file">
			</div>
			<div class="write-actions">
				<button>글쓰기</button>
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</form>
	</div>
</body>
</html>