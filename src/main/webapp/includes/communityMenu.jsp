<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="col-3">
		
	<div class="card" style="width: 18rem; margin-top:20px;">
	  <c:if test="${community.subImg == null}">
	  	<img src="/resources/images/commMainImg/${community.mainImg}" class="card-img-top">
	  </c:if>
	  <c:if test="${community.subImg != null}">
	  	<img src="/resources/images/commMainImg/${community.subImg}" class="card-img-top">
	  </c:if>
	  <div class="card-body">
	  	<table style="width:100%">
	  		<tr>
	  			<td style="font-weight:bold"><c:out value="${community.commName}"/></td>
	  			<td style="text-align:right">
	  				<img src="/resources/images/icons/group.png" style="width:20px; height:20px;">&nbsp;&nbsp;
	  				<c:out value="${community.memberCnt}"/>
	  			</td>
	  		</tr>
	  		<tr style="height:10px;">
	  			<td> </td>
	  		</tr>
	  		<tr>
	  			<td colspan="2" class="card-text">
	  				<c:out value="${community.description}"/>
	  			</td>
	  		</tr>
	  		<tr>
	  			<c:if test="${isCommMember == true}">
	  				<td>
	  					<input type="button" class="btn btn-primary btn-sm" value="정보수정" onclick='location.href="/square/checkMember.jsp?oper=updateForComm&commCode=${community.commCode}"'>
	  					<c:if test="${community.commAdmin != userid}">
	  						<input type="button" class="btn btn-secondary btn-sm" value="탈퇴" onclick='location.href="/square/checkMember.jsp?oper=withdrawFromComm&commCode=${community.commCode}"'>
	  					</c:if>
	  				</td>	
	  			</c:if>
	  			
	  			<c:if test="${isCommMember == false}">
	  				<td>
	  					<input type="button" class="btn btn-primary btn-sm" value="가입하기" onclick='location.href="/square/commJoinForm.jsp"'>
	  				</td>	
	  			</c:if>
	  			
	  			<td style="font-size:15px; text-align:right;">
	  				<c:out value="${community.regdate}"/>
	  			</td>
	  		</tr>
	  	</table>
	  </div>
	</div>
	<br>
	<form action="/modBoardTitle.square" method="post" id="modTitle">
		<input type="hidden" name="modBoardCommCode" value="${boardNameList[0].commCode}">
		<table class="boardListTBL fixed" style="width:100%">
			
			<c:if test="${community.commAdmin == userid}">
	  			<tr style="text-align:center;">
	  				<td>
	  					<input type="button" class="btn btn-primary btn-sm" id="addBoard" value="게시판 추가">&nbsp;&nbsp;&nbsp;
	  					<input type="button" class="btn btn-dark btn-sm" id="modBoard" value="게시판 목록 관리">
	  				</td>
	  			</tr>
	  		</c:if>
	  		
			<tr><td style="font-size: 15px;"><br></td></tr>
			
			<c:if test="${boardCategory != null}">
		  		<c:forEach var="category" items="${boardCategory}" varStatus="status">
		  			
		  			<tr>
		  				<td style="font-size: 18px" class="boardList"><b><c:out value="${category}"/></b></td>
		  			</tr>
		  			
		  			<c:forEach var="board" items="${boardNameList}">
		  				<c:if test="${board.category == category}">
		  					<tr>
		  						<td class="boardList">&nbsp;
		  							<span id=appendArea></span>
		  							<input type="hidden" class="modBoardInput" name="modBoardNum" value="${board.boardNum}">
									<input type="hidden" class="modBoardInput" name="modBoardName" value="${board.boardName}">
		  							<a href="/commBoard.board?boardName=${board.boardName}&commCode=${board.commCode}" style="text-decoration:none">
		  								<c:out value="-${board.boardName}"/>
		  							</a>
		  							<span style="float:right">
			  							<button type="button" class="modTitle" style="all: unset; cursor: pointer;">
			  								<i class="fa-solid fa-screwdriver-wrench"></i>
			  							</button>&nbsp;
			  							<button type="button" class="sendTitle" style="all: unset; cursor: pointer;">
			  								<i class="fa-solid fa-floppy-disk"></i>
			  							</button>
		  							</span>
		  						</td>
		  					</tr>
		  				</c:if>			
		  			</c:forEach>		    
		  		</c:forEach>
		  	</c:if>
		  	<tr><td><br> </td></tr>
		  	<tr>
		  		<td>
		  			<a href='communityShop.shop?commCode=${community.commCode}' style="text-decoration:none"><b>Goods Shop</b></a>
		  		</td>
		  	</tr>
		</table>
	</form>
	<form action="/modifyOrder.square" method="post" id="modifyOrderFrm" name="modifyOrderFrm">
	
		<input type="hidden" class="orderFrmCommCode" name="orderFrmCommCode" value="${community.commCode}">
		
		<div class="modOrder">
			<table class="orderTBL table table-striped" style="width:100%;">
				<c:forEach var="board" items="${boardNameList}">
					<tr>
						<td style="font-size:0.9rem">						
							<input type="hidden" class="orderFrmBoardNum" name="orderFrmBoardNum" value="${board.boardNum}">
							<c:out value="${board.category}"/> - <c:out value="${board.boardName}"/>
						</td>
						<td style="text-align:right;">
							<button class="fa-solid fa-angle-up" style="cursor:pointer" onclick="moveUp(this)"></button>
							<button class="fa-solid fa-angle-down" style="cursor:pointer" onclick="moveDown(this)"></button>
							<button class="fa-solid fa-xmark" style="cursor:pointer"></button>
						</td>
					</tr>
				</c:forEach>
			</table>
			<input type="submit" class="btn btn-md btn-success" value="순서 저장" style="float:right;">
		</div>

	</form>
	
	<form action="/addBoard.square" method="post" id="addBoardFrm" name="addBoardFrm">
		<br>
		<span class="newBoard">
			<input type="hidden" name="newCommCode" value="${community.commCode}">
			<input type="text" name="newCategory" placeholder="카테고리">
			<input type="text" name="newBoardName" placeholder="게시판 이름">
			<input type="submit" value="저장" class="btn btn-sm btn-success" style="display:inline-block; float:right;">
		</span>
		<br>
	</form>
	
</div>

<script src="https://use.fontawesome.com/releases/v6.0.0/js/all.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script type="text/javascript">
	$(document).ready(function(){
		$('.newBoard').hide();
		$('.modOrder').hide();
		$('#modifyBoardFrm').hide();
		
		$("#addBoard").on("click", function(){
			$('.newBoard').toggle();
		});
		
		$("#modBoard").on("click", function(){
			$('.modOrder').toggle();
			$('.boardList').toggle();
			$('#modifyBoardFrm').toggle();
		});
		
		var isActivate = false;
		var boardNum = null;
		var boardName = null;
		
		$(".modTitle").on("click", function(){
			if(isActivate == false){
				$(this).parent().prev().remove();
				boardName = $(this).parent().prev().val();
				boardNum = $(this).parent().prev().prev().val();
				$(".modBoardInput").remove();
				
				isActivate = true;
				
				var str = '<input type="hidden" class="modBoardInput" name="modBoardNum" value="' + boardNum + '">';
				str += ' <input type="text" class="modBoardInput" name="modBoardName" value="' + boardName + '">';
				
				$(this).parent().prev().append(str);
			}else{
				alert("이름을 하나씩 변경 해 주세요");
			}
		});
		
		$(".sendTitle").on("click", function(){
			$("#modTitle").submit();
		});
		
		$(".modBoard").on("click", function(){
			/* var list = new Array();
			$("input[name=boardTitle]").each(function(index, item){
				list.push($(item).val());
			});
			$("#boardTitleList").val(list); */
			$("#modifyBoardFrm").submit();
		});
		
		$(".fa-angle-down").on("click", function(){
			var boardOrder = $(this).closest('input[name="modBoardOrder"]').val();
			console.log(boardOrder);
		});
		
		var boardNumList = new Array();
		var BoardOrderList = new Array();
		
	});
	
	function moveUp(el){
		var $tr = $(el).closest('tr');
		$tr.prev().before($tr);
		/* var selectedIndex = $(el).closest('td').index();
		alert(selectedIndex); */
	};
	
	function moveDown(el){
		var $tr = $(el).closest('tr');
		$tr.next().after($tr);
	};
	
</script>