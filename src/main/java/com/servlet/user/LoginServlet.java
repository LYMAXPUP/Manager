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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/login.ajax")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;character=utf-8");

        String uname = request.getParameter("un");
        String upwd = request.getParameter("up");

        int code = -10;
        if(StringUtils.isAllNotEmpty(uname,upwd)){
            IUserService lus = new UserServiceImpl();
            User loginer = lus.doLogin(uname, upwd);
            if(loginer!=null && loginer.getState()!=1){
                code = 1; // 此用户被禁用或注销
                }
            if(loginer==null){
                code = 2; // 用户名、密码错误
            }else{
                code = 0; // 成功登陆!
                HttpSession session = request.getSession();
                session.setAttribute("userid", uname);
                session.setAttribute("pwd", upwd);
            }
        }else {
            code = -1; // 用户名/密码为空
        }

        JSONObject data = new JSONObject();
        data.put("code",code);
        PrintWriter pw = response.getWriter();
        pw.write(data.toJSONString());
        pw.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
