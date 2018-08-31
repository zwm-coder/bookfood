package com.huadee.bookfood.service.back;

import com.huadee.bookfood.bean.Seller;
import com.huadee.bookfood.dao.daoImpl.DaoFactory;
import com.huadee.bookfood.utils.Md5;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SellerService {

    public static List<Seller> queryAllSeller(int page, int pageSize, String login_name, int status) {
        List<Seller> sellerList = new ArrayList<Seller>();
        try {
            sellerList = DaoFactory.getSellerDaoInstance().findAll(page, pageSize, login_name, status);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sellerList;
    }

    public static boolean changeInfo(Seller seller){
        try {
            return DaoFactory.getSellerDaoInstance().doUpdate(seller);
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

    public static boolean changePwd(String login_name,String origin_pwd, String new_pwd){
        try {
            Seller seller = DaoFactory.getSellerDaoInstance().findByName(login_name);
            if (seller != null){
                if (seller.getPassword().equals(Md5.md5(origin_pwd))){
                    //代表密码相同，通过验证
                    seller.setPassword(Md5.md5(new_pwd));
                    return DaoFactory.getSellerDaoInstance().doUpdate(seller);
                }
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }

}
