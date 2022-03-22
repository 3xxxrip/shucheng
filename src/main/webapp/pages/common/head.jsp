<%--
  Created by IntelliJ IDEA.
  User: adimn
  Date: 2022/1/1
  Time: 21:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!--	写base标签，永远固定相对路径跳转的结果-->
<%
    //依次是协议，服务器ip，端口，工程路径
    String base=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
    pageContext.setAttribute("base",base);
%>

<base href=<%=base%>>
<link type="text/css" rel="stylesheet" href="static/css/style.css" >
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script><!--点点是上级目录-->

