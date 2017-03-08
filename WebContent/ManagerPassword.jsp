<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css" />
<title>修改密码页</title>
</head>
<body>
<jsp:include page="ManagerHeader.jsp" />
	<div align="center" id="content">
	<form action="updateManagerPwd.do" method="post">
		<div align="center">
			<table width="80%" >
			<caption>修改个人密码</caption>
				<tr align="center">
					<td colspan="2">
					<font color="red">${msg }</font>
					</td>
				</tr>
				<tr align="center">
				    <td width="30%">旧密码</td>
				    <td><input type="text" name="old_pwd" style="width:50%;text-align:center;"></td>
				</tr>
				<tr align="center">
				    <td width="30%">新密码</td>
				    <td><input type="text" name="new_pwd" style="width:50%;text-align:center;"></td>
				</tr>
				<tr align="center">
					<td colspan="2"><input type = "submit" value="提交" style="width: 100%"></td>
				</tr>
			</table>
		</div>
		</form>
	</div>
	<br />
	<div align="center" id="footer">Weber后台管理系统</div>
</body>
</html>