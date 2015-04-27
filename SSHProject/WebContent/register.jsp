<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册新用户</title>
</head>
	<body>
		<form name="register" action="user/register" method="post" onsubmit="checkData()" enctype="multipart/form-data">
			<table width="750" align="center">
				<tr>
					<td>用户名</td>
					<td><input type="text" name="userDTO.userName" size="30" maxlength="12"/></td>
				</tr>
				<tr>
					<td>电话</td>
					<td><input type="text" name="userDTO.telphone" size="30" maxlength="11"/></td>
				</tr>
				<tr>
					<td>密码</td>
					<td><input type="password" name="userDTO.password" size="30" maxlength="30"/></td>
				</tr>
				<tr>
					<td>确认密码</td>
					<td><input type="password" name="userDTO.confirmPsw" size="30" maxlength="30"/></td>
				</tr>
				<tr >
					<td colspan="2" align="left"><input type="submit" value="注册" /></td>
				</tr>
			</table>
		</form>
	</body>
</html>
