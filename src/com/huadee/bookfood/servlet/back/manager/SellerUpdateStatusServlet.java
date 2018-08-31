package com.huadee.bookfood.servlet.back.manager;

import com.huadee.bookfood.bean.Seller;
import com.huadee.bookfood.dao.daoImpl.DaoFactory;
import com.huadee.bookfood.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SellerUpdateStatusServlet", urlPatterns = "/back/manager/seller/update/status")
public class SellerUpdateStatusServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String msg = null;

        String status = req.getParameter("status");
        String login_name = req.getParameter("login_name");

        if (ValidateUtil.validateEmpty(login_name) && ValidateUtil.validateEmpty(status)){
            try {
                Seller seller = DaoFactory.getSellerDaoInstance().findByName(login_name);
                if (seller != null){
                    seller.setStatus(Integer.parseInt(status));
                    if (!DaoFactory.getSellerDaoInstance().doUpdate(seller)){
                        msg = "修改失败，请重试";
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        req.setAttribute("msg", msg);
        resp.sendRedirect("/back/manager/seller/list");
    }
}
