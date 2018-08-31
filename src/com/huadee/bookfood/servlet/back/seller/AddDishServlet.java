package com.huadee.bookfood.servlet.back.seller;

import com.huadee.bookfood.bean.Dish;
import com.huadee.bookfood.bean.Seller;
import com.huadee.bookfood.service.back.DishService;
import com.huadee.bookfood.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AddDishServlet", urlPatterns = "/back/seller/dish/add")
public class AddDishServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";

        String dish_name = request.getParameter("dish_name");
        String shop_id = request.getParameter("shop_id");
        String original_price = request.getParameter("original_price");
        String current_price = request.getParameter("current_price");
        String descp = request.getParameter("descp");

        if (ValidateUtil.validateEmpty(dish_name) && ValidateUtil.validateEmpty(shop_id) && ValidateUtil.validateEmpty(current_price)){
            Dish dish = new Dish();
            dish.setDish_name(dish_name);
            dish.setShop_id(Integer.parseInt(shop_id));
            dish.setOriginal_price(Float.parseFloat(original_price));
            dish.setCurrent_price(Float.parseFloat(current_price));
            dish.setDescp(descp);
            if (DishService.addDish(dish)){
                HttpSession session = request.getSession();
                Seller seller = (Seller)session.getAttribute("seller");
                path = "/back/seller/shop/list?seller_id="+seller.getSeller_id();
            }
        }

        request.getRequestDispatcher(path).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
