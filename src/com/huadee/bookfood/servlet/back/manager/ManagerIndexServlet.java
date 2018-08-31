package com.huadee.bookfood.servlet.back.manager;

import com.huadee.bookfood.dao.daoImpl.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ManagerIndexServlet", urlPatterns = "/back/manager/index")
public class ManagerIndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int shopTotal = 0;
        int userTotal = 0;
        int applyShopTotal = 0;

        try {
            shopTotal = DaoFactory.getShopDaoInstance().getTotal();
            userTotal = DaoFactory.getUserDaoInstance().getTotal();
            applyShopTotal = DaoFactory.getShopDaoInstance().getTotalApply();
        } catch (Exception e){
            e.printStackTrace();
        }

        req.setAttribute("shopTotal", shopTotal);
        req.setAttribute("userTotal", userTotal);
        req.setAttribute("applyShopTotal", applyShopTotal);

        req.getRequestDispatcher("/pages/back/manager/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
