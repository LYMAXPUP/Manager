package com.servlet.asset;

import com.alibaba.fastjson.JSONObject;
import com.model.Asset;
import com.service.IAssetService;
import com.service.impl.AssetServiceImpl;
import com.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/asset.ajax")
public class AssetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String indexStr = request.getParameter("index"); // 第几页
        String assetName = request.getParameter("assetName");
        int index =1;
        if(StringUtils.isNotEmpty(indexStr)){
            index = Integer.parseInt(indexStr);
        }

        List<Asset> list = new ArrayList<>();
        int max = 0;
        String action = request.getParameter("action");
        if("all".equals(action)){
            IAssetService ias = new AssetServiceImpl();
            list = ias.allList(index, assetName);
            max = ias.listCount(assetName);
        }else if("my".equals(action)){
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userid");
            IAssetService ias = new AssetServiceImpl();
            list = ias.allMyList(index, userId, assetName);
            max = ias.myListCount(userId, assetName);
        }

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
