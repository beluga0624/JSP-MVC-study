<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
	String oper = request.getParameter("oper");
%>

<%@ include file="/includes/header.jsp" %>

<style>
	table {
		margin: 100px auto;
	}
</style>

<div class="container">
	<div class="row">
    	<div class="col-9 mainSection">
    		<form name="memberInfo" class="userChkForm" action="/recheckMember.mem" method="post">
				
					<table width="70%">
						<tr>
							<td><label for="userpw" class="col-form-label">비밀번호</label></td>
							<td><input type="password" class="form-control" name="userpw" id="userpw"></td>
						</tr>
					</table>
					<input type="hidden" value="<%=oper %>" name="oper">
            		<div class="modal-footer justify-content-md-center">
            			<input type="button" class="btn btn-lg btn-primary subBtn" value="확인" onclick="checkProcess()">
            			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        		<input type="button" class="btn btn-lg btn-secondary" value="취소" onclick="location.href='/square/main.jsp'">
            		</div>
				</form>
    	</div>
    	<%@ include file="/includes/aside.jsp" %>
	</div>
</div>

<script>
	function checkProcess(){
		if('<%=oper %>' === 'withdraw'){
			if(confirm("정말로 탈퇴 하시겠습니까?")){
				$(".userChkForm").submit();
			}else{
				history.back();
				return false;
			}
		}else{
			$(".userChkForm").submit();
		}

	}
</script>
		
<%@ include file="/includes/footer.jsp" %>