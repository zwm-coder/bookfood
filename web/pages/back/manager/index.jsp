<%--
  Created by IntelliJ IDEA.
  User: zm
  Date: 2018/7/14
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.bookfood.cn/c" %>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="head.jsp"%>
    <style>
        .data-view{
            display: flex;
            margin-top: 50px;
        }
        .data-view .uk-card{
            flex: 1;
            margin: 0 20px;
            display: flex;
            align-content: center;
        }
        .data-view .uk-card .image{
            flex: 0 0 40px;
        }
        .data-view .uk-card .image img{
            width: 100%;
            margin-top: 30px;
        }
        .data-view .uk-card .data{
            flex: 1;
            text-align: center;
        }
        .data .count{
            font-size: 24px;
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
                    <a class="account" href=""><c:out value="${sessionScope.login_name}" /></a>
                    <a href="/back/manager/manager/logout" class="exit">退出</a>
                </div>
            </div>

            <div class="content-container">
                <div class="title">
                    <h3>数据总览</h3>
                </div>

                <div class="data-view">
                    <div class="uk-card uk-card-default uk-card-hover uk-card-body">
                        <div class="image">
                            <img src="../../../images/shop.png" alt="">
                        </div>
                        <div class="data">
                            <p>店铺总数</p>
                            <p class="count"><c:out value="${requestScope.shopTotal - requestScope.applyShopTotal}"></c:out></p>
                        </div>
                    </div>
                    <div class="uk-card uk-card-default uk-card-hover uk-card-body">
                        <div class="image">
                            <img src="../../../images/user.png" alt="">
                        </div>
                        <div class="data">
                            <p>用户数量</p>
                            <p class="count"><c:out value="${requestScope.userTotal}"></c:out></p>
                        </div>
                    </div>
                    <div class="uk-card uk-card-default uk-card-hover uk-card-body">
                        <div class="image">
                            <img src="../../../images/apply.png" alt="">
                        </div>
                        <div class="data">
                            <p>申请店铺</p>
                            <p class="count"><c:out value="${requestScope.applyShopTotal}"></c:out></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
