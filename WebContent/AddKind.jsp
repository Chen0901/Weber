<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加类别</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
</head>
<body>
	<jsp:include page="ManagerHeader.jsp" />
	<div align="center" id="content">
	<form action="addKind.do" method="post">
		<div align="center">
			<table width="80%" >
			<caption><a href="selectAllKind.do">添加类别信息</a></caption>
				<tr align="center">
					<td colspan="2">
					<font color="red">${msg }</font>
					</td>
				</tr>
				<tr align="center">
				    <td width="30%">类别名称</td>
				    <td>
				    <input type="text" name="kname" style="width:50%;text-align:center;">
				    </td>
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