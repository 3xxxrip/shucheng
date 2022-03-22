<%--
  Created by IntelliJ IDEA.
  User: adimn
  Date: 2022/2/24
  Time: 11:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        String base=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    %>
    <base href=<%=base%>>
</head>
<body>
很抱歉，您访问的后台程序出现了错误，我们正在抢修！！！
<a href="index.jsp">返回首页</a>
</body>
</html>
