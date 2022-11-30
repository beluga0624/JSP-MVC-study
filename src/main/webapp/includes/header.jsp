<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
	request.setCharacterEncoding("utf-8");
	response.setContentType("text/html; charset=utf-8");
 %>
<!DOCTYPE html>
<html>
	<head>
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
		<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
		<title>Insert title here</title>
		<style>
			html, body {
				max-width: 1280px;
				margin: 0 auto;
				padding: 0;
			}
			
			.modal-open { 
				padding: 0 !important;
				margin: 0 !important; 
			}
			
			.col-9 {
				margin-top: 40px;
				padding: 0;
			}
			
			sup { 
				vertical-align: 0.25em;
				font-size: 0.6em; 
			}
			
			.siteMainImg {
				background-image: url("../resources/images/mainImg.jpg");
				width: 100%;
				height : 250px;
			}
			
			.siteMainImg p {
				font-size: 100px;
				color: white;
				text-align: center;
				line-height: 250px;
			}
		</style>
	</head>
	<body>
		
		<c:if test="${requestScope.isJoinSuccess != null}">
			<script type="text/javascript">
				alert("회원가입이 완료 되었습니다.\n로그인 하세요");
			</script>
		</c:if>
		
		<c:if test="${requestScope.logout != null}">
			<script type="text/javascript">
				alert("로그아웃 되었습니다.");
			</script>
		</c:if>
		
		<c:if test="${requestScope.isUpdateSuccess != null}">
			<script type="text/javascript">
				alert("회원정보 수정이 완료 되었습니다.");
			</script>
		</c:if>
		
		<c:if test="${requestScope.isPwUpdateSucces != null}">
			<script type="text/javascript">
				alert("비밀번호가 성공적으로 수정 되었습니다.");
			</script>
		</c:if>
		
		<div class="siteMainImg img-fluid">
			<p>SQUARE<sup>SQUARE</sup></p>
		</div>
		
		<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
			<div class="container-fluid">
		    	<a class="navbar-brand" href="/main.square">SQUARE<sup>SQUARE</sup></a>
		    	<button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
		    		<span class="navbar-toggler-icon"></span>
		    	</button>
		    	<div class="collapse navbar-collapse" id="navbarSupportedContent">
			    	<ul class="navbar-nav me-auto mb-2 mb-lg-0">
			        	<li class="nav-item">
			        		<a class="nav-link" aria-current="page" href="/main.square">홈</a>
			        	</li>
			        	<li class="nav-item">
			          		<a class="nav-link" href="/orderComm.square?oper=popular">인기 커뮤니티</a>
			        	</li>
			        	<li class="nav-item dropdown">
			          		<a class="nav-link dropdown-toggle" href="#" id="categoryDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			            		카테고리
			          		</a>
				        	<ul class="dropdown-menu" aria-labelledby="categoryDropdown">
					            <li><a class="dropdown-item" href="/orderComm.square?oper=category&type=카테고리1">카테고리1</a></li>
					            <li><a class="dropdown-item" href="/orderComm.square?oper=category&type=카테고리2">카테고리2</a></li>
					            <li><a class="dropdown-item" href="/orderComm.square?oper=category&type=카테고리3">카테고리3</a></li>
					            <li><a class="dropdown-item" href="/orderComm.square?oper=category&type=카테고리4">카테고리4</a></li>
					            <li><a class="dropdown-item" href="/orderComm.square?oper=category&type=카테고리5">카테고리5</a></li>
					            <li><a class="dropdown-item" href="/orderComm.square?oper=category&type=카테고리6">카테고리6</a></li>
				          	</ul>
			        	</li>
			        	<li class="nav-item">
			          		<a class="nav-link" href="/getCommList.square?oper=show"> 내 커뮤니티</a>
			        	</li>
			        	<c:if test="${sessionScope.userid != null}">
			        		<li class="nav-item dropdown">
			          		<a class="nav-link dropdown-toggle" href="#" id="manageCommunityDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			            		커뮤니티 관리
			          		</a>
				        	<ul class="dropdown-menu" aria-labelledby="manageCommunityDropdown">
					            <li><a class="dropdown-item" href="/square/createCommForm.jsp">커뮤니티 개설</a></li>
					            <li><a class="dropdown-item" href="/getCommList.square?oper=mod">커뮤니티 수정</a></li>
					            <li><hr class="dropdown-divider"></li>
					            <li><a class="dropdown-item" href="/getCommList.square?oper=close">커뮤니티 폐쇄</a></li>
				          	</ul>
			        	</li>
			        	</c:if>
			        	<li class="nav-item">
			          		<a class="nav-link" href="/getNotice.board">공지사항</a>
			        	</li>
			        	<li class="nav-item">
			          		<a class="nav-link" href="/getFAQ.board">FAQ</a>
			        	</li>
			        	<li class="nav-item dropdown">
			          		<a class="nav-link dropdown-toggle" href="#" id="companyInfoDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
			            		회사소개
			          		</a>
				        	<ul class="dropdown-menu" aria-labelledby="companyInfoDropdown">
					            <li><a class="dropdown-item" href="#">Action</a></li>
					            <li><a class="dropdown-item" href="#">Another action</a></li>
					            <!-- <li><hr class="dropdown-divider"></li> -->
					            <li><a class="dropdown-item" href="#">Something else here</a></li>
				          	</ul>
			        	</li>
			      	</ul>
			      	<div class="d-grid gap-2 d-md-flex justify-content-md-end">
			      	
			      		<c:if test="${sessionScope.userid == null}">
			      			<button class="btn btn-secondary me-md-2" type="button" data-bs-toggle="modal" data-bs-target="#loginModal">로그인</button>
					  		<button class="btn btn-secondary me-md-2" type="button" data-bs-toggle="modal" data-bs-target="#joinModal">회원가입</button>
			      		</c:if>
			      		
			      		<c:if test="${sessionScope.userid != null}">
			      			<span style="color: #adb5bd; line-height: 2.5;"><a href="/square/checkMember.jsp" style="color: white;"><c:out value="${userid}"/></a>님 환영합니다.</span>&nbsp;&nbsp;
			      			<button class="btn btn-secondary me-md-2" type="button" onclick="location.href='/logout.mem'">로그아웃</button>
			      		</c:if>
					  	
					</div>
		    	</div>
			</div>
		</nav>

		<!-- Login Modal -->
		<div class="modal fade" id="loginModal" tabindex="0" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">로그인</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
				<form name="loginForm" class="loginForm" action="/memberLogin.mem" method="post">
					<label for="useridLogin" class="col-form-label">아이디</label>
            		<input type="text" class="form-control" name="useridLogin" id="useridLogin">
            		<label for="userpwLogin" class="col-form-label">비밀번호</label>
            		<input type="password" class="form-control" name="userpwLogin" id="userpwLogin"><br>
            		<div class="modal-footer justify-content-md-end">
            			<a style="font-size: 13px;" href="/square/findAccount.jsp" onClick="window.open(this.href, '', 'width=400, height=300'); return false;">
            				아이디 찾기
            			</a> 
            			/
            			<a style="font-size: 13px;" href="/square/findPasswd.jsp" onClick="window.open(this.href, '', 'width=400, height=400'); return false;">
            				비밀번호 찾기
            			</a>
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            			<input type="button" class="btn btn-primary" value="로그인" onclick="loginCheck()">
		        		<input type="button" class="btn btn-primary" value="회원가입" data-bs-toggle="modal" data-bs-target="#joinModal">
            		</div>
				</form>
		      </div>
		    </div>
		  </div>
		</div>
		
		<!-- Join Modal -->
		<div class="modal fade" id="joinModal" tabindex="0" aria-labelledby="exampleModalLabel" aria-hidden="true">
		  <div class="modal-dialog modal-dialog-centered">
		    <div class="modal-content">
		      <div class="modal-header">
		        <h5 class="modal-title" id="exampleModalLabel">회원가입</h5>
		        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
		      </div>
		      <div class="modal-body">
				<form name="memberInfo" class="joinForm" action="/memberJoin.mem" method="post">
				
					<table width="100%">
						<tr>
							<td><label for="useridJoin" class="col-form-label">아이디</label></td>
							<td><input type="text" class="form-control" name="useridJoin" id="useridJoin" oninput="checkID()"></td>
						</tr>
						<tr><td colspan="2" class="idCheck"></td></tr>
						<tr>
							<td><label for="userpw1" class="col-form-label">비밀번호</label></td>
							<td><input type="password" class="form-control" name="userpw1" id="userpw1" oninput="checkPwd()"></td>
						</tr>
						<tr>
							<td><label for="userpw2" class="col-form-label">비밀번호 확인</label></td>
							<td><input type="password" class="form-control" name="userpw2" id="userpw2" oninput="checkPwd()"></td>
						</tr>
						<tr><td colspan="2" class="pw2Check"></td></tr>
						<tr>
							<td><label for="userName" class="col-form-label">이름</label></td>
							<td><input type="text" class="form-control" name="userName" id="userName"></td>
						</tr>
						<tr>
							<td><label for="birthDate" class="col-form-label">생년월일</label></td>
							<td><input type="text" class="form-control" name="birthDate" id="birthDate"></td>
						</tr>
						<tr>
							<td><label for="gender1" class="col-form-label">성별</label><br></td>
							<td style="text-align: center;">
								<input type="radio" name="gender" id="gender1" value="남자">남자
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            					<input type="radio" name="gender" id="gender2" value="여자">여자
							</td>
						</tr>
						<tr>
							<td><label for="phone" class="col-form-label">전화번호</label></td>
							<td><input type="text" class="form-control" name="phone" id="phone" oninput="checkPhone()"></td>
						</tr>
						<tr><td colspan="2" class="phoneCheck"></td></tr>
						<tr>
							<td><label for="email" class="col-form-label">이메일</label></td>
							<td><input type="text" class="form-control" name="email" id="email" oninput="checkEmail()"></td>
						</tr>
						<tr><td colspan="2" class="emailCheck"></td></tr>
						<tr>
							<td><label for="postcd" class="col-form-label">우편번호</label></td>
							<td class="input-group mb-3">
								<input type="text" class="form-control" name="postcd" id="postcd" size="5" readonly="readonly">
								<button class="btn btn-outline-secondary" type="button" id="button-addon2" onclick="Postcode()">우편번호 찾기</button>
							</td>
						</tr>
						<tr>
							<td><label for="addr1" class="col-form-label">주소</label></td>
							<td><input type="text" class="form-control" name="addr1" id="addr1" readonly="readonly"></td>
						</tr>
						<tr>
							<td><label for="addr2" class="col-form-label">상세주소</label></td>
							<td><input type="text" class="form-control" name="addr2" id="addr2"></td>
						</tr>
					</table>
            		<div class="modal-footer justify-content-md-end">
            			<input type="button" class="btn btn-primary subBtn" value="회원가입" onclick="checkForm()">
		        		<input type="reset" class="btn btn-secondary" value="초기화">
            		</div>
				</form>
		      </div>
		    </div>
		  </div>
		</div>