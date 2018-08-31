package com.huadee.bookfood.dao;

import com.huadee.bookfood.bean.FeedBack;

import java.util.List;

public interface FeedBackDao {

    /**
     *  根据条件查询所有的反馈记录
     * @param page              第几页
     * @param pageSize          每页大小
     * @param queryName         查询的条件名
     * @param queryValue        查询条件值, 允许通过feedback_id，user_id, shop_id, status等条件查询
     * @return
     * @throws Exception
     */
    public List<FeedBack> findAll(int page, int pageSize, String queryName, String queryValue) throws Exception;


    /**
     *  修改记录
     * @param feedBack
     * @return
     * @throws Exception
     */
    public boolean doChange(FeedBack feedBack) throws Exception;

    /**
     *  通过ID号查找记录
     * @param feedback_id
     * @return
     * @throws Exception
     */
    public FeedBack findById(int feedback_id) throws Exception;

    public int getTotal() throws Exception;

}
