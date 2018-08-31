<%--
  Created by IntelliJ IDEA.
  User: zm
  Date: 2018/7/17
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="head.jsp"%>
    <style>
        .dish-list {
            display: flex;
        }
        .dish-list .dish-item{
            flex: 1;
            width: 30%;
            display: inline-block;
            margin: 10px;
        }
        .dish-item .img{
            width: 100%;
        }
        .dish-item .img img{
            width: 100%;
            height: 150px;
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
            <div class="title">
                <h3>基本信息</h3>
                <table class="uk-table uk-table-divider" style="border-bottom: 30px solid #ebebeb; margin-bottom: 0">
                    <tr>
                        <th class="name uk-width-small">店铺ID</th>
                        <td class="value"><c:out value="${shop_detail.getShop_id()}"></c:out></td>
                    </tr>
                    <tr>
                        <th class="name uk-width-small">店铺名称</th>
                        <td class="value"><c:out value="${shop_detail.getShop_name()}"></c:out></td>
                    </tr>
                    <tr>
                        <th class="name uk-width-small">联系人</th>
                        <td class="value"><c:out value="${shop_detail.getContact()}"></c:out></td>
                    </tr>
                    <tr>
                        <th class="name uk-width-small">联系电话</th>
                        <td class="value"><c:out value="${shop_detail.getPhone()}"></c:out></td>
                    </tr>
                    <tr>
                        <th class="name uk-width-small">地址</th>
                        <td class="value"><c:out value="${shop_detail.getAddress()}"></c:out></td>
                    </tr>
                </table>
            </div>

            <div class="title">
                <h3 style="display: inline-block">菜品预览
                    <a href="/pages/back/seller/dish_add.jsp" class="uk-button uk-button-primary uk-button-small" style="float: right"><span uk-icon="icon: plus"></span>添加菜品</a>
                </h3>
            </div>
            <div class="dish-list">
                <c:forEach var="dish" items="${shop_detail.getDishList()}">
                    <div class="dish-item uk-card uk-card-default uk-card-body">
                        <div class="img">
                            <img src="/upload/img/<c:out value="${dish.getPicture()}"></c:out>" alt="">
                        </div>
                        <div class="info">
                            <h4><c:out value="${dish.getDish_name()}"></c:out></h4>
                            <p>￥ <c:out value="${dish.getCurrent_price()}"></c:out></p>
                        </div>
                        <div class="menu">
                            <a href="/back/seller/dish/detail?dish_id=<c:out value="${dish.getDish_id()}"></c:out>" class="uk-button uk-button-primary uk-button-small">修改</a>
                            <a href="/back/seller/dish/delete?dish_id=<c:out value="${dish.getDish_id()}"></c:out>" class="uk-button uk-button-primary uk-button-small">删除</a>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
