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
        .shop-list ul{
            list-style: none;
        }
        .shop-item {
            display: flex;
            width: 50%;
            margin-top: 20px;
        }
        .shop-item div.img{
            flex: 0 0 200px;
        }
        .shop-item div.info{
            flex: 1;
            padding: 10px 30px;
            box-sizing: border-box;
        }
        .shop-item .info .value{
            font-weight: bold;
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
                    <h3>我的店铺
                        <a href="/pages/back/seller/shop_add.jsp" class="uk-button uk-button-primary uk-button-small" style="float: right"><span uk-icon="icon: plus"></span>添加店铺</a>
                    </h3>
                </div>

                <c:if test="${empty requestScope.shopList}">
                    <div class="not-found" style="margin-top: 100px; text-align: center;">
                        <img src="../../../images/notfound.png" alt="">
                        <p>暂时还没店铺哦！</p>
                    </div>
                </c:if>
                <c:if test="${not empty requestScope.shopList}">
                    <div class="shop-list">
                        <ul>
                            <c:forEach items="${requestScope.shopList}" var="shop">
                                <li class="shop-item uk-card uk-card-default uk-card-body">
                                    <div class="img">
                                        <img src="/upload/shop/<c:out value="${shop.getShop_out_photo()}"></c:out>" alt="">
                                    </div>
                                    <div class="info">
                                        <p>
                                            <span class="name">店铺名称:</span>
                                            <span class="value"><c:out value="${shop.getShop_name()}"></c:out></span>
                                        </p>
                                        <p>
                                            <span class="name">简介:</span>
                                            <span class="value"><c:out value="${shop.getDesc()}"></c:out></span>
                                        </p>
                                        <div class="btns">
                                            <a href="/back/seller/shop/detail?shop_name=<c:out value="${shop.getShop_name()}"></c:out>" class="uk-button uk-button-primary uk-button-small">管理</a>
                                        </div>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
                </c:if>

            </div>
        </div>
    </div>
</body>
</html>
