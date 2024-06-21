<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.outer {
		width: 100%;
		display: flex;
		justify-content: center;
	}
	
	.list-container {
		width: 1050px;
	}
	
	.rectangle {
		width: 100%;
		height: 0.5px;
		background-color: black;
	}
	
	.list-area>tbody tr:hover {
		background-color: rgb(255, 225, 77);
		cursor: pointer;
	}
	
	.list-area {
		width: 100%;
		text-align: center; /* 테이블 셀 내용 가운데 정렬 */
		border-collapse: none; /* 테이블 셀 경계선 따로 그리기 */
	}
	
	.list-area th, .list-area td {
		padding: 5.4px; /* 셀 내용과 경계선 사이 여백 추가 */
	}
	
	.list-area, .list-area th, .list-area td {
		border: none; /* 테이블 셀 경계선 모두 제거 */
	}
	
	.list-area tr {
		border-bottom: 1px solid lightgray;
	}
	
	.list-area th {
		background-color: rgb(241, 205, 27);
	}
	
	.list-header {
		width: 100%;
		display: flex;
		justify-content: space-between;
	}
	
	/* 다음은 여백을 없애기 위한 추가 스타일입니다. */
	body {
		margin-bottom: 0;
	}
	
	.search_area {
		text-align: center;
		padding: 30px;
	}
	
	.search_area select {
		width: 150px;
		height: 30px;
		border: 0px;
	}
	
	.input_area {
		border: solid 1px #dadada;
		padding: 10px 10px 14px 10px;
		margin-right: 20px;
		background: white;
	}
	
	.input_area input {
		width: 250px;
		height: 30px;
		border: 0px;
	}
	
	.input_area input:focus, .search_area select:focus {
		outline: none;
	}
	
	.search_area button {
		width: 100px;
		height: 35px;
		border: 0px;
		color: white;
		background: #282A35;
		margin: 5px;
	}
</style>

</head>
<body>
	<%@include file="/views/common/menubar.jsp"%>
	<!-- 상위폴더로 -->

	</div>
	<div class="outer">
		<div class="list-container">
			<br>
			<br>
			<div class="list-header">
				<h5 style="font-size: 28px; color: rgb(241, 205, 27); font-family: SEBANG_Gothic_Bold;">공지사항</h5>
				<c:if test="${loginMember.grade eq '관리자'}">
					<a href="${contextPath}/ninsert.no" class="btn btn-outline-warning">글작성</a>
				</c:if>
			</div>
			<br>

			<table class="list-area" align="center" border="1">
				<colgroup>
					<col width="10%" />
					<col width="10%" />
					<col width="15%" />
					<col width="10%" />
					<col width="15%" />
					<col width="15%" />
				</colgroup>
				<thead
					style="color: black; height: 45px; font-family: GmarketSansMedium; font-size: 17px">
					<tr>
						<th>글번호</th>
						<th>제목</th>
						<th>내용</th>
						<th>작성자</th>
						<th>조회수</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<!-- 리스트가 비어있는 경우  -->
					<c:choose>
						<c:when test="${empty nlist}">
							<tr>
								<td colspan='6'>게시글이 없습니다</td>
							</tr>
						</c:when>
						<c:otherwise>
							<!-- 목록이 존재하는경우 -->
							<c:forEach items="${nlist}" var="n">
								<tr>
									<td>${n.noticeNo }</td>
									<td>${n.noticeTitle}</td>
									<td style="font-weight: bold;"> ${n.noticeContent}</td>
									<td>${n.nickName}</td>
									<td>${n.count}</td>
									<td>${n.noticeDate}</td>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</tbody>
			</table>

			<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
			<script>
				$(function(){
					$(".list-area>tbody>tr").click(function(){
					
						location.href = "ndetail.no?noticeNo="+$(this).children().eq(0).text();
					});
	
				});
				</script>
			<br>

			<!-- 페이징바 -->
			<div align="center">
				<c:choose>
					<c:when test="${pi.currentPage eq 1}">
						<button class='btn btn-sm' disabled>이전</button>
					</c:when>
					<c:otherwise>
						<button class='btn btn-sm'
							onclick="location.href='nlist.no?currentPage=${pi.currentPage-1}'">이전</button>
					</c:otherwise>
				</c:choose>
				<c:forEach var="i" begin="${pi.startPage}" end="${pi.endPage}">
					<c:choose>
                		<c:when test="${i eq pi.currentPage}">
                			<button class='btn btn-sm active' onclick="location.href='nlist.no?currentPage=${i}'">${i}</button>
                		</c:when>
                		<c:otherwise>
                			<button class='btn btn-sm' onclick="location.href='nlist.no?currentPage=${i}'">${i}</button>
                		</c:otherwise>
                	</c:choose>
					
				</c:forEach>
				<c:choose>
					<c:when test="${pi.currentPage eq pi.maxPage}">
						<button class='btn btn-sm' disabled>다음</button>
					</c:when>
					<c:otherwise>
						<button class='btn btn-sm'
							onclick="location.href='nlist.no?currentPage=${pi.currentPage+1}'">다음</button>
					</c:otherwise>
				</c:choose>
			</div>
			<br>
			<br>
			<br>
		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>