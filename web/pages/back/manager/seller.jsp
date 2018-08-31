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
        .content-container .seller-list{
            padding: 10px 20px;
        }
        .seller-list .uk-badge.red{
            background: #ee395b;
        }
        .seller-list .uk-badge.green{
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
        .seller-list th,td{
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
                <h3>商家列表</h3>
            </div>
            <div class="filter-wrapper">
                <form action="/back/manager/seller/list">
                    <div class="form-wrapper">
                        <div class="left-form">
                            用户名： <input type="text" class="uk-input uk-form-width-medium" name="login_name" required>
                        </div>
                        <div class="btn">
                            <button class="uk-button uk-button-primary" type="submit">查询</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="seller-list">
                <table class="uk-table uk-table-striped">
                    <tr>
                        <th>账号ID</th>
                        <th>账号名称</th>
                        <th>状态</th>
                        <th>联系方式</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${requestScope.sellerList}" var="seller">
                        <tr>
                            <td><c:out value="${seller.getSeller_id()}"></c:out></td>
                            <td><c:out value="${seller.getLogin_name()}"></c:out></td>
                            <td>
                                <c:if test="${seller.getStatus() == 0}">
                                    <span class="uk-badge red">已封</span>
                                </c:if>
                                <c:if test="${seller.getStatus() == 1}">
                                    <span class="uk-badge green">正常</span>
                                </c:if>
                                <c:if test="${seller.getStatus() == 2}">
                                    <span class="uk-badge">待审核</span>
                                </c:if>
                            </td>
                            <td><c:out value="${seller.getSeller_tel()}"></c:out></td>
                            <td>
                                <c:if test="${not empty seller}">
                                    <a href="/back/manager/seller/update/status?login_name=<c:out value="${seller.getLogin_name()}&status=0"></c:out>" class="uk-button uk-button-danger uk-button-small <c:if test="${seller.getStatus() != 1}">uk-disabled</c:if>">封号</a>
                                    <a href="/back/manager/seller/update/status?login_name=<c:out value="${seller.getLogin_name()}&status=1"></c:out>" class="uk-button uk-button-success uk-button-small <c:if test="${seller.getStatus() != 0}">uk-disabled</c:if>">解封</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <div class="pagination">
                    <a class="uk-button uk-button-primary" href="/back/manager/seller/list?page=1">首页</a>
                    <a class="uk-button uk-button-primary <c:if test="${requestScope.page - 1 <= 0}">uk-disabled</c:if>" href='/back/manager/seller/list?page=<c:out value="${requestScope.page - 1}"></c:out>'>上一页</a>
                    <span><c:out value="${requestScope.page}"></c:out>/<c:out value="${requestScope.totalPage}"></c:out></span>
                    <a class="uk-button uk-button-primary <c:if test="${requestScope.page + 1 > requestScope.totalPage}">uk-disabled</c:if>" href='/back/manager/seller/list?page=<c:out value="${requestScope.page + 1}"></c:out>'>下一页</a>
                    <a class="uk-button uk-button-primary" href='/back/manager/seller/list?page=<c:out value="${requestScope.totalPage}"></c:out>'>尾页</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
</script>
</body>
</html>
