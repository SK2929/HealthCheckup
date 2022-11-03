<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="rb" scope="request" class="bean.RegisterBean" />
<!DOCTYPE html>
<html>
<head>
<title>更新結果画面</title>
</head>
<body>
	<h2>更新結果を表示します</h2>
	<button type="button" onclick="location.href='./top.html'">TOPに戻る</button>
	
	<ul>
		<li>日付：<jsp:getProperty name="rb" property="date" /></li>
		<li>身長：<jsp:getProperty name="rb" property="height" /></li>
		<li>体重：<jsp:getProperty name="rb" property="weight" /></li>
		<li>体温：<jsp:getProperty name="rb" property="temperature" /></li>
	</ul>
</body>
</html>