<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加一个新的用户</title>
</head>
<body>
	<form name="addNewUser" method="post" action="${pageContext.request.contextPath}/user/addNewUser">
		<table>
		<tr>
			<td>
				<strong>添加新用户</strong>
			</td>
		</tr>
		
		<tr>
			<td>
				姓名: <input name="userDTO.userName" type="text" id="userName">
			</td>
		</tr>
		
		<tr>
			<td>
				性别: <input type="radio" name="userDTO.gender"  value="1" checked> 男
	                 <input type="radio" name="userDTO.gender" value="2"> 女
	        </td>
		</tr>
		
		<tr>
			<td>
				密码: <input name="userDTO.password" type="text" id="password">
			</td>
		</tr>
		
		<tr>
			<td>
				密码确认: <input name="userDTO.confirmPsw" type="text" id="confirmPsw">
			</td>
		</tr>
		
		<tr>
			<td>
				<input name="Submit" type="submit" value="添加用户">
				<input type="reset" name="reset" value="清空">
			</td>
		</tr>
		</table>
	</form>
</body>
</html>