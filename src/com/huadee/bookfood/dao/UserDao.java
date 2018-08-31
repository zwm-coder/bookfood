package com.huadee.bookfood.dao;

import com.huadee.bookfood.bean.User;

import java.util.List;

public interface UserDao {

    /**
     * 查询用户所有数据
     * @param page         第几页
     * @param pageSize    每页大小
     * @param queryName    按条件查询的查询条件
     * @param queryValue   查询条件的值
     * @param flag          是否是int型的值，true ：int
     * @return
     * @throws Exception
     */
    public List<User> findAll(int page, int pageSize, String queryName, String queryValue, boolean flag) throws Exception;

    public int getTotal() throws Exception;

    public boolean changeStatus(int user_id, int status) throws Exception;
}
