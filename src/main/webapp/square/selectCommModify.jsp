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
	
	th img {
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
  		
	  	<h2>수정할 커뮤니티를 선택하세요</h2>
	  	<br>
 		<table class="table commList">
  			<thead>
			   <tr>
			      <th scope="col" style="width:15%;">이미지</th>
			      <th scope="col" style="width:10%">카테고리</th>
			      <th scope="col" style="width:20%">커뮤니티 이름</th>
			      <th scope="col" style="width:35%">커뮤니티 설명</th>
			      <th scope="col" style="width:10%">개설일자</th>
			      <th scope="col" style="width:10%;">가입자 수</th>
			   </tr>
			</thead>
		  	<tbody>
		  		<c:if test="${ownCommList != null}">
			  		<c:forEach var="comm" items="${ownCommList}" varStatus="status">
			  			<tr style="cursor: pointer;" onclick='location.href="/square/checkMember.jsp?commCode=${comm.commCode}&oper=commMod"'>
					      <th scope="row"><img src="/resources/images/commMainImg/${comm.mainImg}"></th>
					      <td>${comm.category}</td>
					      <td>${comm.commName}</td>
					      <td>${comm.description}</td>
					      <td>${comm.regdate}</td>
					      <td>${comm.memberCnt}</td>
					    </tr>
				    </c:forEach>
			    </c:if>
			    <c:if test="${ownCommList == null}">
			  		<tr style="text-align: center; font-size: 20px;">
			  			<td colspan="6">아직 개설한 커뮤니티가 없습니다.</td>
			  		</tr>
			  	</c:if>
 			</tbody>
		</table>
	  	
	  	
	  	
 
    </div>
    <%@ include file="/includes/aside.jsp" %>
  </div>
</div>

























<%@ include file="/includes/footer.jsp" %>