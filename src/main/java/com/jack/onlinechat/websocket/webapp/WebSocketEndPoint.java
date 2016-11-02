package com.jack.onlinechat.websocket.webapp;



import com.jack.onlinechat.websocket.consultation.GetHttpSessionConfigurator;
import com.jack.security.pojo.SecurityUser;
import com.jack.security.service.SecurityUserService;
import com.jack.security.shiro.ShiroDbRealm;
import com.jack.utils.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

import javax.servlet.http.HttpSession;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by wajiangk on 10/9/2016.
 */
@ServerEndpoint(value="/websocket",configurator=GetHttpSessionConfigurator.class)
public class WebSocketEndPoint extends AbstractWebSocketHandler {


    private static final Map<String,Session> users = new HashMap<String, Session>();
    private static final Logger logger = Logger.getLogger(WebSocketEndPoint.class);

    @Autowired
    private SecurityUserService userService = null;

    /*@Override
    protected void handleTextMessage(WebSocketSession session,
                                     TextMessage message) throws Exception {
        logger.info("handleTextMessage");
        super.handleTextMessage(session, message);
        session.sendMessage(message);
    }
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        logger.debug("链接成功......");
        users.add(session);
        String userName = (String) session.getAttributes().get("WEBSOCKET_USERNAME");
        if(userName!= null){
            //查询未读消息
            int count = 5;
            session.sendMessage(new TextMessage(count + ""));
        }
        System.out.println("Connection Establied!");
    }

    //接受消息处理消息
    @Override
    public void handleMessage(WebSocketSession webSocketSession, WebSocketMessage<?> webSocketMessage) throws Exception {
        sendMessageToUsers(new TextMessage(webSocketMessage.getPayload() + ""));
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        System.out.println("Connection Closed！");
    }

    *//**
     * 给所有在线用户发送消息
     *
     * @param message
     *//*
    public void sendMessageToUsers(TextMessage message) {
        for (WebSocketSession user : users) {
            try {
                if (user.isOpen()) {
                    user.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    *//**
     * 给某个用户发送消息
     *
     * @param userName
     * @param message
     * *//*
    public void sendMessageToUser(String userName, TextMessage message) {
        for (WebSocketSession user : users) {
            if (user.getAttributes().get("WEBSOCKET_USERNAME").equals(userName)) {
                try {
                    if (user.isOpen()) {
                        user.sendMessage(message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }
*/
    private String currentUser;

    //连接打开时执行
    @OnOpen
    public void onOpen(Session session,EndpointConfig config) {
        HttpSession httpSession= (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        currentUser = ((SecurityUser)httpSession.getAttribute("user")).getUserName();
        users.put(currentUser,session);

    }

    //收到消息时执行
    @OnMessage
    public void onMessage(String message, Session session) {
        session.getUserPrincipal();

        JSONObject jsonMsg = new JSONObject(message);
        String targetFriend = jsonMsg.get("target").toString();
        Map<String,Object> map = new HashMap<String, Object>();
        if(targetFriend.equals("group")){

        }else{
            Session targetSession = users.get(targetFriend);
            if(targetSession!=null){
                try {
                    map.put("msgContent",jsonMsg.get("msgContent").toString());
                    map.put("from",jsonMsg.get("from").toString());
                    map.put("receiveDate", StringUtils.getFormatDate_1());
                    targetSession.getBasicRemote().sendText(getJsonResult(map));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //连接关闭时执行
    @OnClose
    public void onClose(Session session, CloseReason closeReason) {
        users.remove(session);
    }

    //连接错误时执行
    @OnError
    public void onError(Throwable t) {
        t.printStackTrace();
    }

    private  String getJsonResult(Map<String,Object> map){
        String result = "";
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.writeValueAsString(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



}