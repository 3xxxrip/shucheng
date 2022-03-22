<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>编辑图书</title>
	<%--	静态包含css样式，js路径，绝对路径base--%>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
	
	h1 a {
		color:red;
	}
	
	input {
		text-align: center;
	}
</style>
</head>
<body>
		<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">编辑图书</span>
<%--			静态包含共有信--%>
			<%@include file="/pages/common/manager_menu.jsp"%>
		</div>
		
		<div id="main">
			<form action="manager/bookServlet" method="post">
<%--				el表达式里面param对象能获取请求参数的值--%>
				<input type="hidden" name="action" value="${param.method}">
				<input type="hidden" name="id" value="${param.id}">
				<table>
					<tr>
						<td>名称</td>
						<td>价格</td>
						<td>作者</td>
						<td>销量</td>
						<td>库存</td>
						<td colspan="2">操作</td>
					</tr>		
					<tr>
						<td><input name="name" type="text"   value="${requestScope.book.name}"/></td>
						<td><input name="price" type="text"  placeholder="价格" value="${requestScope.book.price}"/></td>
						<td><input name="author" type="text" placeholder="作者" value="${requestScope.book.author}"/></td>
						<td><input name="sales" type="text"  placeholder="销量" value="${requestScope.book.sales}"/></td>
						<td><input name="stock" type="text"  placeholder="库存" value="${requestScope.book.stock}"/></td>
						<td><input type="submit" value="提交"/></td>
					</tr>	
				</table>
			</form>
			
	
		</div>
		<%--		静态包含共有的页脚信息--%>
		<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>