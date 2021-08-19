package com.servlet.user;

import com.alibaba.fastjson.JSONObject;
import com.model.User;
import com.service.IUserService;
import com.service.impl.UserServiceImpl;
import com.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/editUser.ajax")
public class EditUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;character=utf-8");

        String action = request.getParameter("action");
        boolean flag = false;
        if("edit".equals(action)){
            String userId = request.getParameter("userid");
            if(StringUtils.isNotEmpty(userId)) {
                User user = new User();
                user.setId(Integer.parseInt(request.getParameter("id")));
                user.setUserid(userId);
                user.setPriority(Integer.parseInt(request.getParameter("priority")));
                user.setText(request.getParameter("text"));
                IUserService lus = new UserServiceImpl();
                flag = lus.editUser(user);
            }
        } else if("resetPwd".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            String pwd = request.getParameter("pwd");
            if (StringUtils.isNotEmpty(pwd)) {
                IUserService lus = new UserServiceImpl();
                flag = lus.resetPwd(id, pwd);
            }
        }

        JSONObject data = new JSONObject();
        data.put("flag",flag);
        PrintWriter pw = response.getWriter();
        pw.write(data.toJSONString());
        pw.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
