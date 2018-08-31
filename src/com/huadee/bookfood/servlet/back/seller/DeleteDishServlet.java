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

@WebServlet(name = "DeleteDishServlet", urlPatterns = "/back/seller/dish/delete")
public class DeleteDishServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";

        String dish_id = request.getParameter("dish_id");
        if (ValidateUtil.validateEmpty(dish_id)){
            if (DishService.deleteDish(Integer.parseInt(dish_id))){
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
