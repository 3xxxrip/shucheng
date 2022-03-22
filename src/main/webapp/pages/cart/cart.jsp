<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>购物车</title>
	<%--	静态包含css样式，js路径，绝对路径base--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("#count").change(function () {
				var itemName=$(this).attr("itemName");
				var itemId=$(this).attr("itemId");
				var count=$(this).val()
				var check=confirm("确认修改《"+itemName+"》数量为"+$(this).val()+"吗?");
				if(check){
					//javaScript语言中提供了一个location地址栏对象
					//他有一个属性href，它可以取到浏览器中地址栏中的地址
					location.href="${pageContext.getAttribute("base")}cartServlet?action=updateCart&id="+itemId+"&count="+count;
				}
				else{
					//defaultValue属性是表单项dom对象的属性,它表示默认的value属性值,在这里就是value="$ {itemsEntry.value.count}"
					this.value=this.defaultValue;
				}
			})
			$("#deleteItem").click(function () {
				var itemName=$(this).attr("itemName");
				// confirm()会返回一个boolean值,你点击确定就会返回true
				return confirm("你确定要删除 《"+itemName+"》 吗?")
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">购物车</span>
			<div>
				<%@include file="/pages/common/login_success_menu.jsp"%>
			</div>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td>商品名称</td>
				<td>数量</td>
				<td>单价</td>
				<td>金额</td>
				<td>操作</td>
			</tr>
<%--			如果购物车为空--%>
			<c:if test="${empty sessionScope.cart.items}">
<%--				colspan是跨行的--%>
				<td colspan="5">你还没有添加任何商品哦!<a href="index.jsp">点击此处浏览商品!</a></td>
			</c:if>
	<%--			如果购物车不为空--%>
			<c:if test="${not empty sessionScope.cart.items}">

				<c:forEach items="${sessionScope.cart.items}" var="itemsEntry">
					<tr>
						<td>${itemsEntry.value.name}</td>
						<td>
							<input type="text" itemId="${itemsEntry.value.id}"
								   value="${itemsEntry.value.count}" style="width:80px"
								   id="count" itemName="${itemsEntry.value.name}"/>
						</td>
						<td>${itemsEntry.value.price}</td>
						<td>${itemsEntry.value.totalprice}</td>
						<td><a href="cartServlet?action=deleteItem&id=${itemsEntry.value.id}" id="deleteItem" itemName="${itemsEntry.value.name}"/>删除</td>
					</tr>
				</c:forEach>
			</c:if>

		</table>
		<c:if test="${not empty sessionScope.cart.items}">
			<div class="cart_info">
				<span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalcount}</span>件商品</span>
				<span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalprice}</span>元</span>
				<span class="cart_span"><a href="cartServlet?action=clearCart">清空购物车</a></span>
				<span class="cart_span"><a href="orderServlet?action=createOrder">去结账</a></span>
			</div>
		</c:if>
	</div>

	<%--		静态包含共有的页脚信息--%>
	<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>