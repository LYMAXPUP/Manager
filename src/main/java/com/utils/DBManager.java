package com.utils;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class DBManager {
    private static Properties properties = null;
    private final static String fileName = "db.properties";
    private static DataSource dataSource;
    private static Connection connection = null;

    static {
        properties = new Properties();
        InputStream is = DBManager.class.getClassLoader().getResourceAsStream(fileName);
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){
        if(dataSource==null){
            ComboPooledDataSource tempdatasource = new ComboPooledDataSource();
            try {
                tempdatasource.setDriverClass(properties.getProperty("driver"));
                tempdatasource.setJdbcUrl(properties.getProperty("url"));
                tempdatasource.setUser(properties.getProperty("username"));
                tempdatasource.setPassword(properties.getProperty("password"));
                dataSource = tempdatasource;
            } catch (PropertyVetoException e) {
                e.printStackTrace();
            }
        }
        if(connection==null){
            try {
                connection = dataSource.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    // 增删改sql通用方法，返回受影响行数
    public static int executeUpdate(Connection con, String sql, Object... param){
        if(con!=null){
            connection = con;
        }else {
            connection = getConnection();
        }
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            if(param!=null){
                for(int i=0; i<param.length; i++){
                    pstm.setObject(i+1,param[i]); // 注意从1开始
                }
            }
            return pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    // 查通用方法
    public static ResultSet executeQuery(Connection con, String sql, Object... param){
        if(con!=null){
            connection = con;
        }else {
            connection = getConnection();
        }
        ResultSet rs = null;
        try {
            PreparedStatement pstm = connection.prepareStatement(sql);
            if(param!=null){
                for(int i=0; i<param.length; i++){
                    pstm.setObject(i+1,param[i]); // 注意从1开始
                }
            }
            rs = pstm.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

}
