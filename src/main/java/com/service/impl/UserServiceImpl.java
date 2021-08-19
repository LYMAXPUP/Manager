package com.service.impl;

import com.dao.IUserDao;
import com.dao.impl.UserDaoImpl;
import com.model.User;
import com.service.IUserService;
import com.utils.DBManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements IUserService {
    private static int pageSize=5;
    @Override
    public List<User> allList(int index, String userid) {
        int offset = (index-1)*pageSize;
        int limit = pageSize;
        IUserDao iud = new UserDaoImpl();
        return iud.getAllUser(offset, limit, userid);
    }

    @Override
    public Integer listCount(String userid) {
        IUserDao iud = new UserDaoImpl();
        int count =  iud.listCount(userid);
        return count % pageSize == 0? count/pageSize : count/pageSize +1;
    }

    @Override
    public User doLogin(String uname, String upwd) {
        IUserDao iud = new UserDaoImpl();
        return iud.findUser(uname, upwd);
    }

    @Override
    public boolean doRegister(String uname, String upwd) {
        IUserDao iud = new UserDaoImpl();
        return iud.registerUser(uname, upwd);
    }

    @Override
    public boolean resetPwd(int id, String pwd) {
        IUserDao iud = new UserDaoImpl();
        return iud.updateUsers(null, id, "pwd", pwd);
    }

    @Override
    public boolean editUser(User user){
        IUserDao iud = new UserDaoImpl();
        Connection con = DBManager.getConnection();
        boolean flag = false;
        try{
            con.setAutoCommit(false);
            int id = user.getId();
            iud.updateUsers(con, id, "user_id", user.getUserid());
            iud.updateUsers(con, id, "priority", user.getPriority());
            iud.updateUsers(con, id, "text", user.getText());
            con.commit();
            flag = true;
        }catch(Throwable e){
            if(con!=null){
                try {
                    con.rollback();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return flag;
    }

    @Override
    public boolean changeUserState(int id, int state) {
        IUserDao iud = new UserDaoImpl();
        return iud.updateUsers(null, id, "`state`", state);
    }

}
