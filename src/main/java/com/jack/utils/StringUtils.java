package com.jack.utils;

import com.jack.security.pojo.SecurityPermission;
import com.jack.security.pojo.SecurityRole;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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


    public static String parseNumToWord(int num){
        String[] words = {"One","Two","Three","Four","Five","Sxi","Seven","Eight","Nine","Ten","Eleven","Twelve","Thirteen","Fourteen","Fifteen","SixTenn"};

        if(num>0){
            return words[num-1];
        }else{
            return "";
        }

    }


    public static boolean hasRight(String rightId, SecurityRole role){
        List<SecurityPermission> rights = role.getRights();

        for(int i=0;i<rights.size();i++){
            if(rightId.equals(rights.get(i).getRightId())){
                return true;
            }else{
                if(i!=rights.size()-1){
                    continue;
                }else{
                    return false;
                }
            }
        }
        return false;

    }

    public static String findRightId(String rightSn,String rightName,List<SecurityPermission> permissions){

        for(int i=0;i<permissions.size();i++){
            if(permissions.get(i).getRightSign().equals(rightSn)&&permissions.get(i).getRightName().equals(rightName)){
                return permissions.get(i).getRightId();
            }
        }
        return "";
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(cs.charAt(i)) == false) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !StringUtils.isBlank(cs);
    }

    public static  boolean contains(String str, String[] strs){
        for(int i=0;i<strs.length;i++){
            if(str.equals(strs[i])){
                return true;
            }else{
                if(i!=strs.length-1){
                    continue;
                }else{
                    return false;
                }
            }
        }
        return false;
    }

    public static String encodeUTF8(String str){
        String result = "";
        try {
            String encode = getEncoding(str);
            result = new String(str.getBytes(encode),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return result;
    }
}
