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
    margin: 0 auto;
}


#reply-area{
	text-align: center;
	margin: 0 auto;
	float: left;
	left: 50%;
}
#reply-area table{
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
.detailContainer{
	justify-content:center;
	width: 1050px;
	margin:0 auto;
}


.button1 {
		border: 2px solid rgb(255, 225, 77);
		padding: 10px 20px;
		height : 45px;
		background-color: rgb(255, 225, 77);
	}
	
	.button1:hover {
		background-color: rgb(251, 236, 152);
	}
	
	.button2 {
		border: 2px solid rgb(249, 219, 150);
		padding: 10px 20px;
		height : 45px;
		background-color: rgb(249, 219, 150);
	}
	
	.button2:hover {
		background-color: rgb(252, 233, 202);
	}
	
	.button3 {
		border: 2px solid rgb(252, 147, 139);
		padding: 10px 20px;
		height : 45px;
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

  
    </style>
</head>
<body>
    <%@ include file="/views/common/menubar.jsp" %>
  	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<link rel="stylesheet"
		href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.1/font/bootstrap-icons.css">
   
   <div class="outer">
      <br><br>
      <h2 align="left" style = "margin-left:450px; font-size:24px; font-family:TheJamsil5Bold">${c.complainTitle }</h2>
      <br>
      <div class = "detailContainer">

      <table id="detail-area" align="center" >
         <tr>
         
            <th width="70">제목</th>
            <td width="10" >${c.complainCategory }</td>
            <th width="100">카테고리</th>
            <td width="70">${c.complainTitle }</td>
         
         </tr>
         
         <tr style="border-bottom: 1px solid black;">
            <th>작성자</th>
            <td width = "100">${c.complainWriter}</td>
            <th>조회수</th>
            <td>${c.count }</td>
            <th >작성일</th>
            <td width="200">${c.complainDate}</td>
         </tr>
			<tr style="border-bottom: 1px solid black;">
            <th>내용</th>
            <td colspan="5">
               <p style="height:200px; white-space:pre;">${c.complainContent}</p>  
            </td>
			</tr>
         <tr style="border-bottom: 1px solid black;">
            <th>사진</th>
            <td colspan="5">
				<img src="${contextPath }${at.filePath}${at.changeName}" alt="사진" width="200" height="200">
			</td>
         </tr>
         <tr >
            <th width="100"><i style="color: rgb(241, 205, 27);"></i></th>
			<td>${p.likeCount}</td>
            <th width ="100"><i class= style="color: rgb(241, 205, 27);"></i></th>
            <td>${p.interestCount}</td>
         </tr> 
      </table>

      
      <br>
  
      
   <!-- 수정 삭제 목록으로 버튼 -->
   <div align = "center" style = "margin-left = 200">
			    <!-- 수정 및 삭제 버튼 표시 -->
   			<c:if test="${not empty loginMember && loginMember.nickname eq c.complainWriter}">
			    <button class = "button2" onclick="location.href='${contextPath}/complainUpdate.bo?complainNo=${c.complainNo}'">수정하기</button>
   			</c:if>
   <c:if test="${not empty loginMember && (loginMember.nickname eq c.complainWriter || loginMember.grade eq '관리자')}">
			    <button class = "button3" onclick="location.href='${contextPath}/deleteComplain.bo?complainNo=${c.complainNo}'">삭제하기</button>
			</c:if>
   		<button class = "button1" onclick="location.href='${header.referer}'">목록가기</button>
   </div>

	<div id="reply-area">
				<table  style = "margin-left:-250px">
					<thead>
						<tr>
							<th>
								<h6 align="center" >
									<b style = "margin-left:50px; font-size:18px"><i class="bi bi-send"></i>&nbsp;댓글</b>
								</h6>
							</th>
							
						</tr>
						<tr>
							<td>
            					<textarea id="commentContent" rows="3" cols="80" style="min-width:auto; margin-left:521px; margin-top:-5px"></textarea>
            						
						</tr>
						<tr>
							<td>
							  <button class="btn btn-outline-warning" onclick="insertComment();" style="margin-left: 950px; font-size: 16px; margin-top: -10px; margin-bottom: 30px; background-color: transparent; height: 40px; width: 70px;">
								    <b>작성</b>
								</button>
							</td>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>작성자</td>
							<td>내용</td>
							<td>작성일</td>
						</tr>
					</tbody>
				</table>
			
			</div>
			</div>
			
		<br><br><br>
		
		<script>
		  $(function() {
		    selectCommentList(); // Call the function to load comments when the page loads
		  });
		
		</script>
		
		<script>
				// 댓글목록 조회 함수
		function selectCommentList() {
		    $.ajax({
		    	url:"selectReply.co",
				data : {complainNo : ${c.complainNo}
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
		                        + "<td><i class='bi bi-person-fill'></i>&nbsp;" + result[i].replyWriterNo + "</td>"
		                        + "<td><input type='text' value='" + result[i].replyContent + "' style='width:400px; margin-left :-500px; margin-top:10px;font-size: 16px;'></td>"
		                        + "<td>" + result[i].replyDate + "</td>"
		                        + "<td>";
		
		                    // 삭제 버튼 생성 부분에 조건 추가
		                    if("${loginMember.memberId}" == "admin" || "${loginMember.nickname}"==result[i].replyWriterNo){
		                        str += '<button class="btn" onclick="deleteComment(' + result[i].replyNo + ');" style="background: none; color:red">삭제</button>';
		                    }
		
		                    str += "</td></tr>";
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
		// 댓글 작성 함수
			function insertComment(){
			    $.ajax({
			        url: "insertReply.co",
			        data: {
			            replyContent: $("#commentContent").val(),
			            complainNo: ${c.complainNo}
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
		
		
			// 댓글 삭제
			  function deleteReply(replyNo) {
			      console.log("클릭한 delete버튼의 no: " + replyNo); // 로그 추가

			      $.ajax({
			          url: "deleteReply.co",
			          data: {
			              replyNo: replyNo,
			              complainNo : ${c.complainNo}
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
		
</div>
</body>
</html>