<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form:form action="${pageContext.request.contextPath }/emp" method="POST" modelAttribute="employee">
		<input type="hidden" name="_method" value="PUT"/>
		
		<form:hidden path="id"/>
		
		LastName:<form:input path="lastName"/><br/>
		Email:<form:input path="email"/><br/>
		BIRTH:<form:input path="birth"/><br/>
		CreateTime:<form:input path="createTime"/><br/>
		DepartmentName:<form:select path="dept.id" 
									itemValue="id" 
									items="${deptList }" 
									itemLabel="deptName"/><br/>
		<input type="submit" value="更新"/>
	</form:form>
	
</body>
</html>