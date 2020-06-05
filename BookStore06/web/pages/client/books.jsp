<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 韩总
  Date: 2020/2/24
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%--将base.jsp页面中的base标签、link标签、script标签包含进来--%>
    <%@include file="/WEB-INF/include/base.jsp"%>
    <script>
        $(function () {
            //给加入到购物车的按钮绑定单击事件
            $(".addBook").click(function () {
                //获取要添加到购物车的图书的id
                var bookId = $(this).attr("id");
                //向CartServlet发送请求将图书添加到购物车中
                window.location.href="CartServlet?methodName=addBook2Cart&bookId="+bookId;
            });
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif" >
    <span class="wel_word">网上书城</span>
    <%@ include file="/WEB-INF/include/welcome.jsp"%>
</div>

<div id="main">

    <c:if test="${empty requestScope.page.list}">
        <br><br><br><br><br><br><br><br><br>
        <h1 align="center">没有任何图书</h1>
    </c:if>
    <c:if test="${!empty requestScope.page.list}">
    <div id="book">
        <div class="book_cond">
            <form action="BookClientServlet?methodName=getPageBooksAndPrice" method="post">
             价格：<input type="text" name="minPrice"> 元 -
                <input type="text" name="maxPrice"> 元 <button>查询</button>
            </form>
        </div>
        <div style="text-align: center">
            <c:if test="${empty sessionScope.cart.cartItems}">
                <span>您的购物车空空如也！</span>
            </c:if>
            <c:if test="${not empty sessionScope.cart.cartItems}">
                <span>您的购物车中有${sessionScope.cart.totalCount}件商品</span>
            </c:if>
            <c:if test="${!empty sessionScope.msg}">
                <div>
                   ${sessionScope.msg}
                    <c:remove var="msg"></c:remove>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.bookTitle}">
            <div>
                您刚刚将<span style="color: red">${sessionScope.bookTitle}</span>加入到了购物车中
                <c:remove var="bookTitle"></c:remove>
            </div>
            </c:if>
        </div>
        <c:forEach items="${requestScope.page.list}" var="book">
            <div class="b_list">
                <div class="img_div">
                    <img class="book_img" alt="" src="${book.imgPath}" />
                </div>
                <div class="book_info">
                    <div class="book_name">
                        <span class="sp1">书名:</span>
                        <span class="sp2">${book.title}</span>
                    </div>
                    <div class="book_author">
                        <span class="sp1">作者:</span>
                        <span class="sp2">${book.author}</span>
                    </div>
                    <div class="book_price">
                        <span class="sp1">价格:</span>
                        <span class="sp2">￥${book.price}</span>
                    </div>
                    <div class="book_sales">
                        <span class="sp1">销量:</span>
                        <span class="sp2">${book.sales}</span>
                    </div>
                    <div class="book_amount">
                        <span class="sp1">库存:</span>
                        <span class="sp2">${book.stock}</span>
                    </div>
                    <div class="book_add">
                        <c:if test="${book.stock == 0}">
                            <span style="color: red">小二拼命补货中...</span>
                        </c:if>
                        <c:if test="${book.stock != 0}">
                            <button id="${book.id}" class="addBook">加入购物车</button>
                            <%--                        <a href="">加入购物车</a>--%>
                        </c:if>
                    </div>
                </div>
            </div>
        </c:forEach>
        </c:if>
    </div>
</div>
<div id="page_nav">
    <%--
       1.当总页数小于5时
       2.总页数大于等于5，当前页小于等于3时
       3.总页数大于等于5，当前页大于3时
   --%>
    <c:choose>
        <c:when test="${requestScope.page.totalPageNo < 5}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="${requestScope.page.totalPageNo}"></c:set>
        </c:when>
        <c:when test="${requestScope.page.pageNo <= 3}">
            <c:set var="begin" value="1"></c:set>
            <c:set var="end" value="5"></c:set>
        </c:when>
        <c:otherwise>
            <c:set var="begin" value="${requestScope.page.pageNo - 2}"></c:set>
            <c:set var="end" value="${requestScope.page.pageNo + 2}"></c:set>
            <c:if test="${requestScope.page.pageNo + 2 > requestScope.page.totalPageNo}">
                <c:set var="begin" value="${requestScope.page.totalPageNo - 4}"></c:set>
                <c:set var="end" value="${requestScope.page.totalPageNo}"></c:set>
            </c:if>
        </c:otherwise>
    </c:choose>
    <c:if test="${requestScope.page.pageNo != 1}">
        <a href="BookClientServlet?methodName=getPageBooksAndPrice&minPrice=${minPrice}&maxPrice=${maxPrice}">首页</a>
        <a href="BookClientServlet?methodName=getPageBooksAndPrice&pageNo=${requestScope.page.pageNo - 1}&minPrice=${minPrice}&maxPrice=${maxPrice}">上一页</a>
    </c:if>
    <c:forEach begin="${begin}" end="${end}" var="index">
        <c:if test="${requestScope.page.pageNo == index}">
            <a style="color: red" href="BookClientServlet?methodName=getPageBooksAndPrice&pageNo=${index}&minPrice=${minPrice}&maxPrice=${maxPrice}">${index}</a>
        </c:if>
        <c:if test="${requestScope.page.pageNo != index}">
            <a href="BookClientServlet?methodName=getPageBooksAndPrice&pageNo=${index}&minPrice=${minPrice}&maxPrice=${maxPrice}">${index}</a>
        </c:if>
    </c:forEach>
    <c:if test="${requestScope.page.pageNo < requestScope.page.totalPageNo}">
        <a href="BookClientServlet?methodName=getPageBooksAndPrice&pageNo=${requestScope.page.pageNo + 1}&minPrice=${minPrice}&maxPrice=${maxPrice}">下一页</a>
        <a href="BookClientServlet?methodName=getPageBooksAndPrice&pageNo=${requestScope.page.totalPageNo}&minPrice=${minPrice}&maxPrice=${maxPrice}">末页</a>
    </c:if>
    共${requestScope.page.totalPageNo}页，${requestScope.page.totalRecord}条记录 到第<input value="${requestScope.page.pageNo}" name="pn" id="pn_input"/>页
    <input type="button" value="确定" id="sub">
    <script>
        $("#sub").click(function () {
            //获取输入的页码
            var pageNo = $("#pn_input").val();
            //跳转到指定的页码
            window.location.href = "BookClientServlet?methodName=getPageBooksAndPrice&pageNo="+pageNo+"&minPrice=${minPrice}&maxPrice=${maxPrice}";
        });
    </script>
</div>

<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
</div>
</body>
</html>