<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>书城首页</title>
    <%--<link type="text/css" rel="stylesheet" href="static/css/style.css" >--%>
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function (){
            //添加商品到购物车
            $(".addToCart").click(function () {
                var BookId=$(this).attr("bookId");
                // 因为在pageContext域中保存了base属性，所以能直接用
                location.href="${pageContext.getAttribute("base")}cartServlet?action=addItem&id="+BookId;
            })

            $("#confirm").click(function () {
                var pageNo=$("#pn_input").val();
                //javaScript语言中提供了一个location地址栏对象
                //他有一个属性href，它可以取到浏览器中地址栏中的地址
                if(pageNo>=1&&pageNo<=${requestScope.page.pageTotal}){
                    if(${param.action=="pageByPrice"}){
                        location.href="${pageContext.getAttribute("base")}${requestScope.page.url}&pageNo="+pageNo;
                    }
                    else if(${param.action=="page"}){
                        location.href="${pageContext.getAttribute("base")}${requestScope.page.url}&pageNo="+pageNo;
                    }

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
    <span class="wel_word">网上书城</span>
    <div>
<%--        如果还没有登录，就显示登录和注册按钮，否则显示登录成功的信息--%>
        <c:if test="${empty sessionScope.username}">
            <a href="pages/user/login.jsp">登录</a> |
            <a href="pages/user/regist.jsp">注册</a> &nbsp;&nbsp;
        </c:if>
        <c:if test="${not empty sessionScope.username}">
            <span>欢迎<span class="um_span">${sessionScope.username}</span>光临尚硅谷书城</span>
            <a href="userServlet?action=logOut">注销</a>&nbsp;&nbsp;
            <a href="index.jsp">返回</a>
        </c:if>
        <a href="pages/cart/cart.jsp">购物车</a>
        <a href="pages/manager/manager.jsp">后台管理</a>
    </div>
</div>
<div id="main">
    <div id="book">
        <div class="book_cond">
            <form action="client/bookServlet" method="get">
                <input type="hidden" name="action" value="pageByPrice">
                价格：<input id="min" type="text" name="min" value="${param.min}" > 元 -
                <input id="max" type="text" name="max" value="${param.max}" > 元
                <input type="submit" value="查询" id="search"/>
            </form>
        </div>
        <div style="text-align: center">
<%--            这里是判断如果购物车中没有商品的话就不显示这两行--%>
            <c:if test="${not empty sessionScope.cart.totalcount && sessionScope.cart.totalcount!=0}">
                <span>您的购物车中有${sessionScope.cart.totalcount}件商品</span>
                <div>
                    您刚刚将<span id="addBookName" style="color: red">${sessionScope.lastItemName}</span>加入到了购物车中
                </div>
            </c:if>
        </div>
        <c:forEach items="${requestScope.page.pageItems}" var="book">
            <div class="b_list">
            <div class="img_div">
                <img class="book_img" alt="" src="${book.imgPath}" />
            </div>
            <div class="book_info">
                <div class="book_name">
                    <span class="sp1">书名:</span>
                    <span class="sp2">${book.name}</span>
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
                    <button bookId="${book.id}" bookName="${book.name}" class="addToCart">加入购物车</button>
                </div>
            </div>
        </div>
        </c:forEach>
    </div>

    <%--	分页的导航栏--%>
<%@include file="/pages/common/page_nav.jsp"%>
</div>

<%@include file="/pages/common/footer.jsp"%>
</body>
</html>