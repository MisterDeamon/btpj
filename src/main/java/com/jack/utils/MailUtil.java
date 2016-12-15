package com.jack.utils;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.mail.*;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.security.Security;
import java.util.Properties;

/**
 * Created by wajiangk on 12/8/2016.
 */
public class MailUtil {

    private static final Logger log = Logger.getLogger(MailUtil.class);
    private static Properties connect_option = new Properties();
    private static Properties mail_account_info = new Properties();
    private static  Session session;
    private static IMAPStore store;
    private static MailUtil mailUtil;
    private MailUtil(){
        if(mail_account_info.isEmpty()){
            PropertiesUtil.initMessageProperties("mail_account_info.properties",mail_account_info);
        }
        if(connect_option.isEmpty()){
            PropertiesUtil.initMessageProperties("mail_connect_options.properties",connect_option);
        }
    }

    public static Session getSession(){
        if(session==null){
            connectMail();
        }
        return session;
    }
    public static void connectMail(){
        Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
        if(mailUtil==null){
            mailUtil = new MailUtil();
        }
        if(session==null){
            session = Session.getDefaultInstance(connect_option);
            session.setDebug(true);
        }

        try {
            if(store==null){
                store = (IMAPStore) session.getStore("imap"); // connect the mail server with IMAP
                if(!store.isConnected()){
                    store.connect(mail_account_info.getProperty("userName"), mail_account_info.getProperty("password"));
                }
            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static Message[] getMails(String mailPath){
        if(store==null){
            connectMail();
        }
        IMAPFolder folder; // inbox
        try {
            folder = (IMAPFolder) store.getFolder(mailPath);
            folder.open(Folder.READ_WRITE);
            Message message[] = folder.getMessages();
            return message;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void sendMail(MimeMessage message){
        if(store==null){
            connectMail();
        }
        try {
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }




}
