package com.huadee.bookfood.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginCheck {
    public static boolean managerHasLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("login_manager") == null) {
            return false;
        }
        return true;
    }

    public static boolean sellerHasLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        if (session == null || session.getAttribute("login_seller") == null) {
            return false;
        }
        return true;
    }
}
