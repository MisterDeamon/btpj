package com.jack.security.service.thread;

import com.jack.utils.*;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.Part;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.Security;
import java.util.Enumeration;
import java.util.Properties;

/**
 * Created by wajiangk on 12/9/2016.
 */
public class RecieveMailThread implements Runnable {
    @Override
    public void run() {

        checkMails(new LinuxUtil());
    }


    private void checkMails(LinuxUtil linuxUtil) {
        Message[] messages;
        MineMail re;
        while(true){
            System.out.println("run.......");
            messages = MailUtil.getMails("inbox/MDM Test");
            try {
                for (int i = 0; i < messages.length; i++) {
                    re = new MineMail((MimeMessage) messages[i]);
                    if (re.isNew()) {
                        String sub = re.getSubject();
                        re.setDateFormat("yy-MM-dd　HH:mm:ss");
                        String sendDate = re.getSentDate();
                        String from = re.getFrom();

                        String receiver = re.getMailAddress("to");
                        String cc = re.getMailAddress("cc");
                        re.getMailContent((Part) messages[i]);
                        String mailContent = re.getBodyText();
                        System.out.println("邮件　" + i + "　主题:　" + sub);
                        System.out.println("邮件　" + i + "　发送时间:　" + sendDate);
                        System.out.println("邮件　" + i + "　是否已读:　" + !re.isNew());
                        System.out.println("邮件　" + i + "　发送人地址:　" + from);
                        System.out.println("邮件　" + i + "　收信人地址:　" + receiver);
                        System.out.println("邮件　" + i + "　抄送:　" + cc);
                        System.out.println("邮件　" + i + "　正文内容:　\r\n" + mailContent);
                        synchronized (this){
                            if ("Jack<1044139917@qq.com>".equals(from)) {
                                String result = linuxUtil.excuteCommand(mailContent);
                                if(result==null||result.equals("")||!result.contains("command not found")){


                                    MimeMessage message = new MimeMessage(MailUtil.getSession());

                                    message.setRecipient(Message.RecipientType.TO,re.getAddress("to")[0]);
                                    Properties linkmanList = new Properties();
                                    PropertiesUtil.initMessageProperties("mail_admins_addr.properties",linkmanList);
                                    InternetAddress[] ccList = new InternetAddress[linkmanList.size()];

                                    int k=0;
                                    for (Enumeration e = linkmanList.propertyNames(); e.hasMoreElements(); )
                                    {
                                        String key = (String)e.nextElement();
                                        ccList[k]=new InternetAddress();
                                        ccList[k].setPersonal(key);
                                        ccList[k].setAddress(linkmanList.getProperty(key));
                                        k++;
                                        if(k>=linkmanList.size()){
                                            k=0;
                                        }
                                    }
                                    message.setRecipients(Message.RecipientType.CC,ccList);
                                    String ccContent = "<html><title>RE: "+sub+"</title><body>Hi all, <br />the command \""+mailContent+"\" has excuted succussfully!<br /> Regards,<br>"+receiver+"</body></html>";
                                    message.setSubject("CONRIFM:"+sub);
                                    message.setContent(ccContent,"text/html;charset=utf-8");

                                    MailUtil.getSession().getTransport("smtp").send(message);
                                }
                            }
                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


    public static void main(String[] args){
        Message[] messages;
        MineMail re;
        messages = MailUtil.getMails("inbox/MDM Test");
        try {
            for (int i = 0; i < messages.length; i++) {
                re = new MineMail((MimeMessage) messages[i]);
                    String sub = re.getSubject();
                    re.setDateFormat("yy-MM-dd　HH:mm:ss");
                    String sendDate = re.getSentDate();
                    String from = re.getFrom();

                    String receiver = re.getMailAddress("to");
                    String cc = re.getMailAddress("cc");
                    re.getMailContent((Part) messages[i]);
                    String mailContent = re.getBodyText();
                    System.out.println("邮件　" + i + "　主题:　" + sub);
                    System.out.println("邮件　" + i + "　发送时间:　" + sendDate);
                    System.out.println("邮件　" + i + "　是否已读:　" + !re.isNew());
                    System.out.println("邮件　" + i + "　发送人地址:　" + from);
                    System.out.println("邮件　" + i + "　收信人地址:　" + receiver);
                    System.out.println("邮件　" + i + "　抄送:　" + cc);
                    System.out.println("邮件　" + i + "　正文内容:　\r\n" + mailContent);
                    if ("Jack<1044139917@qq.com>".equals(from)) {
                        String result = new LinuxUtil().excuteCommand(mailContent);
                        if(result==null||result.equals("")||!result.contains("command not found")){

                            MimeMessage message = new MimeMessage(MailUtil.getSession());

                            message.setRecipient(Message.RecipientType.TO,re.getAddress("to")[0]);
                            Properties linkmanList = new Properties();
                            PropertiesUtil.initMessageProperties("mail_admins_addr.properties",linkmanList);
                            InternetAddress[] ccList = new InternetAddress[linkmanList.size()];

                            int k=0;
                            for (Enumeration e = linkmanList.propertyNames(); e.hasMoreElements(); )
                            {
                                String key = (String)e.nextElement();
                                ccList[k]=new InternetAddress();
                                ccList[k].setPersonal(key);
                                ccList[k].setAddress(linkmanList.getProperty(key));
                                k++;
                                if(k>=linkmanList.size()){
                                    k=0;
                                }
                            }
                            message.setRecipients(Message.RecipientType.CC,ccList);
                            String ccContent = "<html><title>RE: "+sub+"</title><body>Hi all, <br />the command \""+mailContent+"\" has excuted succussfully!<br /> Regards,<br>"+receiver+"</body></html>";
                            message.setSubject("CONRIFM:"+sub);
                            message.setContent(ccContent,"text/html;charset=utf-8");
                            ProxyUtil.openHpProxy();
                            Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
                           MailUtil.getSession().getTransport("smtp").send(message);
                        }
                    }
                }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
