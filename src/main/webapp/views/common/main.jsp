<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL"
	crossorigin="anonymous"></script>
<!-- jQuery library -->
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<!-- Popper JS -->
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<style>
main {
	position: relative;
	display: flex;
	align-items: flex-start;
	justify-content: center;
	width: 100%;
}

article{


    align-items: center;

    width: 1050px;
    min-width: 1050px;
}
article .mediaContent,.postContent{
	width:100%;
	display:flex;
	margin-bottom:30px;
}

.mediaContent>div{
	margin:5px;
	width:50%;
	
}
.postContent>div{
	margin:5px;
	width:50%;
	
}
.youtubeArea{
	
	height:300px;
}


.postArea{
	height:300px;
}
.introSite{
	background-color:black; 
	width:100%;
	margin-bottom:30px;
}
.introSite div{
	color:white;
}
<!--인기게시글 관련 style-->
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
     padding: 5.4px; /* 셀 내용과 경계선 사이 여백 추가 */
 }

.list-area, .list-area th, .list-area td {
    border: none; /* 테이블 셀 경계선 모두 제거 */
    
}
.list-area td{
	text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
    max-width: 100px;
}

.list-area tr {
	border-bottom : 1px solid lightgray;
}

.list-area th {
       background-color : rgb(241, 205, 27);
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
.pageBarDiv{
	align-items:center;
}
.list-area tbody tr:hover{
	cursor:pointer;
	background-color: #f1ec6f; /* 어두운 배경색으로 변경하고자 하는 색상으로 수정 */
    transition: background-color 0.3s ease; /* 부드러운 전환을 위한 설정 */
}
</style>
</head>
<body>
	
	<script>
		
	    function getPopularPost(currentPage) {
	    	$(".pageBarDiv").empty();
	    	$(".list-area>tbody").empty();
	        $.ajax({
	            url: "getPageInfo.po",
	            data: {
	                currentPage: currentPage
	            },
	            success: function(result) {
					
	            	var currentPage = $("<input id='currentPage' type='hidden'>").val(result.currentPage);

	           		$(".pageBarDiv").append(currentPage);
	            	if(result.currentPage == 1){
	            		var preButton = $("<button class='btn btn-sm' onclick='prevPageClick()' disabled></button>").text("이전");
	            		$(".pageBarDiv").append(preButton);
	            
	
	            	}else{
	            		var  preButton = $("<button class='btn btn-sm' onclick='prevPageClick()'></button>").text("이전");
	            		$(".pageBarDiv").append(preButton);
	            		
	            	}
	            	for(var i=result.startPage; i <= result.endPage;i++ ){
	            		var button;
	            	
	            		if(result.currentPage == i){
	            			 button = $("<button class='btn btn-sm pageNo active' onclick='pageNoClick(this)'></button>").text(i);
	            		}else{
	            			button = $("<button class='btn btn-sm pageNo' onclick='pageNoClick(this)'></button>").text(i);
	            		}
	            		
	            		$(".pageBarDiv").append(button);
	            		
	            	}
	            	if(result.currentPage == result.maxPage){
	            		var nextButton = $("<button class='btn btn-sm' onclick='nextPageClick()' disabled></button>").text("다음");
	            		$(".pageBarDiv").append(nextButton);
	            		
	            	}else{
	            		var nextButton = $("<button class='btn btn-sm' onclick='nextPageClick()'></button>").text("다음");
	            		$(".pageBarDiv").append(nextButton);
	            	}
	            	
	                $.ajax({
	                    url: "popularPost.po",
	                    data: {
	                        standard: $(".form-select").val() || 1,
	                        listCount: result.listCount,
	                        currentPage: result.currentPage,
	                        pageLimit: result.pageLimit,
	                        boardLimit: result.boardLimit,
	                        maxPage: result.maxPage,
	                        startPage: result.startPage,
	                        endPage: result.endPage
	                    },
	                    success: function(result) {
	                    	
	                        for (var i = 0; i < result.length; i++) {
	                            var tr = $("<tr></tr>");
	                            var postNo = $("<th></th>").text(result[i].postNo);
	                            var category = $("<td></td>").text(result[i].categoryName);
	                            var title = $("<td></td>").text(result[i].title);
	                            var count = $("<td></td>").text(result[i].count);
	                            var likeCount = $("<td></td>").text(result[i].likeCount);
	                            var interestCount = $("<td></td>").text(result[i].interestCount);
	                            var writer = $("<td></td>").text(result[i].nickname);
	                            var date = $("<td></td>").text(result[i].postDate);
	                            tr.append(postNo, category, title, count, likeCount, interestCount, writer, date);
	                            $(".list-area>tbody").append(tr);
	                         
	                        }
	                    },
	                    error: function() {
	                        console.error("popularPost.po Ajax 오류");
	                    }
	                });
	            },
	            error: function() {
	                console.error("getPageInfo.po Ajax 오류");
	            }
	        });
	    }
	    window.onload = getPopularPost(1);
	    
	    
	 
	</script>
		
		<main style="height: auto;">
		<article>
			<div class="introSite">
				<div style="height:300px;">
					<img src="resources/assets/img/main-card-black.png" alt="main-card"
								class="main-card">
				</div>
			</div>
			<div class="mediaContent">
				
				<div style=" background-color:red;">
					<div class="youtubeArea">
	
						<iframe style="width: 100%; height:100%;" src="https://www.youtube.com/embed/iOJM4GUnE04?si=30Jdgkuno2_JOAQ4" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
					</div>
				</div>
				<div style="background-color:blue;">
					<div class="youtubeArea">


						<iframe style="width: 100%; height:100%;" src="https://www.youtube.com/embed/WjKGpKtQHzA?si=Bu7-8D7KBgeF_zaT" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" allowfullscreen></iframe>
					</div>
				</div>
			</div>
			<div style="height: 50px; display: flex; justify-content: space-between;">
				<h5 style="color: var(--maincolor); font-family: 'SEBANG_Gothic_Bold'; margin:0;">인기 게시글</h5>
				<select class="form-select form-select-sm" style="width:150px; height:35px;">
				  <option value="1" selected>조회순</option>
				  <option value="2">좋아요순</option>
				  <option value="3">관심등록순</option>
				</select>
				<script>
					//정렬 기준 select에 변화가 있으면
					$(".form-select").change(function(){
						 $(".list-area>tbody").empty();
						getPopularPost(1);
					});
					
				</script>
				
			</div>

			
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
				<thead style=" color: black; height:45px ;font-family: GmarketSansMedium; font-size : 13px">
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
				
			</tbody>
			</table>	
			
			<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
			<script>
			
			//행 클릭시 게시글 상세보기로 이동
			$(function(){
				$(".list-area>tbody").on("click","tr",function(){
				
					location.href = "${contextPath}/detail.bo?postNo="+$(this).children().eq(0).text();
				});

			});
			</script>
			
			<br>
			
			<!-- 페이징바 -->
			<div align="center" class="pageBarDiv" >
			    

			</div>
	
			<script>
				function prevPageClick(){
					
					getPopularPost(parseInt($("#currentPage").val())-1);
					
				}
				
				function pageNoClick(button){
					
					$(button).addClass('active');
					getPopularPost($(button).text());
					
				}
				function nextPageClick(){

					getPopularPost(parseInt($("#currentPage").val())+1);	
					
				}
			</script>
			<br><br><br>
		</div>
		
		
			

		</article>
		
	</main>
</body>
</html>