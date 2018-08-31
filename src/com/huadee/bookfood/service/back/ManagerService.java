package com.huadee.bookfood.service.back;

import com.huadee.bookfood.bean.Manager;
import com.huadee.bookfood.dao.daoImpl.DaoFactory;
import com.huadee.bookfood.utils.Md5;

public class ManagerService {
    /**
     * 添加管理员
     * @param manager
     * @return
     * @throws Exception
     */
    public static boolean addManager(Manager manager) throws Exception{
        try {
            if (DaoFactory.getManagerDaoInstance().findByLoginName(manager.getLogin_name()) == null) {
                return DaoFactory.getManagerDaoInstance().doCreate(manager);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 管理员登录
     * @param manager
     * @return
     */
    public static boolean login(Manager manager){
        boolean flag = false;
        try {
            if (DaoFactory.getManagerDaoInstance().findByLoginName(manager.getLogin_name()) != null) {
                // 存在此账号，比对密码是否正确
                if (DaoFactory.getManagerDaoInstance().findByLoginName(manager.getLogin_name()).getPassword().equals(Md5.md5(manager.getPassword()))) {
                    // 密码相同，登录成功
                    DaoFactory.getManagerDaoInstance().doUpdateLoginTime(manager);
                    flag = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return flag;
    }

    /**
     *  改变密码
     * @param manager
     * @return
     */
    public static boolean changePwd(String origin_pwd, Manager manager) {
        try {
            Manager manager1 = DaoFactory.getManagerDaoInstance().findByLoginName(manager.getLogin_name());
            if (manager1.getPassword().equals(Md5.md5(origin_pwd))) {
                DaoFactory.getManagerDaoInstance().doUpdate(manager);
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
