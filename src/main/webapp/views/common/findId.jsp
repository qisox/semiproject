<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String memberId = (String)request.getAttribute("memberId");
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>아이디 찾기</h1>
	<p>아이디 : <%=memberId %></p>
	
	<button onclick="goBack();">로그인 하러가기</button>
	<button onclick="findPwd();">비밀번호 찾기</button>
	
	<script>
		function goBack() {
			location.href = "<%=contextPath%>"
		}
		
		function findPwd() {
			location.href = "<%=contextPath%>/findPwd.me"
		}
	</script>
</body>
</html>