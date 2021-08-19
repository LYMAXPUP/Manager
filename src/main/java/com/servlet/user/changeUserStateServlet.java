package com.servlet.user;

import com.alibaba.fastjson.JSONObject;
import com.service.IUserService;
import com.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/changeUserState.ajax")
public class changeUserStateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;character=utf-8");

        int id = Integer.parseInt(request.getParameter("id"));
        int state = -10;
        String action = request.getParameter("action");
        if("forbid".equals(action)){
            state = 0;
        } else if("lift".equals(action)) {
            state = 1;
        } else if("delete".equals(action)){
            state = -1;
        }

        boolean flag = false;
        IUserService lus = new UserServiceImpl();
        flag = lus.changeUserState(id, state);

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
