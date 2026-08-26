<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>검색 결과</title>
</head>
<body>
    <h2>검색 결과</h2>
    <p>검색어: ${requestScope.searchKeyword}</p>

    <c:choose>
        <c:when test="${not empty requestScope.result}">
            <table border="1">
                <tr><th>학번</th><th>이름</th><th>학과</th><th>평점</th></tr>
                <tr>
                    <td>${requestScope.result.no}</td>
                    <td>${requestScope.result.name}</td>
                    <td>${requestScope.result.majorName}</td>
                    <td>${requestScope.result.score}</td>
                </tr>
            </table>
        </c:when>
        <c:otherwise>
            <p>조회결과 없습니다</p>
        </c:otherwise>
    </c:choose>

    <p><a href="./main.do">목록으로</a></p>
</body>
</html>
