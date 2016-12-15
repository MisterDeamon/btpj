package com.jack.security.pojo;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wajiangk on 10/21/2016.
 */
public class Test {
/*
    public static void main(String[] args){
//        {'from':'Jack','target':'Lucy','msgContent':'Hello Lucy, I said \"I\'m Jack\"'}
        JSONObject jsonMsg = new JSONObject("{'from':'Jack','target':'Lucy','msgContent':'Hello \"Lucy\":</>{}, I\\'m Jack'}");
        System.out.println(jsonMsg);


        String msgContent = "Hello, Jack. I'm Lucy':";
        System.out.println(msgContent.replaceAll("\'","\\\\'"));

        System.out.println();
    }*/


    public static void main(String[] args){
        String test = "^\ufffd";

        System.out.println(test.contains("\ufffd"));

        Map<String,String> map = new HashMap<String,String>();
        map.put("1","1");

        System.out.println(map.get("1"));
        System.out.println(map.get("2"));


    }
}

