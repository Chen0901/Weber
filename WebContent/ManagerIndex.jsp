<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理员首页</title>
<link rel="stylesheet" type="text/css" href="css/main.css" />
</head>
<body>
	<jsp:include page="ManagerHeader.jsp" />
	<div align="center" id="content">
		<div align="center">
			<div id="search">
				<table width="80%">
					<tr align="center">
						<td>
							<form name="search" action="managerSearch.do" method="post">
								搜索 <input type="text" id="s" name="searchword"
									placeholder="请输入搜索内容(ID/名称)" /> <input type="hidden"
									name="indexNum" value="${indexNum }" /> <input type="submit"
									id="x" value="search" name="clickedButton" />&nbsp;&nbsp;&nbsp;&nbsp;
							</form>
						</td>
						<td><font color="red">${msg }</font></td>
						<c:if test="${indexNum==1 }">
							<td><a href="AddKind.jsp">单条添加数据</a></td>
						</c:if>
					</tr>
				</table>
			</div>
		</div>
		<table width="80%" border="1" cellpadding="0" cellspacing="0">

			<c:if test="${indexNum==0 }">
				<caption>用户信息</caption>
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
				<caption>分类信息</caption>
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
				<caption>发布信息</caption>
				<tr>
					<th>信息ID</th>
					<th>内容</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${List }" var="s">
					<tr >
						<td width="10%" align="center"><a href="#">${s.IId }</a></td>
						<td width="80%">${s.IContent }</td>
						<td align="center"><a href="deleteInfo.do?IId=${s.IId }">删除</a></td>
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
							<a href="selectAllRegisterUser.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="selectAllRegisterUser.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
							<a href="selectAllRegisterUser.do?currentPage=${totalpage }">尾页</a>
						</c:if>
						<c:if test="${totalpage>1&&currentPage==totalpage }">
							<a href="selectAllRegisterUser.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="selectAllRegisterUser.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
							<a href="selectAllRegisterUser.do?currentPage=${totalpage }">尾页</a>
						</c:if>
						<c:if test="${totalpage>1&&currentPage>1&&currentPage<totalpage }">
							<a href="selectAllRegisterUser.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="selectAllRegisterUser.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
							<a href="selectAllRegisterUser.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
							<a href="selectAllRegisterUser.do?currentPage=${totalpage }">尾页</a>
						</c:if>
					</c:if>
					<c:if test="${indexNum==1 }">
						<c:if test="${totalpage>1&&currentPage==1 }">
							<a href="selectAllKind.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="selectAllKind.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
							<a href="selectAllKind.do?currentPage=${totalpage }">尾页</a>
						</c:if>
						<c:if test="${totalpage>1&&currentPage==totalpage }">
							<a href="selectAllKind.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="selectAllKind.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
							<a href="selectAllKind.do?currentPage=${totalpage }">尾页</a>
						</c:if>
						<c:if test="${totalpage>1&&currentPage>1&&currentPage<totalpage }">
							<a href="selectAllKind.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="selectAllKind.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
							<a href="selectAllKind.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
							<a href="selectAllKind.do?currentPage=${totalpage }">尾页</a>
						</c:if>
					</c:if>
					<c:if test="${indexNum==2 }">
						<c:if test="${totalpage>1&&currentPage==1 }">
							<a href="selectAllInfo.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="selectAllInfo.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
							<a href="selectAllInfo.do?currentPage=${totalpage }">尾页</a>
						</c:if>
						<c:if test="${totalpage>1&&currentPage==totalpage }">
							<a href="selectAllInfo.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="selectAllInfo.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
							<a href="selectAllInfo.do?currentPage=${totalpage }">尾页</a>
						</c:if>
						<c:if test="${totalpage>1&&currentPage>1&&currentPage<totalpage }">
							<a href="selectAllInfo.do?currentPage=1">首页</a>&nbsp;&nbsp;
							<a href="selectAllInfo.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
							<a href="selectAllInfo.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
							<a href="selectAllInfo.do?currentPage=${totalpage }">尾页</a>
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