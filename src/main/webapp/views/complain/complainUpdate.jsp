<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	
	#update-area>table {
		border: 1px solid white;
	}
	
	#update-area input, #update-area textarea {
		width: 100%;
		box-sizing: border-box;
	}
	
	#boardTitle {
        color: rgb(240, 212, 70);
        border-radius: 20px;
    }
    
	 @font-face {
        font-family: 'SEBANG_Gothic_Bold';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2104@1.0/SEBANG_Gothic_Bold.woff') format('woff');
        font-family: 'GmarketSansMedium';
        src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
        font-weight: normal;
        font-style: normal;
    }
    
    @font-face {
         font-family: 'S-CoreDream-3Light';
         src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-3Light.woff') format('woff');
         font-weight: normal;
         font-style: normal;
    }
	    .button1 {
        border: 2px solid rgb(255, 225, 77);  
        padding: 10px 20px; 
        border-radius: 10px;
        background-color: rgb(255, 225, 77);
   
    }
    .button1:hover {
        background-color: rgb(251, 236, 152);  
    }
    .button2 {
        border: 2px solid rgb(249, 219, 150);  
        padding: 10px 20px; 
        border-radius: 10px;
        background-color: rgb(249, 219, 150);
    }
    .button2:hover {
        background-color: rgb(252, 233, 202);  
    }
</style>
</head>
<body>
	<%@ include file="/views/common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center" id="boardTitle" style = "margin-left : -550px; font-family: S-CoreDream-3Light; font-weight: bold; font-size:26px" >게시글 수정</h2>
		<br>
		<div style="font-family: 'S-CoreDream-3Light'">
		<form action="${contextPath }/complainUpdate.bo" method="post" id="update-area" enctype="multipart/form-data">
			<%--어떠한 게시글을 수정할지 알아야 하기 때문에 번호 보내기 --%>
			<input type="hidden" name="complainNo" value="${c.complainNo}">
			<table align="center" border="1">
				<tr>
					<th width="70">카테고리</th>
					<td width="100">
						<select name="category">
									<option>${c.complainTitle}</option>
									<option>
										<c:choose>
											<c:when test="${c.complainTitle == '신고'}">건의</c:when>
											<c:otherwise>신고</c:otherwise>
										</c:choose>
									</option>
						</select>
						<script>
							$(function() {
								var choose = "${c.complainTitle}"; 
								$("#update-area option").each(function() {
									if ($(this).text()==choose) { 
										$(this).attr("selected", true); 
										return false; 
									}
								});
							});
						</script> 
					</td>
					
				</tr>
				<tr>
					<th width="70">제목</th>
					<td width="600"><input type="text" value="${c.complainCategory }" name="title"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3">
						<textarea name="content" rows="10" style="resize:none" required>${c.complainContent }</textarea>
						
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="3">
						<c:if test="${at != null }">
							<%--첨부파일이 있다면 해당 정보를 보여줘야한다 --%>
							${at.originName }
							<%--원본파일의 파일번호와 수정명을 서버에 전달하기(원본파일에 대한 처리를 위해) --%>
							<input type="hidden" name="originFileNo" value="${at.fileNo }">
							<input type="hidden" name="originFileName" value="${at.changeName }">
						</c:if>
						<input type="file" name="reUploadFile">
					</td>
				</tr>
			</table>
			<div align="center">
				<button type="submit">수정하기</button>
				<button type="reset">취소</button>
			</div>
		</form>
		</div>
		<br>
	</div>
</body>
</html>