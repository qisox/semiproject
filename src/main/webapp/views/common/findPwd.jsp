<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String memberPwd = (String)request.getAttribute("memberPwd");
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>비밀번호 찾기</h1>
	<p>비밀번호 : <%=memberPwd %></p>
	
	<button onclick="goBack();">로그인 하러가기</button>
	<script>
		function goBack() {
			location.href = "<%=contextPath%>"
		}
	</script>
</body>
</html>