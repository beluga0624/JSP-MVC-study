<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <style>
    	.footerBack {
			background: #212429;
			margin: 0;
		}
		
		.footerBack h5 {
			color: white;
		}
		
		.logo {
			margin: auto;
		}
		
		.link-dark {
			font-size: 30px;
			color: white;
			text-align: center;
			line-height: 50px;
		}
		
		.footerBack{
			margin-bottom: 0;
			padding-bottom: 0;
		}
		
    </style>
    
		<!-- footer -->
		<div class="container footerBack">
		  <footer class="row row-cols-5 py-5 my-0 border-top">
		    <div class="col logo">
		      <a href="/main.square" class="d-flex align-items-center mb-3 link-dark text-decoration-none">
			     SQUARE<sup>SQUARE</sup>
		      </a>
		      
		      <p class="text-muted">&copy; 2022 Company, Inc</p>
		      
		    </div>
		
		    <div class="col">
		
		    </div>
		
		    <div class="col">
		      <h5>Section</h5>
		      <ul class="nav flex-column">
		        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Home</a></li>
		        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Features</a></li>
		        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Pricing</a></li>
		        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">FAQs</a></li>
		        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">About</a></li>
		      </ul>
		    </div>
		
		    <div class="col">
		      <h5>Section</h5>
		      <ul class="nav flex-column">
		        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Home</a></li>
		        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Features</a></li>
		        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Pricing</a></li>
		        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">FAQs</a></li>
		        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">About</a></li>
		      </ul>
		    </div>
		
		    <div class="col">
		      <h5>Section</h5>
		      <ul class="nav flex-column">
		        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Home</a></li>
		        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Features</a></li>
		        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">Pricing</a></li>
		        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">FAQs</a></li>
		        <li class="nav-item mb-2"><a href="#" class="nav-link p-0 text-muted">About</a></li>
		      </ul>
		    </div>
		  </footer>
		</div>
		<!-- /footer -->
		
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
		<script src="http://code.jquery.com/jquery-latest.min.js"></script>
		<script type="text/javascript">
			
		
			//로그인, 회원가입 유효성 검사
			function loginCheck(){
				if(!document.loginForm.useridLogin.value){
					alert("아이디를 입력하세요.");
					return loginForm.useridLogin.focus();
				}
				
				if(!document.loginForm.userpwLogin.value){
					alert("비밀번호를 입력하세요.");
					return loginForm.userpw.focus();
				}
				
				loginForm.submit();
			}
		
			function checkForm() {
			
				if (!document.memberInfo.useridJoin.value) {
					alert("아이디를 입력하세요.");
					
					return memberInfo.useridJoin.focus();
				}
		
				if (!document.memberInfo.userpw1.value) {
					alert("비밀번호를 입력하세요.");
					
					return memberInfo.userpw1.focus();
				}
		
				if (document.memberInfo.userpw2.value != document.memberInfo.userpw1.value) {
					alert("비밀번호를 동일하게 입력하세요.");
					return memberInfo.userpw2.focus();
				}
				
				if (!document.memberInfo.userName.value) {
					alert("이름을 입력하세요.");
					return memberInfo.userName.focus();
				}
				
				if (!document.memberInfo.birthDate.value) {
					alert("생년월일을 입력하세요.");
					return memberInfo.birthDate.focus();
				}		
				
				if (!document.memberInfo.gender.value) {
					alert("성별을 선택하세요.");
					return memberInfo.gender.focus();
				}
				
				if (!document.memberInfo.phone.value) {
					alert("휴대폰 번호를 입력하세요.");
					return memberInfo.phone.focus();
				}	
				
				if (!document.memberInfo.email.value) {
					alert("이메일을 입력하세요.");
					return memberInfo.email.focus();
				}	
				
				if (!document.memberInfo.postcd.value) {
					alert("우편번호 찾기를 실행하세요");
					return memberInfo.userpw1.focus();
				}	
				
				if (!document.memberInfo.addr2.value) {
					alert("상세주소를 입력하세요");
					return memberInfo.addr2.focus();
				}
				
				memberInfo.submit();
			}
			
			//아이디 중복 검사
			function checkID(){
				
				var userid = $('#useridJoin').val();

				$.ajax({
					type:'POST',
					url:'/checkID.mem',
					data:{userid: userid},
					success:function(data){
						
						console.log(data);
						
						if(data === "available"){
							$('.idCheck').html('<div class="alert alert-success" role="alert">' +
									  '사용할 수 있는 아이디 입니다.</div>');
							$(".subBtn").prop("disabled", false);
							
						}else if(data === "unavailable"){
							$('.idCheck').html('<div class="alert alert-danger" role="alert">' +
									  '이미 존재하는 아이디 입니다.</div>');
							$(".subBtn").prop("disabled", true);
						}
						
						if($('#useridJoin').val().length < 6){
							$('.idCheck').html('<div class="alert alert-danger" role="alert">' +
							  '아이디를 6자 이상 입력하세요.</div>');
							$(".subBtn").prop("disabled", true);
						}
					}
				});
			}
			
			//이메일 중복 체크
			function checkEmail(){
				
				var email = $('#email').val();

				$.ajax({
					type:'POST',
					url:'/checkEmail.mem',
					data:{email: email},
					success:function(data){
						
						console.log(data);
						
						if(data === "available"){
							$('.emailCheck').html('<div class="alert alert-success" role="alert">' +
									  '사용할 수 있는 이메일 입니다.</div>');
							$(".subBtn").prop("disabled", false);
							
						}else if(data === "unavailable"){
							$('.emailCheck').html('<div class="alert alert-danger" role="alert">' +
									  '이미 존재하는 이메일 입니다.</div>');
							$(".subBtn").prop("disabled", true);
						}
						
						if($('#email').val().length < 5){
							$('.emailCheck').html('<div class="alert alert-danger" role="alert">' +
							  '이메일 형식이 틀렸습니다.</div>');
							$(".subBtn").prop("disabled", true);
						}
					}
				});
			}
			
			//전화번호 중복 체크
			function checkPhone(){
				
				var phone = $('#phone').val();

				$.ajax({
					type:'POST',
					url:'/checkPhone.mem',
					data:{phone: phone},
					success:function(data){
						
						console.log(data);
						
						if(data === "available"){
							$('.phoneCheck').html('<div class="alert alert-success" role="alert">' +
									  '사용할 수 있는 전화번호 입니다.</div>');
							$(".subBtn").prop("disabled", false);
							
						}else if(data === "unavailable"){
							$('.phoneCheck').html('<div class="alert alert-danger" role="alert">' +
									  '이미 존재하는 전화번호 입니다.</div>');
							$(".subBtn").prop("disabled", true);
						}

					}
				});
			}
			
			//비밀번호 체크
			function checkPwd() {
		        var userpw1 = $('#userpw1').val();
		        var userpw2 = $('#userpw2').val();

		        if(userpw1 === userpw2){
		        	$('.pw2Check').html('<div class="alert alert-success" role="alert">' +
					  '비밀번호가 일치합니다.</div>');
					$(".subBtn").prop("disabled", false);
		        }else{
		        	$('.pw2Check').html('<div class="alert alert-danger" role="alert">' +
					  '비밀번호가 일치하지 않습니다.</div>');
					$(".subBtn").prop("disabled", true);
		        }
		    }

		</script>
		<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
		<script>
		    function Postcode() {
		        new daum.Postcode({
		            oncomplete: function(data) {
		                // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
		
		                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
		                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
		                var addr = ''; // 주소 변수
		                var extraAddr = ''; // 참고항목 변수
		
		                //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
		                if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
		                    addr = data.roadAddress;
		                } else { // 사용자가 지번 주소를 선택했을 경우(J)
		                    addr = data.jibunAddress;
		                }
		
		                // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
		                if(data.userSelectedType === 'R'){
		                    // 법정동명이 있을 경우 추가한다. (법정리는 제외)
		                    // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
		                    if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
		                        extraAddr += data.bname;
		                    }
		                    // 건물명이 있고, 공동주택일 경우 추가한다.
		                    if(data.buildingName !== '' && data.apartment === 'Y'){
		                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
		                    }
		                    // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
		                    if(extraAddr !== ''){
		                        extraAddr = ' (' + extraAddr + ')';
		                    }
		                    // 조합된 참고항목을 해당 필드에 넣는다.
		                    /* document.getElementById("addr2").value = extraAddr; */
		                
		                } else {
		                    document.getElementById("addr2").value = '';
		                }
		
		                // 우편번호와 주소 정보를 해당 필드에 넣는다.
		                document.getElementById('postcd').value = data.zonecode;
		                document.getElementById("addr1").value = addr;
		                // 커서를 상세주소 필드로 이동한다.
		                document.getElementById("postcd").focus();
		            }
		        }).open();
		    }
		    
		    var loginModal = document.getElementById('loginModal')
		    loginModal.addEventListener('hidden.bs.modal', function (event) {
		    	$('.loginForm')[0].reset();
		    	$('.idCheck').empty();
		    	$('.pw2Check').empty();
		    })
		    
		    var joinModal = document.getElementById('joinModal')
		    joinModal.addEventListener('hidden.bs.modal', function (event) {
		    	$('.joinForm')[0].reset();
		    	$('.idCheck').empty();
		    	$('.emailCheck').empty();
		    	$('.phoneCheck').empty();
		    	$('.pw2Check').empty();
		    })
		    
		    
		</script>
	</body>
</html>