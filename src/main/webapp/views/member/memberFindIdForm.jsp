<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style> 

    #findId-form table {margin:auto;}
    #findId-form input {
    	margin:5px;
    	margin-top :15px;
    	border: 1px solid rgb(241, 205, 27); /* 테두리 색상 변경 */
    }
    
     
	 @font-face {
	    font-family: 'TheJamsil5Bold';
	    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2302_01@1.0/TheJamsil5Bold.woff2') format('woff2');
	    font-weight: 700;
	    font-style: normal;
	}
	
	@font-face {
	    font-family: 'SUIT-Regular';
	    src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_suit@1.0/SUIT-Regular.woff2') format('woff2');
	    font-weight: normal;
	    font-style: normal;
	}
	
	.idFind {
		font-size: 28px;
        color: rgb(241, 205, 27);
        font-family: TheJamsil5Bold;
        text-align: center; /* 가운데 정렬을 위한 추가 */
        margin-top: 100px;
		
	}
	
	.table_text {
		font-family : SUIT-Regular;
	}

	.btn {
		width: 250px;
  		height: 40px;
  		background-color: rgb(255, 219, 41);
  		margin-top:-50px;
  		margin-left : 40px;
	}
	
	.btn:hover {
		background-color: rgb(241, 205, 27); /* 마우스 호버 시 색상 변경 */
	}
	

</style>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
</head>
<body>
	<%@ include file="../common/menubar.jsp" %> 
	<!-- ../ : 현재 폴더로부터 한번 빠져나감 (즉, 현재 폴더로부터 한단계 상위폴더로 이동)  -->
	
	<div class="outer">

        <br>
        <h2 align="center" class="idFind">아이디 찾기</h2>

        <form id="findId-form" action="<%=contextPath%>/findId.me" method="post"> 
        <!-- menubar.jsp 를 위에서 include 했기 때문에 contextPath 변수를 가져다 쓸 수 있다. -->

            <!-- 아이디, 비밀번호, 이름, 전화번호, 이메일, 주소, 취미 -->
           <!-- 아이디, 비밀번호, 이름, 전화번호, 이메일, 주소, 취미 -->
            <table class= "table_text">
                <!-- (tr>td*3)*8 -->
                <tr>
                    <td><i class="bi bi-person-fill" style="font-size: 40px;"></i></td>
                    <td><input type="text" id="nickname" name="nickname"
                     style = "width:250px; height: 40px;" maxlength="12" placeholder="닉네임" required></td>
                    <td></td>
                </tr>
                <tr>
                    <td><i class="bi bi-envelope" style="font-size: 40px;"></i></td>
                    <td><input type="email" id="email" name="email" maxlength="15" 
                    style = "width:250px; height: 40px" placeholder="email" required></td>
                    <td></td>
                </tr>
            </table>
            
            <br><br>

            <div align="center">
                <button type="submit" onclick="findId();"  class="btn" style="background-color:rgb(241, 205, 27);">아이디 찾기</button><br>
                <button type="reset"  class="btn" style="background-color:rgb(241, 205, 27); margin-top:2px">초기화</button>
            </div>

            <br><br>

        </form>


   

    </div>
    
	
</body>
</html>