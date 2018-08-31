package com.huadee.bookfood.servlet.back.seller;

import com.huadee.bookfood.bean.Seller;
import com.huadee.bookfood.service.back.SellerService;
import com.huadee.bookfood.utils.LoginCheck;
import com.huadee.bookfood.utils.Md5;
import com.huadee.bookfood.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ChangeAccountInfoServlet", urlPatterns = "/back/seller/account/modify/*")
public class ChangeAccountInfoServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null){
            if (status.equals("tel")){
                path = this.changeTel(request);
            } else if (status.equals("pwd")){
                path = this.changePwd(request);
            }
        }
        request.getRequestDispatcher(path).forward(request, response);
    }

    public String changePwd(HttpServletRequest request){
        String msg = null;
        int status = 0;
        if (LoginCheck.sellerHasLogin(request)){
            String origin_pwd = request.getParameter("origin_pwd");
            String new_pwd = request.getParameter("new_pwd");
            if (ValidateUtil.validateEmpty(origin_pwd) && ValidateUtil.validateEmpty(new_pwd)) {
                HttpSession session = request.getSession();
                Seller seller1 = (Seller)session.getAttribute("seller");
                String login_name = seller1.getLogin_name();
                if(SellerService.changePwd(login_name, origin_pwd, new_pwd)){
                    msg = "修改密码成功";
                    status = 1;
                } else {
                    msg = "请检查您的参数";
                }
            } else {
                msg = "请检查您的参数";
            }
        } else {
            msg = "未登录，拒绝操作";
        }

        request.setAttribute("msg", msg);
        request.setAttribute("status", status);
        return "/pages/back/seller/account_pwd.jsp";
    }

    public String changeTel(HttpServletRequest request){
        String phone = request.getParameter("phone");
        String msg = null;
        int status = 0;
        if (ValidateUtil.validateEmpty(phone)){
            HttpSession session = request.getSession(true);
            Seller seller = (Seller)session.getAttribute("seller");
            seller.setSeller_tel(phone);
            if (SellerService.changeInfo(seller)){
                msg = "修改联系方式成功";
                status = 1;
            } else {
                msg = "修改联系方式失败，请重试";
            }
        } else {
            msg = "请检查您的参数";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("status", status);
        return "/pages/back/seller/account_info.jsp";
    }
}
