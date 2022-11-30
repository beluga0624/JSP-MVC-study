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
		/* background: #212429;
		color: white; */
		padding: 30px 10px;
	}
	
	textarea {
		resize:none;
	}
	
	#filePreview {
		padding: 0;
	}
	
	#fileName {
		font-size: 12px;
	}
	
	#filePreview2 img {
		width: 120px;
		height: 120px;
		margin: 10px;
	}
	
	.uploadedFile li {
		margin-left: 1em;
		width: 120px;
		word-break: break-all;
	}
	
	.fa-circle-xmark {
		position: absolute;
		top: 10px;
		left: 110px;
	}
	
	#filePreview img {
		width: 120px;
		height: 120px;
		margin: 10px;
	}
	
	#filePreview2 img {
		width: 120px;
		height: 120px;
		margin: 10px;
	}
	
	#fileName {
		font-size: 12px;
		margin-left: 1em;
		padding: 0;
		width: 140px;
		word-break: break-all;
		list-style-position: inside;
	}
	
	#fileName2 {
		font-size: 12px;
		margin-left: 1em;
		padding: 0;
		width: 140px;
		word-break: break-all;
		list-style-position: inside;
	}
	
	#filePreview td {
		width: 140px;
		position: relative;
	}
	
	#fileName td {
		width: 140px; 
		padding: 10px;
		text-align: center;
	}
	
	#fileName2 td {
		width: 140px; 
		padding: 10px;
		text-align: center;
	}

</style>
<div class="container mb-5 pb-5">
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
			  	
     			<form action="/modSiteBoard.board?category=${board.boardCategory}" method="post" id="noticeWriteFrm" enctype="multipart/form-data">
     				<input type="hidden" name="boardNum" value="${board.boardNum}">
     				
				  	<div class="writeNotice form-control">
				  		<div style="margin: 30px 10px;">
					  		<div class="mb-3">
							  <label for="subject" class="form-label">제목</label>
							  <input type="email" class="form-control" id="subject" name="subject" value="${board.subject}">
							</div>
							<div class="mb-3">
							  <label for="content" class="form-label">내용</label>
							  <textarea class="form-control" id="content" name="content" rows="20"><c:out value="${board.content}"/></textarea>
							</div>
							<div class="mb-3">
							  <label for="fileUpload" class="form-label">첨부파일 추가</label>
							  <input type="file" class="form-control" id="fileUpload" name="fileUpload" multiple>
							</div>
							
							<table>
								<tr id="filePreview2"></tr>
								<tr id="fileName2"></tr>
								<tr>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
									<td></td>
								</tr>
							</table>
							
							<c:if test="${fileList != null}">
								삭제할 파일 선택
							</c:if>
							<table>
							  <tbody>		
				    			<tr id="filePreview">
				    				<c:forEach var="file" items="${fileList}">
				    					
					    				<c:if test="${file.type eq 'image'}">
					    					<td class="uploadedFile">
					    						<img src="../resources/images/siteBoard/${file.fileName}" class="file_container">
					    						<button type='button' class='btn btn-danger btn-circle deleteFile' style="all: unset; cursor: pointer;">
						    						<i class="fa-regular fa-circle-xmark" style="width: 30px; height: 30px; color: red;"></i>
						    					</button>
					    						<input type="hidden" value="${file.fileName}" name="fName">
						    					<input type="hidden" value="${file.type}" name="fType">
						    				</td>
					    				</c:if>
			
				    					<c:if test="${file.type ne 'image'}">
				    						<td class="uploadedFile">
					    						<img src="../resources/images/icons/attach.png" class="file_container">
					    						<button type='button' class='btn btn-danger btn-circle deleteFile' style="all: unset; cursor: pointer;">
						    						<i class="fa-regular fa-circle-xmark" style="width: 30px; height: 30px; color: red;"></i>
						    					</button>
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
							
							<div style="text-align:center;">
						  		<button type="button" id="boardSubmitBtn" class="btn btn-md btn-primary">수정</button>&nbsp;&nbsp;
						  		<button type="reset" id="boardResetBtn" class="btn btn-md btn-secondary">취소</button>
						  	</div>
						</div>
				  	</div>
     			</form>
			  </div>
			</div>
    	</div>
    	<%@ include file="/includes/aside.jsp" %>
	</div>
</div>

<script src="https://use.fontawesome.com/releases/v6.0.0/js/all.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>
	$(document).ready(function(){
		$("input[type='file']").change(function(e){
			if(document.querySelector("tr#filePreview2").childNodes != null){
				$("#filePreview2").empty();
				$("#fileName2").empty();
			};
			
			chkUploadFileName();
			chkUploadFileSize();
			chkUpladFileExt();
			
			var	td1 = document.createElement("td");
			td1.setAttribute("colspan", "5");
			td1.setAttribute("style", "width: 100%;");
			document.querySelector("tr#filePreview2").appendChild(td1);
			
			var files = event.target.files;
			
			for(var file of event.target.files){
				
				if(file.type.startsWith("image")){
					var reader = new FileReader(); 

					var td2 = document.createElement("td");
					td2.setAttribute("style", "width:140px; padding: 10px;");
					
					reader.onload = function(event) { 
						var img = document.createElement("img");

						img.setAttribute("src", event.target.result);
						
						td1.appendChild(img);

					};  
					
					reader.readAsDataURL(file);
					
					document.querySelector("tr#fileName2").appendChild(td2);
					
					td2.append(file.name);

					console.log(file);
				}else{
					
					var td2 = document.createElement("td");
					td2.setAttribute("style", "width:170px; padding: 10px;");
					document.querySelector("tr#fileName2").appendChild(td2);
					
					var img = document.createElement("img");
					td1.appendChild(img);
					
					var fileName = file.name;
					td2.append(file.name);
					
					img.setAttribute("src", "../resources/images/icons/attach.png");
					
					document.querySelector("tr#image_container").appendChild(img);
					ul.append("<li>" + fileName + "</li>");

				}
				
			}
		});
		
		$("#boardResetBtn").on("click", function(){
			history.go(-1);
		});
		
		$("#boardSubmitBtn").on("click", function(){
			$("#noticeWriteFrm").submit();
		});
		
		$(".deleteFile").on("click", function(){
			var index = $(this).closest("td").index();
			
			$(this).closest("td").remove();
			$("#fileName").children().eq(index).remove();
		});

	});

	function chkUploadFileName() {
	    var fileVal = $("#fileUpload").val();
	    var pattern =  /[\{\}\/?,;:|*~`!^\+<>@\#$%&\\\=\'\"]/gi;
	    var fileName = fileVal.split('\\').pop().toLowerCase();
	    if(pattern.test(fileName) ){
	    alert('파일명에 특수문자가 포함되어 있습니다.');
	    $("#fileUpload").val("");
	    }
	}
	
	function chkUploadFileSize() {
	    var maxSize = 5 * 1024 * 1024; // 5MB
	    var fileSize = $("#fileUpload")[0].files[0].size;
	    if(fileSize > maxSize){
	    alert("첨부파일 사이즈는 5MB 이내로 등록 가능합니다.");
	    $("#fileUpload").val("");
	    return;
	    }
	 }
	
	function chkUpladFileExt() {
		var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
		
		if(regex.test($("#fileUpload").val())){
			alert("해당 종류의 파일은 업로드할 수 없습니다.");
			$("#fileUpload").val("");
			return;
		}
	}

</script>





<%@ include file="/includes/footer.jsp" %>