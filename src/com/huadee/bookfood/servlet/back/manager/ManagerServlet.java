package com.huadee.bookfood.servlet.back.manager;

import com.huadee.bookfood.bean.Manager;
import com.huadee.bookfood.service.back.ManagerService;
import com.huadee.bookfood.utils.LoginCheck;
import com.huadee.bookfood.utils.Md5;
import com.huadee.bookfood.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name="ManagerServlet", urlPatterns = "/back/manager/manager/*")
public class ManagerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);
        if (status != null) {
            if ("add".equals(status)) {
                path = this.addManager(req);
            } else if ("login".equals(status)) {
                path = this.login(req);
            } else if ("logout".equals(status)){
                path = this.logout(req);
            } else if ("changepwd".equals(status)){
                path = this.changePwd(req);
                req.getRequestDispatcher(path).forward(req, resp);
                return;
            }
        }
//        req.getRequestDispatcher(path).forward(req, resp);
          resp.sendRedirect(path);
    }

    public String addManager(HttpServletRequest request) {
        String msg = "";
        String url = null;
        String login_name = request.getParameter("name");
        String password = Md5.md5(request.getParameter("password"));

        // 对参数进行验证
        if (ValidateUtil.validateEmpty(login_name) && ValidateUtil.validateEmpty(password)) {
            Manager manager = new Manager();
            manager.setLogin_name(login_name);
            manager.setPassword(password);

            try {
                ManagerService.addManager(manager);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            msg = "输入信息错误，请重新输入";
            url = "/pages/back/manager/addManager.jsp";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("url", url);
        return "/pages/forward.jsp";
    }

    public String login(HttpServletRequest request) {
        String msg = null;
        String url = "/back/manager/index";

        String login_name = request.getParameter("username");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();

        if (ValidateUtil.validateEmpty(login_name) && ValidateUtil.validateEmpty(password)) {
            Manager manager = new Manager();
            manager.setLogin_name(login_name);
            manager.setPassword(password);
            Date current_date = new Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = sdf.format(current_date);
            manager.setLast_time(date);

            if (ManagerService.login(manager)) {
                // 代表登录成功
                session.setAttribute("login_manager", true);
                session.setAttribute("manager_id", manager.getManager_id());
                session.setAttribute("login_name", manager.getLogin_name());
                session.setAttribute("last_time", manager.getLast_time());
                msg = null;
            } else {
                // 登录失败
                msg = "用户名或密码错误";
                url = "/pages/back/login.jsp";
            }
        } else {
            // 参数验证不通过
            msg = "请输入正确的信息";
            url = "/pages/back/login.jsp";
        }

        session.setAttribute("msg", msg);
        session.setAttribute("url", url);
        return url;
    }

    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("login") != null){
            session.setAttribute("login", null);
            session.setAttribute("manager_id", null);
            session.setAttribute("login_name", null);
            session.setAttribute("last_time", null);
        }
        return "/pages/back/login.jsp";
    }

    public String changePwd(HttpServletRequest request) {
        String msg = null;
        boolean flag = false;
        if (LoginCheck.managerHasLogin(request)){
            String origin_pwd = request.getParameter("origin_pwd");
            String new_pwd = request.getParameter("new_pwd");

            if (ValidateUtil.validateEmpty(origin_pwd) && ValidateUtil.validateEmpty(new_pwd)) {
                Manager manager = new Manager();
                manager.setPassword(new_pwd);
                manager.setLogin_name(request.getSession().getAttribute("login_name").toString());

                if (!ManagerService.changePwd(origin_pwd, manager)) {
                    msg = "失败！原始密码不正确！";
                } else {
                    // 代表修改密码成功
                    msg = "修改成功";
                    flag = true;
                }
            } else {
                msg = "输入参数不正确!请重新输入";
            }
        } else {
            msg = "未登录，错误操作!";
        }
        request.setAttribute("msg", msg);
        request.setAttribute("flag", flag);
        return "/pages/back/manager/manager.jsp";
    }
}
