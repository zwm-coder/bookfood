package com.huadee.bookfood.dao.daoImpl;

import com.huadee.bookfood.bean.Seller;
import com.huadee.bookfood.dao.SellerDao;
import com.huadee.bookfood.jdbc.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class SellerDaoImpl implements SellerDao {
    @Override
    public boolean doCreate(Seller seller) throws Exception {
        return false;
    }

    @Override
    public boolean doUpdate(Seller seller) throws Exception {
        Connection conn = DatabaseConnect.getConnection();
        PreparedStatement stmt = null;

        String sql = "update seller set seller_id=?,seller_tel=?,status=?,photo=?,password=? where login_name=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, seller.getSeller_id());
            stmt.setString(2, seller.getSeller_tel());
            stmt.setInt(3, seller.getStatus());
            stmt.setString(4, seller.getPhoto());
            stmt.setString(5, seller.getPassword());
            stmt.setString(6, seller.getLogin_name());
            stmt.executeUpdate();
            return true;
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(conn, stmt, null);
        }

        return false;
    }

    @Override
    public boolean doDelete(String seller_name) throws Exception {
        return false;
    }

    @Override
    public Seller findByName(String seller_name) throws Exception {
        Connection conn = DatabaseConnect.getConnection();
        PreparedStatement stmt = null;
        ResultSet rst = null;
        Seller seller = null;

        String sql = "select * from seller where login_name=?";
        try {
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, seller_name);
            rst = stmt.executeQuery();
            while (rst.next()){
                seller = new Seller();
                seller.setStatus(rst.getInt("status"));
                seller.setSeller_tel(rst.getString("seller_tel"));
                seller.setSeller_id(rst.getInt("seller_id"));
                seller.setPhoto(rst.getString("photo"));
                seller.setLogin_name(rst.getString("login_name"));
                seller.setPassword(rst.getString("password"));
                return seller;
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(conn, stmt, rst);
        }
        return seller;
    }

    @Override
    public List<Seller> findAll(int page, int pageSize, String login_name, int status) throws Exception {
        Connection conn = DatabaseConnect.getConnection();
        String sql = "select seller_id,seller_tel,status,login_name,photo from seller ";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Seller> sellerList = new ArrayList<Seller>();

        boolean queryByStatus = false;

        if (login_name != null && !login_name.equals("")){
            sql += "where login_name='" + login_name + "' ";
        }
        if (status == 0 || status == 1 || status ==2){
            sql = sql + "where status=? ";
            queryByStatus = true;
        }

        sql = sql + "limit ?,?";

        try {
            stmt = conn.prepareStatement(sql);

            if (queryByStatus){
                stmt.setInt(1, status);
                stmt.setInt(2, (page - 1) * pageSize);
                stmt.setInt(3, pageSize);
            } else {
                stmt.setInt(1, (page - 1) * pageSize);
                stmt.setInt(2, pageSize);
            }

            rs = stmt.executeQuery();
            while (rs.next()){
                Seller seller = new Seller();
                seller.setLogin_name(rs.getString("login_name"));
                seller.setPhoto(rs.getString("photo"));
                seller.setSeller_id(rs.getInt("seller_id"));
                seller.setSeller_tel(rs.getString("seller_tel"));
                seller.setStatus(rs.getInt("status"));
                sellerList.add(seller);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(conn, stmt, rs);
        }

        return sellerList;
    }

    @Override
    public int getTotal() throws Exception {
        return BaseDaoImpl.getTotal("seller", null);
    }
}
