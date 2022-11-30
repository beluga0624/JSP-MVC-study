<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>

<style>
	.col-3 {
		background: white;
		/* border-left: 1px solid gray; */
	}
	
	.bar{
		border-left: 1px solid gray; 
		width: 1px;
		height : 850px;
		padding-left: 10px;
	}
	.aTagNotice {
		text-decoration: none;
		color: lightgray;
	}

</style>

<div class="col-3">
	
	<div class = "bar">
		<form class="d-flex" style="margin-top: 60px; width:290px;" action="#" method="post">
	      <input class="form-control me-2" type="search" placeholder="커뮤니티 이름 검색" aria-label="Search">
	      <button class="btn btn-outline-success" type="submit">Search</button>
	    </form>
	    
	    <div class="notice" style="margin-top:50px; height:300px; width:290px;">
	    	<table class="table table-dark table-striped table-borderless">
			  <thead>
			    <tr>
			      <th scope="col" style="font-size:20px; text-align:left; width:100px;">공지사항</th>
			      <th scope="col" style="font-size:15px; text-align:right;"><a href="/getNotice.board" style="text-decoration: none;">더보기</a></th>
			    </tr>
			  </thead>
			  <tbody>
			  	<c:forEach var="notice" items="${sessionScope.noticeList}" varStatus="status">
			  		<tr>
					    <td colspan="2" style="font-size:13px; text-align:left;">
					    	<a href='/getSiteBoardDetail.board?boardNum=${notice.boardNum}&category=${notice.boardCategory}' class="aTagNotice"><c:out value="${notice.subject}" /></a>
					    </td>
			  		</tr>
			  	</c:forEach>
			  </tbody>
			</table>
	    </div>
	    
	    <div class="FAQ" style="margin-top:50px; height:300px; width:290px;">
	    	<table class="table table-dark table-striped table-borderless">
			  <thead>
			    <tr>
			      <th scope="col" style="font-size:20px; text-align:left; width:100px;">FAQ</th>
			      <th scope="col" style="font-size:15px; text-align:right;"><a href="#" style="text-decoration: none;">더보기</a></th>
			    </tr>
			  </thead>
			  <tbody>
			    <c:forEach var="faq" items="${sessionScope.faqList}" varStatus="status">
			  		<tr>
					    <td colspan="2" style="font-size:13px; text-align:left;">
					    	<a href='/getSiteBoardDetail.board?boardNum=${faq.boardNum}&category=${faq.boardCategory}' class="aTagNotice"><c:out value="${faq.subject}" /></a>
					    </td>
			  		</tr>
			  	</c:forEach>
			  </tbody>
			</table>
	    </div>
    </div>
</div>