package com.servlet.asset;

import com.alibaba.fastjson.JSONObject;
import com.model.Asset;
import com.service.IAssetService;
import com.service.impl.AssetServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/editAsset.ajax")
public class EditAssetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;character=utf-8");

        Asset asset = new Asset();
        asset.setAssetId(Integer.parseInt(request.getParameter("assetId")));
        asset.setAssetName(request.getParameter("assetName"));
        asset.setDepartment(request.getParameter("department"));
        asset.setModel(request.getParameter("model"));
        asset.setPrice(Double.parseDouble(request.getParameter("price")));
        asset.setBuyTime(request.getParameter("buyTime"));
        asset.setType(request.getParameter("type"));
        asset.setText(request.getParameter("text"));

        boolean flag = false;
        IAssetService ias = new AssetServiceImpl();
        flag = ias.editAsset(asset);

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
