<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
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
    
    /* body {
        background-color: rgb(255, 225, 77);
    } */
    
    #enroll-area>table {
        border: 1px solid white;
    }
    
    #enroll-area input, #enroll-area textarea {
        width: 100%;
        box-sizing: border-box;
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
    <%@ include file="/views/common/menubar.jsp"%>
    <div class="outer">
        <br><br>
        <h2 align="center" id="boardTitle" style = "margin-left : -550px; font-family: S-CoreDream-3Light; font-weight: bold; font-size:26px" >게시글 작성</h2>
        <br>
        <div style="font-family: 'S-CoreDream-3Light'">
        <form action="${pageContext.request.contextPath}/insert.bo" method="post"
            id="enroll-area" enctype="multipart/form-data">
            <table align="center" border="1">
                <tr>
                <th width="90">카테고리</th>
                <td width="90" style="height: 30px;">
                    <select name="categoryNo">
                    	<c:forEach items="${category}" var="c">					
							<option value="${c.categoryNo}">${c.categoryName}</option>
						</c:forEach>
                    </select>
                </td>
                </tr>
                <th width="80"  style="height: 50px;">제목</th>
                    <td width="600"><input type="text" name="title" style = "height:35px" required></td>
                <tr>
                    <th>내용</th>
                    <td colspan="3"><textarea name="content" rows="10"
                            style="resize: none" required></textarea></td>
                </tr>
                <tr>
                    <th>첨부파일</th>
                    <td colspan="3"><input type="file" id="file1" name="file1" onchange="loadImg(this,1)"></td>
                </tr>
                <tr>
                	<th></th>
                	<td colspan="3"><input type="file" id="file2" name="file2" onchange="loadImg(this,2)"></tr>
                <tr>
                	<th></th>
                	<td colspan="3"><input type="file" id="file3" name="file3" onchange="loadImg(this,3)"></tr>
                <tr>
                	<th></th>
               		<td colspan="3"><input type="file" id="file4" name="file4" onchange="loadImg(this,4)">
                </tr>
            </table>
            <br> <br>
            <div align="center" >
            	<input type="text" name="categoty" hidden>
                <button class="button1" type="submit">글작성</button>
                <button class="button2" type="reset">취소</button>
            </div>
            <script>

	            $('select[name="categoryNo"]').change(function() {
	                // 선택된 option의 text 값을 가져와서 input의 value로 설정
	                $('input[name="category"]').val($('select[name="categoryNo"] option:selected').text());
	                console.log($('select[name="categoryNo"] option:selected').text());
	            });
           	 	
            </script>
        </form>
        </div>
        <br> <br>
    </div>
    <script>
        function checkOtherOption(selectElement) {
            var otherCategoryDiv = document.getElementById("otherCategoryDiv");
            if (selectElement.value == "option6") {
                otherCategoryDiv.style.display = "block";
            } else {
                otherCategoryDiv.style.display = "none";
            }
        }
    </script>
</body>
</html>