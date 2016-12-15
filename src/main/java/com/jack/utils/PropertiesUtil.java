package com.jack.utils;

import org.apache.log4j.Logger;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by wajiangk on 12/9/2016.
 */
public class PropertiesUtil {

    private static final Logger log = Logger.getLogger(PropertiesUtil.class);


    public static void initMessageProperties(String proName,Properties prop) {
        InputStream in = null;
        try {
            ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            Resource[] resources = resourcePatternResolver.getResources("classpath:com/jack/utils/"+proName);
            log.info("mail_connect_options Properties file number: " + resources.length);
            for (Resource resource : resources) {
                Properties p = new Properties();
                String path = resource.getFile().getPath();
                log.info("Load mail_connect_options Properties file: " + path.substring(path.indexOf("WEB-INF") + 16));
                in = resource.getInputStream();
                try{
                    p.load(in);
                    prop.putAll(p);
                }catch(Exception e){
                    log.error("Failed to load mail_connect_options Properties...", e);
                }finally {
                    in.close();
                }
            }
        } catch (IOException e) {
            log.error("Failed to load mail_connect_options Properties.", e);
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
}
