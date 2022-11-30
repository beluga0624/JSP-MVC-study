<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<% 
	String oper = request.getParameter("oper");
	int commCode = Integer.parseInt(request.getParameter("commCode"));
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
    		<form name="memberInfo" class="joinForm" action="/checkMember.mem" method="post">
				<input type="hidden" value="<%=oper %>" name="oper">
				<input type="hidden" value="<%=commCode %>" name="commCode">
				<table width="70%">
					<tr>
						<td><label for="userpw" class="col-form-label">비밀번호</label></td>
						<td><input type="password" class="form-control" name="userpw" id="userpw"></td>
					</tr>
				</table>
            	<div class="modal-footer justify-content-md-center">
            		<input type="submit" class="btn btn-lg btn-primary subBtn" value="확인">
            		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		        	<input type="button" class="btn btn-lg btn-secondary" value="취소" onclick="javascript:history.back()">
            	</div>
			</form>
    	</div>
    	<%@ include file="/includes/aside.jsp" %>
	</div>
</div>
		
<%@ include file="/includes/footer.jsp" %>