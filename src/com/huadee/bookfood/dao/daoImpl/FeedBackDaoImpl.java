package com.huadee.bookfood.dao.daoImpl;

import com.huadee.bookfood.bean.FeedBack;
import com.huadee.bookfood.dao.FeedBackDao;
import com.huadee.bookfood.jdbc.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class FeedBackDaoImpl implements FeedBackDao {
    @Override
    public List<FeedBack> findAll(int page, int pageSize, String queryName, String queryValue) throws Exception {

        Connection connection = DatabaseConnect.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<FeedBack> feedBacks = new ArrayList<>();

        String sql = "";
        if (queryName == null){
            // 查询条件为空，表示查询所有记录
            sql = "select * from feedback limit ?,?";
        } else {
            // 根据条件查询所有的记录
            sql = "select * from feedback where " + queryName + "=" + queryValue + " limit ?,?";
        }

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, (page - 1) * pageSize);
            preparedStatement.setInt(2, pageSize);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                FeedBack feedBack = new FeedBack();
                feedBack.setFeedback_id(resultSet.getInt("feedback_id"));
                feedBack.setUser_id(resultSet.getInt("user_id"));
                feedBack.setShop_id(resultSet.getInt("shop_id"));
                feedBack.setContent(resultSet.getString("content"));
                feedBack.setResult(resultSet.getString("result"));
                feedBack.setStatus(resultSet.getInt("status"));
                feedBacks.add(feedBack);
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(connection, preparedStatement, resultSet);
        }

        return feedBacks;
    }

    @Override
    public boolean doChange(FeedBack feedBack) throws Exception {
        Connection connection = DatabaseConnect.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "update feedback set result=?,status=? where feedback_id=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, feedBack.getResult());
            preparedStatement.setInt(2, feedBack.getStatus());
            preparedStatement.setInt(3, feedBack.getFeedback_id());
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
    public FeedBack findById(int feedback_id) throws Exception {
        Connection connection = DatabaseConnect.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        FeedBack feedBack = null;

        String sql = "select * from feedback where feedback_id="+feedback_id;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                feedBack = new FeedBack();
                feedBack.setFeedback_id(resultSet.getInt("feedback_id"));
                feedBack.setUser_id(resultSet.getInt("user_id"));
                feedBack.setShop_id(resultSet.getInt("shop_id"));
                feedBack.setContent(resultSet.getString("content"));
                feedBack.setResult(resultSet.getString("result"));
                feedBack.setStatus(resultSet.getInt("status"));
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            DatabaseConnect.closeAll(connection, preparedStatement, resultSet);
        }

        return feedBack;
    }

    @Override
    public int getTotal() throws Exception {
        return BaseDaoImpl.getTotal("feedback", null);
    }
}
