
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; cha	rset=UTF-8">
<link href="<c:url value="/css/home.css" />" rel="stylesheet">
<title>Persons list</title>
</head>
<body>
	
	
	<form:form method="POST" commandName="person" action="enter" 
	class="box login" >
		<fieldset class="boxBody">
		<form:label path="name">Name</form:label>
		<form:input path="name"/>
		
		<form:label path="password">Password</form:label>
		<form:password path="password"/>
		
		</fieldset> 
	
	 
	<footer>
	  <label><input type="checkbox" tabindex="3">Keep me logged in</label>
	  <input type="submit" class="btnLogin" value="Login" tabindex="4">
	</footer>
	 
	
	</form:form>
	
	
	
	
</body>
</html> 