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
	
	textarea {
		width: 100%;
	}
	
	.col-9 {
	  	position: relative;
	}
	
	.col-9::before {
		margin: 20px 0;
		padding: 20px 0;
		width: 100%;
		height: 100%;
		content: "";
		background:url("/resources/images/commMainImg/${community.mainImg}");
		background-size: 100% 100%;
		position: absolute;
		top: 0;
		left: 0;
		opacity: 0.35;
		z-index: -1;
	}
	
	.commJoinForm {
		position: absolute;
	}

</style>

<div class="container">
  <div class="row">
  
    <%@ include file="/includes/communityMenu.jsp" %>
    
    <div class="col-9">    	
    	<form name="commMemberUpdateForm" class="commMemberUpdateForm" action="/communityMemberUpdate.square" method="post">
			<table class="table" width="100%" style="margin: 100px auto 30px auto;">
				<tr>
					<td><label for="activityName" class="col-form-label"><b>커뮤니티 활동명</b></label></td>
					
				</tr>
				<tr>
					<td>
						<input type="text" 
							class="form-control" 
							name="activityName" 
							id="activityName" 
							value="${commMember.activityName}"
							readonly="readonly">
					</td>
				</tr>
				<tr>
					<td>
						<label for="answer1" class="col-form-label">
							<b><c:out value="${community.question1}"/></b>							
						</label>
					</td>
				</tr>
				<tr>
					<td>
						<textarea rows="3" cols="20" name="answer1" id="answer1"><c:out value="${commMember.answer1}"/></textarea>
					</td>
				</tr>
				
				<c:if test="${community.question2 != null}">
					<tr>
						<td>
							<label for="answer2" class="col-form-label">
								<b><c:out value="${community.question2}"/></b>					
							</label>
						</td>
					</tr>
					<tr>
						<td>
							<textarea rows="3" cols="20" name="answer2" id="answer2"><c:out value="${commMember.answer2}"/></textarea>
						</td>
					</tr>
				</c:if>
				
				<c:if test="${community.question3 != null}">
					<tr>
						<td>
							<label for="answer3" class="col-form-label">
								<b><c:out value="${community.question3}"/></b>						
							</label>
						</td>
					</tr>
					<tr>
						<td>
							<textarea rows="3" cols="20" name="answer3" id="answer3"><c:out value="${commMember.answer3}"/></textarea>
						</td>
					</tr>
				</c:if>
			</table>
          		
       		<input type="hidden" value="${community.commCode}" name="commCode">
       		<input type="hidden" value="${userid}" name="commUserid">
       		
       		<div style="text-align:center; margin:50px;">
       			<input type="submit" class="btn btn-md btn-primary" value="저장">
       			&nbsp;&nbsp;&nbsp;&nbsp;
      			<input type="reset" class="btn btn-md btn-secondary" value="취소" onclick="javascript:history.go(-2)">
       		</div>
           		
		</form>
    </div>
  </div>
</div>



























<%@ include file="/includes/footer.jsp" %>