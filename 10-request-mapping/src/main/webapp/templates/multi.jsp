<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Multi</title>
</head>
<body>
	<h1>Multi Page</h1>
	<p>${ empty message ? "Default View" : message }</p>
	<ul>
		<!-- 
			Use c:url to get absolute path. If we use just href="/multi/action1",
			multi/action1 is ok if there is one application in our server,
			/multi/action1 is ok for one server or more it must be use always.
			the url will become multi/multi/action1 because it gives only relative path.
		
		 -->
		<li>
			<c:url value="/multi/action1" var="action1"></c:url>  
			<a href="${action1}">Action 1 that not work</a>
		</li>
		<li>
			<c:url value="/multi/action1?id=10" var="action1"></c:url>
			<a href="${action1}">Action 1 with id</a>
		</li>
		<li>
			<c:url value="/multi/action2" var="action2"></c:url>
			<a href="${action2}">Action 2</a>
		</li>
		<li>
			<c:url value="/multi/123" var="digit"></c:url>
			<a href="${digit}">Digit</a>
		</li>
		<li>
			<c:url value="/multi/asb/dsk" var="wildcard"></c:url>
			<a href="${wildcard}">wildcard</a>
		</li>
	</ul>
</body>
</html>