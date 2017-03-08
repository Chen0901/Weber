<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查询结果页</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
</head>
<body>
	<jsp:include page="ManagerHeader.jsp" />
	<div align="center" id="content">
		<table width="80%" border="1" cellpadding="0" cellspacing="0">
			<c:if test="${indexNum==0 }">
				<caption><a href="selectAllRegisterUser.do">用户信息搜索结果</a></caption>
				<tr>
					<th>用户ID</th>
					<th>用户账号名</th>
					<th>性别</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${List }" var="s">
					<tr align="center">
						<td width="20%">${s.RUId }</td>
						<td width="20%">${s.RUName }</td>
						<c:if test="${s.RUSex==0 }">
							<td>女</td>
						</c:if>
						<c:if test="${s.RUSex==1 }">
							<td>男</td>
						</c:if>
						<td><a href="deleteRegisterUser.do?RUId=${s.RUId }">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>



			<c:if test="${indexNum==1 }">
				<caption><a href="selectAllKind.do">分类信息搜索结果</a></caption>
				<tr>
					<th>类别ID</th>
					<th>类别名称</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${List }" var="s">
					<tr align="center">
						<td width="10%">${s.KId }</td>
						<td>${s.KName }</td>
						<td><a href="UpdateKind.jsp?KId=${s.KId }&KName=${s.KName }">修改</a>&nbsp;&nbsp;
							<a href="deleteKind.do?KId=${s.KId }">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
			
			
			<c:if test="${indexNum==2 }">
				<caption><a href="selectAllInfo.do">发布信息搜索结果</a></caption>
				<tr>
					<th>ID</th>
					<th>发布内容</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${List }" var="s">
					<tr align="center">
						<td width="10%">${s.IId }</td>
						<td>${s.IContent }</td>
						<td><a href="deleteInfo.do?IId=${s.IId }">删除</a></td>
					</tr>
				</c:forEach>
			</c:if>
			
			
		</table>


		<table width="80%" border="1" cellpadding="0" cellspacing="0">
			<tr align="center">
				<td>共&nbsp;&nbsp;${totalsize }&nbsp;&nbsp;条记录，共&nbsp;&nbsp;${totalpage }&nbsp;&nbsp;页
					&nbsp;&nbsp;
					<c:if test="${indexNum==0 }">
						<c:if test="${totalpage>1&&currentPage==1 }">
							<a href="searchRegisterUser.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="searchRegisterUser.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
							<a href="searchRegisterUser.do?currentPage=${totalpage }">尾页</a>
						</c:if>
						<c:if test="${totalpage>1&&currentPage==totalpage }">
							<a href="searchRegisterUser.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="searchRegisterUser.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
							<a href="searchRegisterUser.do?currentPage=${totalpage }">尾页</a>
						</c:if>
						<c:if test="${totalpage>1&&currentPage>1&&currentPage<totalpage }">
							<a href="searchRegisterUser.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="searchRegisterUser.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
							<a href="searchRegisterUser.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
							<a href="searchRegisterUser.do?currentPage=${totalpage }">尾页</a>
						</c:if>
					</c:if>
					<c:if test="${indexNum==1 }">
						<c:if test="${totalpage>1&&currentPage==1 }">
							<a href="searchKind.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="searchKind.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
							<a href="searchKind.do?currentPage=${totalpage }">尾页</a>
						</c:if>
						<c:if test="${totalpage>1&&currentPage==totalpage }">
							<a href="searchKind.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="searchKind.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
							<a href="searchKind.do?currentPage=${totalpage }">尾页</a>
						</c:if>
						<c:if test="${totalpage>1&&currentPage>1&&currentPage<totalpage }">
							<a href="searchKind.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="searchKind.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
							<a href="searchKind.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
							<a href="searchKind.do?currentPage=${totalpage }">尾页</a>
						</c:if>
					</c:if>
					<c:if test="${indexNum==2 }">
						<c:if test="${totalpage>1&&currentPage==1 }">
							<a href="searchInfo.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="searchInfo.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
							<a href="searchInfo.do?currentPage=${totalpage }">尾页</a>
						</c:if>
						<c:if test="${totalpage>1&&currentPage==totalpage }">
							<a href="searchInfo.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="searchInfo.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
							<a href="searchInfo.do?currentPage=${totalpage }">尾页</a>
						</c:if>
						<c:if test="${totalpage>1&&currentPage>1&&currentPage<totalpage }">
							<a href="searchInfo.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="searchInfo.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
							<a href="searchInfo.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
							<a href="searchInfo.do?currentPage=${totalpage }">尾页</a>
						</c:if>
					</c:if>
					&nbsp;&nbsp; 当前页为：第&nbsp;&nbsp;${currentPage }&nbsp;&nbsp;页
				</td>
			</tr>
		</table>
	</div>
	<br />
	<div align="center" id="footer">Weber后台管理系统</div>
</body>
</html>