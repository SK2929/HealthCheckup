<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="rb" scope="request" class="bean.RegisterBean" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>更新画面</title>
</head>
<body>
	<h1>更新画面</h1>
	<button type="button" onclick="location.href='./top.html'">TOPに戻る</button>
	
	<form action="/HealthCheckup/view/ChangeServlet" method="post">
		<input type="hidden" name="beforeDate" value=<jsp:getProperty name="rb" property="date" />>
		<input name="date" value=<jsp:getProperty name="rb" property="date" />><br/>
		<input name="height" value=<jsp:getProperty name="rb" property="height" />><br/>
		<input name="weight" value=<jsp:getProperty name="rb" property="weight" />><br/>
		<input name="temperature" value=<jsp:getProperty name="rb" property="temperature" />><br/>
		<input type="submit" value="更新">
	</form>
</body>
</html>