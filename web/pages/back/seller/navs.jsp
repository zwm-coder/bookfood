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
        <h3>后台管理系统</h3>
    </div>
    <div class="nav-items">
        <%--<c:out value="${path}"></c:out>--%>
        <ul>
            <li class="item <c:if test="${path == '/pages/back/seller/index.jsp' || path=='/pages/back/seller/shop_add.jsp' || path=='/pages/back/seller/shop_add_step2.jsp' || path=='/pages/back/seller/shop_detail.jsp' || path=='/pages/back/seller/dish.jsp' || path=='/pages/back/seller/dish_add.jsp'}">active</c:if>">
                <a href="/back/seller/shop/list?seller_id=<c:out value="${sessionScope.seller.getSeller_id()}"></c:out>"><span class="icon" uk-icon="icon: home"></span>店铺管理</a>
            </li>
            <li class="item <c:if test="${path == '/pages/back/seller/account_info.jsp'}">active</c:if>">
                <a href="/pages/back/seller/account_info.jsp"><span class="icon" uk-icon="icon: code"></span>个人信息</a>
            </li>
            <li class="item <c:if test="${path == '/pages/back/seller/account_pwd.jsp'}">active</c:if>">
                <a href="/pages/back/seller/account_pwd.jsp"><span class="icon" uk-icon="icon: users"></span>账号安全</a>
            </li>
            <li class="item <c:if test="${path == '/pages/back/seller/shop_apply_list.jsp'}">active</c:if>">
                <a href="/back/seller/shop/apply?seller_id=<c:out value="${sessionScope.seller.getSeller_id()}"></c:out>"><span class="icon" uk-icon="icon: grid"></span>申请列表</a>
            </li>

        </ul>
    </div>
</div>