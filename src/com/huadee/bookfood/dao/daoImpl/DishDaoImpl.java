package com.huadee.bookfood.dao.daoImpl;

import com.huadee.bookfood.bean.Dish;
import com.huadee.bookfood.dao.DishDao;
import com.huadee.bookfood.jdbc.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DishDaoImpl implements DishDao {
    @Override
    public List<Dish> findAll(int shop_id) throws Exception {

        Connection connection = DatabaseConnect.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Dish> dishList = new ArrayList<>();

        String sql = "select * from dish where shop_id=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, shop_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Dish dish = new Dish();
                dish.setDish_id(resultSet.getInt("dish_id"));
                dish.setDish_name(resultSet.getString("dish_name"));
                dish.setShop_id(resultSet.getInt("shop_id"));
                dish.setCurrent_price(resultSet.getFloat("current_price"));
                dish.setOriginal_price(resultSet.getFloat("original_price"));
                dish.setDescp(resultSet.getString("descp"));
                dish.setPicture(resultSet.getString("picture"));
                dish.setSales(resultSet.getInt("sales"));
                dishList.add(dish);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(connection, preparedStatement, resultSet);
        }

        return dishList;
    }

    @Override
    public Dish findById(int dish_id) throws Exception {

        Connection connection = DatabaseConnect.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Dish dish = null;

        String sql = "select * from dish where dish_id=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, dish_id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                dish = new Dish();
                dish.setDish_id(resultSet.getInt("dish_id"));
                dish.setDish_name(resultSet.getString("dish_name"));
                dish.setShop_id(resultSet.getInt("shop_id"));
                dish.setCurrent_price(resultSet.getFloat("current_price"));
                dish.setOriginal_price(resultSet.getFloat("original_price"));
                dish.setDescp(resultSet.getString("descp"));
                dish.setPicture(resultSet.getString("picture"));
                dish.setSales(resultSet.getInt("sales"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(connection, preparedStatement, resultSet);
        }

        return dish;
    }

    @Override
    public boolean changeInfo(Dish dish) throws Exception {
        Connection connection = DatabaseConnect.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "update dish set dish_name=?,descp=?,original_price=?,current_price=?,picture=? where dish_id=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dish.getDish_name());
            preparedStatement.setString(2, dish.getDescp());
            preparedStatement.setFloat(3, dish.getOriginal_price());
            preparedStatement.setFloat(4, dish.getCurrent_price());
            preparedStatement.setString(5, dish.getPicture());
            preparedStatement.setInt(6, dish.getDish_id());
            preparedStatement.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(connection, preparedStatement, null);
        }
        return false;
    }

    @Override
    public boolean addDish(Dish dish) throws Exception {
        Connection connection = DatabaseConnect.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "insert into dish (dish_name, shop_id, original_price, current_price, descp) values(?,?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, dish.getDish_name());
            preparedStatement.setInt(2, dish.getShop_id());
            preparedStatement.setFloat(3, dish.getOriginal_price());
            preparedStatement.setFloat(4, dish.getCurrent_price());
            preparedStatement.setString(5, dish.getDescp());
            preparedStatement.execute();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(connection, preparedStatement, null);
        }
        return false;
    }

    @Override
    public boolean deleteDish(int dish_id) throws Exception {
        Connection connection = DatabaseConnect.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "delete from dish where dish_id="+dish_id;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.execute();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(connection, preparedStatement, null);
        }
        return false;
    }
}
