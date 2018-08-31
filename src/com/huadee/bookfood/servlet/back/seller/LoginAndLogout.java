package com.huadee.bookfood.servlet.back.seller;

import com.huadee.bookfood.bean.Seller;
import com.huadee.bookfood.dao.daoImpl.DaoFactory;
import com.huadee.bookfood.utils.Md5;
import com.huadee.bookfood.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginAndLogout", urlPatterns = "/back/seller/inout/*")
public class LoginAndLogout extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null){
            if (status.equals("login")){
                path = this.login(request);
                response.sendRedirect(path);
                return;
            } else if (status.equals("logout")){
                path = this.logout(request);
            }
        }

        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    public String login(HttpServletRequest request){
        String msg = null;
        String url = "/pages/back/login.jsp";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        if (!ValidateUtil.validateEmpty(username) && !ValidateUtil.validateEmpty(password)){
            // 参数不正确
            msg = "用户名或密码不可为空";
        } else {
            // 参数验证通过，验证用户名和对应的密码
            try {
                Seller seller = DaoFactory.getSellerDaoInstance().findByName(username);
                if (seller == null) {
                    msg = "用户不存在";
                } else {
                    // 比对密码是否正确
                    if (!seller.getPassword().equals(Md5.md5(password))){
                        msg = "用户名或密码错误";
                    } else {
                        // 登录成功
                        session.setAttribute("seller", seller);
                        session.setAttribute("login_seller", true);
                        url = "/back/seller/shop/list?seller_id="+seller.getSeller_id();
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        session.setAttribute("msg", msg);
        return url;
    }

    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("login_seller") != null){
            session.setAttribute("login_seller", null);
            session.setAttribute("seller", null);
        }
        return "/pages/back/login.jsp";
    }
}

