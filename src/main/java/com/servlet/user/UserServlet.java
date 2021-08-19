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
import java.util.List;

@WebServlet("/user.ajax")
public class UserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        // 分页
        // 前端传index第几页，后端传max最大页数
        String indexStr = request.getParameter("index");
        String userId = request.getParameter("userid");
        // 校验参数
        int index =1;
        if(StringUtils.isNotEmpty(indexStr)){
            index = Integer.parseInt(indexStr);
        }

        IUserService ius = new UserServiceImpl();
        List<User> list = ius.allList(index, userId);
        int max = ius.listCount(userId);

        JSONObject data = new JSONObject();
        data.put("list",list);
        data.put("max", max);
        PrintWriter pw = response.getWriter();
        pw.write(data.toJSONString());
        pw.flush();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
