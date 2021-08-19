package com.servlet.asset;

import com.alibaba.excel.EasyExcel;
import com.alibaba.fastjson.JSONObject;
import com.model.Asset;
import com.service.IAssetService;
import com.service.impl.AssetServiceImpl;
import com.utils.ExcelListener;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/upload.ajax")
public class UploadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");

        List<Asset> assets = new ArrayList<>();
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(1024*500); // 缓存500KB, 单位Byte字节
        URL url = getClass().getClassLoader().getResource("data/");
//        url = path=file:/C:/Users/64809/Desktop/Manager/target/Manager/WEB-INF/classes/data/
        assert url != null;
        factory.setRepository(new File(url.getFile()));// 临时的缓存目录

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setHeaderEncoding("utf-8");
        upload.setFileSizeMax(100*1024*1024); // 100M
        try {
            List<FileItem> items = upload.parseRequest(request);
            for (FileItem item:items) {
                InputStream is = item.getInputStream();
                ExcelListener listener = new ExcelListener();
                EasyExcel.read(is, Asset.class, listener).sheet().doRead();
                assets = listener.getList();
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        String accountName = (String) session.getAttribute("userid");

        IAssetService ias = new AssetServiceImpl();
        boolean flag = ias.addSomeAssets(assets, accountName);
        if(flag) {
            request.getRequestDispatcher("assetlist.jsp").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
