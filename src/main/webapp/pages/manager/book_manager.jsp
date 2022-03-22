<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%--	静态包含css样式，js路径，绝对路径base--%>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			//给删除的a标签绑定确认提示操作
			$("a.deleteClass").click(function () {
				//在事件的function函数中有一个this对象，是当前正在响应的dom对象
				//confirm是确认提示函数，返回true表示点击了确认反之依然
				return confirm("您确定要删除书籍 《"+$(this).parent().parent().find("td:first").text()+"》 吗？");
				// return false是阻止默认行为
			})
			//这个函数是给输入跳转页面用的
			$("#confirm").click(function () {
				var pageNo=$("#pn_input").val();
				//javaScript语言中提供了一个location地址栏对象
				//他有一个属性href，它可以取到浏览器中地址栏中的地址
				if(pageNo>=1&&pageNo<=${requestScope.page.pageTotal}){
					location.href="${pageContext.getAttribute("base")}${requestScope.page.url}&pageNo="+pageNo;
				}
				else{
					alert("总共${requestScope.page.pageTotal}页，您输入的页码超出范围!")
					return false;
				}
			})
		})
	</script>
</head>
<body>
	
	<div id="header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
			<span class="wel_word">图书管理系统</span>
		<%--			静态包含共有信--%>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
<%--			for (Object item: arr)--%>
<%--			items 表示遍历的数据源（遍历的集合）--%>
<%--			var 表示当前遍历到的数--%>
			<c:forEach var="item" items="${requestScope.page.pageItems}">
				<tr>
					<td>${item.name}</td>
					<td>${item.price}</td>
					<td>${item.author}</td>
					<td>${item.sales}</td>
					<td>${item.stock}</td>
					<td><a href="manager/bookServlet?action=getbook&id=${item.id}&method=update">修改</a></td>
	<%--				href="#"是返回页面最开始的页面--%>
					<td><a class="deleteClass" href="manager/bookServlet?action=delete&id=${item.id}">删除</a></td>
				</tr>
			</c:forEach>
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
<%--				这个method是用于book_edit.jsp里面判断提交之添加还是修改数据方法中--%>
				<td><a href="pages/manager/book_edit.jsp?method=add">添加图书</a></td>
			</tr>	
		</table>
	</div>
<%--	静态包含分页条--%>
<%@include file="/pages/common/page_nav.jsp"%>

	<%--		静态包含共有的页脚信息--%>
	<%@ include file="/pages/common/footer.jsp"%>

</body>
</html>