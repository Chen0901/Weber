<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="shortcut icon" href="images/logo.ico" type="image/x-icon" />
<link rel="icon" href="images/logo.ico" type="image/x-icon" />
<title>微吧首页</title>
</head>
<frameset border=0 framespacing=0 rows="60, *" frameborder=0>
	<frame name=head src="UserHeader.jsp" frameborder=0 noresize scrolling=no>
	<frameset cols="250, *">
		<frame name=left src="selectKindForUser.do" frameborder=0 noresize scrolling=yes />
		<frame name=right src="selectInfoForRegister.do" frameborder=0 noresize />
	</frameset>
</frameset>
<noframes>
</noframes>
</html>