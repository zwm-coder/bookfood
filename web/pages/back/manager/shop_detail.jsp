<%--
  Created by IntelliJ IDEA.
  User: zm
  Date: 2018/7/14
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.bookfood.cn/c" %>

<c:set value="${requestScope.shop}" var="shop"></c:set>
<%
    session.setAttribute("shop", request.getAttribute("shop"));
%>
<!DOCTYPE html>
<html>
<head>
    <%@ include file="head.jsp"%>

    <style type="text/css">
        .shop-detail-container {
            padding: 0 20px;
        }
        .shop-detail-container .content .value img{
            width: 50px;
            height: 50px;
            transition: width 1s, height 1s;
        }
        .shop-detail-container .content .value img:hover {
            width: 300px;
            height: 300px;
        }
        .shop-detail-container .content .btns{
            text-align: center;
        }
        .shop-detail-container .content .btns button{
            margin: 10px;
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
                <h3>
                    <c:if test="${not empty shop}">
                        <c:out value="${shop.getShop_name()}"></c:out>
                    </c:if>
                </h3>
            </div>

            <div class="shop-detail-container">
                <c:if test="${not empty shop}">
                    <div class="content">
                        <table class="uk-table uk-table-divider">
                            <tr>
                                <th class="name uk-width-small">店铺名称</th>
                                <td class="value"><c:out value="${shop.getShop_name()}"></c:out></td>
                            </tr>
                            <tr>
                                <th class="name uk-width-small">联系人</th>
                                <td class="value"><c:out value="${shop.getContact()}"></c:out></td>
                            </tr>
                            <tr>
                                <th class="name uk-width-small">联系电话</th>
                                <td class="value"><c:out value="${shop.getPhone()}"></c:out></td>
                            </tr>
                            <tr>
                                <th class="name uk-width-small">地址</th>
                                <td class="value"><c:out value="${shop.getAddress()}"></c:out></td>
                            </tr>
                            <tr>
                                <th class="name uk-width-small">身份证</th>
                                <td class="value">
                                    <img src="/upload/img/<c:out value="${shop.getId_card()}"></c:out>" alt="">
                                </td>
                            </tr>
                            <tr>
                                <th class="name uk-width-small">营业执照</th>
                                <td class="value">
                                    <img src="/upload/img/<c:out value="${shop.getBusiness_license()}"></c:out>" alt="">
                                </td>
                            </tr>
                            <tr>
                                <th class="name uk-width-small">许可证</th>
                                <td class="value">
                                    <img src="/upload/img/<c:out value="${shop.getCater_permit()}"></c:out>" alt="">
                                </td>
                            </tr>
                        </table>

                        <div class="uk-margin btns">
                            <a href="/back/manager/shop/detail?flag=1" class="uk-button uk-button-primary uk-button-small">通过</a>
                            <a href="/back/manager/shop/detail?flag=2" class="uk-button uk-button-danger uk-button-small">拒绝</a>
                            <button class="uk-button uk-button-default uk-button-small" onclick="goBack()">取消</button>
                        </div>
                    </div>

                </c:if>
            </div>

        </div>
    </div>
</div>

<script>

</script>
</body>
</html>
