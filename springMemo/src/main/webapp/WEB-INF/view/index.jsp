
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; cha	rset=UTF-8">
<title>Index</title>
</head>
<body>
	<h2>To-do list</h2>
	<br />
	<br />
	<form action="/" method="POST">
		<div>
			<input name="text">
		</div>
		<div>
			<button type="submit">add</button>
		</div>
	</form>
	<br />
	<br />



	<table>
		<c:forEach items="${memos}" var="memo" varStatus="vs">

			<tr>
				<td>${vs.index + 1}</td>
				<td>${memo.text}</td>
				<td>(id: ${memo.id})</td>
				<td><fmt:formatDate pattern="dd/MM/yyyy" value="${memo.date}" /></td>
				<td><a href="/delete/${memo.id}">del</a></td>
			</tr>


		</c:forEach>

	</table>



	<br />
	<br />

	<form action="/" method="POST">
		<div>
			<input name="id">
		</div>
		<div>
			<button type="submit">delete by id</button>
		</div>
	</form>
</body>
</html>