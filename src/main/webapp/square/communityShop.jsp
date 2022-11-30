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
		margin: auto;
	}
	
	.previewBoard {
		margin-left: 120px;
		padding: 0;
	}
	
	.previewBoard div{
		display: inline-block;
		float: left;
		width: 50%;
	}
</style>

<div class="container">
  <div class="row">
    <%@ include file="/includes/communityMenu.jsp" %>
    <div class="col-9">
    	
    	<div class="container">
		  <div class="row">
		  	
		  	<c:if test="${fn:length(sessionScope.goodsList) != 0}">
		  		<c:forEach var="goodsList" items="${sessionScope.goodsList}" varStatus="status">
		  			
		  			<div class="col">
				      	<div class="card" style="width: 280px; cursor: pointer;" onclick='location.href="/getCommInfo.square?commCode=${comm.commCode}"'>
				      		
						  	<img src="/resources/images/commMainImg/${comm.mainImg}" class="card-img-top">
						  
						 	<div class="card-body" style="height: 150px;">
						 		<p class="card-text"><b>${comm.commName}</b></p>
						    	<p class="card-text"><c:out value="${comm.description}"/></p>
							</div>
							
							<table style="width: 240px; margin: auto;">
								<tr>
									<td style="text-align: left;">
										<c:out value="${comm.regdate}"/>
									</td>
									<td style="text-align: right;">
										<img src="/resources/images/icons/group.png" style="width:20px;">&nbsp;&nbsp;
  										<c:out value="${comm.memberCnt}"/>
									</td>
								</tr>
							</table>
						</div>
				    </div>
				    					    
		  		</c:forEach>
		  		
		  		<c:if test="${fn:length(sessionScope.goodsList) % 3 == 2}">

	  				<div class="col emptySpace" style="width: 300px;"></div>

		  		</c:if>
		  	</c:if>
		  	
		  	<c:if test="${fn:length(sessionScope.goodsList) == 0}">
		  		아직 등록된 상품이 없습니다.
		  	</c:if>
			
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
		</div>
    
    </div>
  </div>
</div>



























<%@ include file="/includes/footer.jsp" %>