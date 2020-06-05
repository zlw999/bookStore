<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%--将base.jsp页面中的base标签、link标签、script标签包含进来--%>
	<%@include file="/WEB-INF/include/base.jsp"%>
	<script type="text/javascript">
		$(function () {
			//给所有删除的超链接绑定单击事件
			$(".delBook").click(function () {
				//获取要删除的图书的书名
				// var title = $(this).parents("tr").children(":first").text();
				var title = $(this).attr("id");
				// var flag = confirm("确定要删除《"+title+"》这本图书吗？");
				// if(!flag){
				// 	return false;
				// }
				return confirm("确定要删除《"+title+"》这本图书吗？");
			});
		});
	</script>
</head>
<body>

	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
			<%@ include file="/WEB-INF/include/manager.jsp"%>
	</div>
	
	<div id="main">
		<c:if test="${empty requestScope.page.list}">
			<br><br><br><br><br><br><br><br><br>
			<h1 align="center">没有任何图书</h1>
		</c:if>
		<c:if test="${not empty requestScope.page.list}">
			<table>
				<tr>
					<td>名称</td>
					<td>价格</td>
					<td>作者</td>
					<td>销量</td>
					<td>库存</td>
					<td colspan="2">操作</td>
				</tr>

			<c:forEach items="${requestScope.page.list}" var="book">
				<tr>
					<td>${book.title}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.sales}</td>
					<td>${book.stock}</td>
					<td><a href="BookManagerServlet?methodName=getBookById&bookId=${book.id}">修改</a></td>
					<td><a id="${book.title}" class="delBook" href="BookManagerServlet?methodName=deleteBook&bookId=${book.id}">删除</a></td>
				</tr>
			</c:forEach>
				<tr>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td></td>
					<td><a href="pages/manager/book_edit.jsp">添加图书</a></td>
				</tr>
			</table>
		</c:if>
		<div id="page_nav">
			<c:if test="${requestScope.page.pageNo != 1}">
				<a href="BookManagerServlet?methodName=getPageBooks">首页</a>
				<a href="BookManagerServlet?methodName=getPageBooks&pageNo=${requestScope.page.pageNo - 1}">上一页</a>
			</c:if>
			<c:forEach begin="1" end="${requestScope.page.totalPageNo}" var="index">
				<c:if test="${requestScope.page.pageNo == index}">
					<a style="color: red" href="BookManagerServlet?methodName=getPageBooks&pageNo=${index}">${index}</a>
				</c:if>
				<c:if test="${requestScope.page.pageNo != index}">
					<a href="BookManagerServlet?methodName=getPageBooks&pageNo=${index}">${index}</a>
				</c:if>
			</c:forEach>
			<c:if test="${requestScope.page.pageNo < requestScope.page.totalPageNo}">
				<a href="BookManagerServlet?methodName=getPageBooks&pageNo=${requestScope.page.pageNo + 1}">下一页</a>
				<a href="BookManagerServlet?methodName=getPageBooks&pageNo=${requestScope.page.totalPageNo}">末页</a>
			</c:if>
			共${requestScope.page.totalPageNo}页，${requestScope.page.totalRecord}条记录 到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
			<input type="button" value="确定" id="sub">
			<script>
				$("#sub").click(function () {
					//获取输入的页码
					var pageNo = $("#pn_input").val();
					//跳转到指定的页码
					window.location.href = "BookManagerServlet?methodName=getPageBooks&pageNo="+pageNo;
				});
			</script>
		</div>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>