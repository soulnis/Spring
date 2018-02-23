<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<c:if test="${error == 'true'}">
아이디나 비밀번호를 확인해주세요.
</c:if>

<c:url value="/login-ok" var="loginUrl"/>
<form action="${loginUrl}" method="POST">
	id: <input type="text" name="id"><br>
	pw: <input type="password" name="pw"><br>
	<input type="submit">
<!-- 스프링시큐리티는 spring security는 form 전송시 csrf 공격 방어를 아래의 토큰값을 사용하도록 규정하고 있다. -->
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>