<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/myfollow.css"/>
<title>Insert title here</title>
</head>
<body style="background-color: #eeeeee">
	<c:forEach items="${InfoList}" var="il">
		<div class="record">
			<div class="record_title">
				<a href="selectInfoById.do?RUId=${il.RUId }" target="right"> ${il.RUName } </a>
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
				<a href="deleteInformation.do?IId=${il.IId }&path=${il.IPicpath}" target="right">删除</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="selectView.do?IId=${il.IId }"> 评论 </a> &nbsp;&nbsp;|&nbsp;&nbsp;
				点赞(${il.good })&nbsp;&nbsp;|&nbsp;&nbsp;
				踩(${il.bad })
			</div>
		</div>
	</c:forEach>
	<div class="record_num">
		共&nbsp;&nbsp;${totalsize }&nbsp;&nbsp;条记录，共&nbsp;&nbsp;${totalpage }&nbsp;&nbsp;页
		&nbsp;&nbsp;
		<c:if test="${totalpage>1&&currentPage==1 }">
			<a href="selectInfoForSelf.do?currentPage=1">首页</a>&nbsp;&nbsp;
			<a href="selectInfoForSelf.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
			<a href="selectInfoForSelf.do?currentPage=${totalpage }">尾页</a>
		</c:if>
		<c:if test="${totalpage>1&&currentPage==totalpage }">
			<a href="selectInfoForSelf.do?currentPage=1">首页</a>&nbsp;&nbsp;
			<a href="selectInfoForSelf.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
			<a href="selectInfoForSelf.do?currentPage=${totalpage }">尾页</a>
		</c:if>
		<c:if test="${totalpage>1&&currentPage>1&&currentPage<totalpage }">
			<a href="selectInfoForSelf.do?currentPage=1">首页</a>&nbsp;&nbsp;
			<a href="selectInfoForSelf.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
			<a href="selectInfoForSelf.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
			<a href="selectInfoForSelf.do?currentPage=${totalpage }">尾页</a>
		</c:if>
		&nbsp;&nbsp; 当前页为：第&nbsp;&nbsp;${currentPage }&nbsp;&nbsp;页
	</div>
</body>
</html>