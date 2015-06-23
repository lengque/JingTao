<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>用户列表</title>
</head>
<body>
	<form id="fileZhidaoForm">
	<table>
		<thead>
			<tr>
				<th>
					用户Id
				</th>
				<th>
					用户名
				</th>
				<th>
					密码
				</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${!empty userList}">
				<c:forEach items="${userList}" var="user">
				<tr>
					<td>
						${user.userId} 
					<td>
					<td>
						${user.userName} 
					<td>
					<td>
						${user.password} 
					<td>
				</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>
</form>

</body>
</html>
