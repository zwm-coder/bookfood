package com.huadee.bookfood.servlet.back.seller;

import com.huadee.bookfood.bean.Shop;
import com.huadee.bookfood.service.back.BaseService;
import com.huadee.bookfood.service.back.ShopService;
import com.huadee.bookfood.utils.GetPage;
import com.huadee.bookfood.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "GetApplyShopListServlet", urlPatterns = "/back/seller/shop/apply")
public class GetApplyShopListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String seller_id = req.getParameter("seller_id");
        int pageSize = 10;
        String osql= "select count(*) as total from shop where seller_id="+seller_id+" and status=0";
        int totalPage = BaseService.getTotalPage(pageSize, "shop", osql);
        int page = GetPage.getPage(req, totalPage);
        List<Shop> shopList = new ArrayList<>();

        if (ValidateUtil.validateEmpty(seller_id)){
            shopList = ShopService.getShopList(page, pageSize, Integer.parseInt(seller_id), 0);
            path = "/pages/back/seller/shop_apply_list.jsp";
        }
        req.setAttribute("page", page);
        req.setAttribute("totalPage", totalPage);
        req.setAttribute("shopList", shopList);

        req.getRequestDispatcher(path).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
