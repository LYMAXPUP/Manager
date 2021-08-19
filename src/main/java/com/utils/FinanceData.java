package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FinanceData {
    // 生成凭证号
    public static String voucher(int id, String accountTime){
        return "入" + id + accountTime;
    }

    public static String accountTime(){
     SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
     String date = sdf.format(new Date());
     return date;
    }

}
