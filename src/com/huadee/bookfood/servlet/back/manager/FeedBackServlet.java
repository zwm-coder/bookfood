package com.huadee.bookfood.servlet.back.manager;

import com.huadee.bookfood.bean.FeedBack;
import com.huadee.bookfood.dao.daoImpl.DaoFactory;
import com.huadee.bookfood.service.back.BaseService;
import com.huadee.bookfood.service.back.FeedBackService;
import com.huadee.bookfood.utils.GetPage;
import com.huadee.bookfood.utils.validate.ValidateUtil;
import com.sun.deploy.net.HttpRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "FeedBackServlet", urlPatterns = "/back/manager/feedback/*")
public class FeedBackServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/pages/errors.jsp";
        String status = req.getRequestURI().substring(req.getRequestURI().lastIndexOf("/") + 1);

        if (status != null){
            if (status.equals("list")){
                path = this.getList(req);
            } else if (status.equals("detail")){
                path = this.getDetail(req);
            } else if (status.equals("handle")){
                path = this.handleFeedback(req);
            }
        }

        req.getRequestDispatcher(path).forward(req, resp);
    }

    public String getList(HttpServletRequest request){

         int pageSize = 1;
         int totalPage = BaseService.getTotalPage(pageSize, "feedback", null);
         int page = GetPage.getPage(request, totalPage);

         String queryName = request.getParameter("query_name");
         String queryValue = request.getParameter("query_value");

         if (ValidateUtil.validateEmpty(queryName)){
             String osql = "select count(*) as total from feedback where " + queryName + "=" + queryValue;
             totalPage = BaseService.getTotalPage(pageSize, "feedback", osql);
         }

         List<FeedBack> feedBackList = null;
         try {
             feedBackList = DaoFactory.getFeedBackDaoInstance().findAll(page, pageSize, queryName, queryValue);
         } catch (Exception e){
             e.printStackTrace();
         }

        String url=request.getScheme()+"://";
        url+=request.getHeader("host");
        url+=request.getRequestURI();
        if(request.getQueryString()!=null)
            url+="?"+request.getQueryString();

        String ptm = request.getParameter("page");
        if (ValidateUtil.validateEmpty(ptm)){
            url = url.replace("?page="+ptm, "");
            url = url.replace("&page="+ptm, "");
        }

         request.setAttribute("page", page);
         request.setAttribute("totalPage", totalPage);
         request.setAttribute("feedBackList", feedBackList);
         request.setAttribute("url", url);

         return "/pages/back/manager/feedback.jsp";
    }

    public String getDetail(HttpServletRequest request){
        String feedback_id = request.getParameter("feedback_id");
        if (ValidateUtil.validateEmpty(feedback_id)){
            try {
                FeedBack feedBack = DaoFactory.getFeedBackDaoInstance().findById(Integer.parseInt(feedback_id));
                request.setAttribute("feedback", feedBack);
                return "/pages/back/manager/feedback_detail.jsp";
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        return "/pages/errors.jsp";
    }

    public String handleFeedback(HttpServletRequest request){
        String result = request.getParameter("result");
        String feedback_id = request.getParameter("feedback_id");

        if (ValidateUtil.validateEmpty(result) && ValidateUtil.validateEmpty(feedback_id)){
            // 验证通过
            if (FeedBackService.handleFeedback(Integer.parseInt(feedback_id), result)){
                return "/back/manager/feedback/list";
            }
        }

        return "/pages/errors.jsp";
    }
}
