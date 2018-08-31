package com.huadee.bookfood.utils.validate;

import javax.servlet.http.HttpServletRequest;

public class ValidateUtil {
    public static boolean validateEmpty(String data) {
        if (data == null || "".equals(data)) {
            return false;
        }
        return true;
    }

    public static boolean validateRegex(String data, String regex) {
        if (validateEmpty(data)) {
            return data.matches(regex);
        }
        return false;
    }
}
