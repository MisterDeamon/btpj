package com.jack.utils;

/**
 * Created by wajiangk on 11/3/2016.
 */
public   class Test {




    public static void main(String[] args){

        StringBuilder sb = new StringBuilder("123");
        System.out.println(sb.toString());
        sb.replace(0,sb.length(),"456");
        System.out.println(sb.toString());
    }
}

