package com.jack.security.pojo;

/**
 * Created by wajiangk on 10/20/2016.
 */
public class WeatherModel {

    private String cityName;
    private String date;
    private String temp;
    private String code;
    private String codeText;
    private String codeIcon;


    public WeatherModel() {
    }

    public String getCodeText() {
        return codeText;
    }

    public void setCodeText(String codeText) {
        this.codeText = codeText;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCodeIcon() {
        return codeIcon;
    }

    public void setCodeIcon(String codeIcon) {
        this.codeIcon = codeIcon;
    }
}
