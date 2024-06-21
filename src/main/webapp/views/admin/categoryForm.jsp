<%@page import="com.gz.member.model.vo.Member"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
</head>
<style>
</style>
<body>
	<%@include file="../common/menubar.jsp"%>
	<div align="center">
		<h4>카테고리 관리</h4>
	</div>
	<br>
	<br>
	<header>
		<div>
			<table class="table">
				<thead>
					<tr style="text-align: center;">
						<th>카테고리 번호</th>
						<th>카테고리 이름</th>
						<th> </th>
						<th> </th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty clist}">
							<tr>
								<td colspan='4' align="center">카테고리 목록이 없습니다.</td>
							</tr>
						</c:when>
					</c:choose>
					<c:forEach items="${clist}" var="c">
						<tr style="text-align: center">
							<td><input type="text" name="no2" value="${c.categoryNo}" style="width: 50px; text-align: center;" readonly></td>
							<td><input type="text" name="name2" value="${c.categoryName}" style="width: 150px; text-align: center;"></td>
							<td><input type="button" onclick="updateCategory(this);" value="수정"></td>
							<td><input type="button" onclick="deleteCategory(this);" value="삭제"></td>
						</tr>
					</c:forEach>
				</tbody>
				<form action="../${contextPath}/insertCategory.ad" method="post">
					<tr style="text-align: center;">
						<td><input type="text" name="no" placeholder="No" style="width: 50px; text-align: center;"></td>
						<td><input type="text" name="name" placeholder="카테고리 이름" style="width: 150px; text-align: center;"></td>
						<td><button type="submit">추가</button></td>
						<td> </td>
					</tr>
				</form>
			</table>
			<script>
				//카테고리 수정 function
				function updateCategory(uCategory) {
					var updateNo = $(uCategory).parent().siblings().eq(0).children().val();
					var updateName = $(uCategory).parent().siblings().eq(1).children().val();

					let f = document.createElement('form');

					let obj;
					obj = document.createElement('input');
					obj.setAttribute('type', 'hidden');
					obj.setAttribute('name', 'no2');
					obj.setAttribute('value', updateNo);

					let obj2;
					obj2 = document.createElement('input');
					obj2.setAttribute('type', 'hidden');
					obj2.setAttribute('name', 'name2');
					obj2.setAttribute('value', updateName);

					f.appendChild(obj);
					f.appendChild(obj2);
					f.setAttribute('method', 'post');
					f.setAttribute('action', 'updateCategory.ad');
					document.body.appendChild(f);
					f.submit();
				}

				//카테고리 삭제 function
				function deleteCategory(dCategory) {
					var deleteNo = $(dCategory).parent().siblings().eq(0).children().val();
					var deleteName = $(dCategory).parent().siblings().eq(1).children().val();

					let f = document.createElement('form');

					let obj;
					obj = document.createElement('input');
					obj.setAttribute('type', 'hidden');
					obj.setAttribute('name', 'no2');
					obj.setAttribute('value', deleteNo);

					let obj2;
					obj2 = document.createElement('input');
					obj2.setAttribute('type', 'hidden');
					obj2.setAttribute('name', 'name2');
					obj2.setAttribute('value', deleteName);

					f.appendChild(obj);
					f.appendChild(obj2);
					f.setAttribute('method', 'post');
					f.setAttribute('action', 'deleteCategory.ad');
					document.body.appendChild(f);
					f.submit();
				}
			</script>
		</div>
	</header>
</body>
</html>