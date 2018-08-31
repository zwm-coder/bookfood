package com.huadee.bookfood.dao;

import com.huadee.bookfood.bean.Shop;

import java.util.List;

public interface ShopDao {

    /**
     * 查找所有的店铺信息
     * @param page        页数
     * @param pageSize    每页大小
     * @param status      账号状态
     * @return
     * @throws Exception
     */
    public List<Shop> findAll(int page, int pageSize, int status) throws Exception;

    /**
     * 查询总条数
     * @return
     * @throws Exception
     */
    public int getTotal() throws Exception;

    /**
     * 通过名称查找
     * @return
     * @throws Exception
     */
    public Shop findByName(String name) throws Exception;

    /**
     * 更新信息
     * @param shop
     * @return
     * @throws Exception
     */
    public boolean doUpdate(Shop shop) throws Exception;

    /**
     * 获取申请中的店铺数量
     * @return
     * @throws Exception
     */
    public int getTotalApply() throws Exception;

    /**
     * 根据商家ID查询指定的店铺列表
     * @param seller_id
     * @param status
     * @return
     * @throws Exception
     */
    public List<Shop> getShopListBySeller(int page, int pageSize, int seller_id, int status) throws Exception;

    public boolean doCreate(Shop shop) throws Exception;
}
