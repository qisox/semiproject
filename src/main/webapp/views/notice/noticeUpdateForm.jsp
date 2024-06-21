<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
#boardTitle {
	color: rgb(240, 212, 70);
	border-radius: 20px;
}

@font-face {
	font-family: 'SEBANG_Gothic_Bold';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2104@1.0/SEBANG_Gothic_Bold.woff')
		format('woff');
	font-family: 'GmarketSansMedium';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

@font-face {
	font-family: 'S-CoreDream-3Light';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff')
		format('woff');
	font-weight: normal;
	font-style: normal;
}

/* body {
        background-color: rgb(255, 225, 77);
    } */
#enroll-area>table {
	border: 1px solid white;
}

#enroll-area input, #enroll-area textarea {
	width: 100%;
	box-sizing: border-box;
}

.button1 {
	border: 2px solid rgb(255, 225, 77);
	padding: 10px 20px;
	border-radius: 10px;
	background-color: rgb(255, 225, 77);
}

.button1:hover {
	background-color: rgb(251, 236, 152);
}

.button2 {
	border: 2px solid rgb(249, 219, 150);
	padding: 10px 20px;
	border-radius: 10px;
	background-color: rgb(249, 219, 150);
}

.button2:hover {
	background-color: rgb(252, 233, 202);
}
</style>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center" id="boardTitle"
		    style="margin-left: -300px; font-family: S-CoreDream-3Light; font-weight: bold; font-size: 26px">공지사항 수정</h2>
		<br>
		<div style="font-family: 'S-CoreDream-3Light'">
		<form action="${contextPath }/nupdate.no" method="post" id="enroll-area">
			<%--어떠한 게시글을 수정할지 알아야 하기 때문에 번호 보내기 --%>
			<input type="hidden" name="noticeNo" value="${n.noticeNo}">
			<table align="center" border="1">
					<th width="70">제목</th>
					<td width="350"><input type="text" value="${n.noticeTitle}" name="title"></td>
				<tr>
					<th>내용</th>
					<td colspan="3">
						<textarea name="content" rows="10" style="resize:none" required>${n.noticeContent}</textarea>
					</td>
				</tr>
			</table>
			<div align="center">
				<button type="submit" class="button1">수정하기</button>
				<button type="reset" class="button2">취소</button>
			</div>
			
		</form>
		<br>
	</div>
</body>
</html>