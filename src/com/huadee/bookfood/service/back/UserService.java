package com.huadee.bookfood.service.back;

import com.huadee.bookfood.bean.User;
import com.huadee.bookfood.dao.daoImpl.DaoFactory;
import com.huadee.bookfood.utils.validate.ValidateUtil;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    public static List<User> getUserList(int page, int pageSize, String queryName, String queryValue, boolean flag){
        List<User> users = new ArrayList<>();
        if (ValidateUtil.validateEmpty(queryName)){
            if (!ValidateUtil.validateEmpty(queryValue)){
                return users;
            }
            if (queryName.equals("status")){
                flag = true;
            }
        }

        try {
            users = DaoFactory.getUserDaoInstance().findAll(page, pageSize, queryName, queryValue, flag);
        } catch (Exception e){
            e.printStackTrace();
        }

        return users;
    }
}
