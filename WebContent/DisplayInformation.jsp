<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv=content-type content="text/html; charset=utf-8" />
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
</script>
</head>
<body style="background-color: #eeeeee">
	<c:if test="${sessionScope.registerUser!=null&&pagingMode==3 }">
		<div class="publish">
			<form enctype="multipart/form-data" action="addpublishInformation.do"
				method="post">
				<div class="from-img">
					<div class="add_ico">
						<img src="images/ic_pic.png" title="请点击右侧按钮添加图片">
					</div>
					<div class="add_pic">
						图片：<input class="add_file" type="file" name="message_img" /> 
						<label class="info_lab">${msg}</label>
					</div>
				</div>
				<textarea class="area" name="IContent" maxlength="140"></textarea>

				<div class="lab">
					<div class="lab_ico">
						<img src="images/ic_lab.png" title="请在右侧选择标签">
					</div>
					<div class="lab_content">
						标签：
						<c:forEach items="${kind }" var="k" varStatus="status">
							<c:if test="${status.count==1 }">
								<input type="radio" name="KId" value="${k.KId }"
									checked="checked" />${k.KName}&nbsp;&nbsp;&nbsp;
						</c:if>
							<c:if test="${status.count>1 }">
								<input type="radio" name="KId" value="${k.KId }" />${k.KName}&nbsp;&nbsp;&nbsp;
						</c:if>
						</c:forEach>
					</div>
					<div class="publish_btn">
						<input class="btn" type="submit" value="发布" />
					</div>
				</div>
			</form>
		</div>
	</c:if>
	<c:forEach items="${InfoList}" var="il">
		<div class="record">
			<div class="record_title">
				<a href="selectInfoById.do?RUId=${il.RUId }" target="right">
					${il.RUName } </a>
			</div>
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
		<c:if test="${pagingMode==0 }">
			<c:if test="${totalpage>1&&currentPage==1 }">
				<a href="selectInfoForUser.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="selectInfoForUser.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
				<a href="selectInfoForUser.do?currentPage=${totalpage }">尾页</a>
			</c:if>
			<c:if test="${totalpage>1&&currentPage==totalpage }">
				<a href="selectInfoForUser.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="selectInfoForUser.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
				<a href="selectInfoForUser.do?currentPage=${totalpage }">尾页</a>
			</c:if>
			<c:if test="${totalpage>1&&currentPage>1&&currentPage<totalpage }">
				<a href="selectInfoForUser.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="selectInfoForUser.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
				<a href="selectInfoForUser.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
				<a href="selectInfoForUser.do?currentPage=${totalpage }">尾页</a>
			</c:if>
		</c:if>
		<c:if test="${pagingMode==1 }">
			<c:if test="${totalpage>1&&currentPage==1 }">
				<a href="selectInfoByKind.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="selectInfoByKind.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
				<a href="selectInfoByKind.do?currentPage=${totalpage }">尾页</a>
			</c:if>
			<c:if test="${totalpage>1&&currentPage==totalpage }">
				<a href="selectInfoByKind.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="selectInfoByKind.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
				<a href="selectInfoByKind.do?currentPage=${totalpage }">尾页</a>
			</c:if>
			<c:if test="${totalpage>1&&currentPage>1&&currentPage<totalpage }">
				<a href="selectInfoByKind.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="selectInfoByKind.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
				<a href="selectInfoByKind.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
				<a href="selectInfoByKind.do?currentPage=${totalpage }">尾页</a>
			</c:if>
		</c:if>
		<c:if test="${pagingMode==2 }">
			<c:if test="${totalpage>1&&currentPage==1 }">
				<a href="searchInfoForUser.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="searchInfoForUser.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
				<a href="searchInfoForUser.do?currentPage=${totalpage }">尾页</a>
			</c:if>
			<c:if test="${totalpage>1&&currentPage==totalpage }">
				<a href="searchInfoForUser.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="searchInfoForUser.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
				<a href="searchInfoForUser.do?currentPage=${totalpage }">尾页</a>
			</c:if>
			<c:if test="${totalpage>1&&currentPage>1&&currentPage<totalpage }">
				<a href="searchInfoForUser.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="searchInfoForUser.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
				<a href="searchInfoForUser.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
				<a href="searchInfoForUser.do?currentPage=${totalpage }">尾页</a>
			</c:if>
		</c:if>
		<c:if test="${pagingMode==3 }">
			<c:if test="${totalpage>1&&currentPage==1 }">
				<a href="selectInfoForRegister.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="selectInfoForRegister.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
				<a href="selectInfoForRegister.do?currentPage=${totalpage }">尾页</a>
			</c:if>
			<c:if test="${totalpage>1&&currentPage==totalpage }">
				<a href="selectInfoForRegister.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="selectInfoForRegister.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
				<a href="selectInfoForRegister.do?currentPage=${totalpage }">尾页</a>
			</c:if>
			<c:if test="${totalpage>1&&currentPage>1&&currentPage<totalpage }">
				<a href="selectInfoForRegister.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="selectInfoForRegister.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
				<a href="selectInfoForRegister.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
				<a href="selectInfoForRegister.do?currentPage=${totalpage }">尾页</a>
			</c:if>
		</c:if>
		&nbsp;&nbsp; 当前页为：第&nbsp;&nbsp;${currentPage }&nbsp;&nbsp;页
	</div>
</body>
</html>