<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/includes/header.jsp" %>

<style>
	.card-img-top {
		width: 280px;
		height: 210px;
	}
	
	.card{
		margin: 20px 0;
		padding: 0;
	}
	
	.title {
		font-size: 50px;
		font-weight: bold;
		text-align:center;
	}
	
	table {
		text-align: center;
	}
</style>
<div class="container">
	<div class="row">
    	<div class="col-9">
    		
    		<div class="container">
			  <div class="row">
			  	
			  	<p class="title">공지사항</p>
     
			  	<table class="table table-hover" style="margin-top:30px;">
				  <thead>
				  	<tr>
				  		<td colspan="6" style="text-align:right;">
				  			<c:if test="${userid == 'admin'}">
				  				<button type="button" 
				  						class="btn btn-outline-primary" 
				  						style="width: 80px;"
				  						onclick='location.href="/square/writeNotice.jsp"'>글쓰기
				  				</button>
				  			</c:if>
				  		</td>
				  	</tr>
				  	
				  	<tr>
				      <th scope="col" style="width: 5%;">#</th>
				      <th scope="col" style="width: 50%;">제목</th>
				      <th scope="col" style="width: 10%;">작성자</th>
				      <th scope="col" style="width: 10%;">댓글</th>
				      <th scope="col" style="width: 10%;">읽은 수</th>
				      <th scope="col" style="width: 15%;">작성시각</th>
				      
				    </tr>
				    
				  </thead>
				  	
				  <tbody>
				  
					<c:forEach var="board" items="${boardList}">
						<tr style="cursor: pointer;" onclick="location.href='/getSiteBoardDetail.board?boardNum=${board.boardNum}&category=${board.boardCategory}'">
					      <th scope="row"><c:out value="${board.boardNum}"/></th>
					      <td style="text-align:left;"><c:out value="${board.subject}"/></td>
					      <td><c:out value="${board.writer}"/></td>
					      <td><c:out value="${board.replyCnt}"/></td>
					      <td><c:out value="${board.readCnt}"/></td>
					      <td><c:out value="${board.regDate}"/></td>
					    </tr>
					</c:forEach>
					
				  </tbody>
				</table>
				
				<form style="display:inline-block;">
					<table style="vertical-align: middle;">
						<tr>
							<td style="width:20%;">
								<input class="form-control me-2" type="search" placeholder="Search" aria-label="Search" style="width:200px;">
							</td>
							<td style="width:10%;">
			          			<input class="btn btn-outline-success" type="submit" value="Search">
		          			</td>
		          			<td style="width:70%;">
		          				<c:if test="${pageInfo != null}">
								  <ul class="pagination justify-content-end" style="margin-top: 10px;">
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
		          			</td>
						</tr>
					</table>
		        </form>
		        
			  </div>
			</div>
    	</div>
    	<%@ include file="/includes/aside.jsp" %>
	</div>
</div>	
<%@ include file="/includes/footer.jsp" %>