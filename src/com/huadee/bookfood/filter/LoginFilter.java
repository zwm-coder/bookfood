package com.huadee.bookfood.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;

        String currentUrl = request.getRequestURI();
        String ctxPath = request.getContextPath();

        String targetUrl = currentUrl.substring(ctxPath.length());
        HttpSession session = request.getSession();

//        if (!("/pages/back/login.jsp".equals(targetUrl)) && targetUrl.indexOf("back") != -1) {
//            // 除去登录页面不需要验证，其余页面都需要验证
//            if (session == null || session.getAttribute("login") == null) {
//                // 如果未登录, 则跳转到登录页面
//                response.sendRedirect("/pages/back/login.jsp");
//                return;
//            } else {
//                // 如果已经登录，则正常跳转
//                filterChain.doFilter(request, response);
//                return;
//            }
//        } else {
//            // 请求的是登录页面，不需要拦截
//            filterChain.doFilter(request, response);
//            return;
//        }

        // 登录过滤器
        if (targetUrl.indexOf("back") != -1 && !targetUrl.equals("/pages/back/login.jsp")){
            if (targetUrl.indexOf("back/manager") != -1 && !targetUrl.equals("/back/manager/manager/login")){
                // 访问管理员后台页面
                if (session == null || session.getAttribute("login_manager") == null){
                    response.sendRedirect("/pages/back/login.jsp");
                    return;
                }
            } else if (targetUrl.indexOf("back/seller") != -1 && !targetUrl.equals("/back/seller/inout/login")){
                // 访问商家后台页面
                if (session == null || session.getAttribute("login_seller") == null){
                    response.sendRedirect("/pages/back/login.jsp");
                    return;
                }
            }
            filterChain.doFilter(request, response);
            return;
        }
        filterChain.doFilter(request, response);
        return;
    }

    @Override
    public void destroy() {

    }
}
