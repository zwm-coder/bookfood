package com.huadee.bookfood.utils;

import javax.servlet.http.HttpServletRequest;

public class GetPage {

    public static int getPage(HttpServletRequest request, int totalPage){
        String spage = request.getParameter("page");
        if (spage == null || spage.equals("")){
            spage = "1";
        }
        int page = Integer.parseInt(spage);

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage && totalPage !=0) {
            page = totalPage;
        }

        return page;
    }
}
