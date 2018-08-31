package com.huadee.bookfood.servlet.back.manager;

import com.huadee.bookfood.bean.Shop;
import com.huadee.bookfood.dao.daoImpl.DaoFactory;
import com.huadee.bookfood.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ShopDetailServlet", urlPatterns = "/back/manager/shop/detail")
public class ShopDetailServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String shop_name = request.getParameter("shop_name");
        String accept_flag = request.getParameter("flag");
        Shop shop = null;
        String path = "/pages/back/manager/shop_detail.jsp";

        if (accept_flag == null){
            if(ValidateUtil.validateEmpty(shop_name)){
                try {
                    shop = DaoFactory.getShopDaoInstance().findByName(shop_name);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        } else if (accept_flag.equals("1")){
            // 通过审核
            path = this.changeStatus(request, 1);
        } else if (accept_flag.equals("2")) {
            // 未通过审核
            path = this.changeStatus(request, 2);
        }

        request.setAttribute("shop", shop);
        request.getRequestDispatcher(path).forward(request, response);
    }

    public String changeStatus(HttpServletRequest request, int status){
        Shop shop = new Shop();
        shop = (Shop)request.getSession().getAttribute("shop");
        shop.setStatus(status);
        try {
            if (DaoFactory.getShopDaoInstance().doUpdate(shop)){
                // 修改成功
                return "/back/manager/shop/list";
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return "/pages/back/manager/shop_detail.jsp";
    }
}
