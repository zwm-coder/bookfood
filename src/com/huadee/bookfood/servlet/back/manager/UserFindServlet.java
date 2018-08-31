package com.huadee.bookfood.servlet.back.manager;

import com.huadee.bookfood.bean.User;
import com.huadee.bookfood.service.back.BaseService;
import com.huadee.bookfood.service.back.UserService;
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

@WebServlet(name = "UserFindServlet", urlPatterns = "/back/manager/user/list")
public class UserFindServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pageSize = 3;
//        int totalPage = UserService.getTotalPage(pageSize);
        int totalPage = BaseService.getTotalPage(pageSize, "user", null);
        int page = GetPage.getPage(request, totalPage);

        String login_name = request.getParameter("login_name");
        String status = request.getParameter("status");

        String queryName = null;
        String queryValue = null;
        if (ValidateUtil.validateEmpty(login_name)){
            queryName = "login_name";
            queryValue = login_name;
        } else if (ValidateUtil.validateEmpty(status)){
            queryName = "status";
            queryValue = status;
        }

        List<User> users = new ArrayList<>();

        try {
            users = UserService.getUserList(page, pageSize, queryName, queryValue, false);
        } catch (Exception e){
            e.printStackTrace();
        }

        request.setAttribute("page", page);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("userList", users);
        request.setAttribute("login_name", login_name);
        request.setAttribute("queryValue", queryValue);
        request.getRequestDispatcher("/pages/back/manager/user.jsp").forward(request, response);

    }
}
