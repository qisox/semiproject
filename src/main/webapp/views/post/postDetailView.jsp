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
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302_01@1.0/TheJamsil5Bold.woff2')
		format('woff2');
	font-weight: 700;
	font-style: normal;
}

@font-face {
	font-family: 'GmarketSansMedium';
	src:
		url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff')
		format('woff');
	font-weight: 700;
	font-style: normal;
}

table#detail-area th, table#detail-area td {
	padding: 5px 0; /* 위아래 여백을 조절하려면 해당 값을 조정하세요 */
}

#reply-area .btn {
	background-color: rgb(241, 205, 27);
	margin-top: -5px;
	width: 80px;
	height: 80px;
	margin: 0;
}

#fixed-header {
	position: fixed;
	top: 0;
	background-color: white;
	width: 100%;
	z-index: 100;
}

#reply-area {
	width: 100%;
	text-align: center;
	
}


#reply-area table {
	margin-bottom: 80px;
}

#reply-area textarea {
	display: block;
	width: 500px; /* 기존 가로 크기 유지 */
	margin-left: auto;
	margin-right: auto;
	margin-top: 20px; /* 원하는 상하 여백 값으로 조정 */
	margin-bottom: 20px; /* 원하는 상하 여백 값으로 조정 */
}

/* 
#reply-area td {
	padding: 0; 수정: 더 적은 여백으로 조절 
}
*/
.btn {
	width: 80px;
	height: 40px;
}

.detailContainer {
	justify-content: center;
	width: 1050px;
	margin: 0 auto;
}

.button1 {
	border: 2px solid rgb(255, 225, 77);
	padding: 10px 20px;
	height: 45px;
	background-color: rgb(255, 225, 77);
}

.button1:hover {
	background-color: rgb(251, 236, 152);
}

.button2 {
	border: 2px solid rgb(249, 219, 150);
	padding: 10px 20px;
	height: 45px;
	background-color: rgb(249, 219, 150);
}

.button2:hover {
	background-color: rgb(252, 233, 202);
}

.button3 {
	border: 2px solid rgb(252, 147, 139);
	padding: 10px 20px;
	height: 45px;
	background-color: rgb(252, 147, 139);
}

.button3:hover {
	background-color: rgb(252, 171, 167);
}

#commentContent {
	width: 300px; /* 원하는 가로 크기로 설정 */
	height: 100px; /* 원하는 세로 크기로 설정 */
	resize: none; /* 사용자가 크기를 조절하지 못하도록 설정 */
}
.commentTable{
	width:750px;
	margin:0 auto;
}
</style>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp"%>
	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script>
	 $(function() {
		    // checkLikeAndInterestStatus(); // 페이지 로딩 시 한 번만 호출하도록 변경
		    selectCommentList();
		    if(${not empty loginMember}){
		    	isLikeOn();
			    isInterestOn();
			    isReportOn();
		    }
		    
		  });
	</script>
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">

	<div class="outer">
		<br> <br>
		<div class="detailContainer">
			<h2 align="left"
				style="width: 700px; margin: 0 auto; font-size: 24px; font-family: TheJamsil5Bold">${p.title }</h2>
			<br>
			<table id="detail-area" align="center">
				<tr>
					<th width="70">번호</th>
					<td width="10">${p.postNo }</td>
					<th width="100">카테고리</th>
					<td width="150">${p.categoryName }</td>
				</tr>
				<tr style="border-bottom: 1px solid black;">
					<th>작성자</th>
					<td width="100">${p.nickname}</td>
					<th>조회수</th>
					<td>${p.count }</td>
					<th>작성일</th>
					<td width="200">${p.postDate}</td>
				</tr>
				<tr style="border-bottom: 1px solid black;">
					<th>내용</th>
					<td colspan="5">
						<p style="height: 200px; white-space: pre-line;  width:650px;">${p.content}</p>
					</td>

				</tr>
				<tr>
					<th>사진</th>
					<td colspan="5">
						<%--<img src="${p.refBno}" alt="사진"> --%> <c:forEach
							items="${list}" var="at" varStatus="vs">
							<!-- 상세이미지 -->

							<img id="contentImg${vs.count}"
								src="${contextPath}${at.filePath}${at.changeName}" width="200"
								height="200">


						</c:forEach> <script>
						console.log("${contextPath }${at.filePath}${at.changeName}");
					</script>
					</td>
				</tr>
				<tr style="border-bottom: 1px solid black;">
					<th width="100">좋아요&nbsp<i class="bi bi-suit-heart-fill"
						style="color: rgb(241, 205, 27);"></i></th>
					<td id="likeCount">${p.likeCount}</td>
					<th width="100">관심&nbsp<i class="bi bi-bookmark"
						style="color: rgb(241, 205, 27);"></i></th>
					<td id="interestCount">${p.interestCount}</td>
					<th width="100">신고&nbsp<i class="bi bi-bell-fill"
						style="color: rgb(241, 205, 27);"></i></th>
					<td>${p.reportCount}</td>
				</tr>
			</table>
			<br>
			<c:if test="${ not empty loginMember }">
				<div style="text-align: center;">
					<!-- 좋아요 버튼 -->
					<button class="btn" onclick="increaseLikeCount();" id="likeButton"
						style="background-color: rgb(241, 205, 27)">
						<i class="bi bi-heart"></i>
					</button>

					<!-- 관심 버튼 -->
					<button class="btn" onclick="increaseInterestCount();"
						id="interestButton" style="background-color: rgb(241, 205, 27)">
						<i class="bi bi-bookmarks"></i>
					</button>
					<!-- 신고 버튼 -->
					<button class="btn" onclick="openReportModal();" id="reportButton"
						style="background-color: rgb(241, 205, 27)">
						<i class="bi bi-bell"></i>
					</button>

					<div class="modal" id="reportModal">
						<div class="modal-dialog">
							<div class="modal-content">

								<!-- 모달 헤더 -->
								<div class="modal-header">
									<h4 class="modal-title">게시물 신고</h4>
								</div>

								<!-- 모달 본문 -->
								<div class="modal-body">
									<p>게시물을 신고하시겠습니까?</p>
									<label for="reportReasonSelect">신고 사유 선택 :</label> <select
										id="reportReasonSelect" class="form-control">
										<option value="부적절한 내용">부적절한 내용</option>
										<option value="도배 또는 스팸">도배 또는 스팸</option>
										<option value="욕설 또는 비방">욕설 또는 비방</option>
										<option value="개인정보 노출">개인정보 노출</option>
										<option value="기타">기타</option>
									</select>
								</div>

								<!-- 모달 푸터 -->
								<div class="modal-footer">
									<button type="button" class="btn btn-secondary"
										data-dismiss="modal" onclick="closeModal();">취소</button>
									<button type="button" class="btn btn-primary"
										onclick="increaseReportCount();">확인</button>
								</div>
							</div>
						</div>
					</div>

				</div>
			</c:if>
			<br> <br>
			<!-- 수정 삭제 목록으로 버튼 -->
			<div align="center">
				<!-- 현재 로그인된 유저의 아이디가 글 작성자와 동일하다면 -->
				<c:if
					test="${not empty loginMember and (loginMember.nickname eq p.nickname or loginMember.grade eq '관리자')}">
					<!-- 수정 및 삭제 버튼 표시 -->
					<button
						onclick="location.href='${contextPath}/update.bo?postNo=${p.postNo}'"
						class="button2">
						<b>수정하기</b>
					</button>
					<button class="button3"
						onclick="location.href='${contextPath}/delete.bo?postNo=${p.postNo}'"
						style="background-color: rgb(252, 147, 139)">
						<b>삭제하기<b></b>
					</button>
				</c:if>
				
				<button onclick="location.href='${contextPath}/list.bo?currentPage=1&category=${p.categoryName}'" class="button1">
					<b>목록가기</b>
					
				</button>
				
				
			</div>
			<br> <br>
			
			<div id="reply-area">
				<table class="commentTable">
					<colgroup>
				      <col width="15%" />
				      
				      <col width="50%" />
				      <col width="15%" />
				      <col width="20%" />
					</colgroup>
					<thead>
						<tr style="justify-content: space-between;">
						
							<th style="height:50px; ">
								<h6>
									<b style="font-size: 18px;"><i
										class="bi bi-send"></i>&nbsp;댓글</b>
								</h6>
							</th>

						
						
							<th colspan="2"><textarea id="commentContent" rows="3" cols="80"
									style="min-width: auto;"></textarea>
							</th>
						
						
							<th style="height:50px;">
								<button class="btn btn-outline-warning"
									onclick="insertComment();"
									style=" font-size: 16px; background-color: transparent; height: 40px; ">
									<b>작성</b>
								</button>
							</th>
						</tr>
					</thead>
					
					<tbody>
						
					</tbody>
				</table>
			</div>
			<br> <br>
		</div>
		
		<script>

		// 댓글목록 조회 함수
		function selectCommentList() {
			 console.log("zzd");
		    $.ajax({
		    	url:"commentList.po",
				data : {postNo : ${p.postNo}
				},
				type: "post",
		        success: function (result) {
		            var str = "";
		
		            if (result.length === 0) {
		                // 댓글이 없는 경우
		                str = "<tr><td colspan='5'>댓글이 없습니다.</td></tr>";
		            } else {
		                // 댓글이 있는 경우
		                for (var i in result) {
		                    str += "<tr>"
		                        + "<td><i class='bi bi-person-fill'></i>&nbsp;" + result[i].writerNo + "</td>"
		                        + /* "<td><textarea type='text' style='width:400px; margin-top:10px;font-size: 16px; resize:none;' readonly>"+ result[i].content +"</textarea></td>" */
		                        	"<td><p style='width:400px; margin:10px; font-size: 16px; text-align:left;'>"+ result[i].content +"</p></td>"
		                        + "<td>" + result[i].commentDate + "</td>";
		                       
		
		                    // 삭제 버튼 생성 부분에 조건 추가
		                    if("${loginMember.memberId}" == "admin" || "${loginMember.nickname}"==result[i].writerNo){
		                        str += '<td><button class="btn" onclick="deleteComment(' + result[i].commentNo + ');" style="background: none; color:red">삭제</button></td>';
		                        		
		                    }
		
		                    str += "</tr>";
		                }
		            }
		
		            $("#reply-area tbody").html(str);
		
		            // 댓글을 업데이트한 후에 텍스트 영역 하단으로 스크롤
		            $("#reply-area").scrollTop($("#reply-area")[0].scrollHeight);
		        },
		        error: function () {
		            console.log("통신오류");
		        }
		    });
		}
		// 댓글목록 수정 함수
		function insertComment(){
			if(${empty loginMember}){
				alert('댓글을 작성하려면 로그인을 해주세요.');
			}else {
				
			    $.ajax({
			        url: "insertComment.po",
			        data: {
			            content: $("#commentContent").val(),
			            postNo: ${p.postNo}
			        },
			        type: "post",
			        success: function (result) {
			            if (result > 0) {
			                alert("댓글 작성 성공");
			                // 추가된 댓글 목록 재조회 등 필요한 동작을 수행
			                $("#commentContent").val("");
			            } else {
			                console.log("댓글 작성 실패");
			            }
			        },
			        error: function () {
			            console.log("통신 오류");
			        }
			    });
			}
		}
		// 댓글 수정
		  function updateComment(commentNo,el) {
			//번호 추출하기 (el은 클릭된 요소객체[this로 전달받음])
			console.log($(el).parents("tbody").find('input[type=text]').val());
		  	//내용 추출하기 (el은 클릭된 요소객체[this로 전달받음])
			var content = $(el).parents("tbody").find('input[type=text]').val();
		  	$.ajax({
		          url: "updateComment.po",
		          data: {
		              content: content,
		              commentNo: commentNo,
		              postNo : ${p.postNo}
		          },
		          type: "post",
		          success: function (result) {
		              if (result > 0) {
		                  alert("댓글 수정 성공");
		                  // 추가된 댓글 목록 재조회 등 필요한 동작을 수행
		                  $("#content").val("");
		                  selectCommentList(); // 댓글 목록을 다시 불러옴
		              } else {
		                  console.log("댓글 수정 실패");
		              }
		          },
		          error: function () {
		              console.log("통신 오류");
		          }
		      });
		  }
	
		  // 댓글 삭제
		  function deleteComment(commentNo) {
		      console.log("클릭한 delete버튼의 no: " + commentNo); // 로그 추가
	
		      $.ajax({
		          url: "deleteComment.po",
		          data: {
		              commentNo: commentNo,
		              postNo : ${p.postNo}
		          },
		          type: "post",
		          success: function (result) {
		              if (result > 0) {
		                  alert("댓글 삭제 성공");
		                  // 삭제된 댓글 목록 재조회 등 필요한 동작을 수행
		                  selectCommentList(); // 댓글 목록을 다시 불러옴
		              } else {
		                  console.log("댓글 삭제 실패");
		              }
		          },
		          error: function () {
		              console.log("통신 오류");
		          }
		      });
		  }
		</script>

		<!-- 좋아요 기능 -->
		<!-- 좋아요 수를 쿠키에 저장 -->
		<script>
		 
		  
		  //게시물을 조회했을 때 게시글 조회한 사용자가 이 게시글을 좋아요 했는지 안했는지 판단. 했으면 하트를 채워두고 안했으면 빈하트로
		  function isLikeOn(){
			 
			  $.ajax({
				  url:"isLike.po",
				  data : {
					  postNo : ${p.postNo},
			  		  mno : ${loginMember.memberNo}
				  },
				  success : function(result){
					  if(result === "exist"){
						   $("#likeButton>i").removeClass('bi-heart');
					  		$("#likeButton>i").addClass('bi-heart-fill');
					  }else{
						  $("#likeButton>i").removeClass('bi-heart-fill');
					  	  $("#likeButton>i").addClass('bi-heart');
					  }
					 
				  },
				  error : function(){
					  console.log("통신에러");
				  }
				  
			  });
		  }
		  //게시물을 조회했을 때 게시글 조회한 사용자가 이 게시글을 관심표시를 했는지 안했는지 판단. 했으면 북마크 아이콘을 채워두고 안했으면 빈 북마크로
		  function isInterestOn(){
			  $.ajax({
				  url:"isInterest.po",
				  data : {
					  postNo : ${p.postNo},
			  		  mno : ${loginMember.memberNo}
				  },
				  success : function(result){
					  if(result === "exist"){
						   $("#interestButton>i").removeClass('bi-bookmarks');
					  		$("#interestButton>i").addClass('bi-bookmarks-fill');
					  }else{
						  $("#interestButton>i").removeClass('bi-bookmarks-fill');
					  	  $("#interestButton>i").addClass('bi-bookmarks');
					  }
					 
				  },
				  error : function(){
					  console.log("통신에러");
				  }
				  
			  });
		  }
		  // 상세보기 들어갔을 때 신고를 했다면 찬 하트 안했다면 빈 하트
		  function isReportOn(){ 
			  $.ajax({
				  url:"isReport.po",
				  data : {
			  		  memberNo : ${loginMember.memberNo},
					  postNo : ${p.postNo}
				  },
				  type: "get",
				  success : function(result){
					  if(result === "exist"){
						   $("#reportButton>i").removeClass('bi-bell');
					  		$("#reportButton>i").addClass('bi-bell-fill');
					  }else{
						  $("#reportButton>i").removeClass('bi-bell-fill');
					  	  $("#reportButton>i").addClass('bi-bell');
					  }
					 
				  },
				  error : function(){
					  console.log("통신에러");
				  }
				  
			  });
		  }
	
		  // 좋아요 버튼 클릭 시
		  function increaseLikeCount() {
			  console.log($('#likeButton>i').hasClass('bi-heart-fill'));
			 //하트가 채워져있다면 (이미 좋아요를 한 글이라면)
				if ($('#likeButton>i').hasClass('bi-heart-fill')) {
					//하트가 빈하트라면 (좋아요를 안 한 글이라면)
					console.log("꽉찬 하트라면 눌리는 곳");
					$.ajax({
				      url: "deleteFromLikeTable.po",
					      data: { 
					    	  postNo: ${p.postNo},
					    	  mno : ${loginMember.memberNo}
					    
					      },
					      success: function(result) {
					        if (result === "success") {
					          alert("좋아요를 해제하였습니다.");
					          $("#likeButton>i").removeClass('bi-heart-fill');
							  $("#likeButton>i").addClass('bi-heart');
							
					       
					        } else {
					        	alert("실패");
					        }
					      },
					      error: function() {
					        console.log("통신 오류");
					      }
					    });
					  
					
				} else {
					console.log("빈하트라면 눌리는 곳");
					//하트가 빈하트라면 (좋아요를 안 한 글이라면)
					 $.ajax({
					      url: "insertToLikeTable.po",
					      data: { 
					    	  postNo: ${p.postNo},
					    	  mno : ${loginMember.memberNo}
					    
					      },
					      success: function(result) {
					        if (result === "success") {
					          alert("좋아요를 눌렀습니다");
					          $("#likeButton>i").removeClass('bi-heart');
						  	  $("#likeButton>i").addClass('bi-heart-fill');
						  
					    
					        } else {
					        	alert("실패");
					        }
					      },
					      error: function() {
					        console.log("통신 오류");
					      }
					    });
				}
			}
		
		   

 
		
		  // 관심 버튼 클릭 시
		
		  function increaseInterestCount() {
			  console.log($('#interestButton>i').hasClass('bi-bookmarks-fill'));
				 //북마크가 채워져있다면 (이미 관심표시를 한 글이라면)
					if ($('#interestButton>i').hasClass('bi-bookmarks-fill')) {
						console.log("꽉찬 북마크라면 눌리는 곳");
						$.ajax({
					      url: "deleteFromInterestTable.po",
						      data: { 
						    	  postNo: ${p.postNo},
						    	  mno : ${loginMember.memberNo}
						    
						      },
						      success: function(result) {
						        if (result === "success") {
						          alert("관심을 해제하였습니다.");
						          $("#interestButton>i").removeClass('bi-bookmarks-fill');
								  $("#interestButton>i").addClass('bi-bookmarks');
								 
						          // 쿠키에 좋아요 수 저장 (이미 클릭한 경우도 저장해야 함)
						         
						        } else {
						        	alert("실패");
						        }
						      },
						      error: function() {
						        console.log("통신 오류");
						      }
						 });
						  
						
					} else {
						console.log("빈 북마크라면 눌리는 곳");
						//북마크가 빈 북마크라면 (좋아요를 안 한 글이라면)
						 $.ajax({
						      url: "insertToInterestTable.po",
						      data: { 
						    	  postNo: ${p.postNo},
						    	  mno : ${loginMember.memberNo}
						    
						      },
						      success: function(result) {
						        if (result === "success") {
						          alert("관심버튼을 눌렀습니다");
						          $("#interestButton>i").removeClass('bi-bookmarks');
							  	  $("#interestButton>i").addClass('bi-bookmarks-fill');
							  	
						      
						        } else {
						        	alert("실패");
						        }
						      },
						      error: function() {
						        console.log("통신 오류");
						      }
						    });
					}
		  }

		</script>
		<!-- 신고 관련 스크립트 -->
		<script>
	 	function openReportModal() {
	 		console.log($('#reportButton>i').hasClass('bi-bell-fill'));
	 		if ($('#reportButton>i').hasClass('bi-bell-fill')) {
				//하트가 빈하트라면 (좋아요를 안 한 글이라면)
				console.log("꽉찬 하트라면 눌리는 곳");
				$.ajax({
			      url: "deleteReport.po",
				      data: { 
				    	  postNo: ${p.postNo},
				    	  mno : ${loginMember.memberNo}
				    
				      },
				      success: function(result) {
				        if (result === "success") {
				          alert("신고를 해제하였습니다.");
				          $("#reportButton>i").removeClass('bi-bell-fill');
						  $("#reportButton>i").addClass('bi-bell');
				
				        } else {
				        	alert("실패");
				        }
				      },
				      error: function() {
				        console.log("통신 오류");
				      }
				    });
				  
				
			}else{
			    $('#reportModal').modal('show');
				
			}
		}
	 	
	 	function closeModal() {
	 		$('#reportModal').modal('hide');
	 	}
	 	function increaseReportCount() {
			  console.log($('#reportButton>i').hasClass('bi-bell-fill'));
			 //하트가 채워져있다면 (이미 신고를 한 글이라면)
			if ($('#reportButton>i').hasClass('bi-bell-fill')) {
				console.log("꽉찬 하트라면 눌리는 곳");
				$.ajax({
			      url: "deleteReport.po",
				      data: { 
				    	  mno : ${loginMember.memberNo},
				    	  postNo: ${p.postNo}
				      },
				      type: "get",
				      success: function(result) {
				        if (result === "success") {
				          alert("신고를 해제하였습니다.");
				          $("#reportButton>i").removeClass('bi-bell-fill');
						  $("#reportButton>i").addClass('bi-bell');
				       	
				        } else {
				        	alert("실패");
				        }
				      },
				      error: function() {
				        console.log("통신 오류");
				      }
				    });
				  
				
			} else {
				console.log("빈하트라면 눌리는 곳");
				
				//하트가 빈하트라면 (좋아요를 안 한 글이라면)
				 $.ajax({
				      url: "insertReport.po",
				      data: { 
				    	  mno : ${loginMember.memberNo},
				    	  postNo: ${p.postNo},
				    	  reportReason: $("#reportReasonSelect").val()
				      },
				      type: "get",
				      success: function(result) {
				        if (result === "success") {
				          alert("게시글 신고가 완료되었습니다");
				          $("#reportButton>i").removeClass('bi-bell');
					  	  $("#reportButton>i").addClass('bi-bell-fill');				
				
				        $('#reportModal').modal('hide');
				        } else {
				        	alert("실패");
				        }
				      },
				      error: function() {
				        console.log("통신 오류");
				      }
				    });
				  }
			}
		</script>
	</div>
</body>
</html>