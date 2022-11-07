<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="rb" scope="request" class="bean.RegisterBean" />
<!-- <!DOCTYPE html> -->
<html>
<head>
<title>登録結果画面</title>
</head>
<body>
	<h2>BDに登録されました。登録情報を確認してください</h2>
	<button type="button" onclick="location.href='./top.html'">TOPに戻る</button><br/>
	日付：<jsp:getProperty name="rb" property="date" /><br/>
	身長：<jsp:getProperty name="rb" property="height" /><br/>
	体重：<jsp:getProperty name="rb" property="weight" /><br/>
	体温：<jsp:getProperty name="rb" property="temperature" />
</body>
</html>
