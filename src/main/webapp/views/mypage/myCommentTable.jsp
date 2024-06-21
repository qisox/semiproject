<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="tab-pane fade profile-edit pt-3" id="profile-comment">
		<div id="myComment-header">
			<div class="flexDiv">
				<h5 class="card-title">내가 쓴 댓글</h5>
				<div class="search">
				  <input type="text" class="search-input" id="myCommentSearchInput" placeholder="내용으로 검색">
				  <img class="search-icon" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png">
				</div>
			</div>
			<div class="flexDiv">
				<button id="myCommentChkBoxBtn" class="btn btn-primary btn-sm" style="display:none">선택</button>
				<button id="deleteMyCommentBtn" class="btn btn-danger btn-sm" style="display:none">삭제</button>	
			</div>
		</div>
		
	      	<div class="table-container">
		      <table class="table table-dark table-hover myTable" id="myCommentTable">
					<colgroup>
					      <col width="5%" />
					      <col width="10%" />
					      <col width="30%" />
					      <col width="10%" />
					      <col width="10%" />
					      <col width="10%" />
					</colgroup>
					<thead>
						
						<tr>
							<th>번호</th>
							<th>카테고리</th>
							<th>내용</th>
							<th>작성자</th>
							<th>작성일</th>
							<th>원글 제목</th>

						</tr>
					</thead>
					<tbody>
					
					</tbody>
					<tfoot>
					
					</tfoot>
				</table>
			</div>
	</div>
	<!------------------------내가 쓴 댓글 태그 관련 스크립트 시작------------------------------------>
	<script>
		//내가 쓴 댓글 탭 클릭 이벤트
		$("#myCommentTab").click(function(){   
		    $.ajax({
		        url: "myCommentList.my",
		        data:{
		            mno: "${loginMember.memberNo}"
		        },
		        success : function(result){
		        	resetCommentTable();
		        	changeToSelectMyComment();            
		            //내가 쓴 게시글이 존재한다면
		            if(result.length>0){
		            	$('#myCommentChkBoxBtn').attr('style','display:block');
		            	appendToCommentTable(result);
		            } else {
		            	//내가 쓴 게시글이 존재하지 않는다면
		            	$('#myCommentChkBoxBtn').attr('style','display:none');
		                var tr = $("<tr></tr>").append($("<td colspan='6'></td>").text("내가 쓴 댓글이 존재하지 않습니다."));
		                $("#myCommentTable>tfoot").append(tr);
		            }
		            
		        
		        },
		        error : function(){
		            console.log("내가 쓴 댓글 탭 클릭 이벤트 실패");
		        }
		    });  
		});
		//내가 쓴 댓글 tr 클릭 이벤트
		$("#myCommentTable>tbody").on("click","tr td.title",function(){
			var parentTr = $(this).closest('tr');
			//게시판 상세보기로 이동하도록
	        //parentTr.children().first().text() -> 클릭된 행중 첫번째 요소(폴더 or 게시글 번호)
	        location.href = "${contextPath}/detail.bo?postNo="+parentTr.children().first().text();
		});
		
		//내가 쓴 댓글 테이블 체크박스 생성하는 버튼
		$('#myCommentChkBoxBtn').click(function() {
			if (($('#myCommentTable').find('.myCommentChkBox').length === 0) && $('#myCommentChkBoxBtn').text() == "선택") {
				changeToDeselectMyComment();
				$('#myCommentTable thead tr').append('<th class="chkBoxTh">선택</th>');
				$('#myCommentTable tbody tr').each(function() {
                    $(this).append('<td class="tdForChkBox"><input type="checkbox" name="" class="myCommentChkBox"></td>');
            	});
            } else {
            	changeToSelectMyComment();
            }
        });
		
    	
		//내가 쓴 댓글 삭제 
		$('#deleteMyCommentBtn').click(function(){
			if($(".myCommentChkBox:checked").length>0){
				if(confirm("정말로 삭제하시겠습니까?")){
					//체크된 게시글 번호를 담을 배열
					var commentNoArr = [];
					//likeChkBox라는 클래스이름을 가진 chkBox중 체크 된것들 반복
					$(".myCommentChkBox:checked").each(function(index,element){
						commentNoArr.push($(element).closest('tr').find('th:first-child').text());
					});
					
					$.ajax({
						url:"myCommentDelete.my",
						data:{
							mno : "${loginMember.memberNo}",
							commentNoArr : commentNoArr
						},
					
						async : false,
						success : function(result){
							console.log("통신 성공");
							if(result == "success"){
								$("#myCommentTab").click();
								alert("삭제 성공");
								
							}else {
								alert("삭제 실패");
							}
						},
						error : function(){
							console.log("내가 쓴 댓글 삭제 통신 실패");
						}
					});
				} 
			}else {
				alert("삭제할 댓글을 선택해주세요.");
			}	
		});
		
		//검색창 입력 이벤트
		$(function () {
			var timeout = null; // 타이핑이 멈춘 후 대기 시간을 저장할 변수
			
			$("#myCommentSearchInput").keyup(function(){
			   clearTimeout(timeout); // 기존 타이머를 취소
			
			   timeout = setTimeout(function () {
					// 1초 이후에 실행할 함수를 여기에 작성
					console.log($("#myCommentSearchInput").val());
					var inputData = $("#myCommentSearchInput").val();
					if(inputData == ""){
						$("#myCommentTab").click();
					} else {
						$.ajax({
							url:"searchMyComment.my",
							data:{
								inputData: inputData,
								mno:"${loginMember.memberNo}"
							},
							success:function(result){
								resetCommentTable();
								changeToSelectMyComment();
								if(result.length !=0){
									appendToCommentTable(result);
								} else{
									//보여줄 폴더나 게시글이 존재하지 않는다면
							        $('#myCommentChkBoxBtn').attr('style','display:none');
							        var tr = $("<tr></tr>").append($("<td colspan='6'></td>").text("검색결과에 일치하는 댓글이 존재하지 않습니다."));
							        $("#myCommentTable>tfoot").append(tr);
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
		//테이블에 댓글 넣는 반복문 함수
		function appendToCommentTable(result){
			for(var i=0; i<result.length;i++){
                var tr = $("<tr></tr>");
                var commentNo = $("<th></th>").text(result[i].commentNo);
                var category = $("<td></td>").text(result[i].categoryName);
                var content = $("<td class='title'></td>").text(result[i].content);
                var writer = $("<td></td>").text(result[i].writerNo);
                var date = $("<td></td>").text(result[i].commentDate);
                var postTitle = $("<td></td>").text(result[i].postTitle);
                tr.append(commentNo,category,content,writer,date,postTitle);
                $("#myCommentTable>tbody").append(tr);
           	}
		}
		
		//삭제버튼, 선택해제버튼, 테이블 내 '선택' th 숨기기 (탭 이동이 있을 시 없애지 않으면 남아있음)
		function removeCommentChkThDeleteClearBtn(){
			$('#myCommentTable thead tr .chkBoxTh').remove();
            $('#deleteMyCommentBtn').attr('style','display:none');
			$('#clearMyCommentChkBoxBtn').attr('style','display:none');
		}
		//테이블 비우기
		function resetCommentTable(){
			 $("#myCommentTable>tbody").empty();
	         $("#myCommentTable>tfoot").empty();
		}
		
		//선택 해제 -> 선택
		function changeToSelectMyComment(){
			$('#deleteMyCommentBtn').attr('style','display:none');
			$('#myCommentChkBoxBtn').attr('class','btn btn-primary btn-sm');
			$('#myCommentChkBoxBtn').text('선택');
			$('#myCommentTable thead tr .chkBoxTh').remove();
			$('#myCommentTable tbody tr .myCommentChkBox').parents('td').remove();
		}
		//선택 >- 선택 해제
		function changeToDeselectMyComment(){
			$('#deleteMyCommentBtn').attr('style','display:block');
			$('#myCommentChkBoxBtn').attr('class','btn btn-secondary btn-sm');
			$('#myCommentChkBoxBtn').text('선택 해제');
			
		}
	</script>
	<!------------------------내가 쓴 댓글 태그 관련 스크립트 끝------------------------------------>
</body>
</html>