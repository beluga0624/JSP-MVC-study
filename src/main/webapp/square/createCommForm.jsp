<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/includes/header.jsp" %>
<style>
	.createCommFrm{
		margin: 50px 0;
	}
</style>

<div class="container">
	<div class="row">
    	<div class="col-9">
    		<div class="container">
			  <div class="row">
			  	<form name="createCommFrm" class="createCommFrm" action="/createComm.square" method="post" enctype="multipart/form-data">
				  <div class="mb-3">
				    <label for="commName" class="form-label">커뮤니티 이름</label>
				    <input type="text" class="form-control" id="commName" name="commName">
				  </div>
				  <div class="mb-3">
				    <label for="description" class="form-label">커뮤니티 설명</label>
				    <textarea class="form-control" id="description" name="description" rows="8" cols="70" style="resize:none"></textarea>
				  </div>
				  <div class="mb-3">
				    <label for="activityName" class="form-label">커뮤니티 활동명</label>
				    <input type="text" class="form-control" id="activityName" name="activityName">
				  </div>
				  <div class="mb-3">
				    <label for="category" class="form-label">카테고리</label>
				    <select class="form-select" aria-label="Default select example" id="category" name="category">
					  <option selected>카테고리를 선택하세요</option>
					  <option id="category" value="카테고리1">One</option>
					  <option id="category" value="카테고리2">Two</option>
					  <option id="category" value="카테고리3">Three</option>
					  <option id="category" value="카테고리4">Four</option>
					  <option id="category" value="카테고리5">Five</option>
					  <option id="category" value="카테고리6">Six</option>
					</select>
				  </div>
				  <div class="mb-3">
				    <label for="question1" class="form-label">커뮤니티 가입질문1</label>
				    <textarea class="form-control" name="question1" id="question1" rows="3" cols="70"  style="resize:none"></textarea>
				  </div>
				  <div class="mb-3">
				    <label for="question2" class="form-label">커뮤니티 가입질문2</label>
				    <textarea class="form-control" name="question2" id="question2" rows="3" cols="70"  style="resize:none"></textarea>
				  </div>
				  <div class="mb-3">
				    <label for="question3" class="form-label">커뮤니티 가입질문3</label>
				    <textarea class="form-control" name="question3" id="question3" rows="3" cols="70"  style="resize:none"></textarea>
				  </div>
				  <div class="mb-3">
				    <label for="mainImg" class="form-label">커뮤니티 대표 이미지</label>
				    <input type="file" class="form-control" id="mainImg" name="mainImg">
				  </div>
				  <div class="mb-3">
				    <label for="subImg" class="form-label">커뮤니티 소개 이미지</label>
				    <input type="file" class="form-control" id="subImg" name="subImg">
				  </div>
				  <button type="submit" class="btn btn-primary">개설하기</button>
				  <button type="reset" class="btn btn-secondary">초기화</button>
				</form>
			  </div>
			</div>
    	</div>
    	<%@ include file="/includes/aside.jsp" %>
	</div>
</div>
<%@ include file="/includes/footer.jsp" %>