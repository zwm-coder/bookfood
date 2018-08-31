package com.huadee.bookfood.servlet.back.seller;

import com.huadee.bookfood.bean.Dish;
import com.huadee.bookfood.service.back.DishService;
import com.huadee.bookfood.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GetDishDetailServlet", urlPatterns = "/back/seller/dish/detail")
public class GetDishDetailServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String dish_id = request.getParameter("dish_id");
        if (!ValidateUtil.validateEmpty(dish_id)){
            request.getRequestDispatcher("/pages/back/errors.jsp").forward(request, response);
            return;
        }

        Dish dish = DishService.getDishDetailById(Integer.parseInt(dish_id));

        request.setAttribute("dish", dish);
        request.getRequestDispatcher("/pages/back/seller/dish.jsp").forward(request, response);
    }
}
