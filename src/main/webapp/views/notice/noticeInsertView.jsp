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

	.button3 {
		border: 2px solid rgb(252, 147, 139);
		padding: 10px 20px;
		border-radius: 10px;
		background-color: rgb(252, 147, 139);
	}
	
	.button3:hover {
		background-color: rgb(252, 171, 167);
	}
</style>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp"%>
	<div class="outer">
		<br>
		<br>
		<h2 align="center" id="boardTitle"
			style="margin-left: -550px; font-family: S-CoreDream-3Light; font-weight: bold; font-size: 26px">공지사항
			작성</h2>
		<br>
		<div style="font-family: 'S-CoreDream-3Light'">
			<form action="${contextPath}/ninsert.no" method="post" id="enroll-area">
				<table align="center" border="1">
					<th width="80" style="height: 50px;">제목</th>
					<td width="600"><input type="text" name="title" style="height: 35px" required></td>
					<tr>
						<th>내용</th>
						<td colspan="3"><textarea name="content" rows="10" style="resize: none" required></textarea></td>
					</tr>
				</table>
				<br> <br>
				<div align="center">
					<button class="button1" type="submit">글작성</button>
					<button class="button3" type="reset">취소</button>
					<button type="button" onclick="location.href='${contextPath}/nlist.no?currentPage=1'" class="button2">목록가기</button>
				</div>
			</form>
		</div>
		<br><br>
	</div>
</body>
</html>