<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input#name {
    margin-left: -300px;
    width: 100px;
}


table.table2 {
	border-collapse: separate;
	border-spacing: 1px;
	text-align: left;
	line-height: 1.5;
	border-top: 1px solid #ccc;
	margin: 20px 10px;
	/*                 background-color: rgb(255, 225, 77); */
}

table.table2 tr {
	width: 50px;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
}

table.table2 td {
	width: 100px;
	padding: 10px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
}
</style>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
</head>
<body>
	<%@ include file="../common/menubar.jsp" %>
	<form method="get" action="write_action.php" class="horizontal-form">
		<table align=center width="60%" border=0 cellpadding=10>
			<tr>
				<td height=70 align=center
					style="background-color: rgb(255, 225, 77); font-size: 24px;"
					colspan="4"><font color=black><b>제목</b></font></td>
			</tr>
			<tr>
				<td align=right colspan="4">
					<div style="white-space: nowrap;">*이미지 업로드는 복사 붙여넣기로 가능합니다.</div>
				</td>
			</tr>

			<tr>
				<td style="background-color: rgb(255, 225, 77);">
					<table class="table2" width="100%">
						
							<tr>
							<th>아이디</th>
							<td>${b.boardWriter }</td>
							<th>작성일자</th>
							<td>${b.createDate }</td>
							<th>조회수</th>
							<td>${b.createDate }</td>
						</tr>

						<tr>
							<td colspan="4" style="padding-top: 10px;"><label
								for="content">내용</label></td>
						</tr>
						<tr>
							<td colspan="4"><textarea name="content" id="content"
									style="width: 100%;" cols=85 rows=20></textarea></td>
						</tr>
					</table>

					<center>
						<input type="submit" value="작성">
					</center>
				</td>
			</tr>
		</table>
	</form>





	<script>
		$(function() {
			$("#file-area").hide(); //file input 숨기기
			//대표이미지를 클릭하면 input file 요소 1번이 클릭되게 하는 함수
			$("#titleImg").click(function() {
				$("#file1").click();
			});
			$("#contentImg1").click(function() {
				$("#file2").click();
			});
			$("#contentImg2").click(function() {
				$("#file3").click();
			});
			$("#contentImg3").click(function() {
				$("#file4").click();
			});

		});

		//이미지를 읽어줄 함수 
		function loadImg(inputFile, num) {
			//inputFile : 이벤트가 발생된 요소 객체 
			console.log(inputFile.files);
			//inputFile.files : 파일업로드 정보를 배열의 형태로 반환해주는 속성
			//파일을 선택하면 files속성의 length가 1이 반환됨
			if (inputFile.files.length == 1) { //파일이 등록되면 
				//해당 파일을 읽어줄 FileReader라고 하는 자바스크립트 객체를 이용한다.
				var reader = new FileReader();
				//파일을 읽어줄 메소드 : reader.readAsDataURL(파일)
				//해당 파일을 읽어서 고유한 url을 부여해주는 메소드 
				//반환받은 url을 미리보기 화면에 넣어주면 된다. 
				reader.readAsDataURL(inputFile.files[0]);

				//해당 reader객체가 읽혀진 시점에 img src속성에 부여된 고유 url을 넣어주기
				reader.onload = function(e) {//e : event 객체
					console.log(e);
					//이벤트 객체에서 reader가 부여한 고유 url 정보 
					//event.target.result 
					console.log(e.target.result);

					switch (num) {
					case 1:
						$("#titleImg").attr("src", e.target.result);
						break;
					case 2:
						$("#contentImg1").attr("src", e.target.result);
						break;
					case 3:
						$("#contentImg2").attr("src", e.target.result);
						break;
					case 4:
						$("#contentImg3").attr("src", e.target.result);
						break;
					}

				}

			} else {//length가 1이 아니면 
				switch (num) {
				case 1:
					$("#titleImg").attr("src", null);
					break;
				case 2:
					$("#contentImg1").attr("src", null);
					break;
				case 3:
					$("#contentImg2").attr("src", null);
					break;
				case 4:
					$("#contentImg3").attr("src", null);
					break;
				}

			}
		}
	</script>


	<br>
	<br>
	</div>
</body>
</html>