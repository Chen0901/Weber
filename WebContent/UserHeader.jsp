<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv=content-type content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/head.css" />
</head>
<body>
	<div class="head">
		<div class="headlogo">
			<img src="images/headlogo.png" />
		</div>
		<form action="searchInfoForUser.do" method="post" target="right">
			<div class="form-text">
				<input class="searchtext" type="text" name="word" />
			</div>
			<div class="form-sub">
				<input class="search" type="submit" value=""
					style="background: url('images/ic_search.png');"
					onmouseover="this.style.background='url(images/ic_search2.png )'"
					onmouseout="this.style.background='url(images/ic_search.png )'" />
			</div>
		</form>
		<c:if test="${sessionScope.registerUser!=null}">
		<div class="find">
			<a href="FindIndex.jsp" target=_top>
				<div class="find_ico">
					<img src="images/ic_find.png"
						onmouseover="this.src='images/ic_find2.png' "
						onmouseout="this.src='images/ic_find.png' " />
				</div>
			</a>
		</div>
		<div class="index">
			<a href="UserIndex.jsp" target=_top>
				<div class="index_ico">
					<img src="images/ic_index.png"
						onmouseover="this.src='images/ic_index2.png' "
						onmouseout="this.src='images/ic_index.png' " />
				</div>
			</a>
		</div>
		<div class="username">
			<a href="RegisterUserIndex.jsp" target=_top>
				<div class="user_ico">
					<img src="images/ic_self.png"
						onmouseover="this.src='images/ic_self2.png' "
						onmouseout="this.src='images/ic_self.png' " />
				</div>
				<div class="head_user">${sessionScope.registerUser.RUName}</div>
			</a>
		</div>
		<div class="loginout">
			<a onclick="if (confirm('确定要退出吗？')) return true; else return false;"
				href="registerLoginOut.do" target=_top>
				<div class="head_out">注销</div>
			</a>
		</div>
		</c:if>
	</div>
</body>
</html>