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
    	<form name="commJoinForm" class="joinForm" action="/communityJoin.square" method="post">
			<table class="table" width="100%" style="margin: 100px auto 30px auto;">
				<tr>
					<td><label for="activityName" class="col-form-label"><b>커뮤니티 활동명</b></label></td>
				</tr>
				<tr>
					<td><input type="text" class="form-control" name="activityName" id="activityName" oninput="checkActName()"></td>
				</tr>
				<tr><td colspan="2" class="actNameCheck"></td></tr>
				<tr>
					<td>
						<label for="answer1" class="col-form-label">
							<b><c:out value="${community.question1}"/></b>							
						</label>
					</td>
				</tr>
				<tr>
					<td>
						<textarea rows="3" cols="20" name="answer1" id="answer1"></textarea>
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
							<textarea rows="3" cols="20" name="answer2" id="answer2"></textarea>
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
							<textarea rows="3" cols="20" name="answer3" id="answer3"></textarea>
						</td>
					</tr>
				</c:if>
			</table>
          		
          	<input type="hidden" value="${community.commCode}" name="commCode">
          	<input type="hidden" value="${userid}" name="commJoinUserid">
          		
          	<div style="text-align:center; margin:50px;">
          		<input type="submit" class="btn btn-md btn-primary joinBtn" value="가입">
          		&nbsp;&nbsp;&nbsp;&nbsp;
        		<input type="reset" class="btn btn-md btn-secondary" value="초기화">
          	</div>
           		
		</form>
    </div>
  </div>
</div>

<script>
	//커뮤니티 활동명 중복 체크
	function checkActName(){
		
		var activityName = $('#activityName').val();
		var commCode = $("input[name=commCode]").val();
	
		$.ajax({
			type:'POST',
			url:'/checkActName.square',
			data:{activityName: activityName,
				  commCode: commCode},
			success:function(data){
				
				console.log(data);
				
				if(data === "available"){
					$('.actNameCheck').html('<div class="alert alert-success" role="alert">' +
							  '사용할 수 있는 활동명 입니다.</div>');
					$(".joinBtn").prop("disabled", false);
					
				}
				if(data === "unavailable"){
					$('.actNameCheck').html('<div class="alert alert-danger" role="alert">' +
							  '사용할 수 없는 활동명 입니다.</div>');
					$(".joinBtn").prop("disabled", true);
					
				}
				
				if($('#activityName').val().length < 5){
					$('.actNameCheck').html('<div class="alert alert-danger" role="alert">' +
					  '사용할 수 없는 활동명 입니다.</div>');
					$(".joinBtn").prop("disabled", true);
				}
			}
		});
	}
</script>

<%@ include file="/includes/footer.jsp" %>