<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<div class="card">

	<div class="card-header">Book Search</div>

	<c:url value="/book" var="searchUrl"></c:url>
	<form action="${searchUrl}" class="card-body row">

		<div class="mb-4">
			<label for="catrgory" class="col-form-label"> Category </label> 
			<select
				name="category" id="category" class="form-select">
				<option value="" disabled="disabled" selected="selected">Select One</option>
				<c:forEach var="c" items="${categories}">
					<c:choose>
						<c:when test="${c.id eq param.category}">
							<option value="${c.id}" selected="selected">${c.name}</option>
						</c:when>
						<c:otherwise>
							<option value="${c.id}">${c.name}</option>
						</c:otherwise>
					</c:choose>
				</c:forEach>
			</select>
		</div>

		<div class="mb-4">
			<label for="keyword" class="col-form-label"> Keyword </label> 
			<input type="text" class="form-control" value="${param.keyword}" id="keyword" name="keyword"
				placeholder="Search Keyword" />
		</div>



		<c:url value="/book/edit" var="editUrl"></c:url>
		<div class="d-grid gap-2">

			<button class="btn btn-primary">Search</button> 
			<a href="#"	id="uploadBtn" class="btn btn-secondary">Upload</a>
			<a href="${editUrl}" class="btn btn-success">Add New</a>

		</div>
		
	</form>
	
	<c:url value="/upload" var="upload"></c:url>
	<form action="${upload}" id="uploadForm" method="post" class="d-none" enctype="multipart/form-data">
		<input id="uploadInput" type="file" name="uploadFile"/>
	</form>
	
	<c:url value="/resources/js/upload.js" var="uploadJs"></c:url>
	<script type="text/javascript" src="${uploadJs}"></script>
</div>