<%@page import="com.gz.member.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<body>
	<%@include file="../common/menubar.jsp"%>
	<div align="center">
		<h4>사용자 관리</h4>
	</div>
	<header>
		<br>
	<!-- 회원정보 검색 후 출력 -->
	<form action="find.ad">
		<select name="searchField" id="search">
			<!--  searchField : 검색옵션 -->
			<option value="MEMBER_NAME">회원이름</option>
			<option value="MEMBER_ID">회원아이디</option>
		</select> <input type="text" placeholder="검색어 입력" name="searchText">
		<button type="submit" class="btn btn-success">검색</button>
	</form>
	<script>
		//검색후 옵션 고정

		window.addEventListener('load', function() {
			const searchSelect = document.getElementById('search');
			const savedOption = localStorage.getItem('searchOption');

			if (savedOption) {
				searchSelect.value = savedOption;
			}

			// 선택 옵션이 변경될 때 로컬 스토리지에 저장
			searchSelect.addEventListener('change', function() {
				localStorage.setItem('searchOption', searchSelect.value);
			});
		});
	</script>
	<div>
		<div>
			<div>
				<div>
					<table class="table">
						<thead>
							<tr style="text-align: center;">
								<th width="60px">번호</th>
								<th>아이디</th>
								<th>이름</th>
								<th>닉네임</th>
								<th>이메일</th>
								<th>회원<br>등급
								</th>
								<th>가입일</th>
								<th>계정<br>상태
								</th>
								<th></th>
							</tr>
						</thead>
						<tbody>
							<c:choose>
								<c:when test="${empty list}">
									<tr>
										<td colspan='9' align="center">사용자가 없습니다.</td>
									</tr>
								</c:when>
							</c:choose>
							<c:forEach items="${list}" var="m" varStatus="vs">
								<tr style="text-align: center;" id="${vs.index}">
									<td>${m.memberNo}</td>
									<td>${m.memberId}</td>
									<td>${m.memberName}</td>
									<td>${m.nickname}</td>
									<td>${m.email}</td>
									<td><select id="grade" name="grade">
											<option>${m.grade}</option>
											<option>
												<c:choose>
													<c:when test="${m.grade == '관리자'}">일반회원</c:when>
													<c:otherwise>관리자</c:otherwise>
												</c:choose>
											</option>
									</select></td>
									<td>${m.enrolldate}</td>
									<td><select id="status" name="status">
											<option>${m.status}</option>
											<option>
												<c:choose>
													<c:when test="${m.status == 'Y'}">N</c:when>
													<c:otherwise>Y</c:otherwise>
												</c:choose>
											</option>
									</select></td>
									<td><input type="button" onclick="updateAdmin(this);" value="수정"></td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<!-- 수정 스크립트 -->
					<script>
						function updateAdmin(upAdmin) {
							var updateNo = $(upAdmin).closest('tr').find(
									'td:first-child').text(); //memberNo
							var updateGrade = $(upAdmin).closest('tr').find(
									"#grade option:selected").val(); //grade select 박스
							var updateStatus = $(upAdmin).closest('tr').find(
									"#status option:selected").val(); //status select 박스

							let f = document.createElement('form');

							let updateNo1;
							updateNo1 = document.createElement('input');
							updateNo1.setAttribute('type', 'hidden');
							updateNo1.setAttribute('name', 'memberNo');
							updateNo1.setAttribute('value', updateNo);

							let updateGrade2;
							updateGrade2 = document.createElement('input');
							updateGrade2.setAttribute('type', 'hidden');
							updateGrade2.setAttribute('name', 'grade');
							updateGrade2.setAttribute('value', updateGrade);

							let updateStatus3;
							updateStatus3 = document.createElement('input');
							updateStatus3.setAttribute('type', 'hidden');
							updateStatus3.setAttribute('name', 'status');
							updateStatus3.setAttribute('value', updateStatus);

							f.appendChild(updateNo1);
							f.appendChild(updateGrade2);
							f.appendChild(updateStatus3);
							f.setAttribute('method', 'post');
							f.setAttribute('action', 'update.ad');
							document.body.appendChild(f);
							f.submit();
						}
					</script>
					<!-- 페이징바 -->
					<div align="center">
						<c:choose>
							<c:when test="${pi.currentPage eq 1}">
								<button disabled>이전</button>
							</c:when>
							<c:otherwise>
								<button
									onclick="location.href='selectUser.ad?currentPage=${pi.currentPage-1}'">이전</button>
							</c:otherwise>
						</c:choose>
						<c:forEach var="i" begin="${pi.startPage}" end="${pi.endPage}">
							<button onclick="location.href='selectUser.ad?currentPage=${i}'">${i}</button>
						</c:forEach>
						<c:choose>
							<c:when test="${pi.currentPage eq pi.maxPage}">
								<button disabled>다음</button>
							</c:when>
							<c:otherwise>
								<button
									onclick="location.href='selectUser.ad?currentPage=${pi.currentPage+1}'">다음</button>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</div>
	</header>
</body>
<footer>

	<%@include file="../common/footer.jsp" %>

</footer>
</html>
