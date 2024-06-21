<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <style>
        .outer table {
            border-color: black;
            color: black;
        }
        @font-face {
            font-family: 'TheJamsil5Bold';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302_01@1.0/TheJamsil5Bold.woff2') format('woff2');
            font-weight: 700;
            font-style: normal;
        }
        @font-face {
            font-family: 'GmarketSansMedium';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
            font-weight: 700;
            font-style: normal;
        }
        table#detail-area th,
        table#detail-area td {
            padding: 5px 0; /* 위아래 여백을 조절하려면 해당 값을 조정하세요 */
        }
		 #fixed-header {
		    position: fixed;
		    top: 0;
		    background-color: white;
		    width: 100%;
		    z-index: 100;
		  }
        .btn {
            width: 80px;
            height: 40px;
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
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">

	<div class="outer">

		<br>
		<br>
		<h2 align="left"
			style="margin-left: 520px; font-size: 24px; font-family: TheJamsil5Bold">${n.noticeTitle }</h2>
		<br>
		<table id="detail-area" align="center" style="margin-left: 530px;">
			<tr>
				<th width="70">번호</th>
				<td width="130" colspan="3">${n.noticeNo}</td>
				<th width="80">조회수</th>
				<td width="130" colspan="3">${n.count}</td>
			</tr>
			<tr style="border-bottom: 1px solid black;">
				<th>작성자</th>
				<td width="100" colspan="3">${n.nickName}</td>
				<th>작성일</th>
				<td width="200" colspan="3">${n.noticeDate}</td>
			</tr>
			<tr style="border-bottom: 1px solid black;">
				<th>내용</th>
				<td colspan="8">
					<p style="height: 200px; white-space: pre;">${n.noticeContent}</p>
				</td>
		</table>
		<br>
		<br>
		<div align="center">
			<c:if test="${loginMember.grade eq '관리자'}">
				<button
					onclick="location.href='${contextPath}/nupdate.no?noticeNo=${n.noticeNo}'"
					class="button2">수정하기</button>
				<button
					onclick="location.href='${contextPath}/ndelete.no?noticeNo=${n.noticeNo}'"
					class="button3">삭제하기</button>
			</c:if>
			<button
				onclick="location.href='${contextPath}/nlist.no?currentPage=1'"
				class="button1">목록가기</button>
		</div>
	</div>
</body>
</html>