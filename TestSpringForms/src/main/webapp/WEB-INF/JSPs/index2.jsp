
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; cha	rset=UTF-8">
<title>Persons list</title>
<style>
	table{
		margin-top: 5px;
		border: solid black 1px;
		}
		table td{
		padding: 5px;}
		.message{
		font-size:90%
		color:blue;
		font-style:italic;
		margin-top:30 px;}
</style>
</head>
<body>
	<!-- 
	<table border="1">
	<tr>
		<th>Name</th>
		<th>Last name</th>
		<th>Gender</th>
		<th>BirthDay</th>
		<th>Skills</th>
		<th>Email</th>
		<th>Profession</th>
		<th>Country</th>
	<tr/>
	<c:forEach items="${personsInfos}" var="info">
	
	<tr>
		<td> ${info.name}<td/>
		<td> ${info.lastName}<td/>
		<td> ${info.gender}<td/>
		<td> ${info.birthDay}<td/>
		<td> ${info.skills}<td/>
		<td> ${info.email}<td/>
		<td> ${info.profession}<td/>
		<td> ${info.country}<td/>
		<td> <a href="deletePerson?id=${info.id }">Delete</a> <td/>
		<td> <a href="editPerson=${info.id }">Edit</a> <td/>
	</tr>
	</c:forEach>
	</table>
	 -->
	
</body>
</html>