package com.dao.impl;

import com.dao.IUserDao;
import com.model.User;
import com.utils.DBManager;
import com.utils.StringUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements IUserDao {
    Connection connection = null;
    PreparedStatement pstm = null;
    ResultSet rs = null; // 查

    @Override
    public User findUser(String uname, String upwd) {
        String sql = "select * from users where user_id=? and pwd=?";
        User user = null;

        Object[] param = new Object[]{uname,upwd};
        rs = DBManager.executeQuery(null, sql, param);
        try {
            while(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setUserid(rs.getString("user_id"));
                user.setPwd(rs.getString("pwd"));
                user.setState(rs.getInt("state"));
                user.setPriority(rs.getInt("priority"));
                user.setText(rs.getString("text"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public boolean registerUser(String uname, String upwd) {
        String sql0 = "SELECT COUNT(*) from users where user_id=?;";
        rs = DBManager.executeQuery(null, sql0, uname);
        try {
            while(rs.next()){
                if(rs.getInt(1)>0) return false;
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sql = "INSERT into users VALUE (DEFAULT, ?, ?, DEFAULT, DEFAULT, DEFAULT);";
        Object[] param = new Object[]{uname,upwd};
        return DBManager.executeUpdate(null, sql, param) > 0;
    }

    @Override
    public List<User> getAllUser(int offset, int limit, String userid) {
        List<User> userlist = new ArrayList<>();
        connection = DBManager.getConnection();
        try {
            if(StringUtils.isEmpty(userid)) {
                pstm = connection.prepareStatement("select * from users limit ?,?");
                pstm.setInt(1, offset);
                pstm.setInt(2, limit);
            } else{
                pstm = connection.prepareStatement("select * from users where user_id like ? limit ?,?");
                pstm.setString(1,"%"+userid+"%");
                pstm.setInt(2, offset);
                pstm.setInt(3, limit);
            }
            rs = pstm.executeQuery();
            while(rs.next()){
                User u = new User();
                u.setId(rs.getInt(1));
                u.setUserid(rs.getString(2));
                u.setPwd(rs.getString(3));
                u.setState(rs.getInt(4));
                u.setPriority(rs.getInt(5));
                u.setText(rs.getString(6));
                userlist.add(u);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userlist;
    }

    @Override
    public Integer listCount(String userid) {
        try {
            connection = DBManager.getConnection();
            if(StringUtils.isEmpty(userid)) {
                pstm = connection.prepareStatement("select COUNT(*) from users");
            } else{
                pstm = connection.prepareStatement("select COUNT(*) from users where user_id like ?");
                pstm.setString(1,"%"+userid+"%");
            }
            rs = pstm.executeQuery();
            while(rs.next()){
                return rs.getInt(1); // 返回Count
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public boolean updateUsers(Connection con, int id, String column, Object value) {
        String sql = "UPDATE `users` SET "+ column +"=? WHERE `id`=?;";
        Object[] param = new Object[]{value,id};
        return DBManager.executeUpdate(con, sql, param) > 0;
    }

}
