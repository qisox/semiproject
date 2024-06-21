<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이페이지 내 내가 쓴 글 탭 클릭시 나오는 테이블</title>
</head>
<body>
	<div class="tab-pane fade profile-edit pt-3" id="profile-post">
		<div id="myPost-header">
			<div class="flexDiv">
				<h5 class="card-title">내가 쓴 글</h5>
				<div class="search">
				  <input type="text" class="search-input" id="myPostSearchInput" placeholder="제목으로 검색">
				  <img class="search-icon" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png">
				</div>
			</div>
			<div class="flexDiv">
				<button id="myPostChkBoxBtn" class="btn btn-primary btn-sm" style="display:none">선택</button>
				<button id="deleteMyPostBtn" class="btn btn-danger btn-sm" style="display:none">삭제</button>	
			</div>
		</div>
		
	      	<div class="table-container">
		      <table class="table table-dark table-hover myTable" id="myPostTable">
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
	<!------------------------내가 쓴 글 태그 관련 스크립트 시작------------------------------------>
	<script>
		//내가 쓴 글 탭 클릭 이벤트
		$("#myPostTab").click(function(){   
		    $.ajax({
		        url: "myPostList.my",
		        data:{
		            mno:"${loginMember.memberNo}"
		        },
		        success : function(result){
		        	resetMyPostTable();
		        	changeToSelectMyPost()
		            //내가 쓴 게시글이 존재한다면
		            if(result.length>0){
		            	$('#myPostChkBoxBtn').attr('style','display:block');
		            	appendMyPostToTable(result);
		            } else {
		            	//내가 쓴 게시글이 존재하지 않는다면
		            	$('#myPostChkBoxBtn').attr('style','display:none');
		                var tr = $("<tr></tr>").append($("<td colspan='6'></td>").text("내가 쓴 게시글이 존재하지 않습니다."));
		                $("#myPostTable>tfoot").append(tr);
		            }
		            
		        
		        },
		        error : function(){
		            console.log("내가 쓴 글 탭 클릭 이벤트 실패");
		        }
		    });
		});
		
		//내가 쓴 게시글 tr 클릭 이벤트
		$("#myPostTable>tbody").on("click","tr td.title",function(){
			var parentTr = $(this).closest('tr');
			//게시판 상세보기로 이동하도록
	        //parentTr.children().first().text() -> 클릭된 행중 첫번째 요소(폴더 or 게시글 번호)
	        location.href = "${contextPath}/detail.bo?postNo="+parentTr.children().first().text();
		});
		//내가 쓴 글 테이블 체크박스 생성하는 버튼
		$('#myPostChkBoxBtn').click(function() {
			//LikeTable안에 LikeChkBox라는 클래스이름을 가진 요소가 없으면 체크박스 추가, 버튼의 text가 '선택' 이라면 선택버튼 역할 수행
			if (($('#myPostTable').find('.myPostChkBox').length === 0) && $('#myPostChkBoxBtn').text() == "선택" ) {
				//선택 해제로 변경
				changeToDeselectMyPost();
				$('#myPostTable thead tr').append('<th class="chkBoxTh">선택</th>');
				$('#myPostTable tbody tr').each(function() {
                    $(this).append('<td class="tdForChkBox"><input type="checkbox" name="" class="myPostChkBox"></td>');
            	});
            } else {
            	//선택 해제 버튼의 역할 수행,선택 버튼으로 변경
            	changeToSelectMyPost();
            }
        });

		//내가 쓴 글 삭제 
		$('#deleteMyPostBtn').click(function(){
			if($(".myPostChkBox:checked").length>0){
				if(confirm("정말로 삭제하시겠습니까?")){
					//체크된 게시글 번호를 담을 배열
					var postNoArr = [];
					//likeChkBox라는 클래스이름을 가진 chkBox중 체크 된것들 반복
					$(".myPostChkBox:checked").each(function(index,element){
							postNoArr.push($(element).closest('tr').find('th:first-child').text());
						
					});
					
					$.ajax({
						url:"myPostDelete.my",
						data:{
							mno : "${loginMember.memberNo}",
							postNoArr : postNoArr
						},
						async : false,
						success : function(result){
							console.log("통신 성공");
							if(result == "success"){
								$("#myPostTab").click();
								alert("삭제 성공");
								
							}else {
								alert("삭제 실패");
							}
						},
						error : function(){
							console.log("내가 쓴 글 삭제 통신 실패");
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
			
			$("#myPostSearchInput").keyup(function(){
			   clearTimeout(timeout); // 기존 타이머를 취소
			
			   timeout = setTimeout(function () {
					// 1초 이후에 실행할 함수를 여기에 작성
					console.log($("#myPostSearchInput").val());
					var inputData = $("#myPostSearchInput").val();
					if(inputData == ""){
						$("#myPostTab").click();
					} else {
						$.ajax({
							url:"searchMyPost.my",
							data:{
								inputData: inputData,
								mno:"${loginMember.memberNo}"
							},
							success:function(result){
								resetMyPostTable();
								changeToSelectMyPost();
								if(result.length !=0){
									appendMyPostToTable(result);
								} else{
									//보여줄 폴더나 게시글이 존재하지 않는다면
							        $('#myPostChkBoxBtn').attr('style','display:none');
							        var tr = $("<tr></tr>").append($("<td colspan='6'></td>").text("검색결과에 일치하는 게시글이 존재하지 않습니다."));
							        $("#myPostTable>tfoot").append(tr);
								}
								
							},
							error:function(){
								console.log("검색창 입력 이벤트 통신 실패");
							}
						});
					}
			   }, 800);
			 });
	    }); 
		//테이블에 글 넣는 반복문 함수
		function appendMyPostToTable(result){
			for(var i=0; i<result.length;i++){
                var tr = $("<tr></tr>");
                var postNo = $("<th></th>").text(result[i].postNo);
                var category = $("<td></td>").text(result[i].categoryNo);
                var title = $("<td class='title'></td>").text(result[i].title);
                var count = $("<td></td>").text(result[i].count);
                var writer = $("<td></td>").text(result[i].writerNo);
                var date = $("<td></td>").text(result[i].enrollDate);
                tr.append(postNo,category,title,count,writer,date);
                $("#myPostTable>tbody").append(tr);
           	}
		}
		
		//테이블 비우기
		function resetMyPostTable(){
			 $("#myPostTable>tbody").empty();
	         $("#myPostTable>tfoot").empty();
		}

		//선택 해제 -> 선택
		function changeToSelectMyPost(){
			$('#deleteMyPostBtn').attr('style','display:none');
			$('#myPostChkBoxBtn').attr('class','btn btn-primary btn-sm');
			$('#myPostChkBoxBtn').text('선택');
			$('#myPostTable thead tr .chkBoxTh').remove();
			$('#myPostTable tbody tr .myPostChkBox').parents('td').remove();
		}
		//선택 >- 선택 해제
		function changeToDeselectMyPost(){
			$('#deleteMyPostBtn').attr('style','display:block');
			$('#myPostChkBoxBtn').attr('class','btn btn-secondary btn-sm');
			$('#myPostChkBoxBtn').text('선택 해제');
			
		}
		</script>
	<!------------------------내가 쓴 글 태그 관련 스크립트 끝------------------------------------>
</body>
</html>