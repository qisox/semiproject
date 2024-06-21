<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.ArrayList"%>

<!DOCTYPE html>
<html lang="ko">
<head>
<%@ include file="../common/menubar.jsp" %>
<meta charset="utf-8">
<meta content="width=device-width, initial-scale=1.0" name="viewport">

<title>Users / Profile - NiceAdmin Bootstrap Template</title>
<meta content="" name="description">
<meta content="" name="keywords">
	<!-- Favicons -->
	<link href="resources/assets/img/favicon.png" rel="icon">
	<link href="resources/assets/img/apple-touch-icon.png"
		rel="apple-touch-icon">
	
	<!-- Google Fonts -->
	<link href="https://fonts.gstatic.com" rel="preconnect">
	<link
		href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Nunito:300,300i,400,400i,600,600i,700,700i|Poppins:300,300i,400,400i,500,500i,600,600i,700,700i"
		rel="stylesheet">

<!-- Template Main CSS File -->
<!-- <link href="../../resources/assets/css/style.css" rel="stylesheet"> -->



<style>
:root {
  scroll-behavior: smooth;
}

body {
  font-family: "Open Sans", sans-serif;
  background: #f6f9ff;
  color: #444444;
}

a {
  color: #4154f1;
  text-decoration: none;
}

a:hover {
  color: #717ff5;
  text-decoration: none;
}

h1,
h2,
h3,
h4,
h5,
h6 {
  font-family: "Nunito", sans-serif;
}

table>tbody tr .title:hover{
	cursor: pointer;
}

/*--------------------------------------------------------------
# Main
--------------------------------------------------------------*/
#main {
  margin-top: 60px; 
  padding: 60px;
  transition: all 0.3s;
}


@media (max-width: 1200px) {
  #main {
    padding: 20px;
  }
}

/*--------------------------------------------------------------
# Page Title
--------------------------------------------------------------*/
.pagetitle {
  margin-bottom: 10px;
}

.pagetitle h1 {
  font-size: 24px;
  margin-bottom: 0;
  font-weight: 600;
  color: #012970;
}



/* Card */
.card {
  margin-bottom: 30px;
  border: none;
  border-radius: 5px;
  box-shadow: 0px 0 30px rgba(1, 41, 112, 0.1);
}

.card-header,
.card-footer {
  border-color: #ebeef4;
  background-color: #fff;
  color: #798eb3;
  padding: 15px;
}

.card-title {
  padding: 20px 0 15px 0;
  font-size: 18px;
  font-weight: 900;
  color: #012970;
  font-family: "Poppins", sans-serif;
}

.card-title span {
  color: #899bbd;
  font-size: 14px;
  font-weight: 400;
}

.card-body {
  padding: 0 20px 20px 20px;
}

.card-img-overlay {
  background-color: rgba(255, 255, 255, 0.6);
}

/* Bordered Tabs */
.nav-tabs-bordered {
  border-bottom: 2px solid #ebeef4;
}

.nav-tabs-bordered .nav-link {
  margin-bottom: -2px;
  border: none;
  color: #2c384e;
}

.nav-tabs-bordered .nav-link:hover,
.nav-tabs-bordered .nav-link:focus {
  color: #FDD946;
}

.nav-tabs-bordered .nav-link.active {
  background-color: #fff;
  color: black;
  border-bottom: 2px solid #FDD946;
}


/*--------------------------------------------------------------
# Profie Page
--------------------------------------------------------------*/
.profile .profile-card img {
  max-width: 120px;
}

.profile .profile-card h2 {
  font-size: 24px;
  font-weight: 700;
  color: #2c384e;
  margin: 10px 0 0 0;
}

.profile .profile-card h3 {
  font-size: 18px;
}

.profile .profile-card .social-links a {
  font-size: 20px;
  display: inline-block;
  color: rgba(1, 41, 112, 0.5);
  line-height: 0;
  margin-right: 10px;
  transition: 0.3s;
}

.profile .profile-card .social-links a:hover {
  color: #012970;
}

.profile .profile-overview .row {
  margin-bottom: 20px;
  font-size: 15px;
}

.profile .profile-overview .card-title {
  color: #012970;
}

.profile .profile-overview .label {
  font-weight: 600;
  color: rgba(1, 41, 112, 0.6);
}

.profile .profile-edit label {
  font-weight: 600;
  color: rgba(1, 41, 112, 0.6);
}

.profile .profile-edit img {
  max-width: 120px;
}
/*--------------------------------------------------------------
# 테이블 뒤로가기 버튼
--------------------------------------------------------------*/
.back-btn{
	margin:20px;
}
#tableBackBtn{
	display:none;
}
/*--------------------------------------------------------------
# 테이블 스타일
--------------------------------------------------------------*/
.table-container {
            max-height: 600px;
            overflow-y: auto;
           
}
.myTable {
    border-collapse: collapse;
    width: 100%;
}
.myTable th{
    border: 1px solid black;
    padding: 8px;
    text-align: center;
}
.myTable td{
    border: 1px solid black;
    padding: 8px;
    text-overflow: ellipsis;
    white-space: nowrap;
    overflow: hidden;
    max-width: 100px;
    text-align: center;
}
.myTable td.title{
	text-align:left;
}
.myTable>thead {
    position: sticky;
    top: 0;
    background-color: #f8f8f8;
}
.myTable>tfoot{
	text-align:center;
}
/*체크박스를 포함한 td 스타일 설정(가운데 정렬)*/
.tdForChkBox {
	text-align: center;
	vertical-align: middle;
	height: 100%;
}

.chkBoxTh {
	width: 10%;
}

/*--------------------------------------------------------------
# 각 테이블 위 헤더 스타일
--------------------------------------------------------------*/
#interest-header,#like-header,#myPost-header,#myComment-header{
	display: flex;
	align-items: center;
	width: 100%;
	justify-content: space-between;
}
.flexDiv{
align-items: center;
	display: flex;
}
.flexDiv button{
	margin-left: 10px;
}

/*--------------------------------------------------------------
# 마이페이지 검색창 스타일
--------------------------------------------------------------*/
.search {
    position: relative;
    width: 300px;
    }

.search-input {
  width: 100%;
  height: 35px;
  border: 1px solid #012970;
  border-radius: 8px;
  padding: 10px 12px;
  font-size: 14px;
  margin-left:20px;
}

.search-icon {
  position : absolute;
  width: 17px;
  top: 10px;
  right: 5px;
  
  margin: 0;
}
</style>

</head>

<body>
	
	<!-- 정보 수정 후 alsertMsg받아서 처리 -->
   	<c:if test="${not empty sessionScope.myAlertMsg}">
    	<script>
    		alert("${sessionScope.myAlertMsg}");
    	</script>
    	<c:remove var="myAlertMsg" scope="session" />
    </c:if>
	<!-- ======= Header ======= -->
	
	<main id="main" class="main">
		<div class="pagetitle">
			<h1>마이페이지</h1>
		</div>
		<!-- End Page Title -->

		<section class="section profile">
			<div class="row">
				<!--좌측 프로필,이름,이메일 보이는 구역-->
				<div class="col-xl-3">
					<div class="card">
						<div
							class="card-body profile-card pt-4 d-flex flex-column align-items-center">
							<img src="resources/assets/img/profile-img.jpg" alt="Profile"
								class="rounded-circle">
							<h2>${loginMember.nickname}</h2>
							<br>
							<h3>${loginMember.email}</h3>
							<div class="social-links mt-2">
								<a href="#" class="twitter"><i class="bi bi-twitter"></i></a> <a
									href="#" class="facebook"><i class="bi bi-facebook"></i></a> <a
									href="#" class="instagram"><i class="bi bi-instagram"></i></a>
								<a href="#" class="linkedin"><i class="bi bi-linkedin"></i></a>
							</div>
						</div>
					</div>
				</div>
				<!--좌측 프로필,이름,이메일 보이는 구역 끝-->
				
				<!--우측 card 구역 시작-->
				<div class="col-xl-8">	
					<div class="card">
						<div class="card-body pt-3">
							<!--탭 구역-->
							<ul class="nav nav-tabs nav-tabs-bordered">
								<li class="nav-item">
									<button class="nav-link active" data-bs-toggle="tab"
										data-bs-target="#profile-info">내 정보</button>
								</li>
								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-interest"
										id="interestPostTab">관심 글</button>
								</li>

								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-like"
										id="likePostTab">내가 좋아요 한 글</button>
								</li>

								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-post"
										id="myPostTab">내가 쓴 글</button>
								</li>
								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-comment"
										id="myCommentTab">내가 쓴 댓글</button>
								</li>

								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-change-password"
										id="changePwdTab">비밀번호 변경</button>
								</li>
								<li class="nav-item">
									<button class="nav-link" data-bs-toggle="tab"
										data-bs-target="#profile-delete_member"
										id="deleteMemberTab">회원 탈퇴</button>
								</li>
							</ul>
							<!--탭 구역 끝-->
							
							<div class="tab-content pt-2">
								<!--내정보/정보변경 내용 구역-->
								
								
									<div class="tab-pane fade show active profile-overview"
										id="profile-info">
										<form action="${contextPath}/memUpdate.my" method="post">
										<h5 class="card-title">내 정보</h5>
	
										<div class="row mb-3">
											<label for="profileImage"
												class="col-md-4 col-lg-3 col-form-label">프로필</label>
											<div class="col-md-8 col-lg-9">
												<img src="resources/assets/img/profile-img.jpg" alt="Profile">
												<div class="pt-2">
													<!--프로필 사진 업로드 구현-->
													<!--부트스트랩에서 제공하는 업로드 버튼을 사용해서 file업로드를 구현하기 위해
	                        file이란 id를 가진 input태그를 숨겨두고 label만 보이게 해둠. label 안에는 
	                      부트스트랩에서 제공하는 업로드버튼형식의 div가 있고, div를 클릭하면 file창이 열림.-->
													<label for="file">
														<div class="btn btn-primary btn-sm btn-sm"
															title="Upload new profile image">
															프로필 업로드
															<i class="bi bi-upload"></i>
														</div>
														
													</label> 
													<input type="file" name="file" id="file"
														style="display: none;"> 
														
												</div>
											</div>
										</div>
	
										<div class="row mb-3">
											<label for="userId" class="col-md-4 col-lg-3 col-form-label">아이디</label>
											<div class="col-md-8 col-lg-9">
												<input name="userId" type="text" class="form-control"
													id="userId" value="${loginMember.memberId}" readonly>
											</div>
										</div>
	
										<div class="row mb-3">
											<label for="userId" class="col-md-4 col-lg-3 col-form-label">이름</label>
											<div class="col-md-8 col-lg-9">
												<input name="userName" type="text" class="form-control"
													id="userName" value="${loginMember.memberName}">
											</div>
										</div>
	
										<div class="row mb-3">
											<label for="userNickName"
												class="col-md-4 col-lg-3 col-form-label">별명</label>
											<div class="col-md-8 col-lg-9">
												<input name="userNickName" type="text" class="form-control"
													id="userNickName" maxlength="15"  value="${loginMember.nickname}">
												<p style="margin-top: 5px; color:red;">최대 15글자 입력 가능</p>
											</div>
										</div>
	
										<div class="row mb-3">
											<label for="email" class="col-md-4 col-lg-3 col-form-label">이메일</label>
											<div class="col-md-8 col-lg-9">
												<input name="email" type="email" class="form-control"
													id="email" value="${loginMember.email}">
											</div>
										</div>
	
										<div class="row mb-3">
											<label for="enrollDate"
												class="col-md-4 col-lg-3 col-form-label">가입일</label>
											<div class="col-md-8 col-lg-9">
												
												<input name="enrollDate" type="text" class="form-control"
													id="enrollDate" value="${loginMember.enrolldate}" readonly>
											</div>
										</div>
	
	
	
										<div class="text-center">
											<button type="submit" id="memUpdateBtn" class="btn btn-primary btn-sm">수정 완료</button>
										</div>
									
										</form>
									</div>
								
								<!--내정보 내용 구역 끝-->

								<!--관심글 내용 구역-->
								<%@include file="../mypage/interestTable.jsp" %>
								<!--관심글 내용 구역 끝-->
								
								<!--좋아요 내용 구역-->
								<%@include file="../mypage/likeTable.jsp" %>
								<!--좋아요 내용 구역 끝-->

								<!--내가 쓴 글 내용 구역-->
								<%@include file="../mypage/myPostTable.jsp" %>
								<!--내가 쓴 글 내용 구역 끝-->

								<!--내가 쓴 댓글 내용 구역-->
								<%@include file="../mypage/myCommentTable.jsp" %>
								<!--내가 쓴 댓글 내용 구역 끝-->


								<!--비밀번호 변경 내용 구역-->
								<div class="tab-pane fade pt-3" id="profile-change-password">
									<!-- 비밀번호 변경 Form -->
									<form action="${contextPath}/memberPwdUpdate.my?mno=${loginMember.memberNo}" method="post">
										<div class="row mb-3">
											<label for="pwd"
												class="col-md-4 col-lg-3 col-form-label">현재 비밀번호</label>
											<div class="col-md-8 col-lg-9">
												<input name="pwd" type="password" class="form-control"
													id="pwd" minlength="4" required>
											</div>
										</div>

										<div class="row mb-3">
											<label for="newPwd"
												class="col-md-4 col-lg-3 col-form-label">새로운 비밀번호</label>
											<div class="col-md-8 col-lg-9">
												<input name="newPwd" type="password"
													class="form-control" id="newPwd" minlength="4" required>
											</div>
											<p style="margin-top: 5px; color:red;">4자 이상 입력</p>
										</div>

										<div class="row mb-3">
											<label for="reNewPwd"
												class="col-md-4 col-lg-3 col-form-label">새로운 비밀번호 확인</label>
											<div class="col-md-8 col-lg-9">
												<input name="reNewPwd" type="password"
													class="form-control" id="reNewPwd" minlength="4" required>
											</div>
										</div>

										<div class="text-center">
											<button type="submit" class="btn btn-primary btn-sm" onclick="return chkEquealNewPwd();">비밀번호 변경</button>
										</div>
									</form>
									<script>
										function chkEquealNewPwd(){
											if($(newPwd).val() != $(reNewPwd).val()){
												alert("새로운 비밀번호 확인을 다시한번 확인해주세요.");
												return false;
											} else {
												return true;
											}
										}
									</script>
								</div>
								<!--비밀번호 변경 내용 구역 끝-->
								<!--회원탈퇴 내용 구역-->
								<div class="tab-pane fade pt-3" id="profile-delete_member">
									<!-- 회원탈퇴 Form -->
									<form action="${contextPath}/memberDelete.my?mno=${loginMember.memberNo}" method="post">
										<div class="row mb-3">
											<label for="pwd"
												class="col-md-4 col-lg-3 col-form-label">현재 비밀번호</label>
											<div class="col-md-8 col-lg-9">
												<input name="pwd" type="password" class="form-control"
													id="pwd" minlength="4" required>
											</div>
										</div>
										<div class="text-center">
											<button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteMemModal">회원탈퇴</button>
										</div>
									
										<div class="modal fade" id="deleteMemModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
											<div>
											  <div class="modal-dialog">
											    <div class="modal-content">
											      <div class="modal-header">
											        <h1 class="modal-title fs-5" id="exampleModalLabel">회원 탈퇴</h1>
											        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
											      </div>
											      <div class="modal-body">
											        <p style="color:red;">정말로 탈퇴하시겠습니까?</p>
											      </div>
											      <div class="modal-footer">
											        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
											        <button type="submit" class="btn btn-danger">회원 탈퇴</button>
											      </div>
											    </div>
											  </div>
											</div>
										</div>

									</form>
									
								</div>
								
								
								<!--회원탈퇴 내용 구역 끝-->
							</div>
						</div>
						

					</div>
				</div>
				<!--우측 card 구역 끝-->
			</div>
			
			
			
			
			
			
		</section>

	</main>
	
	<!-- End #main -->

	<!-- ======= Footer ======= -->
	<footer id="footer" class="footer">
		<div class="copyright">
			&copy; Copyright <strong><span>NiceAdmin</span></strong>. All Rights
			Reserved
		</div>
		<div class="credits">
			Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
		</div>
	</footer>
	<!-- End Footer -->

	<a href="#"
		class="back-to-top d-flex align-items-center justify-content-center"><i
		class="bi bi-arrow-up-short"></i></a>





</body>

</html>