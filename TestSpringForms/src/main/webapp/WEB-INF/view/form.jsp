
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="<c:url value="/css/home.css" />" rel="stylesheet"> 
<title>Persons list</title>
<style>
.error-message {
   color: red;
   font-size:90%;
   font-style: italic;
}
</style>
<body>
	<form:form action="/save" method="POST"  modelAttribute="person" class=".boxBody">
        <table>
           <tr>
               <td>Name</td>
               <td><form:input path="name" /></td>
           </tr>
           
           <tr>
               <td>Last Name</td>
               <td><form:input path="lastName" /></td>
           </tr>
          
           <tr>
               <td>Gender</td>
               <td><form:select path="gender"> 
               <form:option value="" label="- Gender -"/>
               <form:option value="M" label="Male"/>
               <form:option value="F" label="Female"/>
               </form:select></td>
           </tr>
           <tr>
               <td>Email</td>
               <td><form:input path="email" /></td>
           </tr>
           <tr>
               <td>Password</td>
               <td><form:password path="password" /></td>
               
           </tr>
           
           <tr>
               <td>Position</td>
              <td><form:select path="position"> 
               <form:option value="" label="- Position -"/>
               <form:option value="Developer" label="Developer"/>
               <form:option value="QA" label="QA"/>
                <form:option value="HR" label="HR"/>
               </form:select></td>
           </tr>
           
           <tr>
               <td>Hobbie</td>
              <td><form:checkboxes items="${listHobbies}" path="hobbie" itemLabel="id" itemValue="name"/></td>
               <form:errors path="hobbie" cssClass = "error"/>
           </tr>
           
           <tr>
               <td>Country</td>
              <td><form:select items="${listCountries}" path="country" itemLabel="id" itemValue="name"/></td>
           </tr>
           
           
           <tr>
               <td >Description </td>
               <td><form:textarea path="description" cols="20" rows="5" />  </td>
           </tr>
           
            <tr>
               <td>&nbsp;</td>  
               <td><input type="submit" value="submit" /></td>
           </tr>
                     
		</table>
	</form:form>
	
	
	<!-- <form:form action="/index" method="GET" modelAttribute="persons">
	<table>
	<tr>
		<td> ${pers.name}<td/>
		<td> ${pers.lastName}<td/>
		<td> ${pers.gender}<td/>
		<td> ${info.email}<td/>
	</tr>
	</table>
	</form:form> -->
</body>
</html>