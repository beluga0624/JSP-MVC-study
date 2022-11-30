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
		/* background: #212429; */
		/* color: white; */
		padding: 30px 10px;
	}
	
	textarea {
		resize:none;
	}
	
	#filePreview img {
		width: 120px;
		height: 120px;
		margin: 10px;
	}
	
	#fileName {
		font-size: 12px;
		margin-left: 1em;
		padding: 0;
		width: 120px;
		word-break: break-all;
		list-style-position: inside;
	}
	
	
</style>

<div class="container mb-5">
  <div class="row">
    <%@ include file="/includes/communityMenu.jsp" %>
    <div class="col-9">
    	
    	<p class="title">${sessionScope.boardName}</p>
     
	  	<form action="/writeCommBoard.board" method="post" id="noticeWriteFrm" enctype="multipart/form-data">
	  		<input type="hidden" name="writer" value="">
		  	<div class="writeNotice form-control">
		  		<div style="margin: 30px 10px;">
			  		<div class="mb-3">
					  <label for="subject" class="form-label">제목</label>
					  <input type="email" class="form-control" id="subject" name="subject">
					</div>
					<div class="mb-3">
					  <label for="content" class="form-label">내용</label>
					  <textarea class="form-control" id="content" name="content" rows="20"></textarea>
					</div>
					<div class="mb-3">
					  <label for="fileUpload" class="form-label">첨부파일</label>
					  <input type="file" class="form-control" id="fileUpload" name="fileUpload" multiple>
					</div>
					
					<table>
						<tr id="filePreview"></tr>
						<tr id="fileName"></tr>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</table>
					
					<div style="text-align:center;">
				  		<button type="button" id="boardSubmitBtn" class="btn btn-md btn-primary">등록하기</button>&nbsp;&nbsp;
				  		<button type="reset" id="boardResetBtn" class="btn btn-md btn-secondary">초기화</button>
				  	</div>
				</div>
		  	</div>
   		</form>
    
    </div>
  </div>
</div>

<script src="https://use.fontawesome.com/releases/v6.0.0/js/all.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>
	$(document).ready(function(){
		$("input[type='file']").change(function(e){
			if(document.querySelector("tr#filePreview").childNodes != null){
				$("#filePreview").empty();
				$("#fileName").empty();
			};
			
			chkUploadFileName();
			chkUploadFileSize();
			chkUpladFileExt();
			
			var	td1 = document.createElement("td");
			td1.setAttribute("colspan", "5");
			document.querySelector("tr#filePreview").appendChild(td1);
			
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
					
					document.querySelector("tr#fileName").appendChild(td2);
					
					td2.append(file.name);

					console.log(file);
				}else{
					
					var td2 = document.createElement("td");
					td2.setAttribute("style", "width:140px; padding: 10px;");
					document.querySelector("tr#fileName").appendChild(td2);
					
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
			if(document.querySelector("div#image_container").childNodes != null){
				$("#image_container").empty();
				$("#fileName").empty();
			};
		});
		
		$("#boardSubmitBtn").on("click", function(){
			if($("#subject").val() != null && $("#content").val() != null){
				$("#noticeWriteFrm").submit();
			}else{
				alert("제목이나 내용이 작성되지 않았습니다.");
			}
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