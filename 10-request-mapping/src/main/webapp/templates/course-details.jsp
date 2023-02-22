<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</head>
<body>

	<div class="container pt-4">
	
		<h1>Edit Course</h1>
		
		<div class="row">
		
			<div class="col-4">

				<form action="#" method="get">
					
					<c:if test="${not empty result}">
						<div class="alert alert-success" role="alert">${result.message}</div>
					</c:if>
				
					<div class="mb-3">
						<label class="form-label mb-1" for="id">Id</label>
						<input id="id" name="id" type="text" value="${course.id}" class="form-control" readonly="readonly"/>
					</div>
					
					
					<div class="mb-3">
						<label class="form-label mb-1" for="name">Course Name</label>
						<input id="name" name="name" type="text" value="${course.name}" class="form-control" readonly="readonly"/>
					</div>
					
					
					<div class="mb-3">
						<label for="level" class="form-label mb-1">Level</label>
						<input id="level" name="level" type="text" value="${course.level}" class="form-control" readonly="readonly"/>
					</div>
						
					<div class="mb-3">
						<label for="duration" class="form-label mb-1">Duration</label>
						<input id="duration" name="duration" type="number" value="${course.duration}" readonly="readonly" class="form-control"/>
					</div>
					
					<div class="mb-3">
						<label for="fees" class="form-label mb-1">Fees</label>
						<input id="fees" name="fees"  type="number" value="${course.fees}" readonly="readonly" class="form-control"/>
					</div>
					
				</form>
				
				<div>
					<a href="/course">
						<input type="button" class="btn btn-success" value="Back to course"/>
					</a>
				</div>
				
			</div>
		
		</div>
	</div>

</body>
</html>