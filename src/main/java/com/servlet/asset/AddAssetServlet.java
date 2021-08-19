package com.servlet.asset;

import com.alibaba.fastjson.JSONObject;
import com.model.Asset;
import com.service.IAssetService;
import com.service.impl.AssetServiceImpl;
import com.utils.FinanceData;
import com.utils.StringUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addAsset.ajax")
public class AddAssetServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;character=utf-8");

        String assetName = request.getParameter("assetName");
        String department = request.getParameter("department");
        String model = request.getParameter("model");
        String price = request.getParameter("price");
        String buyTime = request.getParameter("buyTime");
        String type = request.getParameter("type");
        HttpSession session = request.getSession();
        String accountName = (String) session.getAttribute("userid");

        int code = -10;
        if(StringUtils.isAllNotEmpty(assetName, department, model, price, buyTime, type)) {
            Asset asset = new Asset();
            asset.setAccountName(accountName);
            asset.setAssetName(assetName);
            asset.setDepartment(department);
            asset.setModel(model);
            asset.setPrice(Double.parseDouble(price));
            asset.setBuyTime(buyTime);
            asset.setType(type);
            IAssetService ias = new AssetServiceImpl();
            boolean result = ias.addAsset(asset);
            if(result) {
                code = 1;
            } else {
                code = 0;
            }
        } else {
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
