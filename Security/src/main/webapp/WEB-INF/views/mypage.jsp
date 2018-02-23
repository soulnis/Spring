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
<h1>${user_name} 로그인 성공!!</h1>
mypage...

<c:url value="/logout-ok" var="logoutUrl" />
<form action="${logoutUrl}" method="POST">
<input type="submit" value="Log out" />
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>

<%-- <form action="${logoutUrl}", metohd="POST" >
	<input type="submit">
</form> --%>
</body>
</html>