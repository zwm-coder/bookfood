<%--
  Created by IntelliJ IDEA.
  User: zm
  Date: 2018/7/14
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="head.jsp"%>
</head>
<body>
<form action="/back/manager/manager/add" method="post">
    <table>
        <tr>
            <th>用户名</th>
            <td><input type="text" name="name"></td>
        </tr>
        <tr>
            <th>密码</th>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2"><button type="submit">提交</button></td>
        </tr>
    </table>
</form>
</body>
</html>
