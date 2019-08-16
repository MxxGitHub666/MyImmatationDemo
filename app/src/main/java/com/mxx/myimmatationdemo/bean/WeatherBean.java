package com.mxx.myimmatationdemo.bean;

/**
 * Created by 98179 on 2019/6/29.
 */

public class WeatherBean {


    private String data;
    private String high;
    private String fx;
    private String low;
    private String fl;
    private String type;
    private String city;
    private String ganmao;
    private String wendu;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getGanmao() {
        return ganmao;
    }

    public void setGanmao(String ganmao) {
        this.ganmao = ganmao;
    }

    public String getWendu() {
        return wendu;
    }

    public void setWendu(String wendu) {
        this.wendu = wendu;
    }

    @Override
    public String toString() {
        return "WeatherBean{" +
                "data='" + data + '\'' +
                ", high='" + high + '\'' +
                ", fx='" + fx + '\'' +
                ", low='" + low + '\'' +
                ", fl='" + fl + '\'' +
                ", type='" + type + '\'' +
                ", city='" + city + '\'' +
                ", ganmao='" + ganmao + '\'' +
                ", wendu='" + wendu + '\'' +
                '}';
    }
}
