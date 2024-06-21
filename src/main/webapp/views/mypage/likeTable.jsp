<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 내 좋아요 탭 클릭시 나오는 테이블</title>
</head>
<body>
	<div class="tab-pane fade profile-edit pt-3" id="profile-like">
		<div id="like-header">
			<div class="flexDiv">
				<h5 class="card-title">좋아요 한 글</h5>
				<div class="search">
				  <input type="text" class="search-input" id="likeSearchInput" placeholder="제목으로 검색">
				  <img class="search-icon" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png">
				</div>
			</div>
			<div class="flexDiv">
				<button id="likeChkBoxBtn" class="btn btn-primary btn-sm" style="display:none;">선택</button>
				<button id="deleteLikeBtn" class="btn btn-danger btn-sm" style="display:none">삭제</button>	
			</div>
		</div>
		
	      	<div class="table-container">
		      <table class="table table-dark table-hover myTable" id="likeTable">
					<colgroup>
					      <col width="5%" />
					      <col width="10%" />
					      <col width="30%" />
					      <col width="10%" />
					      <col width="10%" />
					      <col width="15%" />
					</colgroup>
					<thead>
						
						<tr>
							<th>번호</th>
							<th>카테고리</th>
							<th>제목</th>						
							<th>조회수</th>
							<th>작성자</th>
							<th>등록일</th>
						</tr>
					</thead>
					<tbody>
					
					</tbody>
					<tfoot>
					
					</tfoot>
				</table>
			</div>
	</div>
	<!------------------------좋아요 태그 관련 스크립트 시작----------------------------------->
	<script>
		
		//좋아요 탭 클릭 이벤트
		$("#likePostTab").click(function(){
		    $.ajax({
		        url: "likeList.ll",
		        data:{
		            mno:"${loginMember.memberNo}"
		        },
		        success : function(result){
		        	//탭 클릭시 좋아요 테이블을 일단 비우기(비우지 않으면 다른 탭 갔다가 다시 좋아요 탭을 누르면 tr이 쌓임.)
		            resetLikeTable();
		        	//선택 버튼으로 초기화시키기
		            changeToSelectLike();
					 
		            //비운 다음 db에 접근해서 가져온 result값들로 채우기
		            if(result.length > 0){
		            	$('#likeChkBoxBtn').attr('style','display:block');
		            	appendLikePostToTable(result);
		            	for(var i=0; i<100;i++){
		                    var tr = $("<tr></tr>");
		                    var postNo = $("<th></th>").text(i+"");
		                    var category = $("<td ></td>").text(i+"");
		                    var title = $("<td class='title'></td>").text(i+"");
		                    var count = $("<td></td>").text(i+"");
		                    var writer = $("<td></td>").text(i+"");
		                    var date = $("<td></td>").text(i+"");
		                    tr.append(postNo,category,title,writer,count,date);
		                    $("#likeTable>tbody").append(tr);
		                }
		            } else{
		            	//내가 좋아요 한 게시글이 존재하지 않는다면
		            	$('#likeChkBoxBtn').attr('style','display:none');
		                var tr = $("<tr></tr>").append($("<td colspan='6'></td>").text("내가 쓴 게시글이 존재하지 않습니다."));
		                $("#likeTable>tfoot").append(tr);
		            } 
		        },
		        error : function(){
		            console.log("좋아요 탭 클릭 이벤트 실패");
		        }
		    });  
		});


		//좋아요 한 게시글 tr 클릭 이벤트
		$("#likeTable>tbody").on("click","tr td.title",function(){
			var parentTr = $(this).closest('tr');
			//게시판 상세보기로 이동하도록
	        //parentTr.children().first().text() -> 클릭된 행중 첫번째 요소(폴더 or 게시글 번호)
	        location.href = "${contextPath}/detail.bo?postNo="+parentTr.children().first().text();
		});
		
		//좋아요 테이블 체크박스 생성하는 버튼
		$('#likeChkBoxBtn').click(function() {
			
			//LikeTable안에 likeChkBox라는 클래스이름을 가진 요소가 없으면 체크박스 추가
			//만약 likeChkBoxBtn이 선택이라는 text를 가지고 있으면 '선택' 버튼의 역할을 수행
			if (($('#likeTable').find('.likeChkBox').length === 0)&&$('#likeChkBoxBtn').text()=="선택") {
				changeToDeselectLike();
				$('#likeTable thead tr').append('<th class="chkBoxTh">선택</th>');
				$('#likeTable tbody tr').each(function() {
                    $(this).append('<td class="tdForChkBox"><input type="checkbox" name="" class="likeChkBox"></td>');
            	});
            } else {
            	//만약 likeChkBoxBtn이 '선택해제'라는 text를 가지고 있으면 '선택 해제' 버튼의 역할을 수행
            	changeToSelectLike();
            }
        });
		

    	
		//좋아요 삭제 
		$('#deleteLikeBtn').click(function(){
			if($(".likeChkBox:checked").length>0){
				if(confirm("정말로 삭제하시겠습니까?")){
					//체크된 게시글 번호를 담을 배열
					var postNoArr = [];
					//likeChkBox라는 클래스이름을 가진 chkBox중 체크 된것들 반복
					$(".likeChkBox:checked").each(function(index,element){
							postNoArr.push($(element).closest('tr').find('th:first-child').text());
						
					});
					
					$.ajax({
						url:"likeDelete.my",
						data:{
							mno : "${loginMember.memberNo}",
							postNoArr : postNoArr
						},
					
						async : false,
						success : function(result){
							console.log("통신 성공");
							if(result == "success"){
								$("#likePostTab").click();
								alert("삭제 성공");
							}else {
								alert("삭제 실패");
							}
						},
						error : function(){
							console.log("좋아요 삭제 통신 실패");
						}
					});
				} 
			}else {
				alert("삭제할 게시글을 선택해주세요.");
			}	
		});
		
		//검색창 입력 이벤트
		$(function () {
			var timeout = null; // 타이핑이 멈춘 후 대기 시간을 저장할 변수
			
			$("#likeSearchInput").keyup(function(){
			   clearTimeout(timeout); // 기존 타이머를 취소
			
			   timeout = setTimeout(function () {
					// 1초 이후에 실행할 함수를 여기에 작성
					var inputData = $("#likeSearchInput").val();
					if(inputData == ""){
						$("#likePostTab").click();
					} else {
						$.ajax({
							url:"searchLike.my",
							data:{
								inputData: inputData,
								mno:"${loginMember.memberNo}"
							},
							success:function(result){
								resetLikeTable();
								changeToSelectLike();
								if(result.length !=0){
									//탭 클릭시 좋아요 테이블을 일단 비우기(비우지 않으면 다른 탭 갔다가 다시 좋아요 탭을 누르면 tr이 쌓임.)
									appendLikePostToTable(result);
								} else{

									//보여줄 폴더나 게시글이 존재하지 않는다면
							        $('#likeChkBoxBtn').attr('style','display:none');
							        var tr = $("<tr></tr>").append($("<td colspan='6'></td>").text("검색결과에 일치하는 좋아요한 게시글이 존재하지 않습니다."));
							        $("#likeTable>tfoot").append(tr);
								}
								
							},
							error:function(){
								console.log("검색창 입력 이벤트");
							}
						});
					}
			   }, 800);
			 });
	    }); 
		
		//테이블에 좋아요 글 넣는 반복문 함수
		function appendLikePostToTable(result){
			for(var i=0; i<result.length;i++){
                var tr = $("<tr></tr>");
                var postNo = $("<th></th>").text(result[i].postNo);
                var category = $("<td ></td>").text(result[i].categoryNo);
                var title = $("<td class='title'></td>").text(result[i].title);
                var count = $("<td></td>").text(result[i].count);
                var writer = $("<td></td>").text(result[i].writerNo);
                var date = $("<td></td>").text(result[i].enrollDate);
                tr.append(postNo,category,title,count,writer,date);
                $("#likeTable>tbody").append(tr);
            }
		}
		
		//테이블 초기화
		function resetLikeTable(){
			$("#likeTable>tbody").empty();
            $("#likeTable>tfoot").empty();
		}
		//선택 해제 -> 선택
		function changeToSelectLike(){
			$('#deleteLikeBtn').attr('style','display:none');
			$('#likeChkBoxBtn').attr('class','btn btn-primary btn-sm');
			$('#likeChkBoxBtn').text('선택');
			$('#likeTable thead tr .chkBoxTh').remove();
			$('#likeTable tbody tr .likeChkBox').parents('td').remove();
		}
		//선택 >- 선택 해제
		function changeToDeselectLike(){
			$('#deleteLikeBtn').attr('style','display:block');
			$('#likeChkBoxBtn').attr('class','btn btn-secondary btn-sm');
			$('#likeChkBoxBtn').text('선택 해제');
			
		}
	</script>
	
	<!------------------------좋아요 태그 관련 스크립트 끝------------------------------------>
		 
</body>
</html>