package com.jack.security.pojo;

import org.json.JSONObject;

/**
 * Created by wajiangk on 10/21/2016.
 */
public class Test {

    public static void main(String[] args){
//        {'from':'Jack','target':'Lucy','msgContent':'Hello Lucy, I said \"I\'m Jack\"'}
        JSONObject jsonMsg = new JSONObject("{'from':'Jack','target':'Lucy','msgContent':'Hello \"Lucy\":</>{}, I\\'m Jack'}");
        System.out.println(jsonMsg);


        String msgContent = "Hello, Jack. I'm Lucy':";
        System.out.println(msgContent.replaceAll("\'","\\\\'"));
    }
}
