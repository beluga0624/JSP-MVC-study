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
  		
	  	<h2>내 커뮤니티</h2>
	  	
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
			  			<tr style="cursor: pointer;" onclick='location.href="/getCommInfo.square?commCode=${comm.commCode}"'>
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
	  	
	  	<br><br><br>
	  	
	  	<h2>내가 가입한 커뮤니티</h2>
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
		  		<c:if test="${joinedCommList != null}">
				  	<c:forEach var="joinedComm" items="${joinedCommList}" varStatus="status">
			  			<tr style="cursor: pointer;" onclick='location.href="/getCommInfo.square?commCode=${joinedComm.commCode}"'>
					      <th scope="row"><img src="/resources/images/commMainImg/${joinedComm.mainImg}"></th>
					      <td>${joinedComm.category}</td>
					      <td>${joinedComm.commName}</td>
					      <td>${joinedComm.description}</td>
					      <td>${joinedComm.regdate}</td>
					      <td>${joinedComm.memberCnt}</td>
					    </tr>
					</c:forEach>
				</c:if>
				<c:if test="${joinedCommList == null}">
			  		<tr style="text-align: center; font-size: 20px;">
			  			<td colspan="6">가입한 커뮤니티가 없습니다.</td>
			  		</tr>
			  	</c:if>
 			</tbody>
		</table>
	  	
	  	<br><br><br>
	  	
	  	<c:if test="${pageInfo != null}">
				  <ul class="pagination justify-content-end" style="margin-top: 50px;">
					<c:if test="${pageInfo.page <= 1}">
						<li class="page-item disabled">
				  			<a class="page-link">이전</a>
				  		</li>
				 	</c:if>
				 	<c:if test="${pageInfo.page > 1}">
				 		<li class="page-item">
				 			<a class="page-link" href="/main.square?page=${pageInfo.page - 10}">이전</a>
				 		</li>
				 	</c:if>
				 	
					<c:forEach var="page" begin="${pageInfo.startPage}" end="${pageInfo.endPage}">
						<c:if test="${pageInfo.page == page}">
							<li class="page-item active">
								<a class="page-link"><c:out value="${pageInfo.page}"/></a>
							</li>
						</c:if>
			    		<c:if test="${pageInfo.page != page}">
							<li class="page-item">
								<a class="page-link" href="/main.square?page=${page}"><c:out value="${pageInfo.page}"/></a>
							</li>
						</c:if>
					</c:forEach>

				    <c:if test="${pageInfo.page >= pageInfo.maxPage}">
				      <li class="page-item disabled">
					  	<a class="page-link">다음</a>
					  </li>
					</c:if>
					<c:if test="${pageInfo.page < pageInfo.maxPage}">
					  <li class="page-item disabled">
					  	<a class="page-link" href="/main.square?page=${pageInfo.page + 10}">다음</a>
					  </li>
					</c:if>
				  </ul>
			    </c:if>
 
    </div>
    <%@ include file="/includes/aside.jsp" %>
  </div>
</div>

























<%@ include file="/includes/footer.jsp" %>