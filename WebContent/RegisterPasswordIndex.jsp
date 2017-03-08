<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/right.css" />
</head>
<body style="background-color: #eeeeee">
<div class="pwd_info">
    <form action="updatePassword.do" method="post">
        <div class="pwd">
            旧密码：<input type="password" id="old_pwd" name="old_pwd" placeholder="请输入旧密码" maxlength="20" /><br/><br/>
            新密码：<input type="password" id="new_pwd" name="new_pwd" placeholder="请输入新密码" maxlength="20" /><br/><br/>
           <input class="btn" type="submit" value="确认修改">
        </div>
    </form>
    <br/>
    <div class="error">
        ${pwd_error }
    </div>
</div>
</body>
</html>