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
        .account-info{
            padding: 0 20px;
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
                <h3>个人信息</h3>
            </div>
            <div class="account-info">
                <table class="uk-table" style="width: 50%">
                    <tr>
                        <th>用户ID</th>
                        <td>
                            <span><c:out value="${seller.getSeller_id()}"></c:out></span>
                        </td>
                    </tr>
                    <tr>
                        <th>登录账号</th>
                        <td>
                            <span><c:out value="${seller.getLogin_name()}"></c:out></span>
                        </td>
                    </tr>
                    <tr>
                        <th>联系方式</th>
                        <td>
                            <span><c:out value="${seller.getSeller_tel()}"></c:out></span>
                            <a href="#modal-group-1" uk-toggle style="margin-left: 20px">修改</a>
                        </td>
                    </tr>
                </table>

                <c:if test="${not empty requestScope.msg}">
                    <c:if test="${requestScope.status == 0}">
                        <div class="uk-alert-danger" uk-alert>
                            <a class="uk-alert-close" uk-close></a>
                            <p><c:out value="${requestScope.msg}"></c:out></p>
                        </div>
                    </c:if>
                    <c:if test="${requestScope.status == 1}">
                        <div class="uk-alert-success" uk-alert>
                            <a class="uk-alert-close" uk-close></a>
                            <p><c:out value="${requestScope.msg}"></c:out></p>
                        </div>
                    </c:if>
                </c:if>

                <div id="modal-group-1" uk-modal>
                    <div class="uk-modal-dialog">
                        <form action="/back/seller/account/modify/tel">
                            <button class="uk-modal-close-default" type="button" uk-close></button>
                            <div class="uk-modal-header">
                                <h4 class="uk-modal-title" style="font-size: 16px">修改联系方式</h4>
                            </div>
                            <div class="uk-modal-body" style="text-align: center">
                                <div class="uk-margin">
                                    <div class="uk-inline">
                                        <%--<label>联系方式</label>--%>
                                        <span class="uk-form-icon" uk-icon="icon: receiver"></span>
                                        <input class="uk-input uk-width-medium" name="phone" type="text" autofocus required>
                                    </div>
                                </div>
                            </div>
                            <div class="uk-modal-footer uk-text-right">
                                <button class="uk-button uk-button-default uk-modal-close" type="button">取消</button>
                                <button type="submit" class="uk-button uk-button-primary">修改</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
