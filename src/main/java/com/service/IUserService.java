package com.service;

import com.model.User;

import java.sql.Connection;
import java.util.List;

public interface IUserService {
    List<User> allList(int index, String userid);

    Integer listCount(String userid);

    boolean doRegister(String uname, String upwd);

    User doLogin(String uname, String upwd);

    boolean resetPwd(int id, String pwd);

    boolean editUser(User user);

    boolean changeUserState(int id, int state);

}
