<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--将base.jsp页面中的base标签、link标签、script标签包含进来--%>
	<%@include file="/WEB-INF/include/base.jsp"%>
	<script>
		$(function () {
			//给清空购物车的超链接绑定单击事件
			$("#emptCart").click(function () {
				return confirm("确定要清空购物车吗？三思啊！！！");
			});
			//给删除购物项的超链接绑定单击事件
			$(".deleteCartItem").click(function () {
				//获取要删除的购物项中图书的书名
				var title = $(this).attr("id");
				return confirm("确定要删除《"+title+"》这个购物项吗？");
			});
			//给输入图书数量的文本框绑定change事件
			$(".inputCount").change(function () {
				//获取文本框中默认的值
				var deftValue = this.defaultValue;
				//获取用户输入的图书的数量，即input的value属性值
				var inputCount = $(this).val();
				//获取当前购物项中图书的id
				var bookId = $(this).attr("id");
				//获取图书的库存
				var stock = $(this).attr("name");
				//将图书的库存转换为一个数
				// var bookStock = new Number(stock);
				var bookStock = parseInt(stock);

				//设置验证输入的值是非零的正整数的正则表达式
				var reg = /^\+?[1-9][0-9]*$/;
				if(!reg.test(inputCount)){
					alert("请输入非零的正整数!")
					//将当前文本框中的值设置为默认值
					this.value = deftValue;
					return false;
				}
				if(inputCount > bookStock){
					alert("该图书的库存只有"+bookStock+"本");
					//将当前文本框中的值设置为最大库存的值
					this.value = bookStock;
					//将当前文本框的默认值设置为最大库存
					this.defaultValue = bookStock;
					//将用户输入的数量设置为最大库存
					inputCount = bookStock;
				}else{
					//输入的数量是一个正整数，并且没有超库存
					//将当前文本框的默认值设置为当前值
					this.defaultValue = inputCount;
				}
				//发送请求更新购物项
				//window.location.href="CartServlet?methodName=updateCartItem&bookId="+bookId+"&bookCount="+inputCount;

				//设置请求地址
				var url = "CartServlet?methodName=updateCartItemByAjax";
				//设置请求参数
				var params = {"bookId":bookId,"bookCount":inputCount};
				//获取显示当前购物项中图书的金额小计的td标签
				var amountTd = $(this).parent().next().next();
				//发送Ajax请求
				$.post(url,params,function (res) {
					//获取购物车中图书的总数量
					var totalCount = res.totalCount;
					//设置到显示的span标签中
					$("#totalCount").text(totalCount);
					//获取购物车中图书的总金额
					var totalAmount = res.totalAmount;
					//设置到显示的span标签中
					$("#totalAmount").text(totalAmount);
					//获取当前购物项中的金额小计
					var amount = res.amount;
					//将金额小计设置到amountTd中
					amountTd.text(amount);
				},"json");
				});
				});
				</script>
				</head>
				<body>
				<div id="header">
						<img class="logo_img" alt="" src="static/img/logo.gif" >
						<span class="wel_word">购物车</span>
						<%@ include file="/WEB-INF/include/welcome.jsp"%>
						</div>

						<div id="main">
						<c:if test="${empty sessionScope.cart.cartItems}">
						<br><br><br><br><br><br><br><br><br>
						<h1 align="center">您的购物车已经饥渴难耐，快去<a style="color: red" href="index.jsp">购物</a>吧！</h1>
				</c:if>
				<c:if test="${!empty sessionScope.cart.cartItems}">
				<table>
				<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
				</tr>
				<c:forEach items="${sessionScope.cart.cartItems}" var="cartItem">
				<tr>
				<td>${cartItem.book.title}</td>
				<td>
					<input name="${cartItem.book.stock}" id="${cartItem.book.id}" class="inputCount" type="text" value="${cartItem.count}" style="width: 50px;text-align: center">
				</td>
					<td>${cartItem.book.price}</td>
				<td>${cartItem.amount}</td>
				<td><a id="${cartItem.book.title}" class="deleteCartItem" href="CartServlet?methodName=deleteCartItem&bookId=${cartItem.book.id}">删除</a></td>
			</tr>
		</c:forEach>
			
		</table>
		
		<div class="cart_info">
			<span class="cart_span">购物车中共有<span class="b_count" id="totalCount">${sessionScope.cart.totalCount}</span>件商品</span>
			<span class="cart_span">总金额<span class="b_price" id="totalAmount">${sessionScope.cart.totalAmount}</span>元</span>
			<span class="cart_span"><a href="index.jsp">继续购物</a></span>
			<span class="cart_span"><a href="CartServlet?methodName=emptyCart" id="emptCart">清空购物车</a></span>
			<span class="cart_span"><a href="OrderClientServlet?methodName=checkout">去结账</a></span>
		</div>
		</c:if>
	</div>
	
	<div id="bottom">
		<span>
			尚硅谷书城.Copyright &copy;2015
		</span>
	</div>
</body>
</html>