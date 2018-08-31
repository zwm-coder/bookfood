<%--
  Created by IntelliJ IDEA.
  User: zm
  Date: 2018/7/18
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path1 = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path1+"/";
%>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<base href=" <%=basePath%>">
<title>后台管理系统</title>
<link rel="stylesheet" href="css/uikit.min.css">
<link rel="stylesheet" href="css/back/style.css">
<script src="js/uikit.min.js"></script>
<script src="js/uikit-icons.min.js"></script>
<script src="js/back/index.js"></script>