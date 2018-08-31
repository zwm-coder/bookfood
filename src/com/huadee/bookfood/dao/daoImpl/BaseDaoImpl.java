package com.huadee.bookfood.dao.daoImpl;

import com.huadee.bookfood.jdbc.DatabaseConnect;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BaseDaoImpl {
    public static int getTotal(String name, String osql){
        Connection conn = null;
        String sql = null;
        if (osql == null){
            sql = "select count(*) as total from " + name;
        } else {
            sql = osql;
        }
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = DatabaseConnect.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()){
                return rs.getInt("total");
            }
            DatabaseConnect.closeAll(conn, stmt, rs);
        } catch (Exception e){
            e.printStackTrace();
        }
        return 0;
    }
}
