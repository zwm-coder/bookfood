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
        .content-container .title .content{
            padding: 10px;
            border-bottom: 20px solid #ebebeb;
        }
        .content-container .form-wrapper form{
            padding: 10px;
        }
        .form-wrapper input {
            border-radius: 5px;
            /*border: 1px solid #d9d9d9;*/
        }
    </style>
</head>
<body>
<div class="container">
    <%@ include file="navs.jsp"%>
    <div class="right">
        <div class="top-nav">
            <div class="content">
                <a class="account" href=""><c:out value="${seller.getLogin_name()}" /></a>
                <a href="/back/seller/inout/logout" class="exit">退出</a>
            </div>
        </div>

        <div class="content-container">
            <div class="title">
                <h3>账号信息</h3>
                <div class="content">
                    <p>账号ID： <span><c:out value="${seller.getSeller_id()}"></c:out></span></p>
                    <p>账号名称： <span><c:out value="${seller.getLogin_name()}"></c:out></span></p>
                </div>
            </div>
            <div class="form-wrapper">
                <div class="title">
                    <h3>修改密码</h3>
                </div>
                <form action="/back/seller/account/modify/pwd" name="changePwdForm" method="post" onsubmit="return validatePwd()">
                    <div class="uk-margin">
                        <label>原&nbsp;&nbsp;密&nbsp;&nbsp;码：</label><input name="origin_pwd" type="password" class="uk-input uk-form-width-medium" required>
                    </div>
                    <div class="uk-margin">
                        <label>新&nbsp;&nbsp;密&nbsp;&nbsp;码：</label><input name="new_pwd" type="password" class="uk-input uk-form-width-medium" required>
                    </div>
                    <div class="uk-margin">
                        <label>确认密码：</label><input type="password" name="new_pwd1" class="uk-input uk-form-width-medium" required>
                    </div>

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

                    <div class="uk-margin" style="text-align: center;">
                        <button class="uk-button uk-button-primary" type="submit">修改</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function validatePwd() {
        var new_pwd = document.changePwdForm.new_pwd.value;
        var new_pwd1 = document.changePwdForm.new_pwd1.value;
        if (new_pwd !== new_pwd1) {
            utils.addClass(document.changePwdForm.new_pwd, "uk-form-danger");
            utils.addClass(document.changePwdForm.new_pwd1, "uk-form-danger");
            return false;
        }
        return true;
    }
</script>
</body>
</html>
