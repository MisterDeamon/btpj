package com.jack.utils;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by wajiangk on 10/20/2016.
 */
public class Climacon {

    private static Climacon climacon;
    private static final Logger log = Logger.getLogger(Climacon.class);
    public static Properties prop = new Properties();


    private Climacon(){
        init();
    }

    public static void init(){
        if(prop == null){
            prop = new Properties();
        }
        if(prop.isEmpty()){
            initMessageProperties();
        }

    }

    public static Climacon getClimacon(){
        if(climacon==null){
            climacon = new Climacon();
        }
        return climacon;
    }

    private static void initMessageProperties() {
        InputStream in = null;
        try {
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resourcePatternResolver.getResources("classpath:com/jack/utils/Climacon.properties");
            log.info("Message Properties file number: " + resources.length);

            for (Resource resource : resources) {
                Properties p = new Properties();
                String path = resource.getFile().getPath();
                log.info("Load Message Properties file: " + path.substring(path.indexOf("WEB-INF") + 16));

                in = resource.getInputStream();
                try{
                    p.load(in);
                    prop.putAll(p);
                }catch(Exception e){
                    log.error("Failed to load Message Properties.", e);
                }finally {
                    in.close();
                }

            }
        } catch (IOException e) {
            log.error("Failed to load Message Properties.", e);
            try {
                in.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args){
        String p = Climacon.getClimacon().prop.getProperty("300");
    }

}
