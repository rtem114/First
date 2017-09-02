
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

<title>ViewAll</title>
<link href="<c:url value="/css/home.css" />" rel="stylesheet"> 
</head>
<body>

	<table  class=".box label">  
<tr><th>Id</th><th>Name</th><th>LastName</th>
<th>Gender</th><th>Email</th><th>Password</th><th>Position</th>
<th>Description</th><th>Hobby</th><th>Country</th>
</tr>  
   
   <c:forEach items="${persons}" var="emp" >   
   
   <tr>  
   <td>${emp.id}</td>  
   <td>${emp.name}</td>  
   <td>${emp.lastName}</td>  
   <td>${emp.gender}</td>  
   <td>${emp.email}</td>
   <td>${emp.password}</td>
   <td>${emp.position}</td>
   <td>${emp.description}</td>
   <td>
	<c:forEach items="${emp.hobbie}" var="em" >  
	${em },
 </c:forEach>  
</td>
    <td>${emp.country}</td>
    
   <td><a href="/delete/${emp.id}">del</a></td>
   </tr> 
   
   </c:forEach>  
   
   
   </table>  
   <br>
     <br>
  <a href="/" class=".btnLogin:active">On the begining</a>  
  
</body>
</html>
