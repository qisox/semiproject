<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.gz.member.model.vo.Member" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <style>
        .outer {
            color: black;
            width: 1000px;
            margin: auto;
            margin-top: 50px;
            padding: 20px;
        }

        #login-form {
            text-align: center;
        }

        #login-form input[type="text"],
        #login-form input[type="password"] {
            height: 50px;
            width: 250px;
            border-radius: 5px;
            background-color: rgb(241, 241, 241);
            border: none;
            margin-bottom: 10px;
        }

        #login-form button {
            border-radius: 5px;
            background-color: rgb(255, 219, 41);
            height: 50px;
            width: 250px;
            border: none;
        }

        @font-face {
            font-family: 'SEBANG_Gothic_Bold';
            src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2104@1.0/SEBANG_Gothic_Bold.woff') format('woff');
            font-weight: normal;
            font-style: normal;
        }
        .logo div:hover{
        	cursor : pointer;
        }
        .findDiv button:hover{
        	cursor:pointer;
        }
    </style>
</head>
<body>

<div class="logo" align="center" >
    <div style="display: flex; align-items: center; justify-content: center;" onclick="window.location.href='<%=request.getContextPath()%>';">
        <h2 style="font-size: 48px; font-weight: bold; color: rgb(241, 205, 27); font-family: SEBANG_Gothic_Bold; display: inline;">꿀.zip</h2>
        <img src="../img/bee.png" alt="Bee Image" width="50" height="50" style="display: inline; margin-top: -15px">
    </div>
</div>

<div class="outer">
    <h2 align="center" style="margin-left: -180px; color: rgb(252, 212, 11); margin-top: 40px">Login</h2>

    <form id="login-form" action="<%=request.getContextPath()%>/login.me" method="post">
        <table align="center">
            <tr>
                <td>
                    <input type="text" id="memberId" name="memberId" required placeholder="아이디">
                </td>
            </tr>
            <tr>
                <td><i class="bi bi-file-lock"></i>
                    <input type="password" name="memberPwd" required placeholder="비밀번호">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="submit" style="margin-bottom: 10px;"><b>로그인</b></button><br>
                    <button onclick="location.href='<%=request.getContextPath()%>/enrollForm.me'"><b>회원가입</b></button>
                </td>
            </tr>
        </table>
    </form>
    <br>

    <!-- 버튼으로 회원가입 및 아이디/비밀번호 찾기 기능 액세스 -->
    <div class="findDiv" align="center" style="margin-left: -80px">
        <button onclick="location.href='<%=request.getContextPath()%>/findId.me'" style="background-color: transparent; border: none;">아이디 찾기</button>
        <button onclick="location.href='<%=request.getContextPath()%>/findPwd.me'" style="background-color: transparent; border: none;">비밀번호 찾기</button>
    </div>

    <% Member loginMember = (Member) session.getAttribute("loginMember");
       if (loginMember != null) { %>
       <script>
           window.location.href = "<%=request.getContextPath()%>/Board/src/main/webapp/index.jsp";
       </script>
    <% } else { %>
    <!-- 로그인하지 않은 사용자를 위한 콘텐츠 -->
    <form action="<%=request.getContextPath()%>/login.me" id="login-form" method="post">
        <!-- 로그인 양식을 여기에 추가 -->
    </form>
    <% } %>
</div>
</body>
</html>