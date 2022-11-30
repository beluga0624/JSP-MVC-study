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
    	<form name="withdrawFromComm" class="withdrawFromComm" action="/withdrawFromComm.square" method="post">
			<table class="table" width="100%" style="margin: 100px auto 30px auto;">
				<tr>
					<td style="font-size:30px; font-weight:bold; text-align:center;">정말로 <span style="color:red;">탈퇴</span>하시겠습니까?</td>
				</tr>
			</table>
          		
       		<input type="hidden" value="${community.commCode}" name="commCode">
       		<input type="hidden" value="${userid}" name="useridForWithdraw">
       		
       		<div style="text-align:center; margin:50px;">
       			<input type="submit" class="btn btn-md btn-primary" value="탈퇴">
       			&nbsp;&nbsp;&nbsp;&nbsp;
      			<input type="reset" class="btn btn-md btn-secondary" value="취소" onclick="javascript:history.go(-2)">
       		</div>
           		
		</form>
    </div>
  </div>
</div>



























<%@ include file="/includes/footer.jsp" %>