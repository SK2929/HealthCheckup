<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="rb" scope="request" class="bean.RegisterBean" />
<!DOCTYPE html>	
<%-- メモ：検索結果画面から更新画面に遷移する際、値が検証ツールを使用すると丸見えなので隠す処理を追加する --%>
<%-- 案１：session.setAttribute("date", "<jsp:getProperty name=\"rb\" property=\"date\" />");  --%>
<html>
<head>
<title>検索結果</title>
</head>
<body>
	<h2>検索日の健診結果を表示します</h2>
	<button type="button" onclick="location.href='./top.html'">TOPに戻る</button>
	<div>日付：<jsp:getProperty name="rb" property="date" /></div>
	<div>身長：<jsp:getProperty name="rb" property="height" /></div>
	<div>体重：<jsp:getProperty name="rb" property="weight" /></div>
	<div>体温：<jsp:getProperty name="rb" property="temperature" /></div>
	<div>--------------------</div>
	<%--
	<div>健診結果をCSV形式でダウンロードします</div>
	<div>ボタン型：<input type="button" value="csvダウンロード" onclick="location.href='GenerateCsv'"><br/></div>
	<div>リンク型：<a href="GenerateCsv">[csvダウンロード]</a></div>
	<div>--------------------</div>
	 --%>
 	健診結果を更新する場合はこちら↓
	<form action="/HealthCheckup/view/ChangeServlet" method="get">
		<input type="hidden" name="date" value=<jsp:getProperty name="rb" property="date" />>
 		<input type="hidden" name="date" value=<jsp:getProperty name="rb" property="date" />>
		<input type="hidden" name="height" value=<jsp:getProperty name="rb" property="height" />>
		<input type="hidden" name="weight" value=<jsp:getProperty name="rb" property="weight" />>
		<input type="hidden" name="temperature" value=<jsp:getProperty name="rb" property="temperature" />>
		<input type="submit" value="更新画面に遷移">
	</form>
</body>
</html>
