package com.jack.utils;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * Created by wajiangk on 12/9/2016.
 */
public class LinuxUtil {

    private static Session session;
    private static Properties properties;
    private static Connection conn;

    public static synchronized String excuteCommand(String command){

        if(conn==null){
            if(properties==null){
                properties=new Properties();
                PropertiesUtil.initMessageProperties("linux_account_info.properties",properties);
            }
             conn = new Connection(properties.getProperty("host"));
            try {
                conn.connect();

                boolean isAuthenticated = conn.authenticateWithPassword(properties.getProperty("username"), properties.getProperty("password"));
                if (isAuthenticated == false){
                    throw new IOException("Authentication failed.");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            if(session==null){
                session =  conn.openSession();
            }
            session.execCommand(command);
            InputStream stdout =new StreamGobbler(session.getStdout());
            BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
            String result="";
            while (true) {
                String line = br.readLine();
                if (line == null)
                    break;
                result+=line+" ";
            }
            System.out.println(result);
            System.out.println(">$ " + session.getExitStatus());
            session.waitForCondition(1,150000);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void close(){
        session.close();
    }

}
