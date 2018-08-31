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

    <style type="text/css">
        .content-container .shop-list{
            padding: 10px 20px;
        }
        .shop-list .uk-badge.red{
            background: #ee395b;
        }
        .shop-list .uk-badge.green{
            background: #32d296;
        }
        .filter-wrapper .form-wrapper {
            display: flex;
            padding: 20px;
        }
        .filter-wrapper .form-wrapper .left-form{
            flex: 1;
            text-align: left;
        }
        .filter-wrapper .form-wrapper .btn{
            flex: 0 0 120px;
            text-align: right;
        }
        .pagination{
            text-align: right;
            margin-top: 30px;
        }
        .pagination .uk-button{
            border-radius: 3px;
            color: #fff;
            margin: 0 10px;
        }
        .uk-button.uk-disabled{
            background: #ebebeb;
            color: #3d465e;
        }
        .shop-list th,td{
            text-align: center;
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
                <h3>申请列表</h3>
            </div>

            <c:if test="${empty requestScope.shopList}">
                <div class="not-found" style="margin-top: 100px; text-align: center;">
                    <img src="../../../images/notfound.png" alt="">
                    <p>暂时还没申请店铺哦！</p>
                </div>
            </c:if>

            <c:if test="${not empty requestScope.shopList}">
                <div class="filter-wrapper">
                    <form action="/back/manager/shop/list">
                        <div class="form-wrapper">
                            <div class="left-form">
                                店铺名称： <input type="text" class="uk-input uk-form-width-medium" name="shop_name" required>
                            </div>
                            <div class="btn">
                                <button class="uk-button uk-button-primary" type="submit">查询</button>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="shop-list">
                    <table class="uk-table uk-table-striped">
                        <tr>
                            <th>店铺ID</th>
                            <th>店铺名称</th>
                            <th>状态</th>
                            <th>联系人</th>
                            <th>联系方式</th>
                            <th>操作</th>
                        </tr>
                        <c:forEach items="${requestScope.shopList}" var="shop">
                            <tr>
                                <td><c:out value="${shop.getShop_id()}"></c:out></td>
                                <td><c:out value="${shop.getShop_name()}"></c:out></td>
                                <td>
                                    <c:if test="${shop.getStatus() == 2}">
                                        <span class="uk-badge red">审核未通过</span>
                                    </c:if>
                                    <c:if test="${shop.getStatus() == 1}">
                                        <span class="uk-badge green">正常</span>
                                    </c:if>
                                    <c:if test="${shop.getStatus() == 0}">
                                        <span class="uk-badge">待审核</span>
                                    </c:if>
                                </td>
                                <td><c:out value="${shop.getContact()}"></c:out></td>
                                <td><c:out value="${shop.getPhone()}"></c:out></td>
                                <td>
                                    <a href="/back/manager/shop/detail?shop_name=<c:out value="${shop.getShop_name()}"></c:out>" class="uk-button uk-button-primary uk-button-small">详情</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                    <div class="pagination">
                        <a class="uk-button uk-button-primary" href="/back/manager/shop/list?page=1&status=<c:out value="${requestScope.status}"></c:out>">首页</a>
                        <a class="uk-button uk-button-primary <c:if test="${requestScope.page - 1 <= 0}">uk-disabled</c:if>" href='/back/manager/shop/list?page=<c:out value="${requestScope.page - 1}"></c:out>&status=<c:out value="${requestScope.status}"></c:out>'>上一页</a>
                        <span><c:out value="${requestScope.page}"></c:out>/<c:out value="${requestScope.totalPage}"></c:out></span>
                        <a class="uk-button uk-button-primary <c:if test="${requestScope.page + 1 > requestScope.totalPage}">uk-disabled</c:if>" href='/back/manager/shop/list?page=<c:out value="${requestScope.page + 1}"></c:out>&status=<c:out value="${requestScope.status}"></c:out>'>下一页</a>
                        <a class="uk-button uk-button-primary" href='/back/manager/shop/list?page=<c:out value="${requestScope.totalPage}"></c:out>&status=<c:out value="${requestScope.status}"></c:out>'>尾页</a>
                    </div>
                </div>
            </div>
            </c:if>
    </div>
</div>

<script>
</script>
</body>
</html>
