
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>

<title>ViewAll</title>
</head>
<body>

	<table >  
<tr><th>Id</th><th>Name</th><th>LastName</th><th>Gender</th><th>Email</th></tr>  
   
   <c:forEach items="${persons}" var="emp" >   
  <thead>
   <tr>  
   <td>${emp.id}</td>  
   <td>${emp.name}</td>  
   <td>${emp.lastName}</td>  
   <td>${emp.gender}</td>  
   <td>${emp.email}</td>
   </tr> 
   </thead> 
   </c:forEach>  
   </table>  
  <p>  ${persons.name}</p>
  
</body>
</html>
