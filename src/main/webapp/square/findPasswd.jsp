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
		
		
		<form action="/findPasswd.mem" method="post" id="userInfoChk">
			<div class="mb-3">
			  <label for="useridChk" class="form-label">아이디</label>
			  <input type="text" class="form-control" id="useridChk" placeholder="아이디를 입력하세요">
			</div>
			<div class="mb-3">
			  <label for="phone" class="form-label">휴대폰 번호</label>
			  <input type="text" class="form-control" id="phone" placeholder="휴대폰 번호를 입력하세요">
			</div>
			<div class="mb-4">
			  <label for="email" class="form-label">이메일</label>
			  <input type="text" class="form-control" id="email" placeholder="이메일을 입력하세요">
			</div>
			<div class="mb-3 resultArea" style="text-align: center;">
				<div id="result"></div>
				<button class="btn btn-md btn-primary" type="button" onclick="userInfoChk()">비밀번호 찾기</button>
			</div>
		</form>
		
		<form action="/resetPasswd.mem" method="post" id="resetPasswdFrm">
			<input type="hidden" name="userid" id="userid" value="">
			<div class="mb-3">
			  <label for="newPasswd" class="form-label">비밀번호</label>
			  <input type="password" class="form-control" name="pw1" id="newPasswd" placeholder="새로운 비밀번호를 입력하세요">
			</div>
			<div class="mb-3">
			  <label for="passwdChk" class="form-label">비밀번호 확인</label>
			  <input type="password" class="form-control" name="pw2" id="passwdChk" placeholder="비밀번호를 한번 더 입력해주세요" oninput="checkPwd()">
			</div>
			
			<div class="comentArea"></div>
			
			<div class="mb-3 resultArea" style="text-align: center;">
				<button class="btn btn-md btn-primary subBtn" type="submit">비밀번호 변경</button>
			</div>
		</form>
		
	</body>
	<script>
	
		$(document).ready(function(){
			$("#resetPasswdFrm").hide();
			$(".subBtn").prop("disabled", true);
		});
	
		function userInfoChk(){
			var userid = $("#useridChk").val();
			var phone = $("#phone").val();
			var email = $("#email").val();
			
			$.ajax({
				type:'POST',
				url:'/findPasswd.mem',
				data:{
					userid : userid,
					phone : phone,
					email : email
					},
				success:function(data){
					
					if(data === 'yes'){
						$("#userInfoChk").hide();
						$("#resetPasswdFrm").show();
						$("#userid").val(userid);
					}else{
						$('#result').html('<div class="alert alert-danger" role="alert">' +
						  '일치하는 사용자가 없습니다.</div>');
					}
				}
			});
		}
		
		function checkPwd() {
	        var userpw1 = $('#newPasswd').val();
	        var userpw2 = $('#passwdChk').val();

	        if(userpw1 === userpw2){
	        	$(".comentArea").hide();
				$(".subBtn").prop("disabled", false);
	        }else{
	        	$('.comentArea').html('<div class="alert alert-danger" role="alert">' +
				  '비밀번호가 일치하지 않습니다.</div>');
				$(".subBtn").prop("disabled", true);
	        }
	    }
	</script>
</html>

