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

    public static boolean isNotNullAndEmpty(String str){
        return str!=null&&!"".equals(str);
    }

    public static String getFormatDate_1(){
        return SDF_1.format(new Date());
    }

    public static String getFormatDate_2(){
        return SDF_2.format(new Date());
    }

}
