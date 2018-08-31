package com.huadee.bookfood.servlet.back.seller;

import com.huadee.bookfood.bean.Dish;
import com.huadee.bookfood.dao.daoImpl.DaoFactory;
import com.huadee.bookfood.service.back.DishService;
import com.huadee.bookfood.utils.UploadFile;
import com.huadee.bookfood.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "ChangeDishInfoServlet", urlPatterns = "/back/seller/dish/modify/*")
public class ChangeDishInfoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
        if (status != null){
            if (status.equals("picture")){
                path = this.changePicture(request);
            } else if (status.equals("info")){
                path = this.changeOtherInfo(request);
            }
        }

        request.getRequestDispatcher(path).forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);

    }

    public String changePicture(HttpServletRequest request){
        String dish_id = request.getParameter("dish_id");
        if (ValidateUtil.validateEmpty(dish_id)){
            Date current_date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String date = sdf.format(current_date);

            String fileName = "dish_" + dish_id + "_" + date;
            UploadFile.uploadImg(request, fileName);
            DishService.changePicture(Integer.parseInt(dish_id), fileName);
            return "/back/seller/dish/detail?dish_id="+dish_id;
        }

        return "/pages/errors.jsp";
    }

    public String changeOtherInfo(HttpServletRequest request){
        String dish_name = request.getParameter("dish_name");
        String descp = request.getParameter("descp");
        String original_price = request.getParameter("original_price");
        String current_price = request.getParameter("current_price");
        String dish_id = request.getParameter("dish_id");

        try {
            Dish dish = DaoFactory.getDishDaoInstance().findById(Integer.parseInt(dish_id));
            dish.setDish_name(dish_name);
            dish.setDescp(descp);
            dish.setOriginal_price(Float.parseFloat(original_price));
            dish.setCurrent_price(Float.parseFloat(current_price));
            DishService.changeOtherInfo(dish);
        } catch (Exception e){
            e.printStackTrace();
        }

        return "/pages/back/seller/index.jsp";
    }
}
