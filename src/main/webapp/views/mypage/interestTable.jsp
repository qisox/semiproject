<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>관심글 테이블</title>
<style>
	.modal-body input{
		width:100%;
		height:100%;
		border-width: 0;
	}
	#myFolderNames {
		color:#000080; <!--네이비-->
	}
	.modal-title b{
		color:#000080;
		margin-left: 15px;
	}
	
	#currentFolderName{
		color:#012970; <!--관심글 타이틀이랑 같은 색-->
	}
</style>
</head>
<body>
	<div class="tab-pane fade pt-3" id="profile-interest">
		<div id="interest-header">
			<div class="flexDiv">
				<h5 class="card-title">관심글</h5>
				<div class="search">
				  <input type="text" class="search-input" id="interestSearchInput" placeholder="제목으로 검색">
				  <img class="search-icon" src="https://s3.ap-northeast-2.amazonaws.com/cdn.wecode.co.kr/icon/search.png">
				</div>
			</div>
			
			<div class="flexDiv">
				<button id="goMoveFolderModal" class="btn btn-dark btn-sm" data-bs-toggle="modal" data-bs-target="#moveFolderModal">폴더 이동</button>
				<button id="goAddFolderModal" class="btn btn-primary btn-sm" data-bs-toggle="modal" data-bs-target="#addFolderModal" >폴더 추가</button>
				<button id="interestChkBoxBtn" class="btn btn-primary btn-sm">선택</button>
				<button id="deleteInterestBtn" class="btn btn-danger btn-sm" style="display:none">삭제</button>	
			</div>
						
		</div>
		<button type="button" id="tableBackBtn" class="btn btn-dark back-btn">뒤로가기</button>
		 <!--현재 폴더 값 담아둘 hidden input태그 -->
		<input type="hidden" id="currentFolderNo" value="0">
		<div>
			<p id="currentFolderName">
				<b>현재 폴더 : /</b>
			</p>
		</div>
		<div class="table-container">
			<table class="table table-dark table-hover myTable" id="interestTable">
				<colgroup>
				      <col width="10%" />
				      <col width="15%" />
				      <col width="40%" />
				      <col width="15%" />
				</colgroup>
				<thead>
					<tr>
						<th>번호</th>
						<th>분류</th>
						<th colspan='2'>제목</th>
						<th>수정일</th>
					</tr>
				</thead>
				<tbody>
				</tbody>
				<tfoot>
				</tfoot>
			</table>
		</div>
		
		<!-- 폴더 추가 모달창 -->
		<div class="modal fade" id="addFolderModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div>
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h1 class="modal-title fs-5" id="exampleModalLabel">폴더 추가</h1>
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			        <input type="text" id="addFolderInput" placeholder="추가할 폴더 이름 입력">
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			        <button type="button" class="btn btn-primary" id="addFolderBtn" data-bs-dismiss="modal">폴더 추가</button>
			      </div>
			    </div>
			  </div>
			</div>
		</div>
		<!-- 폴더 이동 모달창 -->
		<div class="modal fade" id="moveFolderModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
			<div>
			  <div class="modal-dialog">
			    <div class="modal-content">
			      <div class="modal-header">
			        <h1 class="modal-title fs-5" id="exampleModalLabel">이동할 폴더의 번호 입력<b>[폴더이름 - 폴더번호]</b></h1>
			        
			        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
			      </div>
			      <div class="modal-body">
			      	<b>내 폴더 목록 [최상단으로 가려면 0입력]</b>
			      	
			      	<p id="myFolderNames"></p>
			        <input type="text" id="moveFolderInput" placeholder="이동할 폴더의 번호 입력">
			      </div>
			      <div class="modal-footer">
			        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
			        <button type="button" class="btn btn-primary" id="moveFolderBtn" data-bs-dismiss="modal">폴더 이동</button>
			      </div>
			    </div>
			  </div>
			</div>
		</div>
	
	</div>
	<!------------------------관심글 태그 관련 스크립트 시작------------------------------------>
	<script>
		//관심탭 클릭 이벤트 (관심글 모아둔 테이블에 최상위 폴더,게시글 보여주기)
		$("#interestPostTab").click(function(){
		    //ajax를 이용하여 클릭된 폴더를 상위폴더로 취하는 폴더 리스트와
		    //클릭된 폴더번호를 갖고있는 관심글 리스트를 가져올 리스트를 만들어 둔다.
		    //db로부터 폴더/게시글을 조회해서 가져오기 전에 tbody tfoot 비우기
		    resetTable();
		    changeToSelectInterest();
		    $("#tableBackBtn").attr("style", "display:none;");
		    
		    //폴더 리스트 / 게시글 리스트
		    var fList = [];
		    var pList = [];		    
		    
		    //최상단 폴더 불러오기
		    $.ajax({
		        url : "topFolder.fd",
		        data :{ 
		            //유저 번호 전달
		            mno : "${loginMember.memberNo}"
		        },
		        async:false, //비동기처리를 순서대로 하기 위해 추가
		        success : function(result){
		            fList = result;
		        },
		        error : function(){
		            console.log("최상단 폴더 불러오기 실패");
		        }
		    });
		    //최상단 게시글 불러오기
		    $.ajax({
		        url : "topInterst.in",
		        data :{ 
		            //유저 번호 전달
		            mno : "${loginMember.memberNo}"
		        },
		        async:false, //비동기처리를 순서대로 하기 위해 추가
		        success : function(result){
		            pList = result;
		        },
		        error : function(){
		            console.log("최상단 게시글 불러오기 실패");
		        }
		    }); 
		    //보여줄 폴더나 게시글이 존재한다면		
		    if(fList.length != 0 || pList.length !=0){
		    	$('#interestChkBoxBtn').attr('style','display:block');
		        appendFolderToTable(fList);
		        appendPostToTable(pList);
		    } else {
		        //보여줄 폴더나 게시글이 존재하지 않는다면
		        $('#interestChkBoxBtn').attr('style','display:none');
		        var tr = $("<tr></tr>").append($("<td colspan='6'></td>").text("관심 게시글이 존재하지 않습니다."));
		        $("#interestTable>tfoot").append(tr);
		    }
		});

		//관심태그 내 테이블에 존재하는 행 내 제목(폴더 또는 게시글) 클릭 이벤트
		$("#interestTable>tbody").on("click","tr td.title",function(){
			var parentTr = $(this).closest('tr');
		    //분류가 폴더일때는 하위폴더로 이동하도록
		    if(parentTr.children().eq(1).text() == '폴더'){
		        //ajax를 이용하여 클릭된 폴더를 상위폴더로 취하는 폴더 리스트와
		        //클릭된 폴더번호를 갖고있는 관심글 리스트를 가져올 리스트를 만들어 둔다.
		        var fList = [];
		        var pList = [];
		        
		        //폴더 클릭 시 현재 폴더 p태그에 나타내기 추후 뒤로가기할때 요소 하나씩 빼려면 class 네임 부여 필요해보임
		        $("#currentFolderName").append( 
		        						$("<b class='currentFolderName'></b>").append(parentTr.children().eq(2).text()+"/")
		        						);
		        
		        //하위폴더에 존재하는 폴더 구현
		        $.ajax({
		        url : "subFolder.fd",
		        data :{ 
		            //폴더 번호 전달
		            fno : parentTr.children().first().text(),
		            mno : "${loginMember.memberNo}"
		        },
		        async:false, //async false로 처리하면 순서대로 처리 가능.
		        success : function(result){
		            //미리 만들어둔 리스트에 일단 저장
		            fList = result;
		        },
		        error : function(){
		            console.log("하위폴더에 존재하는 폴더 구현 실패");
		        }
		        });
		        //하위폴더에 존재하는 게시글 구현
		        $.ajax({
		            url : "subInterst.in",
		            data :{ 
		                //유저 번호 전달
		                mno : "${loginMember.memberNo}",
		                fno : parentTr.children().first().text()
		            },
		            async:false,
		            success : function(result){
		                //미리 만들어둔 리스트에 일단 저장
		                pList = result;
		            },
		            error : function(){
		                console.log("하위폴더에 존재하는 게시글 구현 실패");
		            }
		        }); 
		        
		        
	            //폴더를 눌렀을때 보여줄 폴더나 게시글이 존재한다면 테이블 비우고 다시 채우기
	            resetTable();
	            changeToSelectInterest();
	            //뒤로가기 버튼 보이게 하기
	            $("#tableBackBtn").attr("style", "display:inline-block;");
	            //현재 클릭한 폴더번호를 저장해두기
	            $("#currentFolderNo").val(parentTr.children().first().text());
	            console.log($("#currentFolderNo").val());

	          	//보여줄 폴더나 게시글이 존재한다면		
			    if(fList.length != 0 || pList.length !=0){
			    	$('#interestChkBoxBtn').attr('style','display:block');
			        appendFolderToTable(fList);
			        appendPostToTable(pList);
			    } else {
			        //보여줄 폴더나 게시글이 존재하지 않는다면
			        $('#interestChkBoxBtn').attr('style','display:none');
			        var tr = $("<tr></tr>").append($("<td colspan='6'></td>").text("관심 게시글이 존재하지 않습니다."));
			        $("#interestTable>tfoot").append(tr);
			    }
		        
		        
		        
		    } else {
		        //분류가 게시판일때는 게시판 상세보기로 이동하도록
		        //parentTr.children().first().text() -> 클릭된 행중 첫번째 요소(폴더 or 게시글 번호)
		        location.href = "${contextPath}/detail.bo?postNo="+parentTr.children().first().text();
		        
		    }
		    
		});

		//관심글 폴더 구조 뒤로가기 버튼 구현
		$("#tableBackBtn").click(function(){
		    //ajax를 이용하여 클릭된 폴더를 상위폴더로 취하는 폴더 리스트와
		    //클릭된 폴더번호를 갖고있는 관심글 리스트를 가져올 리스트를 만들어 둔다.
		    //db로부터 폴더/게시글을 조회해서 가져오기 전에 tbody tfoot 비우기
		    resetTable();
		    changeToSelectInterest();
		    //result 리스트를 담을 빈 리스트 준비. 담아서 한번에 처리하기 위함
		    var fList = [];
		    var pList = [];
		    
		    //현재 폴더 번호를 저장
		    var cuFolderNo = $("#currentFolderNo").val();
		    //현재 폴더 이름 저장한 p태그에서 currentFolderName 이란 클래스 이름을 가진 요소들 중 마지막 제거
		    $(".currentFolderName:last").remove();
		    
		    //상위폴더 리스트 가져오기
		    $.ajax({
		        url : "backFolder.fd",
		        data :{ 
		            //유저 번호
		            mno : "${loginMember.memberNo}",
		            //현재 폴더 번호 전달
		            cuFolderNo : cuFolderNo
		        },
		        async:false, //비동기처리를 순서대로 하기 위해 추가
		        success : function(result){
		            fList = result;
		            //현재 폴더 번호를 upFolderNo로 변경해주기. 뒤로가기할때 현재 폴더번호를 상위폴더번호로 변경하는 것.
		            $("#currentFolderNo").val(fList[0].upFolderNo);
		            console.log($("#currentFolderNo").val());
		            if($("#currentFolderNo").val() == 0){
		                //뒤로가기 버튼 안 보이게 하기
		                $("#tableBackBtn").attr("style", "display:none;");
		            }      
		        },
		        error : function(){
		            console.log("상위폴더 리스트 가져오기 실패");
		        }
		    });
		    //상위폴더에 있는 게시글 가져오기
		    $.ajax({
		        url : "backInterest.in",
		        data :{ 
		            //유저 번호
		            mno : "${loginMember.memberNo}",
		            //현재 폴더 번호 전달
		            cuFolderNo : cuFolderNo
		        },
		        async:false, //비동기처리를 순서대로 하기 위해 추가
		        success : function(result){
		            pList = result;
		        },
		        error : function(){
		            console.log("상위폴더에 있는 게시글 가져오기 실패");
		        }
		    }); 
		    
		    //상위폴더로 갔을때 폴더나 게시글 둘중 하나라도 있다면
		    if(fList.length != 0 || pList.length != 0){
		    	//선택 버튼 없어졌을 경우 다시 보이도록
		    	$('#interestChkBoxBtn').attr('style','display:block');
		        //폴더를 눌렀을때 보여줄 폴더나 게시글이 존재한다면 테이블 비우고 다시 채우기
		        resetTable();
		        appendFolderToTable(fList);
		        appendPostToTable(pList);
		    }
		});
		
		
		//관심글 테이블 체크박스 생성하는 버튼
		$('#interestChkBoxBtn').click(function() {
			
			//interestTable안에 interestChkBox라는 클래스이름을 가진 요소가 없으면 체크박스 추가
			if (($('#interestTable').find('.interestChkBox').length === 0) && $('#interestChkBoxBtn').text() == "선택") {
				changeToDeselectInterest();
				$('#interestTable thead tr').append('<th class="chkBoxTh">선택</th>');
				$('#interestTable tbody tr').each(function() {
                    $(this).append('<td class="tdForChkBox"><input type="checkbox" name="" class="interestChkBox"></td>');
            	});
            } else {
            	changeToSelectInterest();
	            
            }
        });

		//관심글 삭제 
		$('#deleteInterestBtn').click(function(){
			if($(".interestChkBox:checked").length>0){
				if(confirm("정말로 삭제하시겠습니까?\n폴더를 삭제하시면 폴더 내 데이터는 전부 삭제됩니다.")){
					//체크된 폴더 번호를 담을 배열과 게시글 번호를 담을 배열
					var folderNoArr = [];
					var postNoArr = [];
					//interestChkBox라는 클래스이름을 가진 chkBox중 체크 된것들 반복
					$(".interestChkBox:checked").each(function(index,element){
						//체크된 체크박스에서 가장 가까운 tr요소의 2번째 td자식요소가 분류값을 가지고 있고, 그 이름이 폴더라면
						if($(element).closest('tr').find('td:nth-child(2)').text() == '폴더'){
							//폴더 배열에 폴더번호를 넣는다.
							folderNoArr.push($(element).closest('tr').find('th:first-child').text());
						}else{
							//아니라면 게시글 번호 배열에 번호 넣기.
							postNoArr.push($(element).closest('tr').find('th:first-child').text());
						}								
					});
					
					$.ajax({
						url:"interestDelete.my",
						data:{
							mno : "${loginMember.memberNo}",
							folderNoArr: folderNoArr,
							postNoArr : postNoArr
						},

						async : false,
						success : function(result){
							console.log("통신 성공");
							if(result == "success"){
								$("#interestPostTab").click();
								alert("삭제 성공");
								
							}else {
								alert("삭제 실패");
							}
						},
						error : function(){
							console.log("관심글 삭제 통신 실패");
						}
					});
				}	
			} else {
				alert("삭제할 폴더 혹은 게시글을 선택해주세요.");
			}	
		});
		
		
		//검색창 입력 이벤트
		$(function () {
			var timeout = null; // 타이핑이 멈춘 후 대기 시간을 저장할 변수
			
			$("#interestSearchInput").keyup(function(){
			   clearTimeout(timeout); // 기존 타이머를 취소
			
			   timeout = setTimeout(function () {
					// 1초 이후에 실행할 함수를 여기에 작성
					console.log($("#interestSearchInput").val());
					var inputData = $("#interestSearchInput").val();
					if(inputData == ""){
						$("#interestPostTab").click();
					} else {
						$.ajax({
							url:"searchInterest.my",
							data:{
								inputData: inputData,
								mno:"${loginMember.memberNo}"
							},
							success:function(result){
								if(result.length !=0){
									resetTable();
									appendPostToTable(result);
								} else{
									resetTable();
									//보여줄 폴더나 게시글이 존재하지 않는다면
							        $('#interestChkBoxBtn').attr('style','display:none');
							        var tr = $("<tr></tr>").append($("<td colspan='6'></td>").text("검색결과에 일치하는 관심 게시글이 존재하지 않습니다."));
							        $("#interestTable>tfoot").append(tr);
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
		
		//폴더 추가 버튼
		$("#addFolderBtn").click(function(){
			if($("addFolderInput").val() != ""){
				$.ajax({
					url:"addFolder.my",
					data : {
						mno : "${loginMember.memberNo}",
						folderName: $("#addFolderInput").val(),
						currentFolderNo: $("#currentFolderNo").val()
					},
					success : function(result){
						if(result === "success"){
							alert("폴더 추가 완료");
							$('.currentFolderName').remove();
							$("#interestPostTab").click();
							
						}else {
							alert("폴더 추가 실패");
						}
						//입력칸 초기화
						$("#addFolderInput").val("");
					},
					error : function(){
						console.log("폴더 추가버튼 클릭 통신 에러");
					}
				
				});
			} else {
				alert("폴더이름이 입력되지 않았습니다.");
			}	
		});
		//폴더이동 누르면 p태그에 내가 갖고있는 폴더 번호 불러오기
		$("#goMoveFolderModal").click(function(){
			
			$.ajax({
				url : "getMyFolderName.my",
				data : {
					mno : "${loginMember.memberNo}"
				},
				success : function(result){
					var str = "";
					for(var i=0; i<result.length;i++){
						if(i == result.length -1){
							str+="["+result[i].folderName+"-"+result[i].folderNo+"]";
						}else{
							str+="["+result[i].folderName+"-"+result[i].folderNo+"],";
						}
						
					}
					
					$("#myFolderNames").html(str);
				},
				error : function(){
					console.log("폴더이동 모달창 클릭 통신 에러");
				}
				
			});
		});

		
		//폴더/게시글 이동 버튼
		$("#moveFolderBtn").click(function(){
			if($(".interestChkBox:checked").length>0){
				//체크된 폴더 번호를 담을 배열과 게시글 번호를 담을 배열
				var folderNoArr = [];
				var postNoArr = [];
				//interestChkBox라는 클래스이름을 가진 chkBox중 체크 된것들 반복
				$(".interestChkBox:checked").each(function(index,element){
					//체크된 체크박스에서 가장 가까운 tr요소의 2번째 td자식요소가 분류값을 가지고 있고, 그 이름이 폴더라면
					if($(element).closest('tr').find('td:nth-child(2)').text() == '폴더'){
						//폴더 배열에 폴더번호를 넣는다.
						folderNoArr.push($(element).closest('tr').find('th:first-child').text());
					}else{
						//아니라면 게시글 번호 배열에 번호 넣기.
						postNoArr.push($(element).closest('tr').find('th:first-child').text());
					}								
				});

				$.ajax({
					url : "moveFolder.my",
					data : {
						mno : "${loginMember.memberNo}",
						targetFolderNo : $("#moveFolderInput").val(),
						folderNoArr : folderNoArr,
						postNoArr : postNoArr
					},
					async : false,
					success : function(result){
						console.log(result);
						if(result == "NNNNY"){
							alert("이동이 완료되었습니다.");
							$("#interestPostTab").click();

						}else if(result == "CONFLICT"){
							alert("이동에 실패하였습니다.\n이동 대상 폴더와 이동되는 폴더의 번호가 일치합니다.");
						} else{
							alert("이동에 실패하였습니다. 존재하는 폴더 번호를 입력해주세요.");
						}
						
						//입력칸 초기화
						$("#moveFolderInput").val("");
					},
					error : function(){
						console.log("폴더/게시판 이동 통신 에러");
					}
				});
			} else {
				alert("이동시킬 폴더 혹은 게시글을 선택해주세요.");
			}
			
		});
	
		
		
		//조회해온 폴더 리스트를 table에 뿌려주는 함수
		function appendFolderToTable(fList){
			for(var i=0; i<fList.length;i++){
	            var tr = $("<tr></tr>");
	            var folderNo = $("<th></th>").text(fList[i].folderNo);
	            var classify = $("<td>폴더</td>");
	            var folderName = $("<td colspan='2' class='title'></td>").text(fList[i].folderName);
	            var folderImg = $("<img src='resources/assets/img/folder_icon.png'>");
	            folderName.prepend(folderImg);
	            var createDate = $("<td></td>").text(fList[i].createDate);
	            tr.append(folderNo,classify,folderName,createDate);
	            $("#interestTable>tbody").append(tr);					
	        }
		}
		//조회해온 게시물 리스트를 table에 뿌려주는 함수
		function appendPostToTable(pList){
			for(var i=0; i<pList.length;i++){
				var tr = $("<tr></tr>");
	            var postNo = $("<th></th>").text(pList[i].postNo);
	            var classify = $("<td>게시글</td>");
	            var title = $("<td colspan='2' class='title'></td>").text(pList[i].title);
	            var postDate = $("<td></td>").text(pList[i].postDate);
	            tr.append(postNo,classify,title,postDate);
	            $("#interestTable>tbody").append(tr);				
	        }
		}
		
		//tbody,thead, '선택' th 비우는 함수
		function resetTable(){
			$("#interestTable>tbody").empty();
		    $("#interestTable>tfoot").empty();
		    $('#interestTable thead tr .chkBoxTh').remove();
		}

		//선택 해제 버튼 -> 선택 버튼으로 변경
		function changeToDeselectInterest(){
			//삭제 버튼 보이기
			$('#deleteInterestBtn').attr('style','display:block');
			$("#interestChkBoxBtn").attr("class", "btn btn-secondary btn-sm btn-sm");
			$("#interestChkBoxBtn").text("선택 해제");
		
		}
		
		//선택 해제 버튼 -> 선택 버튼으로 변경
		function changeToSelectInterest(){
			//삭제 버튼 숨이기
			$('#deleteInterestBtn').attr('style','display:none');
			$("#interestChkBoxBtn").attr("class", "btn btn-primary btn-sm btn-sm");
			$("#interestChkBoxBtn").text("선택");
			$('#interestTable thead tr .chkBoxTh').remove();
			$('#interestTable tbody tr .interestChkBox').parents('td').remove();
		}
	</script>
	<!------------------------관심글 태그 관련 스크립트 끝------------------------------------>
</body>
</html>