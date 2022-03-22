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
很抱歉，您访问的资源我们没找到
<a href="index.jsp">返回首页</a>
</body>
</html>
