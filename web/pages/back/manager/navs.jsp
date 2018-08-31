<%--
  Created by IntelliJ IDEA.
  User: zm
  Date: 2018/7/14
  Time: 20:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://www.bookfood.cn/c" %>
<%
    String path = request.getRequestURI();
%>
<c:set value="<%=path%>" var="path"></c:set>
<div class="left">
    <div class="title">
        <h3>在线预约管理系统</h3>
    </div>
    <div class="nav-items">
        <%--<c:out value="${path}"></c:out>--%>
        <ul>
            <li class="item <c:if test="${path == '/pages/back/manager/index.jsp'}">active</c:if>">
                <a href="/back/manager/index"><span class="icon" uk-icon="icon: home"></span>首页</a>
            </li>
            <li class="item <c:if test="${path == '/pages/back/manager/seller.jsp'}">active</c:if>">
                <a href="/back/manager/seller/list"><span class="icon" uk-icon="icon: code"></span>商家管理</a>
            </li>
            <li class="item <c:if test="${path == '/pages/back/manager/user.jsp'}">active</c:if>">
                <a href="/back/manager/user/list"><span class="icon" uk-icon="icon: users"></span>用户管理</a>
            </li>
            <li class="item <c:if test="${path == '/pages/back/manager/shop.jsp' || path == '/pages/back/manager/shop_detail.jsp'}">active</c:if>">
                <a href="/back/manager/shop/list?status=0"><span class="icon" uk-icon="icon: grid"></span>申请列表</a>
            </li>
            <li class="item <c:if test="${path == '/pages/back/manager/manager.jsp'}">active</c:if>">
                <a href="/pages/back/manager/manager.jsp"><span class="icon" uk-icon="icon: nut"></span>我的账户</a>
            </li>
            <li class="item <c:if test="${path == '/pages/back/manager/feedback.jsp' || path == '/pages/back/manager/feedback_detail.jsp'}">active</c:if>">
                <a href="/back/manager/feedback/list"><span class="icon" uk-icon="icon:  file-edit"></span>反馈处理</a>
            </li>

        </ul>
    </div>
</div>