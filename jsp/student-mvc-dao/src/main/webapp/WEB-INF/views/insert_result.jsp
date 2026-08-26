<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
${pageContext.request.contextPath }<br>
${requestScope.flag ? "/main.do" : "/insertView.do"}<br>
${requestScope.flag}
<script>
	alert('${requestScope.msg}');
	location.href=`${pageContext.request.contextPath }/${requestScope.flag ? "main.do" : "insertView.do"}`;
</script>