package com.huadee.bookfood.service.back;

import com.huadee.bookfood.bean.Dish;
import com.huadee.bookfood.bean.Shop;
import com.huadee.bookfood.dao.daoImpl.DaoFactory;

import java.util.ArrayList;
import java.util.List;

public class ShopService {
    public static List<Shop> getShopList(int page, int pageSize, int seller_id, int status){
        List<Shop> shopList = new ArrayList<>();
        try {
            shopList = DaoFactory.getShopDaoInstance().getShopListBySeller(page, pageSize, seller_id, status);
        } catch (Exception e){
            e.printStackTrace();
        }

        return shopList;
    }

    public static Shop getShopDetail(String shop_name){
        Shop shop = null;
        try {
            shop = DaoFactory.getShopDaoInstance().findByName(shop_name);
            if (shop != null){
                List<Dish> dishList = DaoFactory.getDishDaoInstance().findAll(shop.getShop_id());
                shop.setDishList(dishList);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return shop;
    }

    public static boolean createShop(Shop shop){
        try {
            return DaoFactory.getShopDaoInstance().doCreate(shop);
        } catch (Exception e){
            e.printStackTrace();
        }

        return false;
    }
}
