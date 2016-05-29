<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.7.2.js"></script>
<script type="text/javascript">
$(function(){
	$(".deleteEmp").click(function(){
		var label = $(this).prev(":hidden").val();
		var flag = confirm("确定要删除" + label + "的信息吗?");
		
		if(!flag){
			return false;
		}
		
		var url = this.href;
		var args = {"time":new Date(),"_method":"DELETE"}; 
		$.post(url,args,function(data){
			if("1" == data){
				alert("删除成功");
			}
		});
		
		return false;
	});
});

</script>
</head>
<body>

	<c:if test="${empty empList }">
		<h1>暂时没有员工信息</h1>
	</c:if>	
	<c:if test="${!empty empList }">
	
	<table border="1" align="center" >
		<tr>
			<th>ID</th>
			<th>LastName</th>
			<th>EMAIL</th>
			<th>BIRTH</th>
			<th>CreateTime</th>
			<th>Department</th>
			<td>Edit</td>
			<th>Remove</th>
		</tr>
		
		<c:forEach items="${empList }" var="emp">
			<tr>
				<td>${emp.id }</td>
				<td>${emp.lastName }</td>
				<td>${emp.email }</td>
				<td>
					<fmt:formatDate value="${emp.birth }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${emp.createTime }" pattern="yyyy-MM-dd"/>
				</td>
				<td>${emp.dept.deptName }</td>
				<td>
					<a href="${pageContext.request.contextPath }/emp/${emp.id}">Edit</a>
				</td>
				<td>
					<input type="hidden" value="${emp.lastName}"/>
					<a href="${pageContext.request.contextPath }/emp/${emp.id}" class="deleteEmp">Remove</a>
				</td>
			</tr>
		</c:forEach>
		
	</table>
		
	</c:if>

</body>
</html>