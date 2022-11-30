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
	
	.writeNotice {
		background: #212429;
		color: white;
		padding: 30px 10px;
	}
	
	textarea {
		resize:none;
	}
	
	#image_container{
		width: 150px;
		height: 150px;
		margin: 10px;
	}
	
	#fileName {
		font-size: 12px;
	}
	
	#noticeTBL {
		text-align: left;
	}
	
	#noticeTBL th {
		text-align: center;
	}
	
	#contentArea {
		min-height: 500px;
	}
	
	.fileArea {
		display: inline-block;
	}
	
	#imagePrviewArea img {
		width:100%;
	}
	
	.uploadedFile img{
		width: 120px;
		height: 120px;
		margin: 10px;
	}
	
	.uploadedFile li {
		margin-left: 1em;
		width: 120px;
		word-break: break-all;
	} 
	
	#filePreview img {
		width: 120px;
		height: 120px;
		margin: 10px;
	}
	
	#filePreview td {
		width: 140px;
		cursor: pointer;
	}
	
	#fileName {
		font-size: 12px;
		margin-left: 1em;
		padding: 0;
		width: 140px;
		word-break: break-all;
		list-style-position: inside;
	}
	
	#fileName td {
		width: 140px; 
		padding: 10px;
		text-align: center;
	}
	
	#noticeTBL {
		width: 100%;
	}
	
</style>
<div class="container">
	<div class="row">
    	<div class="col-9">
    		
    		<div class="container">
			  <div class="row">
			  	
			  	<c:if test="${board.boardCategory eq 'notice'}">
			  		<p class="title">공지사항</p>	
			  	</c:if>
			  	
			  	<c:if test="${board.boardCategory eq 'faq'}">
			  		<p class="title">자주 묻는 질문</p>	
			  	</c:if>
     			
			  	<table class="table table-light" id="noticeTBL">
				  <thead>
				    <tr style="text-align: center;">
				      <th scope="col" style="width: 25%;">글번호:</th>
				      <td scope="col" style="width: 25%;"><c:out value="${board.boardNum}"/></td>
				      <th scope="col" style="width: 25%;">조회수:</th>
				      <td scope="col" style="width: 25%;"><c:out value="${board.readCnt}"/></td>
				    </tr>
				    <tr>
				      <th scope="col">작성자:</th>
				      <td scope="col"><c:out value="${board.writer}"/></td>
				      <th scope="col">작성일자:</th>
				      <td scope="col"><c:out value="${board.regDate}"/></td>
				    </tr>
				  </thead>
				  <thead>
				    <tr>
				      <th scope="col" colspan="4"><c:out value="${board.subject}"/></th>
				    </tr>
				  </thead>
				  <tbody>
				    <tr>
				      <td colspan="4" style="vertical-align:top; text-indent: 2em;"><div id="contentArea"><c:out value="${board.content}"/></div></td>
				    </tr>
				  </tbody>
				  <thead>
				    <tr>
				      <th scope="col" style="width: 150px;">첨부파일</th>
				      <td colspan="3" style="text-indent: 2em;"><c:out value=""/></td>
				    </tr>
				  </thead>
				</table>
				<table>
				  <tbody>		
	    			<tr id="filePreview">
	    				<c:forEach var="file" items="${fileList}">
	    					
		    				<c:if test="${file.type eq 'image'}">
		    					<td class="uploadedFile">
		    						<img src="../resources/images/siteBoard/${file.fileName}">
		    						<input type="hidden" value="${file.fileName}" name="fName">
			    					<input type="hidden" value="${file.type}" name="fType">
			    				</td>
		    				</c:if>

	    					<c:if test="${file.type ne 'image'}">
	    						<td class="uploadedFile">
		    						<img src="../resources/images/icons/attach.png">
		    						<input type="hidden" value="${file.fileName}" name="fName">
			    					<input type="hidden" value="${file.type}" name="fType">
		    					</td>
	    					</c:if>
	    					
	    				</c:forEach>
	    			</tr>
					<tr id="fileName">
						<c:forEach var="file" items="${fileList}">
							<td>
								<c:out value="${file.fileName}"/>
							</td>
						</c:forEach>
					</tr>
					<tr>
						<td> </td>
						<td> </td>
						<td> </td>
						<td> </td>
						<td> </td>
					</tr>
				  </tbody>
				</table>
				
				<table style="text-align: right;">
					<tr>
						<td>
							<button type="button" class="btn btn-md btn-outline-secondary" onclick=''>목록</button>&nbsp;&nbsp;
							<c:if test="${sessionScope.userid ne null && (rpl.userid eq sessionScope.userid || sessionScope.userid eq 'admin')}">
								<button type="button" class="btn btn-md btn-outline-primary" onclick='location.href="/modBoard.board?boardNum=${board.boardNum}&category=${board.boardCategory}"'>수정</button>&nbsp;&nbsp;
								<button type="button" class="btn btn-md btn-outline-danger" onclick='location.href="/deleteBoard.board?boardNum=${board.boardNum}&category=${board.boardCategory}"'>삭제</button>
							</c:if>
						</td>
					</tr>
				</table>
				
				<form action="/regReply.board" method="post">
					<input type="hidden" value="${board.boardNum}" name="bno">
					<input type="hidden" value="${board.boardCategory}" name="category">
					<input type="hidden" value="${board.replyCnt}" name="replyCnt">
					<table class="table table-hover">
					  <c:if test="${sessionScope.userid != null}">
						  <thead>
						    <tr style="height: 100px;">
						      <th scope="col" style="width: 10%; vertical-align:middle">
						      	<c:if test="${userid eq 'admin'}">
						      		<c:out value="관리자"/>
						      	</c:if>
						      	<c:if test="${userid ne 'admin'}">
						      		<c:out value="${userid}"/>
						      	</c:if>
						      </th>
						      <th scope="col" colspan="2"  style="width: 80%; ">
					      		<textarea style="width:100% " name="reply" rows="3"></textarea>
						      </th>
						      <th scope="col" style="width: 10%; vertical-align:middle">
						      	<button class="btn btn-md btn-outline-primary" type="submit">등록</button>
						      </th>
						    </tr>
						  </thead>
  					  </c:if>
  					</table>
  					<table class="table table-hover" style="vertical-align:middle;">
					  <tbody>
					  	<c:if test="${replies != null}">
						  	<c:forEach var="rpl" items="${replies}">
						  		<tr>
							      <th style="width: 5%;"><c:out value="${rpl.replyNum}"/></th>
							      <td style="width: 10%;"><c:out value="${rpl.userid}"/></td>
							      <td style="width: 65%;" class="rplContent"><c:out value="${rpl.replyContent}"/></td>
							      <c:if test="${rpl.updateDate == null}">
							      	<td style="width: 20%; text-align: right;">
								      	<c:out value="${rpl.regDate}"/>&nbsp;&nbsp;
								      	<c:if test="${rpl.userid == sessionScope.userid || sessionScope.userid == 'admin'}">
								      		<button type="button" class="saveRpl" style="all: unset; cursor: pointer;">
								      		  <i class="fa-solid fa-floppy-disk" style="cursor: pointer;"></i>
								      		</button>
								      		<button type="button" class="modRpl" style="all: unset; cursor: pointer;">
								      		  <i class="fa-solid fa-screwdriver-wrench" style="cursor: pointer;"></i>
								      		</button>
								      		<button type="button" class="delRpl" style="all: unset; cursor: pointer;">
								      		  <i class="fa-solid fa-trash" style="cursor: pointer;"></i>
								      		</button>
								      	</c:if>
							      	</td>
							      	
							      </c:if>
							      
							      <c:if test="${rpl.updateDate != null}">
							      	<td style="width: 20%; text-align: right;">
								      	<c:out value="${rpl.updateDate}(수정일)"/>&nbsp;&nbsp;
								      	<c:if test="${rpl.userid == sessionScope.userid || sessionScope.userid == 'admin'}">
								      		<button type="button" class="saveRpl" style="all: unset; cursor: pointer;">
								      		  <i class="fa-solid fa-floppy-disk" style="cursor: pointer;"></i>
								      		</button>
								      		<button type="button" class="modRpl" style="all: unset; cursor: pointer;">
								      		  <i class="fa-solid fa-screwdriver-wrench" style="cursor: pointer;"></i>
								      		</button>
								      		<button type="button" class="delRpl" style="all: unset; cursor: pointer;">
								      		  <i class="fa-solid fa-trash" style="cursor: pointer;"></i>
								      		</button>
								      	</c:if>
							      	</td>
							      </c:if>
							    </tr>
						  	</c:forEach>					  	
					  	</c:if>
					  </tbody>
					</table>
				</form>
				
				<form action="/modReply.board" method="post" name="modReplyFrm">
					<input type="hidden" name="modReplyNum" value="">
					<input type="hidden" name="modReplyBoardNum" value="${board.boardNum}">
					<input type="hidden" name="modReplyCategory" value="${board.boardCategory}">
				</form>
				
				<form action="/delReply.board" method="post" name="delReplyFrm">
					
				</form>
				
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
    	<%@ include file="/includes/aside.jsp" %>
	</div>
</div>



<!-- Modal -->
<div class="modal fade" id="imagePreview" tabindex="-1" aria-labelledby="imagePreviewLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="imagePreviewLabel"></h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body" id="imagePrviewArea">

      </div>
    </div>
  </div>
</div>



<script src="https://use.fontawesome.com/releases/v6.0.0/js/all.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>
	$(document).ready(function(){
		//첨부파일 미리보기, 다운로드
		$(".uploadedFile").on("click", function(){
			
			$("#imagePreviewLabel").empty();
			$("#imagePrviewArea").empty();
			
			var fileName = $(this).children("input[name=fName]").val();
			var fileType = $(this).children("input[name=fType]").val();
			
			document.querySelector("h5#imagePreviewLabel").append(fileName);
			
			if(fileType == 'image'){
				const img = document.createElement('img');
				img.setAttribute("src", "../resources/images/siteBoard/" + fileName);
				document.querySelector("div#imagePrviewArea").appendChild(img);
				$("#imagePreview").modal('toggle');
			}else{
				window.location = '../resources/images/siteBoard/' + fileName;
			} 
	
		});
		
		
		//댓글 수정
		var area = null;
		var BTNarea = null;
		var content = null;
		var rplNum = null;
		
		$(".saveRpl").hide();
		
		$(".modRpl").on("click", function(){
			BTNarea = $(this).prev();
			area = $(this).parent().prev();
			content = $(this).parent().prev().text();
			rplNum = $(this).parent().prev().prev().prev().text();
			
			$(this).parent().prev().empty();
			
			BTNarea.show();
			
			var textarea = document.createElement('textarea');
			textarea.setAttribute("rows", "3");
			textarea.setAttribute("name", "modReplyContent");
			textarea.setAttribute("style", "width: 100%;");
			textarea.setAttribute("id", "textBox");
			
			if ($('.rplContent').is(':empty')){
				area.append(textarea);
				$("#textBox").val(content);
			}
			
			$("input[name=modReplyNum]").val(rplNum);
		});
		
		//댓글 수정내용 저장
		$(".saveRpl").on("click", function(){
			var replyContent = $(this).parent().prev();
			$("form[name=modReplyFrm]").append(replyContent);
			$("form[name=modReplyFrm]").submit();
		});
		
		
		
		//댓글 삭제
		$(".delRpl").on("click", function(){
			
			if(confirm("댓글을 삭제하시겠습니까?")){
				rplNum = $(this).parent().prev().prev().prev().text();
				
				var input1 = document.createElement('input');
				var input2 = document.createElement('input');
				var input3 = document.createElement('input');
				
				input1.setAttribute("type", "hidden");
				input1.setAttribute("name", "delBoardNum");
				input1.setAttribute("value", ${board.boardNum});
				
				input2.setAttribute("type", "hidden");
				input2.setAttribute("name", "delCategory");
				input2.setAttribute("value", '${board.boardCategory}');
				
				input3.setAttribute("type", "hidden");
				input3.setAttribute("name", "delReplyNum");
				input3.setAttribute("value", rplNum);

				
				$("form[name=delReplyFrm]").append(input1);
				$("form[name=delReplyFrm]").append(input2);
				$("form[name=delReplyFrm]").append(input3);
				
				$("form[name=delReplyFrm]").submit();
			}
		});
		

	});
</script>





<%@ include file="/includes/footer.jsp" %>