<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
@font-face {
     font-family: 'S-CoreDream-3Light';
     src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff') format('woff');
     font-weight: normal;
     font-style: normal;
}
.outer{
	width:100%;
	display:flex;
	justify-content: center;
}
.list-container{
	width:1050px;
	
}
 .rectangle {
           width:100%;
           height: 0.5px;
          	background-color : black;
       }
      
        
.list-area > tbody tr:hover {
       	background-color : rgb(255, 225, 77);
       	cursor : pointer;
      }


   

.list-area {
	width:100%;
    text-align: center; /* 테이블 셀 내용 가운데 정렬 */
    border-collapse: none; /* 테이블 셀 경계선 따로 그리기 */
 }

 .list-area th, .list-area td {
    padding: 10px; /* 각 셀의 안쪽 여백 조절 */
    font-size: 14px; /* 원하는 글자 크기로 조절 */
	font-family: 'S-CoreDream-3Light';
  }
.list-area, .list-area th, .list-area td {
    border: none; /* 테이블 셀 경계선 모두 제거 */
}

.list-area tr {
	border-bottom : 1px solid lightgray;
}

.list-area th {
       background-color : rgb(241, 205, 27);
}
.list-area td {
	text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
    max-width: 100px;
}
.list-header{
	width:100%;
	display:flex;
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

 .list-area th, .list-area td {
    padding: 10px; /* 각 셀의 안쪽 여백 조절 */
    font-size: 14px; /* 원하는 글자 크기로 조절 */
    font-family: 'S-CoreDream-3Light';
  }

.categoryDiv{
	display: flex;
	border-top: 2px solid #000;  /* 위쪽에 1px 두께의 실선 추가 (색상은 검정색)  */
    border-bottom: 2px solid #000; /* 아래쪽에 1px 두께의 실선 추가 (색상은 검정색) */
    background-color: #e0e0e0;
    padding:20px;
}

.categoryDiv>a{
	text-decoration: none; /* 밑줄 제거 */
    color: black; /* 원하는 색상으로 변경 */
    margin-right: 20px;
    
}
	</style>
	
</head>
<body>
	<%@include file="/views/common/menubar.jsp" %>
	<!-- 상위폴더로 -->

	</div>
	<div class="outer">
		<div class="list-container">
			
			<br><br>
			<div class="list-header">
				
				
			    <h5 style="font-size: 28px; color: rgb(241, 205, 27); font-family: SEBANG_Gothic_Bold; ">
			        꿀팁게시판
			    </h5>
			  
			    <c:if test="${not empty loginMember }">
					<!-- (loginUser != null ) -->
					<div align="center">
						<button class="btn btn-warning" onclick="location.href='${pageContext.request.contextPath}/insert.bo'"><b>글작성</b></button>
						<%-- 				<a href="<%=contextPath %>/list.no" class="btn btn-secondary">목록으로</a> --%>
					</div>
				</c:if>
			</div>
			<br>
			<div class="categoryDiv ">
				<a href="${contextPath}/list.bo?currentPage=1&category=꿀팁" style="font-family: SEBANG_Gothic_Bold; ">전체</a>
				<c:forEach items="${categoryList}" var="cList">
					<a  href="${contextPath}/ggulPostByCategory.po?currentPage=1&categoryNo=${cList.categoryNo}" style="font-family: SEBANG_Gothic_Bold;">${cList.categoryName}</a>
				</c:forEach>
			</div>
			<br><br>
			<table class="list-area" align="center" border = "1">
				<colgroup>
				      <col width="5%" />
				      <col width="10%" />
				      <col width="15%" />
				      <col width="5%" />
				      <col width="5%" />
				      <col width="5%" />
				      <col width="10%" />
				      <col width="10%" />
				</colgroup>
				<thead style=" color: black; height:45px ;font-family: GmarketSansMedium; font-size : 17px">
					<tr>
						<th>글번호</th>
						<th>카테고리</th>
						
						<th>제목</th>
						<th>조회수</th>
						<th>좋아요</th>
						<th>관심글</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<!-- 리스트가 비어있는 경우  -->
					<c:choose>
						<c:when test="${empty list}">
							<tr style="pointer-events: none;">
								<th colspan = '8'> 게시글이 없습니다 </th>
							</tr>
						</c:when>
						<c:otherwise>
						<!-- 목록이 존재하는경우 -->
						<c:forEach items = "${list}" var = "p">
						<tr>
						
						    <td>${p.postNo }</td>
						    <td>${p.categoryName }</td>
						    <td style="font-weight: bold;">
							    <c:forEach items="${attList}" var="a" >
								    <c:if test="${a eq p.postNo}">
								    	<img src="resources/assets/img/image.svg">
								    </c:if>
							    </c:forEach>
						    	${p.title}
						    </td>
						    
						   
						    
						    <td>${p.count }</td>
						    <td>${p.likeCount }</td>
						    <td>${p.interestCount }</td>
						    <td>${p.nickname}</td>
						    <td>${p.postDate }</td>
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
					
						location.href = "detail.bo?postNo="+$(this).children().eq(0).text();
					});
	
				});
				</script>
				
				<br>
				
			    <c:choose>
					<c:when test="${categoryNo eq null}">
						<!-- 페이징바 -->
						<div class="wholeList" align="center">
			                <c:choose>
			                    <c:when test="${pi.currentPage eq 1}">
			                        <button  class='btn btn-sm' disabled>이전</button>
			                    </c:when>
			                    <c:otherwise>
			                        <button  class='btn btn-sm' onclick="location.href='list.bo?currentPage=${pi.currentPage-1}&category=꿀팁'">이전</button>
			                    </c:otherwise>
			                </c:choose>
			                <c:forEach var="i" begin="${pi.startPage}" end ="${pi.endPage}">
			                	<c:choose>
			                		<c:when test="${i eq pi.currentPage}">
			                			<button class='btn btn-sm active' onclick="location.href='list.bo?currentPage=${i}&category=꿀팁'">${i}</button>
			                		</c:when>
			                		<c:otherwise>
			                			<button class='btn btn-sm' onclick="location.href='list.bo?currentPage=${i}&category=꿀팁'">${i}</button>
			                		</c:otherwise>
			                	</c:choose>
			                </c:forEach>
			                <c:choose>
			                    <c:when test="${pi.currentPage eq pi.maxPage}">
			                        <button  class='btn btn-sm' disabled>다음</button>
			                    </c:when>
			                    <c:otherwise>
			                        <button  class='btn btn-sm' onclick="location.href='list.bo?currentPage=${pi.currentPage+1}&category=꿀팁'">다음</button>
			                    </c:otherwise>
			                </c:choose>
						</div>
					</c:when>
					<c:otherwise>
						<div class="categoryList" align="center">
			                <c:choose>
			                    <c:when test="${pi.currentPage eq 1}">
			                        <button  class='btn btn-sm' disabled>이전</button>
			                    </c:when>
			                    <c:otherwise>
			                        <button  class='btn btn-sm' onclick="location.href='ggulPostByCategory.po?currentPage=${pi.currentPage-1}&categoryNo=${categoryNo}'">이전</button>
			                    </c:otherwise>
			                </c:choose>
			                <c:forEach var="i" begin="${pi.startPage}" end ="${pi.endPage}">
			                	<c:choose>
			                		<c:when test="${i eq pi.currentPage}">
			                			<button class='btn btn-sm active' onclick="location.href='ggulPostByCategory.po?currentPage=${i}&categoryNo=${categoryNo}'">${i}</button>
			                		</c:when>
			                		<c:otherwise>
			                			<button class='btn btn-sm' onclick="location.href='ggulPostByCategory.po?currentPage=${i}&categoryNo=${categoryNo}'">${i}</button>
			                		</c:otherwise>
			                	</c:choose>
			                </c:forEach>
			                <c:choose>
			                    <c:when test="${pi.currentPage eq pi.maxPage}">
			                        <button  class='btn btn-sm' disabled>다음</button>
			                    </c:when>
			                    <c:otherwise>
			                        <button  class='btn btn-sm' onclick="location.href='ggulPostByCategory.po?currentPage=${pi.currentPage+1}&categoryNo=${categoryNo}'">다음</button>
			                    </c:otherwise>
			                </c:choose>
						</div>
					</c:otherwise>
					
				</c:choose> 
				
				
				
				<br><br><br>
			</div>
		</div>
		<div class="search_area">
			<form method="get" action="${contextPath }/pSearch.bo"> 
				<select id="searchCondition" name="searchCondition">
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="nickname">닉네임</option>
					<option value="titlecontent">제목+내용</option>
				</select> <span class="input_area"> 
				<input type="search" id="searchValue" name="searchValue">
				<input type="text" name="category" value="꿀팁" hidden>
				<input type="text" name="currentPage" value="1" hidden>
				</span>
				<button type="submit" onclick="displaySearchPiDiv();">검색하기</button>
			</form>
		</div>
		
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>


</html>
