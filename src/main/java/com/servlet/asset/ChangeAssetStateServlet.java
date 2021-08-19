package com.servlet.asset;

import com.alibaba.fastjson.JSONObject;
import com.service.IAssetService;
import com.service.impl.AssetServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/changeAssetState.ajax")
public class ChangeAssetStateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;character=utf-8");

        int assetId = Integer.parseInt(request.getParameter("assetId"));
        boolean flag = false;
        String action = request.getParameter("action");
        int state = -10;

        IAssetService ias = new AssetServiceImpl();
        if("claim".equals(action)){
            state = 1;
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("userid");
            flag = ias.claimAsset(assetId, userId);
        } else if("giveBack".equals(action)) {
            state = 0;
            flag = ias.giveBackAsset(assetId);
        } else if("delete".equals(action)){
            state = -1;
            flag = ias.deleteAsset(assetId);
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
