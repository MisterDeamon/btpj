package com.jack.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeUtility;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @author Jack(jiang-kang.wang@hpe.com)
 */

public class MineMail {


    private MimeMessage mimeMessage = null;
    private String saveAttachPath = "C:\\Users\\wajiangk\\Desktop\\MailTest"; // file to save attach file
    private StringBuffer bodyText = new StringBuffer(); // object to save mail content
    private String defaultDateFormat = "yy-MM-dd HH:mm"; // format date

    //mail info
   /* public static final String USER_NAME = "jiang-kang.wang@hpe.com";
    public static final String SERVER_HOST = "casarray1.houston.hpecorp.net";
    public static final String PASSWORD = "lljk_1993629";*/

    /**
     * * Constructor, initial a MimeMessage Object
     */
    public MineMail() {
    }

    public MineMail(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
    }

    public void setMimeMessage(MimeMessage mimeMessage) {
        this.mimeMessage = mimeMessage;
    }


    /**
     * * get the e-mail addr and name of the sender
     */
    public String getFrom() throws Exception {
        InternetAddress address[] = (InternetAddress[]) mimeMessage.getFrom();
        String from = address[0].getAddress();

        //can not get the information about sender
        if (from == null) {
            from = "";
        }
        String personal = address[0].getPersonal();
        if (personal == null) {
            personal = "";
        }
        String fromAddr = null;
        if (personal != null || from != null) {
            fromAddr = personal + "<" + from + ">"; //sender e-mail addr
        }
        return fromAddr;
    }

    /**
     * * get info of the receiver and cc
     * *　"to"----Receiver　"cc"---Carbon Copy　"bcc"--- Blind Carbon Copy
     */
    public String getMailAddress(String type) throws Exception {
        String mailAddr = "";
        String addType = type.toUpperCase();
        InternetAddress[] address = null;
        if (addType.equals("TO") || addType.equals("CC")
                || addType.equals("BCC")) {
            if (addType.equals("TO")) {
                address = (InternetAddress[]) mimeMessage
                        .getRecipients(Message.RecipientType.TO);
            } else if (addType.equals("BCC")) {
                address = (InternetAddress[]) mimeMessage
                        .getRecipients(Message.RecipientType.BCC);
            } else {
                address = (InternetAddress[]) mimeMessage
                        .getRecipients(Message.RecipientType.CC);
            }
            //decode e-mail address
            if (address != null) {
                for (int i = 0; i < address.length; i++) {
                    String emailAddr = address[i].getAddress();
                    if (emailAddr == null) {
                        emailAddr = "";
                    } else {
                        emailAddr = MimeUtility.decodeText(emailAddr);
                    }
                    String personal = address[i].getPersonal();
                    if (personal == null) {
                        personal = "";
                    } else {
                        personal = MimeUtility.decodeText(personal);
                    }

                    //completed mail address
                    String compositeto = personal + "<" + emailAddr + ">";
                    mailAddr += "," + compositeto;
                }
                mailAddr = mailAddr.substring(1);
            }

        } else {
            throw new Exception("Wrong E-mail type!");
        }
        return mailAddr;
    }


    public InternetAddress[] getAddress(String type) throws Exception {
        String addType = type.toUpperCase();
        InternetAddress[] address;
        if (addType.equals("TO") || addType.equals("CC")
                || addType.equals("BCC")) {
            if (addType.equals("BCC")) {
                address = (InternetAddress[]) mimeMessage
                        .getRecipients(Message.RecipientType.BCC);
            } else if (addType.equals("CC")) {
                address = (InternetAddress[]) mimeMessage
                        .getRecipients(Message.RecipientType.CC);
            } else {
                address = (InternetAddress[]) mimeMessage
                        .getRecipients(Message.RecipientType.TO);
            }

        } else {
            throw new Exception("Wrong E-mail type!");
        }

        return address;
    }

    /**
     * 　*　get the mail subject
     */
    public String getSubject() throws MessagingException {
        String subject = "";
        try {
            subject = MimeUtility.decodeText(mimeMessage.getSubject());
            if (subject == null) {
                subject = "";
            }
        } catch (Exception exce) {
            exce.printStackTrace();
        }
        return subject;
    }

    /**
     * *　get the mail sending date
     */
    public String getSentDate() throws Exception {
        Date sentDate = mimeMessage.getSentDate();
        SimpleDateFormat format = new SimpleDateFormat(defaultDateFormat);
        String strSentDate = format.format(sentDate);
        return strSentDate;

    }

    /**
     * 　*　get the mail content
     */
    public String getBodyText() {
        return bodyText.toString();
    }


    /**
     * *　analysis the mail
     */
    public void getMailContent(Part part) throws Exception {

        String contentType = part.getContentType();

        // get MimeType
        int nameIndex = contentType.indexOf("name");
        boolean conName = false;
        if (nameIndex != -1) {
            conName = true;
        }

        if (part.isMimeType("text/plain") && conName == false) {
            bodyText.append((String) part.getContent());
        } else if (part.isMimeType("text/html") && conName == false) {
            // text/html
            // bodyText.append((String) part.getContent());
        } else if (part.isMimeType("multipart/*")) {
            Multipart multipart = (Multipart) part.getContent();
            int counts = multipart.getCount();
            for (int i = 0; i < counts; i++) {
                getMailContent(multipart.getBodyPart(i));
            }
        } else if (part.isMimeType("message/rfc822")) {
            getMailContent((Part) part.getContent());
        } else {
        }

    }


    /**
     * *　judge the reply sign
     */
    public boolean getReplySign() throws MessagingException {
        boolean replySign = false;
        String needReply[] = mimeMessage
                .getHeader("Disposition-Notification-To");
        if (needReply != null) {
            replySign = true;
        }
        return replySign;

    }


    /**
     * 　get the Message-Id
     */
    public String getMessageId() throws MessagingException {
        String messageID = mimeMessage.getMessageID();
        return messageID;
    }

    /**
     * judge whether the mail is new or not
     */
    public boolean isNew() throws MessagingException {
        boolean isNew = true;
        Flags flags = ((Message) mimeMessage).getFlags();
        Flags.Flag[] flag = flags.getSystemFlags();
        for (int i = 0; i < flag.length; i++) {
            if (flag[i] == Flags.Flag.SEEN) {
                isNew = false;
                break;
            }
        }
        return isNew;
    }


    /**
     * judge whether the mail includes attach file or not
     */
    public boolean isContainAttach(Part part) throws Exception {
        boolean attachFlag = false;
        // String contentType = part.getContentType();
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart mPart = mp.getBodyPart(i);
                String disposition = mPart.getDisposition();
                if ((disposition != null)
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition
                        .equals(Part.INLINE))))
                    attachFlag = true;
                else if (mPart.isMimeType("multipart/*")) {
                    attachFlag = isContainAttach((Part) mPart);
                } else {
                    String conType = mPart.getContentType();
                    if (conType.toLowerCase().indexOf("application") != -1)
                        attachFlag = true;
                    if (conType.toLowerCase().indexOf("name") != -1)
                        attachFlag = true;
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            attachFlag = isContainAttach((Part) part.getContent());
        }
        return attachFlag;

    }

    /**
     * 　*　save attach file
     */
    public void saveAttachMent(Part part) throws Exception {

        String fileName = "";
        if (part.isMimeType("multipart/*")) {
            Multipart mp = (Multipart) part.getContent();
            for (int i = 0; i < mp.getCount(); i++) {
                BodyPart mPart = mp.getBodyPart(i);
                String disposition = mPart.getDisposition();
                if ((disposition != null)
                        && ((disposition.equals(Part.ATTACHMENT)) || (disposition
                        .equals(Part.INLINE)))) {
                    fileName = mPart.getFileName();
                    if (fileName.toLowerCase().indexOf("gb2312") != -1) {
                        fileName = MimeUtility.decodeText(fileName);
                    }
                    saveFile(fileName, mPart.getInputStream());
                } else if (mPart.isMimeType("multipart/*")) {
                    saveAttachMent(mPart);
                } else {
                    fileName = mPart.getFileName();
                    if ((fileName != null)
                            && (fileName.toLowerCase().indexOf("GB2312") != -1)) {
                        fileName = MimeUtility.decodeText(fileName);
                        saveFile(fileName, mPart.getInputStream());
                    }
                }
            }
        } else if (part.isMimeType("message/rfc822")) {
            saveAttachMent((Part) part.getContent());
        }
    }


    /**
     * 　set attach file path
     */
    public void setAttachPath(String attachPath) {
        this.saveAttachPath = attachPath;

    }

    /**
     * 　*　set date format
     */
    public void setDateFormat(String format) throws Exception {
        this.defaultDateFormat = format;
    }


    /**
     * 　*　get attach file path
     */
    public String getAttachPath() {
        return saveAttachPath;
    }


    /**
     * 　*　真正的保存附件到指定目录里
     */
    private void saveFile(String fileName, InputStream in) throws Exception {
        String osName = System.getProperty("os.name");
        String storeDir = getAttachPath();
        String separator = "";
        if (osName == null) {
            osName = "";
        }

        if (osName.toLowerCase().indexOf("win") != -1) {
            separator = "\\";
            if (storeDir == null || storeDir.equals(""))
                storeDir = "c:\\tmp";
        } else {
            separator = "/";
            storeDir = "/tmp";
        }

        File storeFile = new File(storeDir + separator + fileName);
        // for(int　i=0;storefile.exists();i++){
        // storefile　=　new　File(storedir+separator+fileName+i);
        // }

        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        try {
            bos = new BufferedOutputStream(new FileOutputStream(storeFile));
            bis = new BufferedInputStream(in);
            int c;
            while ((c = bis.read()) != -1) {
                bos.write(c);
                bos.flush();
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            throw new Exception("save file failed!");
        } finally {
            bos.close();
            bis.close();
        }
    }


    /**
     * 　ReceiveEmail类测试
     */
    public static void main(String args[]) throws Exception {

        Message messages[] = MailUtil.getMails("inbox/MDM Test");
        MineMail re;
//        LinuxUtil linuxUtil = new LinuxUtil();
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
            }

        }


    }

}