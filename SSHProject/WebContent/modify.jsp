<%@ page language="java" contentType="text/html; charset=GB18030"
    pageEncoding="GB18030"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title>��½ҳ��</title>
</head>
<body>
<form name="register" action="user/modifyInfo" method="post" onsubmit="checkData()" enctype="multipart/form-data">
			<table width="750" align="center">
				<tr>
					<td>�û���</td>
					<td><input type="text" name="userDTO.userName" size="30" maxlength="10"/></td>
				</tr>
			
				<tr>
					<td>����</td>
					<td><input type="password" name="userDTO.password" size="30" maxlength="10"/></td>
				</tr>
				
				<tr>
					<td colspan="2" align="left"><input type="submit" value="��¼" /></td>
				</tr>
			</table>
		</form>
</body>
</html>