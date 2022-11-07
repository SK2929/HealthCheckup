<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="rb" scope="request" class="bean.RegisterBean" />
<!DOCTYPE html>	
<html>
<head>
<title>エラー画面</title>
</head>
<body>
	<h1>エラー画面</h1>
	<button type="button" onclick="location.href='./top.html'">TOPに戻る</button>
	<br/>
	<%-- errorList分出力 --%>
	<%= rb.getErrorList() %>
</body
</html>

<%-- sessionとrequest①と②で入力値の持ち方が異なるので観察 --%>
<%-- 
①<jsp:getProperty name="rb" property="errorList" /><br/>
②<%= rb.getErrorList() %><br/> 
--%>