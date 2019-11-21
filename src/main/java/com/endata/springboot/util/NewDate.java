package com.endata.springboot.util;

import com.fasterxml.jackson.databind.util.StdDateFormat;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ligang
 * @create 2019-11-20 20:12
 * 获取系统时间
 */
public class NewDate{

    public Date  getNewDate() throws ParseException {
        Date currentTime = new Date();
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           String dateString = formatter.format(currentTime);
           ParsePosition pos = new ParsePosition(8);
           Date currentTime_2 = formatter.parse(dateString);
           return currentTime_2;

    }

        public  String  getStringDate(){
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        return  dateString;
    }

}
