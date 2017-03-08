<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/comment.css" />
<script src="js/jquery-1.7.2.min.js"></script>
<title>Insert title here</title>
<script type="text/javascript">
	function publish(){
		var content = $.trim($("#vcontent").val());
		if(content==""){
			alert("评论内容不得为空！")
			return false;
		}else{
			return true;
		}
	}
</script>
</head>
<body style="background-color: #eeeeee">
	<div class="record">
		<div class="record_title">
			<a href="selectInfoById.do?RUId=${info.RUId }" target="right">
				${info.RUName } </a>
		</div>
		<div class="record_date"><fmt:formatDate value="${info.ITime }" pattern="yyyy-MM-dd HH:mm:ss"/></div>
		<div class="record_content">${info.IContent }</div>
		<div class="record_pic">
			<c:if test="${info.IPicpath!=null }">
					<img src="upload/S${info.IPicpath}"
						onmouseover="this.src='upload/B${info.IPicpath}'"
						onmouseout="this.src='upload/S${info.IPicpath}'"
						style="cursor: pointer;" />
			</c:if>
		</div>
	</div>
	<div class="comment">
        <form action="addView.do" method="post" onsubmit="return publish()">
            <div class="comment_content">
            	<input type="hidden" name="IId" value="${info.IId }" />
                <input class="content" id="vcontent" type="text" name="VContent" maxlength="140" placeholder="请务必输入评论内容，并在140字以内">
            </div>
            <div class="comment_btn">
                <input class="btn" type="submit" value="评论">
            </div>
        </form>
    </div>
    <c:forEach items="${vlist }" var="v">
    <div class="comment_info">
        <div class="commenter">
            <a href="selectInfoById.do?RUId=${v.RUId }" target="right">
                ${v.RUName}
            </a>
             	&nbsp;：&nbsp;${v.VContent }
        </div>
        <div class="comment_date">
            ${v.VTime }
        </div>
        <div class="comment_delete">
        <c:if test="${v.RUId==sessionScope.registerUser.RUId||info.RUId==sessionScope.registerUser.RUId }">
            <a href="deleteView.do?VId=${v.VId }&IId=${info.IId}">
                   	删除
            </a>
        </c:if>
        </div>
    </div>
    </c:forEach>
    <div class="record_num">
        共&nbsp;&nbsp;${totalsize }&nbsp;&nbsp;条记录，共&nbsp;&nbsp;${totalpage }&nbsp;&nbsp;页
		&nbsp;&nbsp;
		<c:if test="${totalpage>1&&currentPage==1 }">
			<a href="selectView.do?currentPage=1">首页</a>&nbsp;&nbsp;
			<a href="selectView.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
			<a href="selectView.do?currentPage=${totalpage }">尾页</a>
		</c:if>
		<c:if test="${totalpage>1&&currentPage==totalpage }">
			<a href="selectView.do?currentPage=1">首页</a>&nbsp;&nbsp;
			<a href="selectView.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
			<a href="selectView.do?currentPage=${totalpage }">尾页</a>
		</c:if>
		<c:if test="${totalpage>1&&currentPage>1&&currentPage<totalpage }">
			<a href="selectView.do?currentPage=1">首页</a>&nbsp;&nbsp;
			<a href="selectView.do?currentPage=${currentPage-1 }">上一页</a>&nbsp;&nbsp;
			<a href="selectView.do?currentPage=${currentPage+1 }">下一页</a>&nbsp;&nbsp;
			<a href="selectView.do?currentPage=${totalpage }">尾页</a>
		</c:if>
		&nbsp;&nbsp; 当前页为：第&nbsp;&nbsp;${currentPage }&nbsp;&nbsp;页
    </div>
</body>
</html>