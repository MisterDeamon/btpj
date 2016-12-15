package com.jack.utils;

import com.jack.security.pojo.WeatherModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wajiangk on 10/20/2016.
 */
public class WeatherInfo {

    /**
     * 根据IP地址获取详细的地域信息
     *
     * @project:personGocheck
     * @class:AddressUtils.java
     * @author:heguanhua E-mail:37809893@qq.com
     * @date：Nov 14, 2012 6:38:25 PM
     */

    public String getCityName(String content, String encodingString)
            throws Exception {
        // 这里调用pconline的接口
        String urlStr = "http://ip.taobao.com/service/getIpInfo.php";
        // 从http://whois.pconline.com.cn取得IP所在的省市区信息
        String returnStr = this.getUrlInfo(urlStr, content, encodingString);
        if (returnStr != null) {
            // 处理返回的省市区信息
            String[] temp = returnStr.split(",");
            if (temp.length < 3) {
                return "0";//无效IP，局域网测试
            }
/*            String region = (temp[5].split(":"))[1].replaceAll("\"", "");
            region = decodeUnicode(region);// 省份

            String country = "";
            String area = "";*/
            String city = "";
/*            String county = "";
            String isp = "";*/
            for (int i = 0; i < temp.length; i++) {
                switch (i) {
                    /*case 1:
                        country = (temp[i].split(":"))[2].replaceAll("\"", "");
                        country = decodeUnicode(country);// 国家
                        break;
                    case 3:
                        area = (temp[i].split(":"))[1].replaceAll("\"", "");
                        area = decodeUnicode(area);// 地区
                        break;
                    case 5:
                        region = (temp[i].split(":"))[1].replaceAll("\"", "");
                        region = decodeUnicode(region);// 省份
                        break;*/
                    case 7:
                        city = (temp[i].split(":"))[1].replaceAll("\"", "");
                        city = decodeUnicode(city).replace("市","");// 市区
                        break;
                    /*case 9:
                        county = (temp[i].split(":"))[1].replaceAll("\"", "");
                        county = decodeUnicode(county);// 地区
                        break;
                    case 11:
                        isp = (temp[i].split(":"))[1].replaceAll("\"", "");
                        isp = decodeUnicode(isp); // ISP公司
                        break;*/
                }
            }
            return city;
        }
        return null;
    }


    public String requestWeatherInfo(String httpUrl, String httpArg,String encode) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        httpUrl = httpUrl + "?" + httpArg;

        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setRequestMethod("GET");
            // 填入apikey到HTTP header
            connection.setRequestProperty("apikey", "0eb01ba2e1fb9050dcd1bae939a6ba21");
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, encode));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    public Map<String,Object> now_weatherInfo(String requestUrl) throws Exception {

        Map<String,Object> map = new HashMap<String, Object>();
        String city = getCityName(requestUrl,"UTF-8");

        ProxyUtil.openHpProxy();

        String httpUrl = "http://apis.baidu.com/heweather/weather/free";
        String httpArg = "city="+ new PinyinTool().toPinYin(city,"", PinyinTool.Type.LOWERCASE);
        String jsonResult = this.requestWeatherInfo(httpUrl, httpArg,"UTF-8");

        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        //解析json
        JSONObject jo = new JSONObject(jsonResult);
        JSONArray ja = jo.getJSONArray("HeWeather data service 3.0");
        JSONObject jo1 = (JSONObject) ja.get(0);
        JSONObject now = jo1.getJSONObject("now");//实时天气信息
        JSONObject cond = now.getJSONObject("cond");
        JSONArray daily_forecast = jo1.getJSONArray("daily_forecast");

        WeatherModel todayWeather = new WeatherModel();
        todayWeather.setCityName(city);
        todayWeather.setCode(cond.getString("code"));
        todayWeather.setCodeText(cond.getString("txt"));
        todayWeather.setTemp(now.getString("tmp"));
        todayWeather.setDate(StringUtils.getWeek(StringUtils.getFormatDate_1()));
        todayWeather.setCodeIcon(Climacon.getClimacon().prop.getProperty(todayWeather.getCode()));

        List<WeatherModel> forecatWeather = new ArrayList<WeatherModel>();

        for(int i=1;i<=3;i++){
            JSONObject temp =  (JSONObject)daily_forecast.get(i);
            WeatherModel tempModel = new WeatherModel();
            tempModel.setCode(temp.getJSONObject("cond").getString("code_d"));
            tempModel.setCodeText(temp.getJSONObject("cond").getString("txt_d"));
            tempModel.setCodeIcon(Climacon.getClimacon().prop.getProperty(tempModel.getCode()));
            tempModel.setTemp(temp.getJSONObject("tmp").getString("min")+"°C-"+temp.getJSONObject("tmp").getString("max")+"°C");
            tempModel.setDate(StringUtils.getWeek(temp.getString("date")));
            forecatWeather.add(tempModel);
        }

        map.put("nowWeather",todayWeather);
        map.put("forecastWeather",forecatWeather);
        return map;
    }

    /**
     * @param urlStr   请求的地址
     * @param content  请求的参数 格式为：name=xxx&pwd=xxx
     * @param encoding 服务器端请求编码。如GBK,UTF-8等
     * @return
     */
    private String getUrlInfo(String urlStr, String content, String encoding) {
        URL url = null;
        HttpURLConnection connection = null;
        try {

            ProxyUtil.openHpProxy();

            url = new URL(urlStr+"?ip="+content);
            connection = (HttpURLConnection) url.openConnection();// 新建连接实例
            connection.setRequestProperty("apikey", "0eb01ba2e1fb9050dcd1bae939a6ba21");
            connection.setConnectTimeout(2000);// 设置连接超时时间，单位毫秒
            connection.setReadTimeout(2000);// 设置读取数据超时时间，单位毫秒
            connection.setDoOutput(true);// 是否打开输出流 true|false
            connection.setDoInput(true);// 是否打开输入流true|false
            connection.setRequestMethod("GET");// 提交方法POST|GET
            connection.setUseCaches(false);// 是否缓存true|false
            connection.connect();// 打开连接端口

            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(), encoding));// 往对端写完数据对端服务器返回数据
            // ,以BufferedReader流来读取
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            reader.close();
            return buffer.toString();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();// 关闭连接
            }
        }
        return null;
    }

    /**
     * unicode 转换成 中文
     *
     * @param theString
     * @return
     * @author fanhui 2007-3-15
     */
    public  String decodeUnicode(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed      encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't') {
                        aChar = '\t';
                    } else if (aChar == 'r') {
                        aChar = '\r';
                    } else if (aChar == 'n') {
                        aChar = '\n';
                    } else if (aChar == 'f') {
                        aChar = '\f';
                    }
                    outBuffer.append(aChar);
                }
            } else {
                outBuffer.append(aChar);
            }
        }
        return outBuffer.toString();
    }

}
