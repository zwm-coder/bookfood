package com.huadee.bookfood.service.back;

import com.huadee.bookfood.dao.daoImpl.BaseDaoImpl;

public class BaseService {

    public static int getTotalPage(int pageSize, String table_name, String osql){
        try {
            int total = BaseDaoImpl.getTotal(table_name, osql);
            return (total % pageSize == 0) ? (total / pageSize) : (total / pageSize) + 1;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}
