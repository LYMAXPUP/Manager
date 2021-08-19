package com.utils;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.model.Asset;

import java.util.ArrayList;
import java.util.List;

public class ExcelListener extends AnalysisEventListener<Asset> {

    private List<Asset> list = new ArrayList<>();
    //一行一行去读取excle内容
    @Override
    public void invoke(Asset asset, AnalysisContext analysisContext) {
        System.out.println("哈哈***"+asset);
        list.add(asset);
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }

    public List<Asset> getList(){
        return list;
    }

}
