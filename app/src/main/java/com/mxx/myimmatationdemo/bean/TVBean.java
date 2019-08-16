package com.mxx.myimmatationdemo.bean;

/**
 * Created by 98179 on 2019/7/9.
 */

public class TVBean {

    private String url;
    private String title;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    @Override
    public String toString() {
        return "TVBean{" +
                "url='" + url + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
