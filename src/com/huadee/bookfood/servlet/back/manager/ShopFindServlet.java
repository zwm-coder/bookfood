package com.huadee.bookfood.servlet.back.manager;

import com.huadee.bookfood.bean.Shop;
import com.huadee.bookfood.dao.daoImpl.DaoFactory;
import com.huadee.bookfood.service.back.BaseService;
import com.huadee.bookfood.service.back.ShopService;
import com.huadee.bookfood.utils.GetPage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ShopFindServlet", urlPatterns = "/back/manager/shop/list")
public class ShopFindServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageSize = 2;
//        int totalPage = ShopService.getTotalPage(pageSize);
        int totalPage = BaseService.getTotalPage(pageSize, "shop", null);
        int page = GetPage.getPage(request, totalPage);

        String sstatus = request.getParameter("status");
        if (sstatus == null){
            sstatus = "1";
        }
        int status = Integer.parseInt(sstatus);
        List<Shop> shopList = new ArrayList<>();

        String shop_name = request.getParameter("shop_name");
        if (shop_name == null){
            // 查询所有
            try {
                shopList = DaoFactory.getShopDaoInstance().findAll(page, pageSize, status);
            } catch (Exception e){
                e.printStackTrace();
            }
        } else {
            // 根据名字查询
            try {
                Shop shop = DaoFactory.getShopDaoInstance().findByName(shop_name);
                shopList.add(shop);
                totalPage = 1;
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        request.setAttribute("shopList", shopList);
        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("status", status);
        request.getRequestDispatcher("/pages/back/manager/shop.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
