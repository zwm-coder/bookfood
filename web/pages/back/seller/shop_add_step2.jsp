<%--
  Created by IntelliJ IDEA.
  User: zm
  Date: 2018/7/17
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.bookfood.cn/c" %>
<c:set value="${requestScope.applyShop}" var="apply_shop"></c:set>
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
                <%--<form action="/back/seller/shop/apply/step2" method="post" enctype="multipart/form-data">--%>
                    <table class="uk-table">
                        <tr>
                            <th>身份证照片</th>
                            <th>
                                <c:if test="${not empty apply_shop.getId_card()}">
                                    <img src="<c:out value="${apply_shop.getId_card()}"></c:out>" width="200" height="200" alt="">
                                </c:if>
                                <c:if test="${empty apply_shop.getId_card()}">
                                    <form action="/back/seller/shop/apply/step2?img_type=id_card" method="post" enctype="multipart/form-data">
                                        <input type="file" name="id_card">
                                        <div class="uk-margin">
                                            <button type="submit" class="uk-button uk-button-primary uk-button-small">上传</button>
                                        </div>
                                    </form>
                                </c:if>
                            </th>
                        </tr>
                        <tr>
                            <th>营业执照</th>
                            <th>
                                <c:if test="${not empty apply_shop.getBusiness_license()}">
                                    <img src="<c:out value="${apply_shop.getBusiness_license()}"></c:out>" width="200" height="200" alt="">
                                </c:if>
                                <c:if test="${empty apply_shop.getBusiness_license()}">
                                    <form action="/back/seller/shop/apply/step2?img_type=business_license" method="post" enctype="multipart/form-data">
                                        <input type="file" name="business_license">
                                        <div class="uk-margin">
                                            <button type="submit" class="uk-button uk-button-primary uk-button-small">上传</button>
                                        </div>
                                    </form>
                                </c:if>
                            </th>
                        </tr>
                        <tr>
                            <th>许可证</th>
                            <th>
                                <c:if test="${not empty apply_shop.getCater_permit()}">
                                    <img src="<c:out value="${apply_shop.getCater_permit()}"></c:out>" width="200" height="200" alt="">
                                </c:if>
                                <c:if test="${empty apply_shop.getCater_permit()}">
                                    <form action="/back/seller/shop/apply/step2?img_type=cater_permit" method="post" enctype="multipart/form-data">
                                        <input type="file" name="cater_permit">
                                        <div class="uk-margin">
                                            <button type="submit" class="uk-button uk-button-primary uk-button-small">上传</button>
                                        </div>
                                    </form>
                                </c:if>
                            </th>
                        </tr>
                        <tr>
                            <th>门店照片</th>
                            <th>
                                <c:if test="${not empty apply_shop.getShop_out_photo()}">
                                    <img src="<c:out value="${apply_shop.getShop_out_photo()}"></c:out>" width="200" height="200" alt="">
                                </c:if>
                                <c:if test="${empty apply_shop.getShop_out_photo()}">
                                    <form action="/back/seller/shop/apply/step2?img_type=shop_out_photo" method="post" enctype="multipart/form-data">
                                        <input type="file" name="shop_out_photo">
                                        <div class="uk-margin">
                                            <button type="submit" class="uk-button uk-button-primary uk-button-small">上传</button>
                                        </div>
                                    </form>
                                </c:if>
                            </th>
                        </tr>
                    </table>
                    <div class="uk-margin" style="text-align: center">
                        <a href="/back/seller/shop/apply/commit" class="uk-button uk-button-primary uk-button-small">立即申请</a>
                    </div>
                <%--</form>--%>
            </div>
        </div>
    </div>
</div>
</body>
</html>
