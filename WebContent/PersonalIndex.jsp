<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>他人主页</title>
<link rel="stylesheet" href="css/myfollow.css" />
<script type="text/javascript">
	//创建XMLHttpRequest对象
	function create() {
		if (window.XMLHttpRequest) {
			xmlHttp = new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			xmlHttp = new ActiveXObject("Microsoft.XMLHTTP")
		}
	}
	/* 点赞*/
	function checkG(iid) {
		runCheckG("selectGBInfoById.do?IId=" + iid);
	}
	function runCheckG(url) {
		create();
		xmlHttp.open("POST", url, true);
		xmlHttp.onreadystatechange = callbackCheckG;
		xmlHttp.send();
	}
	function callbackCheckG() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var checkRes = xmlHttp.responseText;
				if (checkRes == 0) {
					return;
				} else if (checkRes == "LoginRegisterUser") {
					alert("请先登录");
				} else {
					addGood(checkRes);
				}
			} else {
				alert("检查点赞AJAX服务器返回错误！");
			}
		}
	}
	function addGood(iid) {
		runGood("addGood.do?IId=" + iid);
	}
	function runGood(url) {
		create();
		xmlHttp.open("POST", url, true);
		xmlHttp.onreadystatechange = callbackAddG;
		xmlHttp.send();
	}
	function callbackAddG() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var checkRes = xmlHttp.responseText;
				if (checkRes == 0) {
					return;
				} else if (checkRes == "LoginRegisterUser") {
					alert("请先登录");
				} else {
					var good = Number(document.getElementById("g" + checkRes).innerHTML) + 1;
					document.getElementById("g" + checkRes).innerHTML = good;
				}
			} else {
				alert("点赞AJAX服务器返回错误！");
			}
		}
	}
	/*上面是点赞*/

	/*点踩*/
	function checkB(iid) {
		runCheckB("selectGBInfoById.do?IId=" + iid);
	}
	function runCheckB(url) {
		create();
		xmlHttp.open("POST", url, true);
		xmlHttp.onreadystatechange = callbackCheckB;
		xmlHttp.send();
	}
	function callbackCheckB() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var checkRes = xmlHttp.responseText;
				if (checkRes == 0) {
					return;
				} else if (checkRes == "LoginRegisterUser") {
					alert("请先登录");
				} else {
					addBad(checkRes);
				}
			} else {
				alert("检查点踩AJAX服务器返回错误！");
			}
		}
	}
	function addBad(iid) {
		runBad("addBad.do?IId=" + iid);
	}
	function runBad(url) {
		create();
		xmlHttp.open("POST", url, true);
		xmlHttp.onreadystatechange = callbackAddB;
		xmlHttp.send();
	}
	function callbackAddB() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var checkRes = xmlHttp.responseText;
				if (checkRes == 0) {
					return;
				} else if (checkRes == "LoginRegisterUser") {
					alert("请先登录");
				} else {
					var bad = Number(document.getElementById("b" + checkRes).innerHTML) + 1;
					document.getElementById("b" + checkRes).innerHTML = bad;
				}
			} else {
				alert("点踩AJAX服务器返回错误！");
			}
		}
	}
	/* 上面是点踩 */

	/* 检测是否已经收藏 */
	function checkCollect(iid) {
		runCheck("selectCollectionInfoById.do?IId=" + iid);
	}
	function runCheck(url) {
		create();
		xmlHttp.open("POST", url, true);
		xmlHttp.onreadystatechange = callbackCheck;
		xmlHttp.send();
	}
	function callbackCheck() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var checkRes = xmlHttp.responseText;
				if (checkRes == 0) {
					alert("您已收藏过此信息！");
				} else if (checkRes == "LoginRegisterUser") {
					alert("收藏请先登录");
				} else {
					collect(checkRes);
				}
			} else {
				alert("检测收藏AJAX服务器返回错误！");
			}
		}
	}

	/* 收藏 */
	function collect(iid) {
		runCollect("addCollectionInformation.do?IId=" + iid);
	}
	function runCollect(url) {
		create();
		xmlHttp.open("POST", url, true);
		xmlHttp.onreadystatechange = callbackCollect;
		xmlHttp.send();
	}
	function callbackCollect() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var checkRes = xmlHttp.responseText;
				if (checkRes == 0) {
					alert("收藏失败，请稍后再试！");
				} else {
					alert("收藏成功！");
				}
			} else {
				alert("收藏AJAX服务器返回错误！");
			}
		}
	}

	//关注
	function follow(RUId) {
		runFollow("addFollowUser.do?EUId=" + RUId);
	}
	function runFollow(url) {
		create();
		xmlHttp.open("POST", url, true);
		xmlHttp.onreadystatechange = callbackFollow;
		xmlHttp.send();
	}
	function callbackFollow() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				var checkRes = xmlHttp.responseText;
				if (checkRes == 0) {
					alert("关注失败，请稍后再试！");
				} else if (checkRes == "LoginRegisterUser") {
					alert("关注请先登录");
				} else {
					document.getElementById("followInfo").innerHTML = "取消关注";
				}
			} else {
				alert("AJAX服务器返回错误！");
			}
		}
	}

	//取关
	function deleteFollow(RUId) {
		runDeleteFollow("deleteFollowUser.do?EUId=" + RUId);
	}
	function runDeleteFollow(url) {
		create();
		xmlHttp.open("POST", url, true);
		xmlHttp.onreadystatechange = callbackDeleteFollow;
		xmlHttp.send();
	}
	function callbackDeleteFollow() {
		if (xmlHttp.readyState == 4) {
			if (xmlHttp.status == 200) {
				document.getElementById("followInfo").innerHTML = "点击关注";
			} else {
				alert("AJAX服务器返回错误！");
			}
		}
	}
</script>
</head>
<body style="background-color: #eeeeee">
	<div class="follower_info">
		<div class="follower_name">
			${user.RUName}
			<c:if test="${user.RUSex==1 }">
				<img src="images/ic_sex0.png">
			</c:if>
			<c:if test="${user.RUSex==0 }">
				<img src="images/ic_sex1.png">
			</c:if>
		</div>
		<div class="follower_introduce">简介 ${user.RUIntroduction}</div>
		<div class="follower_follow">
			<c:if test="${user.RUName!=sessionScope.registerUser.RUName }">
				<c:if test="${msg=='点击关注' }">
					<a href="javascript:follow(${user.RUId})"> <span
						id="followInfo">${msg }</span>
					</a>
				</c:if>
			</c:if>
			<c:if test="${msg=='取消关注' }">
				<a href="javascript:deleteFollow(${user.RUId})"> <span
					id="followInfo">${msg }</span>
				</a>
			</c:if>
		</div>
	</div>
	<c:forEach items="${InfoList}" var="il">
		<div class="record">
			<div class="record_title">${il.RUName }</div>
			<div class="record_date"><fmt:formatDate value="${il.ITime }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
			<div class="record_content">${il.IContent }</div>
			<div class="record_pic">
				<c:if test="${il.IPicpath!=null }">
					<img src="upload/S${il.IPicpath}"
						onmouseover="this.src='upload/B${il.IPicpath}'"
						onmouseout="this.src='upload/S${il.IPicpath}'"
						style="cursor: pointer;" />
				</c:if>
			</div>
			<div class="record_response">
				<c:if test="${il.RUId!=sessionScope.registerUser.RUId }">
					<a href="javascript:checkCollect(${il.IId})">收藏</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				</c:if>
				<c:if test="${sessionScope.registerUser!=null}">
					<a href="selectView.do?IId=${il.IId }"> 评论 </a> &nbsp;&nbsp;|&nbsp;&nbsp;
				</c:if>
				<a href="javascript:checkG(${il.IId})"> 点赞(<span
					id="g${il.IId }">${il.good }</span>)
				</a> &nbsp;&nbsp;|&nbsp;&nbsp; <a href="javascript:checkB(${il.IId})">
					踩(<span id="b${il.IId }">${il.bad }</span>)
				</a>
			</div>
		</div>
	</c:forEach>
	<div class="record_num">
		共&nbsp;&nbsp;${totalsize }&nbsp;&nbsp;条记录，共&nbsp;&nbsp;${totalpage }&nbsp;&nbsp;页
		&nbsp;&nbsp;
		<c:if test="${totalpage>1&&currentPage==1 }">
			<a href="selectInfoById.do?currentPage=1">首页</a>&nbsp;&nbsp;
			<a href="selectInfoById.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
			<a href="selectInfoById.do?currentPage=${totalpage }">尾页</a>
		</c:if>
		<c:if test="${totalpage>1&&currentPage==totalpage }">
			<a href="selectInfoById.do?currentPage=1">首页</a>&nbsp;&nbsp;
			<a href="selectInfoById.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
			<a href="selectInfoById.do?currentPage=${totalpage }">尾页</a>
		</c:if>
		<c:if test="${totalpage>1&&currentPage>1&&currentPage<totalpage }">
			<a href="selectInfoById.do?currentPage=1">首页</a>&nbsp;&nbsp;
			<a href="selectInfoById.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
			<a href="selectInfoById.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
			<a href="selectInfoById.do?currentPage=${totalpage }">尾页</a>
		</c:if>
		&nbsp;&nbsp; 当前页为：第&nbsp;&nbsp;${currentPage }&nbsp;&nbsp;页
	</div>
</body>
</html>