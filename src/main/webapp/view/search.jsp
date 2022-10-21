<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="rb" scope="request" class="bean.RegisterBean" />
<!DOCTYPE html>	
<html>
<head>
<title>検索結果</title>
</head>
<body>
	<h2>検索日の健診結果を表示します</h2>
	<button type="button" onclick="location.href='./top.html'">TOPに戻る</button><br/>
	日付：<jsp:getProperty name="rb" property="date" /><br/>
	身長：<jsp:getProperty name="rb" property="height" /><br/>
	体重：<jsp:getProperty name="rb" property="weight" /><br/>
	体温：<jsp:getProperty name="rb" property="temperature" />
</body>
</html>
