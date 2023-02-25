<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN"
	crossorigin="anonymous"></script>
</head>
<body>
	<h1>Hello Scope</h1>
	
	<div class="row mt-4 mb-4">
	
		<div class="col">		
			<div class="card">			
				<div class="card-header">Request Scope</div>				
				<div class="card-body">	
					<h1 class="text-center">${request}</h1>			
				</div>			
			</div>		
		</div>
		
		<div class="col">		
			<div class="card">			
				<div class="card-header">Session Scope</div>				
				<div class="card-body">	
					<h1 class="text-center">${session}</h1>				
				</div>			
			</div>
		</div>
		
		<div class="col">
			<div class="card">
				<div class="card-header">Application Scope</div>
				<div class="card-body">
					<h1 class="text-center">${application}</h1>	
				</div>
			</div>
		</div>
		
		
	
	</div>
	
	<c:url value="/" var="home"></c:url>
	<a href="${home}" class="btn btn-primary">Load Home</a>
	
	<c:url value="/create-session" var="create"></c:url>
	<a href="${create}" class="btn btn-primary">Create Session</a>
	

</body>
</html>