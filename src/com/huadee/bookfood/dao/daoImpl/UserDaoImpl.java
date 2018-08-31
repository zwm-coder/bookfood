package com.huadee.bookfood.dao.daoImpl;

import com.huadee.bookfood.bean.User;
import com.huadee.bookfood.dao.UserDao;
import com.huadee.bookfood.jdbc.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public List<User> findAll(int page, int pageSize, String queryName, String queryValue, boolean flag) throws Exception {
        Connection connection = DatabaseConnect.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        List<User> users = new ArrayList<>();

        String sql = "";
        // 如果不是按条件查询
        if (queryName == null){
            sql = "select * from user limit ?,?";
        } else {
            if (!flag) {
                sql = "select * from user where " + queryName + "='"+ queryValue +"' limit ?,?";
            } else {
                sql = "select * from user where " + queryName + "=" + queryValue +" limit ?,?";
            }
        }

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (page - 1) * pageSize);
            preparedStatement.setInt(2, pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setLogin_name(resultSet.getString("login_name"));
                user.setNick_name(resultSet.getString("nickname"));
                user.setPassword(resultSet.getString("password"));
                user.setPicture(resultSet.getString("picture"));
                user.setReal_name(resultSet.getString("real_name"));
                user.setRegister_time(resultSet.getString("register_time"));
                user.setSex(resultSet.getInt("sex"));
                user.setStatus(resultSet.getInt("status"));
                user.setUser_tel(resultSet.getString("user_tel"));
                user.setUser_id(resultSet.getInt("user_id"));

                users.add(user);
            }
        } catch (Exception e){
            e.printStackTrace();
        }finally {
            DatabaseConnect.closeAll(connection, preparedStatement, resultSet);
        }

        return users;
    }

    @Override
    public int getTotal() throws Exception {
        return BaseDaoImpl.getTotal("user", null);
    }

    @Override
    public boolean changeStatus(int user_id, int status) throws Exception {
        Connection conn = DatabaseConnect.getConnection();
        PreparedStatement stmt = null;

        String sql = "update user set status=? where user_id=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, status);
            stmt.setInt(2, user_id);
            stmt.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(conn, stmt, null);
        }

        return false;
    }
}
