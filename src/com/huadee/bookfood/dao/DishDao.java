package com.huadee.bookfood.dao;

import com.huadee.bookfood.bean.Dish;

import java.util.List;

public interface DishDao {

    public List<Dish> findAll(int shop_id) throws Exception;

    public Dish findById(int dish_id) throws Exception;

    public boolean changeInfo(Dish dish) throws Exception;

    public boolean addDish(Dish dish) throws Exception;

    public boolean deleteDish(int dish_id) throws Exception;
}
