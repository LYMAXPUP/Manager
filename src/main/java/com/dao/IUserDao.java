package com.dao;

import com.model.User;

import java.sql.Connection;
import java.util.List;

public interface IUserDao {
    User findUser(String uname, String upwd);

    boolean registerUser(String uname, String upwd);

    List<User> getAllUser(int offset, int limit, String userid);
    // 总记录数max
    Integer listCount(String userid);

    boolean updateUsers(Connection con, int id, String column, Object value);

}
