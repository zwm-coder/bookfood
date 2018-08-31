package com.huadee.bookfood.dao;

import com.huadee.bookfood.bean.Manager;

public interface ManagerDao {
    /**
     * 创建管理员
     * @param manager
     * @return
     * @throws Exception
     */
    Boolean doCreate(Manager manager) throws Exception;

    /**
     * 根据ID查询管理员
     * @param login_name
     * @return
     * @throws Exception
     */
    Manager findByLoginName(String login_name) throws Exception;

    /**
     * 更新管理员信息
     * @param manager
     * @throws Exception
     */
    void doUpdate(Manager manager) throws Exception;

    /**
     * 根据ID删除管理员信息
     * @param min
     * @return
     * @throws Exception
     */
    Boolean doDelete(int min) throws Exception;

    boolean doUpdateLoginTime(Manager manager) throws Exception;
}
