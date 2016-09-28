package com.jack.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wajiangk on 8/24/2016.
 */
public class StringUtils {

    private static final String DATE_FORMAT_1 = "yyyy-MM-dd HH:mm:ss";
    private static final String DATE_FORMAT_2 = "yyyyddMMhhmmsSSS";
    private static final SimpleDateFormat SDF_1=new SimpleDateFormat(DATE_FORMAT_1);
    private static final SimpleDateFormat SDF_2= new SimpleDateFormat(DATE_FORMAT_2);


    public static boolean isEmpty(String str){

        if(str==null){
            return true;
        }else{
            str = str.trim();
            if("".equals(str)){
                return true;
            }
        }
        return false;
    }

    public static boolean isNotEmpty(String str){

        return !isEmpty(str);
    }

    public static String getEncoding(String str) {
        String encode = "GB2312";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s = encode;
                return s;
            }
        } catch (Exception exception) {
        }
        encode = "ISO-8859-1";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s1 = encode;
                return s1;
            }
        } catch (Exception exception1) {
        }
        encode = "UTF-8";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s2 = encode;
                return s2;
            }
        } catch (Exception exception2) {
        }
        encode = "GBK";
        try {
            if (str.equals(new String(str.getBytes(encode), encode))) {
                String s3 = encode;
                return s3;
            }
        } catch (Exception exception3) {
        }
        return "";
    }



    public static String getFormatDate_1(){
        return SDF_1.format(new Date());
    }

    public static String getFormatDate_2(){
        return SDF_2.format(new Date());
    }

    public static String dateFormat(Date date){
        if(date!=null){
            return SDF_1.format(date);
        }else{
            return null;
        }
    };


    public static String ellipsisStr(String str){
        if(str.length()>=15)
            return str.substring(0,15)+"...";
        else
            return str;
    }
}
