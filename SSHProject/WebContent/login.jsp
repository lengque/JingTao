<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>登陆页面</title>
</head>
<body>
	<form name="login" action="login" method="post" onsubmit="checkData()" enctype="multipart/form-data">
		<table width="750" align="center">
			<tr>
				<td>用户名</td>
				<td><input type="text" name="userDTO.userName" size="30" maxlength="10"/></td>
			</tr>
		
			<tr>
				<td>密码</td>
				<td><input type="password" name="userDTO.password" size="30" maxlength="10"/></td>
			</tr>
			
			<tr>
				<td colspan="2" align="left"><input type="submit" value="登录" /></td>
			</tr>
		</table>
	</form>
</body>
</html>