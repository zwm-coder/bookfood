<%--
  Created by IntelliJ IDEA.
  User: zm
  Date: 2018/7/17
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.bookfood.cn/c" %>
<html>
<head>
    <%@ include file="head.jsp"%>
    <style>

    </style>
</head>
<body>
<div class="container">
    <%@ include file="navs.jsp"%>
    <div class="right">
        <div class="top-nav">
            <div class="content">
                <a class="account" href=""><c:out value="${sessionScope.seller.getLogin_name()}" /></a>
                <a href="/back/seller/inout/logout" class="exit">退出</a>
            </div>
        </div>

        <div class="content-container">
            <div class="title">
                <h3>申请店铺</h3>
            </div>

            <div class="form-container">
                <form action="/back/seller/shop/apply/step1">
                    <table class="uk-table">
                        <tr>
                            <th>店铺名称</th>
                            <th>
                                <input type="text" name="shop_name" class="uk-input uk-width-medium">
                            </th>
                        </tr>
                        <tr>
                            <th>联系人</th>
                            <th>
                                <input type="text" name="contact" class="uk-input uk-width-medium">
                            </th>
                        </tr>
                        <tr>
                            <th>联系人电话</th>
                            <th>
                                <input type="text" name="phone" class="uk-input uk-width-medium">
                            </th>
                        </tr>
                        <tr>
                            <th>地址</th>
                            <th>
                                <input type="text" name="address" class="uk-input uk-width-medium">
                            </th>
                        </tr>
                        <tr>
                            <th>简介</th>
                            <th>
                                <textarea name="descp" class="uk-textarea uk-width-medium" rows="4"></textarea>
                            </th>
                        </tr>
                    </table>
                    <div class="uk-margin" style="text-align: center">
                        <button type="submit" class="uk-button uk-button-primary uk-button-small">下一步</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
