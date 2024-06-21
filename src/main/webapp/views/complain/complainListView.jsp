<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	  .rectangle {
	            width:100%;
	            height: 0.5px;
	           	background-color : black;
	        }
		      
		        
		.list-area > tbody tr:hover {
	        	background-color : rgb(255, 225, 77);
	        	cursor : pointer;
	       }

	
		@font-face {
			    font-family: 'SEBANG_Gothic_Bold';
			    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2104@1.0/SEBANG_Gothic_Bold.woff') format('woff');
			    font-family: 'GmarketSansMedium';
		    	src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
			    font-weight: normal;
			    font-style: normal;
			}	    
	
	   .list-area {
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
			border-bottom : 1px solid lightgray;
		}
		
		.list-area th {
	        background-color : rgb(241, 205, 27);
		}
		
		footer{
		    border-top: 1px solid #e4e4e4;
		    background-color:#f8f9fa;
		    padding:1rem 0;
		    margin:1rem 0;
		}
		
		
		.footer-message{
		    font-weight: bold;
		    font-size:0.9rem;
		    color:#545e6f;
		    margin:0 0 0 0.6rem;
		}
		.footer-contact{
		    font-size:0.9rem;
		    color:#545e6f;
		    margin:0.6rem;
		}
		.footer-copyright{
		    font-size:0.9rem;
		    color:#545e6f;
		    margin:0.6rem;
		}	
		 footer {
        background-color: rgb(171, 171, 171);
        padding: 1rem 0;
        margin: 0;
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
    .marquee-container {
      width: 100%; 
      overflow: hidden;
      position: relative;
      color:red;
      padding: 20px; 
      display: flex;
      justify-content: center;
      align-items: center;
      
      margin: 0;
    }

    .marquee {
      white-space: nowrap;
      animation: marquee 10s linear infinite;
    }
    


    @keyframes marquee {
      0% {
        transform: translateX(-100%);
      }
      100% {
        transform: translateX(100%);
      }
    }
	 
	</style>
	
</head>
<body>
	<%@include file="/views/common/menubar.jsp" %>
			<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
	
	<!-- 상위폴더로 -->

	<div class="outer">
		<br><br>
		<div>
		    <h5 align="left" style="font-size: 28px; color: rgb(241, 205, 27); font-family: SEBANG_Gothic_Bold; display: inline-block; margin-left : 350px">
		        신고/건의
		    </h5>
		    <c:if test="${not empty loginMember }">
		    <a href="${contextPath}/complainInsert.bo" class="btn btn-warning" style="display: inline-block; margin-left: 740px;">글작성</a>
		    </c:if>
		<div class="marquee-container" >
		    <div class="marquee" style="font-weight: bold;">
      <!-- 이동할 텍스트 -->
      		신고 게시물은 작성자만 열람 가능합니다
    	</div>
  	</div>
		    
		</div>
		<br>
		
		<table class="list-area" align="center" border = "1">
			<thead style=" color: black; height:45px ;font-family: GmarketSansMedium; font-size : 17px">
				<tr>
					<th width="70">글 번호</th>
					<th width="100">건의/신고</th>
					<th width="230">제목</th>
					<th width="100">작성자</th>
					<th width="70">조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>
				<!-- 리스트가 비어있는 경우  -->
				<c:choose>
					<c:when test="${empty list}">
						<tr>
							<td colspan = '7'> 게시글이 없습니다 </td>
						</tr>
					</c:when>
					<c:otherwise>
					<!-- 목록이 존재하는경우 -->
					<c:forEach items = "${list}" var = "c">
						 <c:if test="${c.complainCategory == '신고' && (loginMember.grade == '관리자' || loginMember.nickname == c.complainWriter)}">
						        <tr>
						            <td>${c.complainNo}</td>
						            <td style="font-weight: bold; color: red;">${c.complainCategory}</td>
						            <td style="font-weight: bold;">${c.complainTitle}</td>
						            <td>${c.complainWriter}</td>
						            <td>${c.count }</td>
						            <td>${c.complainDate}</td>
						        </tr>
						    </c:if>
						    <c:if test="${c.complainCategory == '건의'}">
						        <tr>
						            <td>${c.complainNo}</td>
						            <td style="font-weight: bold;">${c.complainCategory}</td>
						            <td style="font-weight: bold;">${c.complainTitle}</td>
						            <td>${c.complainWriter}</td>
						            <td>${c.count }</td>
						            <td>${c.complainDate}</td>
						        </tr>
						    </c:if>
					</c:forEach>
				</c:otherwise>
				</c:choose>
				</tbody>
			</table>	
			
			<script>
			$(function(){
				$(".list-area>tbody>tr").click(function(){
				
					location.href = "complainDetail.bo?complainNo="+$(this).children().eq(0).text();
				});

			});
			</script>
			
			<br>
			
			<!-- 페이징바 -->
			<div align="center" class="wholeList">
                    <c:choose>
	                    <c:when test="${pi.currentPage eq 1}">
	                        <button class='btn btn-sm'  disabled>이전</button>
	                    </c:when>
	                    <c:otherwise>
	                        <button class='btn btn-sm' onclick="location.href='complainList.bo?currentPage=${pi.currentPage-1}'">이전</button>
	                    </c:otherwise>
	                </c:choose>
	                <c:forEach var="i" begin="${pi.startPage}" end ="${pi.endPage}">
	                    <button class='btn btn-sm active' onclick="location.href='complainList.bo?currentPage=${i}'">${i}</button>
	                </c:forEach>
	                <c:choose>
	                    <c:when test="${pi.currentPage eq pi.maxPage}">
	                        <button class='btn btn-sm' disabled>다음</button>
	                    </c:when>
	                    <c:otherwise>
	                        <button class='btn btn-sm' onclick="location.href='complainList.bo?currentPage=${pi.currentPage+1}'">다음</button>
	                    </c:otherwise>
	                </c:choose>
			</div>
			<br><br><br>
		</div>
<%-- 		
		<div class="search_area">
			<form method="get" action="${contextPath }/pSearch.po"> 
				<select id="searchCondition" name="searchCondition">
					<option value="title">제목</option>
					<option value="content">내용</option>
					<option value="nickname">닉네임</option>
					<option value="titlecontent">제목+내용</option>
				</select> <span class="input_area"> <input type="search"
					name="searchValue">
				</span>
				<button type="submit">검색하기</button>
					<!--c:if test 변수 보시고 수정해주세요-->
				<c:if test="${ userLogin.userId eq 'admin' }">
					<!--아래 작성하기 버튼 혹시 없으면 아래 경로 수정해주시고 있으면 지워주세요-->
					<button type="button" onclick='location.href="${ contextPath }/notice/insert"'>작성하기</button> 
				</c:if>
			</form>
			<script>
				function detailView(nno){ // 경로보고 아래 링크 수정해주세요
					location.href = '${contextPath}/notice/detail?nno=' + nno;
				}
			</script>
	</div>
	 --%>
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>


</html>
