package com.jack.onlinechat.websocket.consultation;

import java.io.Serializable;

/**
 * Created by wajiangk on 10/9/2016.
 */
public class Consultation implements Serializable {

    //主键ID
    private String id;
    //发送人ID
    private String c_send_userid;
    //发送人姓名
    private String c_send_username;
    //接收人ID
    private String c_receive_userid;
    //接收人姓名
    private String c_receive_username;
    //咨询信息
    private String c_context;
    //发送时间
    private String c_date;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getC_send_userid() {
        return c_send_userid;
    }

    public void setC_send_userid(String c_send_userid) {
        this.c_send_userid = c_send_userid;
    }

    public String getC_send_username() {
        return c_send_username;
    }

    public void setC_send_username(String c_send_username) {
        this.c_send_username = c_send_username;
    }

    public String getC_receive_userid() {
        return c_receive_userid;
    }

    public void setC_receive_userid(String c_receive_userid) {
        this.c_receive_userid = c_receive_userid;
    }

    public String getC_receive_username() {
        return c_receive_username;
    }

    public void setC_receive_username(String c_receive_username) {
        this.c_receive_username = c_receive_username;
    }

    public String getC_context() {
        return c_context;
    }

    public void setC_context(String c_context) {
        this.c_context = c_context;
    }

    public String getC_date() {
        return c_date;
    }

    public void setC_date(String c_date) {
        this.c_date = c_date;
    }
}
