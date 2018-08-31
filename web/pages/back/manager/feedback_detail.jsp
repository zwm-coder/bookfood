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
        .feedback-detail-container {
            padding: 0 20px;
        }
        .feedback-detail-container .content .value img{
            width: 50px;
            height: 50px;
            transition: width 1s, height 1s;
        }
        .feedback-detail-container .content .value img:hover {
            width: 300px;
            height: 300px;
        }
        .feedback-detail-container .content .btns{
            text-align: center;
        }
        .feedback-detail-container .content .btns button{
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
                    <c:if test="${not empty feedback}">
                        编辑反馈信息： <c:out value="${feedback.getFeedback_id()}"></c:out>
                    </c:if>
                    <button style="float: right;border-radius: 5px" class="uk-button uk-button-primary uk-button-small" onclick="goBack()">返回</button>
                </h3>

            </div>

            <div class="feedback-detail-container">
                <c:if test="${not empty feedback}">
                    <div class="content">
                        <table class="uk-table uk-table-divider">
                            <tr>
                                <th class="name uk-width-small">ID</th>
                                <td class="value"><c:out value="${feedback.getFeedback_id()}"></c:out></td>
                            </tr>
                            <tr>
                                <th class="name uk-width-small">反馈用户ID</th>
                                <td class="value"><c:out value="${feedback.getUser_id()}"></c:out></td>
                            </tr>
                            <tr>
                                <th class="name uk-width-small">店铺ID</th>
                                <td class="value"><c:out value="${feedback.getShop_id()}"></c:out></td>
                            </tr>
                            <tr>
                                <th class="name uk-width-small">反馈内容</th>
                                <td class="value"><c:out value="${feedback.getContent()}"></c:out></td>
                            </tr>
                            <tr>
                                <th class="name uk-width-small">处理状态</th>
                                <td class="value">
                                    <c:if test="${feedback.getStatus() == 0}">
                                        <span>未处理</span>
                                    </c:if>
                                    <c:if test="${feedback.getStatus() == 1}">
                                        <span>已处理</span>
                                    </c:if>
                                </td>
                            </tr>
                            <tr>
                                <th class="name uk-width-small">处理结果</th>
                                <td class="value">
                                    <c:if test="${feedback.getStatus() == 0}">
                                        <form action="/back/manager/feedback/handle">
                                            <div class="uk-margin">
                                                <input hidden="hidden" type="text" name="feedback_id" value="<c:out value="${feedback.getFeedback_id()}"></c:out>">
                                                <textarea class="uk-textarea" rows="5" placeholder="请输入处理结果" name="result" required></textarea>
                                            </div>

                                            <div class="uk-margin">
                                                <button type="submit" class="uk-button uk-button-primary uk-button-small">提交</button>
                                            </div>
                                        </form>
                                    </c:if>
                                    <c:if test="${feedback.getStatus() == 1}">
                                        <span><c:out value="${feedback.getResult()}"></c:out></span>
                                    </c:if>
                                </td>
                            </tr>
                        </table>
                    </div>

                </c:if>
            </div>

        </div>
    </div>
</div>

<script>
    function goBack() {
        history.back();
    }
</script>
</body>
</html>
