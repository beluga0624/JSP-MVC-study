<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/includes/header.jsp" %>

<style>
	.boardList {
        margin-top: 20px;
        margin-bottom: 30px;
	}
	
	.commMain {
		margin-top: 20px;
        margin-bottom: 30px;
	}
</style>

<div class="container">
  <div class="row">
    <div class="col-3">
		
		<div class="card" style="width: 18rem; margin-top:20px;">
		  <img src="/resources/images/commMainImg/${community.mainImg}" class="card-img-top">
		  <div class="card-body">
		  	<table style="width:100%">
		  		<tr>
		  			<td style="font-weight:bold"><c:out value="${community.commName}"/></td>
		  			<td style="text-align:right">
		  				<img src="/resources/images/icons/group.png" style="width:20px; height:20px;">&nbsp;&nbsp;
		  				<c:out value="${community.memberCnt}"/>
		  			</td>
		  		</tr>
		  		<tr style="height:10px;">
		  			<td> </td>
		  		</tr>
		  		<tr>
		  			<td colspan="2" class="card-text">
		  				<c:out value="${community.description} 커뮤니티 설명이 너무 짧아서 임의로 넣은 텍스트"/>
		  			</td>
		  		</tr>
		  		<tr>
		  			<c:if test="${isCommMember == true}">
		  				<td>
		  					<input type="button" class="btn btn-secondary btn-sm" value="정보수정" onclick="#">
		  					<input type="button" class="btn btn-danger btn-sm" value="탈퇴" onclick="#">
		  				</td>	
		  			</c:if>
		  			
		  			<c:if test="${isCommMember == false}">
		  				<td>
		  					<input type="button" class="btn btn-primary btn-sm" value="가입하기" onclick="#">
		  				</td>	
		  			</c:if>
		  			
		  			<td style="font-size:15px; text-align:right;">
		  				<c:out value="${community.regdate}"/>
		  			</td>
		  		</tr>
		  	</table>
		  </div>
		</div>
		
		<table class="boardList">
			
			<c:if test="${boardCategory != null}">
			  		<c:forEach var="category" items="${boardCategory}" varStatus="status">
			  			
			  			<tr>
			  				<td style="font-size: 18px"><b><c:out value="${category}"/></b></td>
			  			</tr>
			  			
			  			<c:forEach var="boardName" items="${boardNameList}">
			  				<c:if test="${boardName.category == category}">
			  					<tr>
			  						<td>&nbsp;<a href="" style="text-decoration:none"><c:out value="-${boardName.boardName}"/></a></td>
			  					</tr>
			  				</c:if>
			  			</c:forEach>
					    
			  		</c:forEach>
			  	</c:if>
		</table>
		
	</div>
    <div class="col-9">
    	
    	<form name="memberInfo" class="joinForm" action="updateMemberInfo.mem" method="post">
				
				<table width="70%">
					<tr>
						<td><label for="useridUpdate" class="col-form-label">아이디</label></td>
						<td><input type="text" class="form-control" name="useridUpdate" id="useridUpdate" value="${memberInfo.userid}" readonly="readonly"></td>
					</tr>
					<tr>
						<td><label for="userpw1U" class="col-form-label">비밀번호</label></td>
						<td><input type="button" value="비밀번호 변경" class="btn btn-md btn-secondary" onclick="location.href='/square/recheckMember.jsp?oper=change'"></td>
					</tr>
					<tr>
						<td><label for="userNameU" class="col-form-label">이름</label></td>
						<td><input type="text" class="form-control" name="userNameU" id="userNameU" value="${memberInfo.name}"></td>
					</tr>
					<tr>
						<td><label for="birthDateU" class="col-form-label">생년월일</label></td>
						<td><input type="text" class="form-control" name="birthDateU" id="birthDateU" value="${memberInfo.birthDate}"></td>
					</tr>
					<tr>
						<td><label for="gender1U" class="col-form-label">성별</label><br></td>
						<td style="text-align: center;">
							<c:if test="${memberInfo.gender eq '남자'}">
								<input type="radio" name="genderU" id="gender1U" value="남자" checked>남자
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            					<input type="radio" name="genderU" id="gender2U" value="여자">여자
							</c:if>
							
							<c:if test="${memberInfo.gender eq '여자'}">
								<input type="radio" name="genderU" id="gender1U" value="남자">남자
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            					<input type="radio" name="genderU" id="gender2U" value="여자" checked>여자
							</c:if>
						
						</td>
					</tr>
					<tr>
						<td><label for="phoneU" class="col-form-label">전화번호</label></td>
						<td><input type="text" class="form-control" name="phoneU" id="phoneU" value="${memberInfo.phone}"></td>
					</tr>
					<tr>
						<td><label for="emailU" class="col-form-label">이메일</label></td>
						<td><input type="text" class="form-control" name="emailU" id="emailU" value="${memberInfo.email}"></td>
					</tr>
					<tr>
						<td><label for="postcdU" class="col-form-label">우편번호</label></td>
						<td class="input-group mb-3">
							<input type="text" class="form-control" name="postcdU" id="postcdU" size="5" readonly="readonly" value="${memberInfo.postcd}">
							<button class="btn btn-outline-secondary" type="button" id="button-addon2" onclick="Postcode()">우편번호 찾기</button>
						</td>
					</tr>
					<tr>
						<td><label for="addr1U" class="col-form-label">주소</label></td>
						<td><input type="text" class="form-control" name="addr1U" id="addr1U" readonly="readonly" value="${memberInfo.addr1}"></td>
					</tr>
					<tr>
						<td><label for="addr2U" class="col-form-label">상세주소</label></td>
						<td><input type="text" class="form-control" name="addr2U" id="addr2U" value="${memberInfo.addr2}"></td>
					</tr>
				</table>
           		<div class="modal-footer justify-content-md-center">
           			<input type="submit" class="btn btn-lg btn-primary" value="정보 수정">
           			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        		<input type="button" class="btn btn-lg btn-secondary" value="취소" onclick="/square/main.jsp'">
	        		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	        		<input type="button" class="btn btn-lg btn-danger withdrawBtn" value="탈퇴" onclick="location.href='/square/recheckMember.jsp?oper=withdraw'">
           		</div>
			</form>
    
    </div>
  </div>
</div>



























<%@ include file="/includes/footer.jsp" %>