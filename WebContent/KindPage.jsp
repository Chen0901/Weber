<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv=content-type content="text/html; charset=utf-8" />
<link rel="stylesheet" href="css/left.css" />
</head>
<body>
	<div class="attention">
		<c:if test="${sessionScope.registerUser!=null }">
		<a class="test" href="selectInfoForRegister.do" target="right"> 我的首页 </a>
		</c:if>
		<c:if test="${sessionScope.registerUser==null }">
		<a class="test" href="selectInfoForUser.do" target="right"> 全部 </a>
		</c:if>
	</div>
	<c:forEach items="${KindList}" var="kl">
		<div class="item">
			<a class=menuchild href="selectInfoByKind.do?KId=${kl.KId }" target="right">
				${kl.KName } 
			</a>
		</div>
	</c:forEach>
	<c:if test="${sessionScope.registerUser==null }">
		<div class="attention">
			<a class="test" href="LoginRegisterUser.jsp" target="_top">登录或注册 </a>
		</div>
	</c:if>
</body>
</html>