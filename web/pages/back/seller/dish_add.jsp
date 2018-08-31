<%--
  Created by IntelliJ IDEA.
  User: zm
  Date: 2018/7/17
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.bookfood.cn/c" %>
<c:set value="${requestScope.dish}" var="dish"></c:set>
<html>
<head>
    <%@ include file="head.jsp"%>
    <style>
        .dish-container .info{
            width: 50%;
            padding: 10px 30px;
        }
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
            <div class="dish-container">
                <div class="title">
                    <h3>基本信息</h3>
                </div>
                <div class="info">
                    <form action="/back/seller/dish/add" method="get">
                        <input type="text" name="shop_id" value="<c:out value="${shop_detail.getShop_id()}"></c:out>" hidden>
                        <table class="uk-table">
                            <tr>
                                <th>菜品名称</th>
                                <td>
                                    <div class="uk-margin">
                                        <div class="uk-form-controls">
                                            <input class="uk-input" name="dish_name" type="text">
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>简介</th>
                                <td>
                                    <div class="uk-margin">
                                        <div class="uk-form-controls">
                                            <textarea class="uk-textarea" name="descp"  rows="5"></textarea>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>原价</th>
                                <td>
                                    <div class="uk-margin">
                                        <div class="uk-form-controls">
                                            <input class="uk-input" name="original_price" type="text">
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>现价</th>
                                <td>
                                    <div class="uk-margin">
                                        <div class="uk-form-controls">
                                            <input class="uk-input" name="current_price" type="text">
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>

                        <div class="uk-margin" style="text-align: center">
                            <button type="submit" class="uk-button uk-button-primary uk-button-small">添加</button>
                            <button type="button" class="uk-button uk-button-default uk-button-small" onclick="goBack()">取消</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
