package com.huadee.bookfood.servlet.back.seller;

import com.huadee.bookfood.bean.Shop;
import com.huadee.bookfood.service.back.ShopService;
import com.huadee.bookfood.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetShopDetailServlet", urlPatterns = "/back/seller/shop/detail")
public class GetShopDetailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String shop_name = request.getParameter("shop_name");
        Shop shop = null;
        if (ValidateUtil.validateEmpty(shop_name)){
            shop = ShopService.getShopDetail(shop_name);
        }
        request.getSession().setAttribute("shop_detail", shop);
        request.getRequestDispatcher("/pages/back/seller/shop_detail.jsp").forward(request, response);
    }
}
