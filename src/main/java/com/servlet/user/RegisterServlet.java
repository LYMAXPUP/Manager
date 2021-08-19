package com.servlet.user;

import com.alibaba.fastjson.JSONObject;
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

@WebServlet("/register.ajax")
public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;character=utf-8");

        String uname = request.getParameter("userid");
        String upwd = request.getParameter("pwd");

        int code = 0;
        if(StringUtils.isAllNotEmpty(uname,upwd)){
            IUserService lus = new UserServiceImpl();
            boolean result = lus.doRegister(uname, upwd);
            if(result){
                code = 1;
            }
        }else {
            code = -1;
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
