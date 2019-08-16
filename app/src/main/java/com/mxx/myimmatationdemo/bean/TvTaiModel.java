package com.mxx.myimmatationdemo.bean;

import java.io.Serializable;

/**
 * Created by 98179 on 2019/7/3.
 */

public class TvTaiModel implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 网页上面的视频连接
     */
    private String href;
    /**
     * 解析以后的真正的视频连接
     */
    private String realHref;
    /**
     * 视频的图片链接
     */
    private String img;
    /**
     * 视频的标题
     */
    private String title;
    /**
     * 视频的清晰度
     */
    private String shdIco;
    /**
     * 视频的时间
     */
    private String v_time_num;
    /**
     * 视频的艺人
     */
    private String man;
    /**
     * 视频的描述
     */
    private String description;
    /**
     * 视频的评分
     */
    private String grade;
    /**
     * 视频的更新集数
     */
    private String series;



    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getRealHref() {
        return realHref;
    }

    public void setRealHref(String realHref) {
        this.realHref = realHref;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShdIco() {
        return shdIco;
    }

    public void setShdIco(String shdIco) {
        this.shdIco = shdIco;
    }

    public String getV_time_num() {
        return v_time_num;
    }

    public void setV_time_num(String v_time_num) {
        this.v_time_num = v_time_num;
    }

    public String getMan() {
        return man;
    }

    public void setMan(String man) {
        this.man = man;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    @Override
    public String toString() {
        return "TvTaiModel{" +
                "href='" + href + '\'' +
                ", realHref='" + realHref + '\'' +
                ", img='" + img + '\'' +
                ", title='" + title + '\'' +
                ", shdIco='" + shdIco + '\'' +
                ", v_time_num='" + v_time_num + '\'' +
                ", man='" + man + '\'' +
                ", description='" + description + '\'' +
                ", grade='" + grade + '\'' +
                ", series='" + series + '\'' +
                '}';
    }
}
