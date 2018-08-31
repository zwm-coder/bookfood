package com.huadee.bookfood.servlet.back.manager;

import com.huadee.bookfood.bean.Seller;
import com.huadee.bookfood.service.back.BaseService;
import com.huadee.bookfood.service.back.SellerService;
import com.huadee.bookfood.utils.GetPage;
import com.huadee.bookfood.utils.validate.ValidateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;


@WebServlet(name = "SellerFindAllServlet", urlPatterns = "/back/manager/seller/list")
public class SellerFindAllServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Seller> sellerList = new ArrayList<Seller>();

        int pageSize = 3;
//        int totalPage = SellerService.getTotalPage(pageSize);
        int totalPage = BaseService.getTotalPage(pageSize, "seller", null);

        int page = GetPage.getPage(req, totalPage);
//        String spage = req.getParameter("page");
//        if (spage == null || spage.equals("")){
//            spage = "1";
//        }
//        int page = Integer.parseInt(spage);
//
//        if (page < 1) {
//            page = 1;
//        }
//        if (page > totalPage) {
//            page = totalPage;
//        }

        String login_name = req.getParameter("login_name");
        String sstatus = req.getParameter("status");
        int status = -1;
        if (ValidateUtil.validateEmpty(sstatus)){
            status = Integer.parseInt(sstatus);
        }

        sellerList = SellerService.queryAllSeller(page, pageSize, login_name, status);

        req.setAttribute("sellerList", sellerList);
        req.setAttribute("page", page);
        req.setAttribute("totalPage", totalPage);
        req.getRequestDispatcher("/pages/back/manager/seller.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
