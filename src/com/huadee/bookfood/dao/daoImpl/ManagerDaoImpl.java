package com.huadee.bookfood.dao.daoImpl;

import com.huadee.bookfood.bean.Manager;
import com.huadee.bookfood.dao.ManagerDao;
import com.huadee.bookfood.jdbc.DatabaseConnect;
import com.huadee.bookfood.utils.Md5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ManagerDaoImpl implements ManagerDao {
    @Override
    public Boolean doCreate(Manager manager) throws Exception {
        Connection conn = DatabaseConnect.getConnection();
        String sql = "insert into manager(password, login_name) values (?, ?)";
        PreparedStatement stmt = null;

       try {
           stmt = conn.prepareStatement(sql);
           stmt.setString(1, manager.getPassword());
           stmt.setString(2, manager.getLogin_name());
           stmt.executeUpdate();
       } catch (SQLException se){
           se.printStackTrace();
           return false;
       } finally {
           DatabaseConnect.closeAll(conn, stmt, null);
       }
        return true;
    }

    @Override
    public Manager findByLoginName(String login_name) throws Exception {
        Connection conn = DatabaseConnect.getConnection();
        PreparedStatement stmt = null;
        String sql = "select manager_id,last_time,login_name,password from manager where login_name=?";
        stmt = conn.prepareStatement(sql);
        stmt.setString(1, login_name);
        ResultSet rst = stmt.executeQuery();
        Manager manager = null;

        if (rst.next()) {
            manager = new Manager();
            manager.setManager_id(rst.getInt("manager_id"));
            manager.setLogin_name(rst.getString("last_time"));
            manager.setLogin_name(rst.getString("login_name"));
            manager.setPassword(rst.getString("password"));
        }

        return manager;
    }

    @Override
    public void doUpdate(Manager manager) throws Exception {
        Connection conn = DatabaseConnect.getConnection();
        PreparedStatement stmt = null;
        String sql = "update manager set password=? where login_name=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, Md5.md5(manager.getPassword()));
            stmt.setString(2, manager.getLogin_name());
            stmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(conn, stmt, null);
        }
    }

    @Override
    public Boolean doDelete(int min) throws Exception {
        return null;
    }

    @Override
    public boolean doUpdateLoginTime(Manager manager) throws Exception {
        Connection conn = DatabaseConnect.getConnection();
        PreparedStatement stmt = null;
        String sql = "update manager set last_time=? where login_name=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, manager.getLast_time());
            stmt.setString(2, manager.getLogin_name());
            stmt.executeUpdate();
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(conn, stmt, null);
        }
        return true;
    }
}
