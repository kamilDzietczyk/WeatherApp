package com.example.weatherapp;

public class DBObject {

    private  String id;
    private  String CITY;
    private  String sky;
    private  String desc;
    private  String temp;
    private  String press;
    private  String wind;
    private  String searchTime;

    public DBObject(String id, String CITY, String sky, String desc, String temp, String press, String wind, String searchTime) {
        this.id = id;
        this.CITY = CITY;
        this.sky = sky;
        this.desc = desc;
        this.temp = temp;
        this.press = press;
        this.wind = wind;
        this.searchTime = searchTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCITY() {
        return CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getSky() {
        return sky;
    }

    public void setSky(String sky) {
        this.sky = sky;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }
}
