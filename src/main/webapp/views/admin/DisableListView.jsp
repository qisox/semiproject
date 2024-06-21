<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%@include file="../common/menubar.jsp"%>
	<div align="center">
		<h4>정지회원 관리</h4>
	</div>
	<br>
	<br>
	<header>
	<div>
		<c:choose>
			<c:when test="${empty dlist}">
				<tr>
					<td colspan='5' align="center">목록이 없습니다.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:forEach items="${dlist}" var="d">
					<form action="${contextPath}/updateDisable.ad" method="post">
						<input type="hidden" name="memberNo" value="${d.memberNo}">
						<table class="table">
							<thead>
								<tr style="text-align: center;">
									<th>회원 아이디</th>
									<th>회원 이름</th>
									<th>이메일</th>
									<th>가입일</th>
									<th>정지사유</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<tr style="text-align: center">
									<td>${d.memberId}</td>
									<td>${d.memberName}</td>
									<td>${d.email}</td>
									<td>${d.enrolldate}</td>
									<td><input type="text" name="reason" value="${d.reason}"></td>
									<td><button type="submit">수정</button></td>
								</tr>
							</tbody>
						</table>
					</form>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
	</header>
</body>
</html>