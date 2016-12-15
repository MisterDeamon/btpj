package com.jack.utils;

/**
 * Created by wajiangk on 12/2/2016.
 */


public class ProxyUtil {

    public static void openHpProxy(){
        System.setProperty("http.proxySet", "true");
        System.setProperty("http.proxyHost", "web-proxy.atl.hp.com");
        System.setProperty("http.proxyPort", "8080");
    }
}
