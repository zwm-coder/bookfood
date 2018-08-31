<%--
  Created by IntelliJ IDEA.
  User: zm
  Date: 2018/7/14
  Time: 20:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.bookfood.cn/c" %>

<%
    String url = request.getAttribute("url").toString();
    if (url.lastIndexOf("?") == -1){
        url += "?";
    } else {
        url += "&";
    }
    request.setAttribute("url", url);
%>

<c:set value="${requestScope.url}" var="paramUrl"></c:set>
<!DOCTYPE html>
<html>
<head>
    <%--<meta charset="UTF-8">--%>
    <%--<meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <%--<title>后台管理系统</title>--%>
    <%--<link rel="stylesheet" href="../../../css/uikit.min.css">--%>
    <%--<link rel="stylesheet" href="../../../css/back/style.css">--%>
    <%--<script src="../../../js/uikit.min.js"></script>--%>
    <%--<script src="../../../js/uikit-icons.min.js"></script>--%>
    <%--<script src="../../../js/back/index.js"></script>--%>
    <%@ include file="head.jsp"%>

    <style type="text/css">
        .content-container .feedback-list{
            padding: 10px 20px;
        }
        .feedback-list .uk-badge.red{
            background: #ee395b;
        }
        .feedback-list .uk-badge.green{
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
        .feedback-list th,td{
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
                <h3>反馈信息</h3>
            </div>
            <div class="filter-wrapper">
                <form action="/back/manager/feedback/list">
                    <div class="form-wrapper">
                        <input type="text" name="query_name" value="shop_id" hidden>
                        <div class="left-form">
                            店铺ID： <input type="text" class="uk-input uk-form-width-medium" name="query_value" required>
                        </div>

                        <div class="btn">
                            <button class="uk-button uk-button-primary" type="submit">查询</button>
                        </div>
                    </div>
                </form>
            </div>
            <div class="feedback-list">
                <table class="uk-table uk-table-striped">
                    <tr>
                        <th>ID</th>
                        <th>用户ID</th>
                        <th>店铺ID</th>
                        <th>反馈内容</th>
                        <th>处理状态</th>
                        <th>处理结果</th>
                        <th>操作</th>
                    </tr>
                    <c:forEach items="${requestScope.feedBackList}" var="feedback">
                        <tr>
                            <td><c:out value="${feedback.getFeedback_id()}"></c:out></td>
                            <td><c:out value="${feedback.getUser_id()}"></c:out></td>
                            <td><c:out value="${feedback.getShop_id()}"></c:out></td>
                            <td><c:out value="${feedback.getContent()}"></c:out></td>
                            <td>
                                <c:if test="${feedback.getStatus() == 0}">
                                    <span class="uk-badge">待处理</span>
                                </c:if>
                                <c:if test="${feedback.getStatus() == 1}">
                                    <span class="uk-badge green">已处理</span>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${feedback.getStatus() == 0}">
                                    <span>未处理</span>
                                </c:if>
                                <c:if test="${feedback.getStatus() == 1}">
                                    <c:out value="${feedback.getResult()}"></c:out>
                                </c:if>
                            </td>
                            <td>
                                <c:if test="${not empty feedback}">
                                    <a href="/back/manager/feedback/detail?feedback_id=<c:out value="${feedback.getFeedback_id()}"></c:out>" class="uk-button uk-button-primary uk-button-small">编辑</a>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <div class="pagination">
                    <a class="uk-button uk-button-primary" href="<c:out value="${paramUrl}"></c:out>page=1">首页</a>
                    <a class="uk-button uk-button-primary <c:if test="${requestScope.page - 1 <= 0}">uk-disabled</c:if>" href='<c:out value="${paramUrl}"></c:out>page=<c:out value="${requestScope.page - 1}"></c:out>'>上一页</a>
                    <span><c:out value="${requestScope.page}"></c:out>/<c:out value="${requestScope.totalPage}"></c:out></span>
                    <a class="uk-button uk-button-primary <c:if test="${requestScope.page + 1 > requestScope.totalPage}">uk-disabled</c:if>" href='<c:out value="${paramUrl}"></c:out>page=<c:out value="${requestScope.page + 1}"></c:out>'>下一页</a>
                    <a class="uk-button uk-button-primary" href='<c:out value="${paramUrl}"></c:out>page=<c:out value="${requestScope.totalPage}"></c:out>'>尾页</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script>
</script>
</body>
</html>
