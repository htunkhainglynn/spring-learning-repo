<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Courses</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
  	<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
  </head>
  <body>
    <div class="container pt-4">
    	<h1>Course List</h1>
    	
    	<div>
    		<c:url value="/course/edit" var="courseEdit"></c:url>
    		<a href="${ courseEdit }" class="btn btn-primary">Add New Course</a>
    	</div>
  
	    <div class="mt-4">
	    	<table class="table">
	    		<thead>
	    			<tr>
	    				<th>Id</th>
		    			<th>Name</th>
		    			<th>Level</th>
		    			<th>Duration</th>
		    			<th>Fees</th>
	    			</tr>
	    		</thead>
	    		<tbody>
	    			<c:forEach var="c" items="${list}">
	    				<tr>
	    					<td>${c.id}</td>
	    					<td>
	    						<c:url value="/course/detail?id=${c.id}" var="defaultUrl"></c:url>
	    						<a href="${ defaultUrl }">${c.name}</a>
	    					
	    					</td>
	    					<td>${c.level}</td>
	    					<td>${c.duration}</td>
	    					<td>${c.fees}</td>
	    				</tr>
	    			</c:forEach>
	    		</tbody>
	    	</table>
	    </div>
	    
    </div>
  </body>
</html>