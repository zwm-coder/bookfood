<%--
  Created by IntelliJ IDEA.
  User: zm
  Date: 2018/7/12
  Time: 14:02
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.bookfood.cn/c" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>后台登录</title>
    <base href=" <%=basePath%>">
    <link rel="stylesheet" href="css/uikit.min.css">
    <link rel="stylesheet" href="css/back/style.css">
    <script src="js/uikit.min.js"></script>
    <script src="js/uikit-icons.min.js"></script>
    <script src="js/back/index.js"></script>
    <style>
        .login{
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: rgba(0, 0, 0, 0.7);
        }
        .login .background{
            position: absolute;
            bottom: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: -1;
            overflow: hidden;
        }
        .login-container{
            width: 30%;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate3d(-50%, -50%, 0);
        }
        .login-container .header{
            text-align: center;
            border-bottom: 1px solid #0f7ae5;
        }
        .login-container .body{
            text-align: center;
        }
        .form{
            padding: 40px 40px 0 40px;
        }
        .form .uk-inline{
            width: 100%;
        }
        .form .submit-btn{
            margin-top: 20px;
            border-radius: 5px;
        }
    </style>
</head>
<body>
    <div class="login">
        <div class="background">
            <img src="images/background.jpg" alt="">
        </div>
        <div class="login-container uk-card uk-card-default uk-card-body">
            <div class="header">
                <h3>后台登录</h3>
            </div>
            <div class="body">
                <div class="form form-manager">
                    <form action="/back/manager/manager/login" method="post" name="login_form">

                        <c:if test="${not empty sessionScope.msg}">
                            <div class="uk-margin">
                                <div class="uk-alert-danger" uk-alert>
                                    <a class="uk-alert-close" uk-close></a>
                                    <p><c:out value="${sessionScope.msg}"></c:out></p>
                                </div>
                            </div>
                        </c:if>

                        <div class="uk-margin">
                            <div class="uk-inline">
                                <span class="uk-form-icon" uk-icon="icon: user"></span>
                                <input class="uk-input" type="text" name="username" required>
                            </div>
                        </div>
                        <div class="uk-margin">
                            <div class="uk-inline">
                                <span class="uk-form-icon" uk-icon="icon: lock"></span>
                                <input class="uk-input" type="password" name="password" required>
                            </div>
                        </div>
                        <div class="uk-margin uk-grid-small uk-child-width-auto" style="padding: 0 40px">
                            <label style="float: left"><input class="uk-radio" type="radio" name="login_type" value="1" checked> 管理员</label>
                            <label style="float: right"><input class="uk-radio" type="radio" name="login_type" value="2"> 商家</label>
                        </div>
                        <div class="uk-margin" style="clear: both;margin-bottom: 0;">
                            <button id="submit-btn" class="uk-button uk-button-primary submit-btn" type="submit">登录</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <script>
        function changeAction() {
            var form = document.login_form;
            var radio = form.login_type.value;
            if (radio === "2"){
                form.action = "/back/seller/inout/login";
            }
            form.submit();
        }

        window.onload = function (ev) {
            var login_btn = document.getElementById("submit-btn");
            login_btn.addEventListener("click", function (ev1) {
                changeAction();
            })
        }
    </script>
</body>
</html>
