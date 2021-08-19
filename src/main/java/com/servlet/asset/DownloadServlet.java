package com.servlet.asset;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;

@WebServlet("/download.ajax")
public class DownloadServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/x-msdownload");
        String fileName = "文件模板.xlsx";
        response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(fileName, "utf-8"));
        OutputStream os = response.getOutputStream();

        URL url = getClass().getClassLoader().getResource("data/template.xlsx");
        File file = new File(url.getFile());
        InputStream is = new FileInputStream(file);
        byte[] bytes = new byte[1024];
        int len = 0;
        while((len=is.read(bytes))!=-1){
            os.write(bytes, 0, len);
        }
        is.close();
        os.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
