package com.huadee.bookfood.dao;

import com.huadee.bookfood.bean.Seller;

import java.util.List;
import java.util.Map;

public interface SellerDao {

    public String[] integerList = {"status", "seller_id"};
    /**
     * 创建商家账号
     * @param seller  商家类实例
     * @return
     * @throws Exception
     */
    public boolean doCreate(Seller seller) throws Exception;

    /**
     * 更新商家账号信息
     * @param seller
     * @return
     * @throws Exception
     */
    public boolean doUpdate(Seller seller) throws Exception;

    /**
     * 删除商家账号信息
     * @param seller_name 商家账号登录名
     * @return
     * @throws Exception
     */
    public boolean doDelete(String seller_name) throws Exception;

    /**
     * 通过商家账号登录名查找账号信息
     * @param seller_name
     * @return
     * @throws Exception
     */
    public Seller findByName(String seller_name) throws Exception;

    /**
     * 查找所有商家信息
     * @return
     * @throws Exception
     */
    public List<Seller> findAll(int page, int pageSize, String login_name, int status) throws Exception;

    /**
     * 获取总记录数
     * @return
     * @throws Exception
     */
    public int getTotal() throws Exception;

}
