package com.huadee.bookfood.dao.daoImpl;

import com.huadee.bookfood.bean.Shop;
import com.huadee.bookfood.dao.ShopDao;
import com.huadee.bookfood.jdbc.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShopDaoImpl implements ShopDao {
    @Override
    public List<Shop> findAll(int page, int pageSize, int status) throws Exception {
        Connection connection = DatabaseConnect.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Shop> shopList = new ArrayList<>();

        String sql = "select shop_id,contact,phone,shop_name,status from shop where status=? limit ?,?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, status);
            preparedStatement.setInt(2, (page-1)*pageSize);
            preparedStatement.setInt(3, pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Shop shop = new Shop();
                shop.setShop_name(resultSet.getString("shop_name"));
                shop.setContact(resultSet.getString("contact"));
                shop.setPhone(resultSet.getString("phone"));
                shop.setShop_id(resultSet.getInt("shop_id"));
                shop.setStatus(resultSet.getInt("status"));

                shopList.add(shop);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(connection, preparedStatement, resultSet);
        }

        return shopList;

    }

    @Override
    public int getTotal() throws Exception {
        return BaseDaoImpl.getTotal("shop", null);
    }

    @Override
    public Shop findByName(String name) throws Exception {
        Connection connection = DatabaseConnect.getConnection();
        PreparedStatement preparedStatement = null;
        Shop shop = null;
        ResultSet resultSet = null;

        String sql = "select * from shop where shop_name=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                shop = new Shop();
                shop.setShop_name(resultSet.getString("shop_name"));
                shop.setStatus(resultSet.getInt("status"));
                shop.setShop_id(resultSet.getInt("shop_id"));
                shop.setPhone(resultSet.getString("phone"));
                shop.setContact(resultSet.getString("contact"));
                shop.setAddress(resultSet.getString("address"));
                shop.setBusiness_license(resultSet.getString("business_license"));
                shop.setCater_permit(resultSet.getString("cater_permit"));
                shop.setId_card(resultSet.getString("id_card"));
                shop.setDesc(resultSet.getString("descp"));
                shop.setShop_inner_photo(resultSet.getString("shop_inner_photo"));
                shop.setShop_out_photo(resultSet.getString("shop_out_photo"));
                shop.setSeller_id(resultSet.getInt("seller_id"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(connection, preparedStatement, resultSet);
        }

        return shop;
    }

    @Override
    public boolean doUpdate(Shop shop) throws Exception {
        Connection connection = DatabaseConnect.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "update shop set id_card=?,business_license=?,cater_permit=?,shop_out_photo=?" +
                ",shop_inner_photo=?,contact=?,phone=?,descp=?,address=?,seller_id=?,status=?,shop_name=? where shop_id=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, shop.getId_card());
            preparedStatement.setString(2, shop.getBusiness_license());
            preparedStatement.setString(3, shop.getCater_permit());
            preparedStatement.setString(4, shop.getShop_out_photo());
            preparedStatement.setString(5, shop.getShop_inner_photo());
            preparedStatement.setString(6, shop.getContact());
            preparedStatement.setString(7, shop.getPhone());
            preparedStatement.setString(8, shop.getDesc());
            preparedStatement.setString(9, shop.getAddress());
            preparedStatement.setInt(10, shop.getSeller_id());
            preparedStatement.setInt(11, shop.getStatus());
            preparedStatement.setString(12, shop.getShop_name());
            preparedStatement.setInt(13, shop.getShop_id());

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
    public int getTotalApply() throws Exception {
        Connection conn = null;
        String sql = "select count(*) from shop where status=0";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnect.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                return rs.getInt(1);
            }
            DatabaseConnect.closeAll(conn, stmt, rs);
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<Shop> getShopListBySeller(int page, int pageSize, int seller_id, int status) throws Exception {
        Connection connection = DatabaseConnect.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Shop> shopList = new ArrayList<>();

        String sql = "select shop_id,contact,phone,shop_name,status,shop_out_photo,address,descp  from shop where seller_id=? and status=? limit ?,?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, seller_id);
            preparedStatement.setInt(2, status);
            preparedStatement.setInt(3, (page-1)*pageSize);
            preparedStatement.setInt(4, pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Shop shop = new Shop();
                shop.setShop_name(resultSet.getString("shop_name"));
                shop.setContact(resultSet.getString("contact"));
                shop.setPhone(resultSet.getString("phone"));
                shop.setShop_id(resultSet.getInt("shop_id"));
                shop.setStatus(resultSet.getInt("status"));
                shop.setShop_out_photo(resultSet.getString("shop_out_photo"));
                shop.setDesc(resultSet.getString("descp"));
                shop.setAddress(resultSet.getString("address"));

                shopList.add(shop);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(connection, preparedStatement, resultSet);
        }

        return shopList;
    }

    @Override
    public boolean doCreate(Shop shop) throws Exception {
        Connection connection = DatabaseConnect.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "insert into shop (id_card, business_license, cater_permit, shop_out_photo, shop_inner_photo, contact" +
                ", phone, descp, address, seller_id, status, shop_name) values (?,?,?,?,?,?,?,?,?,?,?,?)";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, shop.getId_card());
            preparedStatement.setString(2, shop.getBusiness_license());
            preparedStatement.setString(3, shop.getCater_permit());
            preparedStatement.setString(4, shop.getShop_out_photo());
            preparedStatement.setString(5, shop.getShop_inner_photo());
            preparedStatement.setString(6, shop.getContact());
            preparedStatement.setString(7, shop.getPhone());
            preparedStatement.setString(8, shop.getDesc());
            preparedStatement.setString(9, shop.getAddress());
            preparedStatement.setInt(10, shop.getSeller_id());
            preparedStatement.setInt(11, 0);
            preparedStatement.setString(12, shop.getShop_name());

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
