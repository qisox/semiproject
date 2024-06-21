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
		<form action="${contextPath }/update.bo" method="post" id="update-area" enctype="multipart/form-data">
			<%--어떠한 게시글을 수정할지 알아야 하기 때문에 번호 보내기 --%>
			<input type="hidden" name="postNo" value="${p.postNo }">
			<table align="center" border="1">
				<tr>
					<th width="70">카테고리</th>
					<td width="100">
						<select name="category">
							<%--반복문을 이용해서 카테고리 옵션 태그 뽑아주기 --%>
							<c:forEach items="${cList }" var="c">
								<option value="${c.categoryNo }">${c.categoryName }</option>
							</c:forEach>
						</select>
						<script>
							$(function() {
								var choose = "${p.category}"; 
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
					<td width="600"><input type="text" value="${p.title }" name="title"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3">
						<textarea name="content" rows="10" style="resize:none" required>${p.content }</textarea>
						
					</td>
				</tr>
				<tr>
					<th>첨부파일</th>
					<td colspan="3">
						<c:if test="${atList != null }">
							
							<c:forEach items="${atList}" var="at" varStatus="vs">
								<%--첨부파일이 있다면 해당 정보를 보여줘야한다 --%>
							
								<%--원본파일의 파일번호와 수정명을 서버에 전달하기(원본파일에 대한 처리를 위해) --%>
								<input type="hidden" name="originFileNo${vs.index}" value="${at.fileNo }">
								<input type="hidden" name="originFileName${vs.index}" value="${at.changeName }">
								<input type="file" name="reUploadFile${vs.index}">${at.originName}
							</c:forEach>
							<c:forEach var="i" begin="${fn:length(atList)}" end="3" varStatus="vs">
								<input type="file" name="newUploadFile${vs.index}">
							</c:forEach>
							
						</c:if>
						
					</td>
				</tr>
			</table>
			<div style="text-align: center; margin-top: 30px;">
				<button class="button1" type="submit">수정하기</button>
				<button class="button2" onclick="location.href='${header.referer}'">취소</button>
			</div>
		</form>
		</div>
		<br>
	</div>
</body>
</html>