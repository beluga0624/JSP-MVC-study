<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
		<title>Insert title here</title>
		<style>
			* {
				margin: 0;
				padding: 0;
			}
		
			.siteMainImg {
				background-image: url("../resources/images/mainImg.jpg");
				width: 100vw;
				height: 20%;

			}
			
			.siteMainImg p {
				font-size: 30px;
				color: white;
				text-align: center;
				line-height: 50px;
			}
			
			form {
				width: 90vw;
				margin: auto;
			}
			
			.btn-primary {
				margin: auto;
			}
		</style>
	</head>
	<body>
		
		<div class="siteMainImg img-fluid">
			<p>SQUARE<sup>SQUARE</sup></p>
		</div>
		
		
		<form action="/findUserid.mem" method="post">
			<div class="mb-3">
			  <label for="name" class="form-label">이름</label>
			  <input type="text" class="form-control" id="name" placeholder="이름을 입력하세요">
			</div>
			<div class="mb-4">
			  <label for="email" class="form-label">이메일</label>
			  <input type="text" class="form-control" id="email" placeholder="이메일을 입력하세요">
			</div>
			<div class="mb-3 resultArea" style="text-align: center;">
				<button class="btn btn-md btn-primary" type="button" onclick="findID()">아이디 찾기</button>
				<div id="result">찾으시는 아이디는 <span id="resultData" style="font-size: 20px; font-weight: bold;"></span> 입니다.</div>
			</div>
		</form>
		
	</body>
	<script>
		$(document).ready(function(){
			$("#result").hide();
		});
	
		function findID(){
			
			var name = $("#name").val();
			var email = $("#email").val();
		
			$.ajax({
				type:'POST',
				url:'/findUserid.mem',
				data:{
					name : name,
					email : email
					},
				success:function(data){
					
					$(".resultArea").children("button").hide();
					$("#resultData").append(data);
					$("#result").show();
				}
			});
		}
	</script>
</html>

