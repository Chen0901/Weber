<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en" class="no-js">
<head>
<meta charset="utf-8">
<title>用户登录页</title>
<!-- CSS -->
<link rel="shortcut icon" href="images/logo.ico" type="image/x-icon" />
<link rel="icon" href="images/logo.ico" type="image/x-icon" />
<link rel="stylesheet" href="css/userlogin.css">
<script src="js/jquery-1.7.2.min.js"></script>
<script>
	$(document).ready(function() {

		$(".form").slideDown(500);

		$("#landing").addClass("border-btn");

		$("#registered").click(function() {
			$("#landing").removeClass("border-btn");
			$("#landing-content").hide(500);
			$(this).addClass("border-btn");
			$("#registered-content").show(500);

		})

		$("#landing").click(function() {
			$("#registered").removeClass("border-btn");
			$(this).addClass("border-btn");

			$("#landing-content").show(500);
			$("#registered-content").hide(500);
		})
	});
</script>
<script type="text/javascript">
	//创建XMLHttpRequest对象
	var xmlHttp;
	var res;
	function createXmlHttpRequest() {
		try {
			//非IE内核时
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			//IE内核
			try {
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("您的浏览器不支持ajax");
				}
			}
		}
	}
	//调用方法创建xmlhttpRequest对象
	createXmlHttpRequest();

	//登录按钮点击 验证注册名是否存在 两次密码是否一致
	function register() {
		var RUName = $.trim($("#Name").val());
		var RUPassWord = $.trim($("#Password").val());
		if (RUName == "") {
			alert("用户名不得为空！");
			return;
		}
		var regx =  /^[\u4E00-\u9FFF-\a-\z\A-\Z0-9]+$/;
		if (!regx.test(RUName)) {
			alert("不能有标点符号！");
			return;
		}
		if (RUPassWord.length < 6) {
			alert("密码不得少于6位！");
			return;
		}
		if (res == "no") {
			alert("此用户名已存在！");
		} else if (res == "yes") {
			$.ajax({
				url : "addRegisterUser.do",
				data : "RUName=" + RUName + "&RUPassWord=" + RUPassWord,
				type : "POST",
				dataType : "text",
				success : function(data) {
					if (data == 1) {
						//success
						alert("注册成功，请登录！");
						window.location.href="LoginRegisterUser.jsp";
					} else {
						//flase
						alert("注册失败，请重新注册！");
					}
				},
				error : function() {
					alert("ajax失败");
				}
			});
		}
	}

	//查询是否存在用户
	function test1() {
		var RUName = $.trim($("#Name").val());
		if (RUName == "") {
			alert("用户名不得为空！");
			return;
		}
		$.ajax({
			url : "selectRegisterUserByRUName.do",
			data : "RUName=" + RUName,
			type : "POST",
			dataType : "text",
			success : function(data) {
				if (data == 1) {
					//success
					res = "yes";
				} else {
					//flase
					res = "no";
				}
			},
			error : function() {
				alert("ajax失败");
			}
		});
	}

	//登录
	function login() {
		document.getElementById("signup_form").submit();
	}
</script>
</head>
<body>
	<div class="form">
		<div id="landing">登录</div>
		<div id="registered">注册</div>
		<div class="fix"></div>
		<div id="landing-content">
			<div align="center">
				<img src="images/loginlogo.png" />
			</div>
			<form action="loginRegisterUser.do" method="post" id="signup_form">
				<div class="inp">
					<input type="text" name="rName" placeholder="用户名" />
				</div>
				<div class="inp">
					<input type="password" name="rPassword" placeholder="密码" />
				</div>
				<div class="login">
					<a href="#" onclick="login()">登录</a>
				</div>
			</form>
			<div id="bottom">
				<div align="center">
					<a href="VisitorIndex.jsp">热门广场</a>
				</div>
			</div>
		</div>
		<div id="registered-content">
			<div align="center">
				<img src="images/loginlogo.png" />
			</div>
			<div class="inp">
				<input type="text" id="Name" name="RUName" placeholder="请输入用户名"
					maxlength="10" onblur="test1()" />
			</div>
			<div class="inp">
				<input type="password" id="Password" name="RUPassWord"
					placeholder="请输入密码" maxlength="20" />
			</div>
			<div class="login">
				<a href="#" onclick="register()">立即注册</a>
			</div>
			<div id="bottom">
				<div align="center">
					<a href="VisitorIndex.jsp">热门广场</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>