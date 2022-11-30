<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/includes/header.jsp" %>

<%
	String isRightUser = (String)request.getAttribute("isRightUser");

	if(isRightUser == null) {
		response.setContentType("text/html; charset=utf-8");
		out.println("<script>");
		out.println("alert('잘못된 요청입니다. 다시 시도하세요')");
		out.println("history.back();");
		out.println("</script>");
	}
%>

<style>
	table {
		margin: 100px auto;
	}
</style>

<div class="container">
	<div class="row">
    	<div class="col-9 mainSection">
    		<form name="memberInfo" class="joinForm" action="updatePw.mem" method="post">
				
					<table width="70%">
						<tr>
							<td><label class="col-form-label">비밀번호</label></td>
							<td><input type="password" class="form-control" name="pw1" id="pw1" oninput="checkPwd2();"></td>
						</tr>
						<tr>
							<td><label class="col-form-label">비밀번호 확인</label></td>
							<td><input type="password" class="form-control" name="pw2" id="pw2" oninput="checkPwd2();"></td>
						</tr>
						<tr><td colspan="2" class="pwCheck"></td></tr>
					</table>
            		<div class="modal-footer justify-content-md-center">
            			<input type="submit" class="btn btn-lg btn-primary subBtn" value="비밀번호 변경">
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        		<input type="button" class="btn btn-lg btn-secondary" value="취소" onclick="location.href='/square/main.jsp'">
            		</div>
				</form>
    	</div>
    	<%@ include file="/includes/aside.jsp" %>
	</div>
</div>

<script>

	function checkPwd2() {
	    var pw1 = $('#pw1').val();
	    var pw2 = $('#pw2').val();
	
	    if(pw1 === pw2){
	    	$('.pwCheck').html('<div class="alert alert-success" role="alert">' +
			  '비밀번호가 일치합니다.</div>');
	    	$(".subBtn").prop("disabled", false);
	    }else{
	    	$('.pwCheck').html('<div class="alert alert-danger" role="alert">' +
			  '비밀번호가 일치하지 않습니다.</div>');
	    	$(".subBtn").prop("disabled", true);
	    }
	}

</script>

		
<%@ include file="/includes/footer.jsp" %>