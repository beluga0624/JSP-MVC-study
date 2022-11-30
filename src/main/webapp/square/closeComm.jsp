<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/includes/header.jsp" %>

<style>
	.commList {
		margin:20px 0;
	}
	
	h1 {
		text-align: center;
		margin: 200px 0;
	}
	
	h2 {
		text-align: center;
		margin: 30px 0;
	}
	
	td img {
		width: 100px;
		height: 75px;
	}
	
	th, td {
		text-align: center;
		vertical-align: middle;
	}
</style>

<div class="container">
  <div class="row">
  	<div class="col-9">
  		
	  	<h2>폐쇄할 커뮤니티를 선택하세요</h2>
	  	<c:if test="${ownCommList != null}">
	  		<form action="/square/checkMember.jsp" method="post" id="closeFrm">
	  			<table class="table commList" style="padding:0;">
		  			<thead>
					   <tr>
					   	  <th scope="col" style="width:7%;">선택</th>
					      <th scope="col" style="width:15%;">이미지</th>
					      <th scope="col" style="width:10%">카테고리</th>
					      <th scope="col" style="width:18%">커뮤니티 이름</th>
					      <th scope="col" style="width:30%">커뮤니티 설명</th>
					      <th scope="col" style="width:10%">개설일자</th>
					      <th scope="col" style="width:10%;">가입자 수</th>
					   </tr>
					</thead>
				  	<tbody>
				  		<c:forEach var="comm" items="${ownCommList}" varStatus="status">
				  			<tr>
				  			  <td>
				  			  	<input type="radio" id="commCode" name="commCode" value="${comm.commCode}">
				  			  	<input type="hidden" name="oper" value="close">
				  			  	<input type="hidden" name="closeCommName" value="${comm.commName}">
				  			  </td>
						      <td scope="row"><img src="/resources/images/commMainImg/${comm.mainImg}"></td>
						      <td>${comm.category}</td>
						      <td>${comm.commName}</td>
						      <td>${comm.description}</td>
						      <td>${comm.regdate}</td>
						      <td>${comm.memberCnt}</td>
						    </tr>
					    </c:forEach>
					    <tr>
					    	<td colspan="7">
					    		<button type="button" class="btn btn-md btn-danger" id="closeBtn" data-bs-toggle="modal" data-bs-target="#exampleModal" disabled="disabled">
								  폐쇄하기
								</button>&nbsp;&nbsp;
								<button type="button" class="btn btn-md btn-secondary" onclick="javascript:history.back()">취소</button>
					    	</td>
					    </tr>
	  				</tbody>
				</table>
			</form>
	  	</c:if>
	  	
	  	<c:if test="${ownCommList == null}">
	  		<h1>생성된 커뮤니티가 없습니다.</h1>
	  	</c:if>
	  	
	  	
 
    </div>
    <%@ include file="/includes/aside.jsp" %>
  </div>
</div>

<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
      	<div id="data" style="font-size:1.2rem; font-weight:bold;"></div><br>
        선택하신 커뮤니티를 폐쇄 하시겠습니까?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" id="closeSubmit">폐쇄하기</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
      </div>
    </div>
  </div>
</div>

<script src="http://code.jquery.com/jquery-latest.min.js"></script>


<script>
	
	$(document).ready(function(){
		$("input[name=commCode]").on("click", function(){
			$("#closeBtn").prop("disabled", false);
		    
		});
		
		$("#closeBtn").on("click", function(){
			var commName = $("input[name=commCode]:checked").next().next().val();
			
			document.getElementById("data").innerHTML=commName;
		});
		
		$("#closeSubmit").on("click", function(){
			$("#closeFrm").submit();
		});
	});
	
</script>






















<%@ include file="/includes/footer.jsp" %>