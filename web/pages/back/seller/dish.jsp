<%--
  Created by IntelliJ IDEA.
  User: zm
  Date: 2018/7/17
  Time: 21:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.bookfood.cn/c" %>
<c:set value="${requestScope.dish}" var="dish"></c:set>
<html>
<head>
    <%@ include file="head.jsp"%>
    <style>
        .img-responsive {
            display: inline-block;
            height: auto;
            max-width: 100%;
        }
        .picture{
            text-align: center;
            padding: 30px;
            border-bottom: 20px solid #ebebeb;
        }
        .picture form{
            display: inline-block;
            width: 250px;
        }
        .dish-container .info{
            width: 50%;
            padding: 10px 30px;
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
                <h3>菜品图片</h3>
            </div>

            <div class="dish-container">
                <div class="picture">
                    <%--<img src="/upload/img/<c:out value="${dish.getPicture()}"></c:out>" style="max-height: 150px" alt="">--%>

                    <table class="uk-table">
                        <tr>
                            <%--<th>展示图片</th>--%>
                            <td>
                                <img src="/upload/img/<c:out value="${dish.getPicture()}"></c:out>" width="250" class="img-responsive" alt="">
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <form action="/back/seller/dish/modify/picture?dish_id=<c:out value="${dish.getDish_id()}"></c:out>" method="post" enctype="multipart/form-data">
                                    <div class="js-upload uk-placeholder uk-text-center">
                                        <span uk-icon="icon: cloud-upload"></span>
                                        <span class="uk-text-middle">将图片拖至此处或</span>
                                        <div uk-form-custom>
                                            <input type="file" multiple name="picture">
                                            <span class="uk-link">点击选择</span>
                                        </div>
                                    </div>
                                    <div class="uk-margin" style="text-align: center">
                                        <button type="submit" class="uk-button uk-button-primary uk-button-small">上传图片</button>
                                    </div>
                                </form>
                            </td>
                        </tr>
                    </table>
                </div>

                <div class="title">
                    <h3>基本信息</h3>
                </div>
                <div class="info">
                    <form action="/back/seller/dish/modify/info" method="get">
                        <input type="text" name="dish_id" value="<c:out value="${dish.getDish_id()}"></c:out>" hidden>
                        <table class="uk-table">
                            <tr>
                                <th>菜品名称</th>
                                <td>
                                    <div class="uk-margin">
                                        <div class="uk-form-controls">
                                            <input class="uk-input" name="dish_name" type="text" value="<c:out value="${dish.getDish_name()}"></c:out>">
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>简介</th>
                                <td>
                                    <div class="uk-margin">
                                        <div class="uk-form-controls">
                                            <textarea class="uk-textarea" name="descp"  rows="5"><c:out value="${dish.getDescp()}"></c:out></textarea>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>原价</th>
                                <td>
                                    <div class="uk-margin">
                                        <div class="uk-form-controls">
                                            <input class="uk-input" name="original_price" type="text" value="<c:out value="${dish.getOriginal_price()}"></c:out>">
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <th>现价</th>
                                <td>
                                    <div class="uk-margin">
                                        <div class="uk-form-controls">
                                            <input class="uk-input" name="current_price" type="text" value="<c:out value="${dish.getCurrent_price()}"></c:out>">
                                        </div>
                                    </div>
                                </td>
                            </tr>
                        </table>

                        <div class="uk-margin" style="text-align: center">
                            <button type="submit" class="uk-button uk-button-primary uk-button-small">修改</button>
                            <button type="button" class="uk-button uk-button-default uk-button-small" onclick="goBack()">取消</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
