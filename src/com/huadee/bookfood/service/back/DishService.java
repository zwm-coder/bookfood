package com.huadee.bookfood.service.back;

import com.huadee.bookfood.bean.Dish;
import com.huadee.bookfood.dao.daoImpl.DaoFactory;

public class DishService {
    public static boolean changePicture(int dish_id, String picture){
        try {
            Dish dish = DaoFactory.getDishDaoInstance().findById(dish_id);
            if (dish != null){
                dish.setPicture(picture);
                return DaoFactory.getDishDaoInstance().changeInfo(dish);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean changeOtherInfo(Dish dish){
        try {
            return DaoFactory.getDishDaoInstance().changeInfo(dish);
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static Dish getDishDetailById(int dish_id){
        Dish dish = null;
        try {
            dish = DaoFactory.getDishDaoInstance().findById(dish_id);
        }catch (Exception e){
            e.printStackTrace();
        }

        return dish;
    }

    public static boolean addDish(Dish dish){
        try {
            return DaoFactory.getDishDaoInstance().addDish(dish);
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public static boolean deleteDish(int dish_id){
        try {
            return DaoFactory.getDishDaoInstance().deleteDish(dish_id);
        } catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
}
