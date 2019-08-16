package com.mxx.myimmatationdemo.bean;

/**
 * Created by 98179 on 2019/6/19.
 */

public class News {
    private String newsTitle;   //新闻标题
    private String newsUrl;     //新闻链接地址
    private String desc;        //新闻概要
    private String newsTime;    //新闻时间与来源

    private int marginStart;
    private int marginEnd;

    public News(String newsTitle, String newsUrl, String desc, String newsTime) {
        this.newsTitle = newsTitle;
        this.newsUrl = newsUrl;
        this.desc = desc;
        this.newsTime = newsTime;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getNewsTime() {
        return newsTime;
    }

    public void setNewsTime(String newsTime) {
        this.newsTime = newsTime;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public int getMarginStart() {
        return marginStart;
    }

    public void setMarginStart(int marginStart) {
        this.marginStart = marginStart;
    }

    public int getMarginEnd() {
        return marginEnd;
    }

    public void setMarginEnd(int marginEnd) {
        this.marginEnd = marginEnd;
    }


    @Override
    public String toString() {
        return "newsTime"+newsTime+";"+"newsUrl"+newsUrl+";"+"newsTitle"+newsTitle+";"+"desc"+desc;
    }
}
