<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.HttpServletRequest" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
    

    #enroll-form table {margin:auto;}
    #enroll-form input {margin:5px;}
    
     @font-face {
            font-family: 'SEBANG_Gothic_Bold';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2104@1.0/SEBANG_Gothic_Bold.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
        
       table input {
		    background-color: rgb(241, 241, 241);
		    border-radius: 5px; 
		    border : none;
		    width : 300px;
		    height :45px;
		}
		
		button[type="submit"],
		button[type="reset"] 
		{
		    background-color: rgb(255, 219, 41);
		    border :none; 
		   	width : 300px;
		    height :45px;
		    border-radius: 5px;
			margin-left: -70px;
		}
		.logo div:hover{
        	cursor : pointer;
        }
	
</style>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
	
	<div class="logo" align="center" style="margin-top:30px">
	    <div style="display: flex; align-items: center; justify-content: center;" onclick="window.location.href='<%=request.getContextPath()%>';">
	        <h2 style="font-size: 48px; font-weight: bold; color: rgb(241, 205, 27); font-family: SEBANG_Gothic_Bold; display: inline;">꿀.zip</h2>
	        <img src="<%= request.getContextPath() %>/views/img/bee.png" alt="Bee Image" width="50" height="50" style="display: inline; margin-top: -15px">
	        
		</div>
	</div>
	<div class="outer">
        <br>
        <h2 align="center" style = "font-size:24px; margin-left:-280px">JOIN</h2>

        <form id="enroll-form" action="<%= request.getContextPath() %>/enrollForm.me" method="post">
        <!-- menubar.jsp 를 위에서 include 했기 때문에 contextPath 변수를 가져다 쓸 수 있다. -->

            <!-- 아이디, 비밀번호, 이름, 전화번호, 이메일, 주소, 취미 -->
            <table id = "join" style ="align :center;">
                <!-- (tr>td*3)*8 -->
                <tr><i class="bi bi-person-fill"></i>
                    <td><input type="text" id="enrollId" name="memberId" maxlength="12" required placeholder="아이디(4자리 이상)"></td>
                    <td><button type="button" onclick="idCheck();" style = "height:45px; 
                    border:none; background-color:rgb(255, 219, 41); border-radius: 5px;"><b>중복확인</b></button></td>
                </tr>
                <tr>
                    <td><input type="password" name="memberPwd" id="password1" maxlength="15" required placeholder="비밀번호(4자리 이상)"></td>
                    <td></td>
                </tr>
                <tr>
                    <td><input type="password" maxlength="15" id="password2" required placeholder="비밀번호 확인"></td> <!-- 단순 비교 확인 용도라 key 값을 부여 안해도 됨 -->
                    <td></td>
                </tr>
                <tr>
                    <td><input type="text" name="memberName" maxlength="6" required placeholder=이름></td>
                    <td></td>
                </tr>
				<tr>
                    <td><input type="text" id="enrollNick" maxlength="15" name="nickname" required placeholder="닉네임"></td> <!-- 단순 비교 확인 용도라 key 값을 부여 안해도 됨 -->
                    <td><button type="button" onclick="nickCheck();" style = "height:45px;
                    border:none; background-color:rgb(255, 219, 41); border-radius: 5px;"><b>중복확인</b></button></td>
                </tr>
                <tr>
                    <td><input type="email" name="email" placeholder="이메일"></td>
                    <td></td>
                </tr>
            </table>
            
            <br><br>

            <div align="center" style = "margin-left : -50x; margin-top:-20px">
                <button type="submit" style ="margin-top : -40px" onclick="checkPwd();" disabled ><b>회원가입</b></button><br>
                <button type="reset" style = "margin-top : 20px"><b>초기화</b></button>
            </div>

            <br><br>

        </form>
        <script>
    // 아이디 중복 체크 결과 변수
    var idCheckResult = false;

    // 닉네임 중복 체크 결과 변수
    var nickCheckResult = false;

    function idCheck() {
        // 입력 필드에서 아이디 가져오기
        var checkId = document.getElementById("enrollId").value;

        // 아이디가 비어 있지 않은지 및 최소 4자리 이상인지 확인
        if (checkId.trim() !== "" && checkId.trim().length >= 4) {
            // 서버에 AJAX 요청 보내기
            $.ajax({
                type: "GET",
                url: "<%= request.getContextPath() %>/idCheck.me",
                data: { checkId: checkId },
                success: function (response) {
                    // 서버 응답 처리
                    if (response.trim() === "NNNNN") {
                        alert("이미 사용 중인 아이디입니다. 다른 아이디를 입력해주세요.");
                        // 필요한 경우 여기에서 제출 버튼을 비활성화할 수 있습니다.
                        idCheckResult = false;
                    } else if (response.trim() === "NNNNY") {
                        alert("사용 가능한 아이디입니다.");
                        // 필요한 경우 여기에서 제출 버튼을 활성화할 수 있습니다.
                        idCheckResult = true;
                        checkActivation();
                    } else {
                        alert("아이디 중복 확인에 실패했습니다. 다시 시도해주세요.");
                        idCheckResult = false;
                    }
                },
                error: function () {
                    alert("아이디 중복 확인에 실패했습니다. 다시 시도해주세요.");
                    idCheckResult = false;
                }
            });
        } else {
            alert("아이디의 길이가 4자리 미만입니다. 다시 입력해주세요");
            idCheckResult = false;
        }
    }

    function nickCheck() {
        // 입력 필드에서 닉네임 가져오기
        var nickname = document.getElementById("enrollNick").value;

        // 닉네임이 비어 있지 않은지 확인
        if (nickname.trim() !== "") {
            // 서버에 AJAX 요청 보내기
            $.ajax({
                type: "GET",
                url: "<%= request.getContextPath() %>/nickCheck.me",
                data: { checkNick: nickname },
                success: function (response) {
                    // 서버 응답 처리
                    if (response.trim() === "NNNNN") {
                        alert("이미 사용 중인 닉네임입니다. 다른 닉네임을 입력해주세요.");
                        // 필요한 경우 여기에서 제출 버튼을 비활성화할 수 있습니다.
                        nickCheckResult = false;
                    } else if (response.trim() === "NNNNY") {
                        alert("사용 가능한 닉네임입니다.");
                        // 필요한 경우 여기에서 제출 버튼을 활성화할 수 있습니다.
                        nickCheckResult = true;
                        checkActivation();
                    } else {
                        alert("닉네임 중복 확인에 실패했습니다. 다시 시도해주세요.");
                        nickCheckResult = false;
                    }
                },
                error: function () {
                    alert("닉네임 중복 확인에 실패했습니다. 다시 시도해주세요.");
                    nickCheckResult = false;
                }
            });
        } else {
            alert("닉네임을 입력하세요.");
            nickCheckResult = false;
        }
    }

    function checkActivation() {
        // 두 결과가 모두 성공일 때만 회원가입 버튼을 활성화
        if (idCheckResult && nickCheckResult) {
            document.querySelector('button[type="submit"]').disabled = false;
        } else {
            document.querySelector('button[type="submit"]').disabled = true;
        }
    }

    function checkPwd() {
        // 비밀번호와 비밀번호 확인 입력 필드에서 값을 가져오기
        var password1 = document.getElementById("password1").value;
        var password2 = document.getElementById("password2").value;

        // 비밀번호가 4자리 이상인지 확인
        if (password1.length < 4) {
            alert("비밀번호는 4자리 이상이어야 합니다.");
            document.querySelector('button[type="submit"]').disabled = true;
            return;
        }

        // 비밀번호와 비밀번호 확인이 일치하는지 확인
        if (password1 !== "" && password1 === password2) {
            // 비밀번호 일치 시 회원가입 버튼 활성화
            document.querySelector('button[type="submit"]').disabled = false;
        } else {
            // 비밀번호 불일치 시 회원가입 버튼 비활성화
            document.querySelector('button[type="submit"]').disabled = true;
            alert("비밀번호와 비밀번호 확인이 일치하지 않습니다. 다시 확인해주세요.");
        }
    }
</script>
    </div>
</body>
</html>