package com.huadee.bookfood.servlet.back.manager;

import com.huadee.bookfood.dao.daoImpl.DaoFactory;
import com.huadee.bookfood.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserChangeStatusServlet", urlPatterns = "/back/manager/user/update/status")
public class UserChangeStatusServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String suser_id = request.getParameter("user_id");
        String sstatus = request.getParameter("status");

        if (ValidateUtil.validateEmpty(suser_id) && ValidateUtil.validateEmpty(sstatus)){
            int user_id = Integer.parseInt(suser_id);
            int status = Integer.parseInt(sstatus);
            if (status == 0 || status == 1){
                try {
                    DaoFactory.getUserDaoInstance().changeStatus(user_id, status);
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }

        request.getRequestDispatcher("/back/manager/user/list").forward(request, response);

    }
}
