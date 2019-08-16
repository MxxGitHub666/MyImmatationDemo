package com.mxx.myimmatationdemo.bean;

/**
 * Created by 98179 on 2019/6/25.
 */

public class TechnologyBean {

    private String Url;
    private String imgUrl;
    private String title;
    private String content;
    private String readNum;
    private String appraiseNum;
    private String time;
    private String name;
    private String nameUrl;


    public String getImgUrl() {
        return imgUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return Url;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.Url = url;
    }

    public void setContent(String mcontent) {
        this.content = mcontent;
    }

    public String getContent() {
        return content;
    }

    public String getReadNum() {
        return readNum;
    }

    public void setReadNum(String readNum) {
        this.readNum = readNum;
    }

    public String getAppraiseNum() {
        return appraiseNum;
    }

    public void setAppraiseNum(String appraiseNum) {
        this.appraiseNum = appraiseNum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameUrl() {
        return nameUrl;
    }

    public void setNameUrl(String nameUrl) {
        this.nameUrl = nameUrl;
    }

    @Override
    public String toString() {
        return "TechnologyBean{" +
                "Url='" + Url + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", readNum='" + readNum + '\'' +
                ", appraiseNum='" + appraiseNum + '\'' +
                ", time='" + time + '\'' +
                ", name='" + name + '\'' +
                ", nameUrl='" + nameUrl + '\'' +
                '}';
    }
}

