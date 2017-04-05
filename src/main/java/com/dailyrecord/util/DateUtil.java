package com.dailyrecord.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017-4-5.
 */
public class DateUtil {
    private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    public static String date2Str(Date date) throws Exception{
        if(date == null){
            date = new Date();
        }
        return sdf.format(date);
    }
}
