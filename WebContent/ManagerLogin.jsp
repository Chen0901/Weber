<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">

    <head>

        <meta charset="utf-8">
        <title>后台登录页</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="description" content="">
        <meta name="author" content="">

        <!-- CSS -->
        <link rel="stylesheet" href="css/reset.css">
        <link rel="stylesheet" href="css/supersized.css">
        <link rel="stylesheet" href="css/style.css">
        
    <body>
        <div class="page-container">
            <img src="images/logo.png">
            <form action="managerLogin.do" method="post">
                <input type="text" name="managerName" class="username" placeholder="请输入您的用户名">
                <input type="password" name="managerPassword" class="password" placeholder="请输入您的用户密码">
                <br/><br/>
            	<span>${msg }</span>
                <button type="submit" class="submit_button">登录</button>
                <div class="error"><span>Null Input</span></div>
            </form>
        </div>
		<!-- Javascript -->
        <script src="js/jquery-1.7.2.min.js" ></script>
        <script src="js/supersized.3.2.7.min.js" ></script>
        <script src="js/supersized-init.js" ></script>
        <script src="js/scripts.js" ></script>
    </body>
</html>