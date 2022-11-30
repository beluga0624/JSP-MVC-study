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
    	
    	<table class="commMain">
    		<tr>
    			<td colspan="2">
    				<img src="/resources/images/commMainImg/${community.mainImg}" class="img-fluid mainImg">
    			</td>
    		</tr>
    	</table>
    	<div class="previewBoard">
    		<div>
    			
    			<div class="notice" style="margin-top:50px; height:300px; width:300px;">
			    	<table class="table table-hover">
					  <thead>
					    <tr>
					      <th scope="col" style="font-size:20px; text-align:left; width:70%;"><c:out value="${commNotice[0].boardName}"/></th>
					      <th scope="col" style="font-size:15px; text-align:right;"><a href="#" style="text-decoration: none;">더보기</a></th>
					    </tr>
					  </thead>
					  <tbody>
					  	<c:forEach var="notice" items="${commNotice}" varStatus="status">
					  		<tr>
							    <td colspan="2" style="font-size:13px; text-align:left;">
							    	<a href='/getSiteBoardDetail.board?boardNum=${notice.boardNum}&category=${notice.boardName}' class="aTagNotice" style="text-decoration: none;"><c:out value="${notice.subject}" /></a>
							    </td>
					  		</tr>
					  	</c:forEach>
					  </tbody>
					</table>
			    </div>
    		</div>
    		
    		<div>
    			
    			<div class="FAQ" style="margin-top:50px; height:300px; width:300px;">
			    	<table class="table table-hover">
					  <thead>
					    <tr>
					      <th scope="col" style="font-size:20px; text-align:left; width:70%;"><c:out value="${commFreeBoard[0].boardName}"/></th>
					      <th scope="col" style="font-size:15px; text-align:right;"><a href="#" style="text-decoration: none;">더보기</a></th>
					    </tr>
					  </thead>
					  <tbody>
					    <c:forEach var="freeBaord" items="${commFreeBoard}" varStatus="status">
					  		<tr>
							    <td colspan="2" style="font-size:13px; text-align:left;">
							    	<a href='/getSiteBoardDetail.board?boardNum=${freeBaord.boardNum}&category=${freeBaord.boardName}' class="aTagNotice" style="text-decoration: none;"><c:out value="${freeBaord.subject}" /></a>
							    </td>
					  		</tr>
					  	</c:forEach>
					  </tbody>
					</table>
			    </div>
    		</div>
    	</div>
    
    </div>
  </div>
</div>



























<%@ include file="/includes/footer.jsp" %>