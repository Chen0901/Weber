<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/myfollow.css" />
<title>Insert title here</title>
</head>
<body style="background-color: #eeeeee">
	<c:forEach items="${List}" var="user">
		<div class="follower_info">
			<div class="follower_name">
				<a href="selectInfoById.do?RUId=${user.RUId }" target="right">
					${user.RUName}
				</a>
				<c:if test="${user.RUSex==1 }">
					<img src="images/ic_sex0.png">
				</c:if>
				<c:if test="${user.RUSex==0 }">
					<img src="images/ic_sex1.png">
				</c:if>
			</div>
			<div class="follower_introduce">简介 ${user.RUIntroduction}</div>
		</div>
	</c:forEach>
	<div class="record_num">
		共&nbsp;&nbsp;${totalsize }&nbsp;&nbsp;条记录，共&nbsp;&nbsp;${totalpage }&nbsp;&nbsp;页
		&nbsp;&nbsp;
		<c:if test="${listPage==0 }">
			<c:if test="${totalpage>1&&currentPage==1 }">
				<a href="selectFollowUser.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="selectFollowUser.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
				<a href="selectFollowUser.do?currentPage=${totalpage }">尾页</a>
			</c:if>
			<c:if test="${totalpage>1&&currentPage==totalpage }">
				<a href="selectFollowUser.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="selectFollowUser.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
				<a href="selectFollowUser.do?currentPage=${totalpage }">尾页</a>
			</c:if>
			<c:if test="${totalpage>1&&currentPage>1&&currentPage<totalpage }">
				<a href="selectFollowUser.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="selectFollowUser.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
				<a href="selectFollowUser.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
				<a href="selectFollowUser.do?currentPage=${totalpage }">尾页</a>
			</c:if>
		</c:if>
		<c:if test="${listPage==1 }">
			<c:if test="${totalpage>1&&currentPage==1 }">
				<a href="selectMyFans.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="selectMyFans.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
				<a href="selectMyFans.do?currentPage=${totalpage }">尾页</a>
			</c:if>
			<c:if test="${totalpage>1&&currentPage==totalpage }">
				<a href="selectMyFans.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="selectMyFans.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
				<a href="selectMyFans.do?currentPage=${totalpage }">尾页</a>
			</c:if>
			<c:if test="${totalpage>1&&currentPage>1&&currentPage<totalpage }">
				<a href="selectMyFans.do?currentPage=1">首页</a>&nbsp;&nbsp;
				<a href="selectMyFans.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
				<a href="selectMyFans.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
				<a href="selectMyFans.do?currentPage=${totalpage }">尾页</a>
			</c:if>
		</c:if>
		&nbsp;&nbsp; 当前页为：第&nbsp;&nbsp;${currentPage }&nbsp;&nbsp;页
	</div>
</body>
</html>