package com.jack.security.webapp;

import com.jack.utils.ProxyUtil;
import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wajiangk on 11/24/2016.
 */
@Controller
@RequestMapping(value = "/management/security/musicPlayer")
public class MusicPlayerController extends BaseController{

    private static final String MUSIC_PLAYER = "management/security/musicPlayer/musicPlayer";

    private static final String MUSIC_INFO_SITE_1="http://www.lrcgc.com/so/";
    private static final String MUSIC_INFO_SITE_2="http://www.90lrc.cn/so.php";

    @RequestMapping(value="",method = RequestMethod.GET)
    public String musicPlayer(){
        return MUSIC_PLAYER;
    }

    @RequestMapping(value="/musicInfo",method={RequestMethod.POST,RequestMethod.GET},produces ="application/json;charset=UTF-8")
    public @ResponseBody String musicInfo(String musicInfo){

        Map<String,Object> map = new HashMap<String,Object>();

        System.out.println(musicInfo);

        JSONObject json = new JSONObject(musicInfo);

        String lyric =  catchMusicInfo(json);

        map.put("lyric",lyric);
        return getJsonResult(map);
    }

    private String catchMusicInfo(JSONObject music){

        Document doc = requestLrc(music.getString("title"),music.getString("album"),music.getString("artist"),music.getString("name"));

        Elements links =  doc.select(".so_list").select("ul").select("li");

        String lyricHref="";
        if(links.size()<=0){
            links=requestLrc(music.getString("title"),music.getString("artist")).select(".so_list").select("ul").select("li");
            if(links.size()<=0){
                links=requestLrc(music.getString("title")).select(".so_list").select("ul").select("li");
            }
        }

        Element el_href = links.get(0).select("a[href]").get(0);
        lyricHref="http://www.lrcgc.com"+el_href.attr("href");

        Document lyricDoc;

        String result = "";
        if(!"".equals(lyricHref)){
            ProxyUtil.openHpProxy();

            try {
                lyricDoc = Jsoup.connect(lyricHref).get();
                Elements el = lyricDoc.select("#J_lyric");
                result = el.toString();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    private Document requestLrc(String title,String album,String artist,String musicName) {

        title = title.replaceAll("\\(.*\\)","");
        artist = artist.replaceAll("\\(.*\\)","");
        album = album.replaceAll("\\(.*\\)","");
        Document doc = null;
        try {
            //open system proxy
            ProxyUtil.openHpProxy();
            doc = Jsoup.connect(MUSIC_INFO_SITE_1).data("q",title+" "+artist).data("csrf_token","c0dc643292f16ecb").timeout(8000).get();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;

    }

    private Document requestLrc(String title,String artist) {

        title = title.replaceAll("\\(.*\\)","");
        artist = artist.replaceAll("\\(.*\\)","");
        Document doc = null;
        try {
            //open system proxy
            ProxyUtil.openHpProxy();

            doc = Jsoup.connect(MUSIC_INFO_SITE_1).data("q",title+" "+artist).data("csrf_token","c0dc643292f16ecb").timeout(6000).get();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;

    }

    private Document requestLrc(String title) {

        title = title.replaceAll("\\(.*\\)","");
        Document doc = null;
        try {
            //open system proxy
            ProxyUtil.openHpProxy();
            doc = Jsoup.connect(MUSIC_INFO_SITE_1).data("q",title).data("csrf_token","c0dc643292f16ecb").timeout(6000).get();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return doc;

    }

}
