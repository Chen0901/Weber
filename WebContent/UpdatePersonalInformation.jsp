<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/myfollow.css" />
<title>修改用户信息</title>
</head>
<body style="background-color: #eeeeee">
	<div class="information">
    <form action="updatePersonalInformation.do" method="post" >
        <div class="information_name">
            用户名&nbsp;&nbsp;&nbsp;&nbsp;${sessionScope.registerUser.RUName}<br/><br/>
        </div>
        <div class="information_sex">
            个人性别&nbsp;&nbsp;&nbsp;&nbsp;
            <c:if test="${sessionScope.registerUser.RUSex==0 }">
            <input type="radio" name="user_sex" value=1 />男&nbsp;&nbsp;
            <input type="radio" name="user_sex" value=0  checked="checked" />女<br/><br/>
            </c:if>
            <c:if test="${sessionScope.registerUser.RUSex==1 }">
            <input type="radio" name="user_sex" value=1  checked="checked"  />男&nbsp;&nbsp;
            <input type="radio" name="user_sex" value=0 />女<br/><br/>
            </c:if>
        </div>
        <div class="information_introduce">
            个人简介<br/><br/>
            <textarea class="introduce_area" maxlength="35" name="user_introduce" >${sessionScope.registerUser.RUIntroduction }</textarea><br/><br/>
        </div>
        <div class="information_btn">
            <input class="btn" type="submit" value="修改">
        </div>
        <div class="error">
            ${msg}
        </div>
    </form>
</div>
</body>
</html>