<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<style>
iframe {
	width: 0px;
	height: 0px;
	border: 0px;
}
</style>
<body>
	<form action="uploadForm" method="post" enctype="multipart/form-data" target="pilFrame">
		<input type="file" name="file">
		<input type="submit">	
	</form>
	<iframe name="pilFrame"></iframe>
	<script>
		function addFilePath(msg) {
			alert(msg);
			document.getElementById("form1").reset();
		}
	</script>
</body>
</html>