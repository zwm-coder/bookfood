package com.huadee.bookfood.dao.daoImpl;

import com.huadee.bookfood.dao.*;

public class DaoFactory {
    public static ManagerDao getManagerDaoInstance() {
        return new ManagerDaoImpl();
    }
    public static SellerDao getSellerDaoInstance() {
        return new SellerDaoImpl();
    }

    public static ShopDao getShopDaoInstance(){
        return new ShopDaoImpl();
    }

    public static UserDao getUserDaoInstance() {
        return new UserDaoImpl();
    }

    public static FeedBackDao getFeedBackDaoInstance() {
        return new FeedBackDaoImpl();
    }

    public static DishDao getDishDaoInstance() {
        return new DishDaoImpl();
    }
}
