package com.coca.cola;

/**
 * Created by 98179 on 2019/8/19.
 */

public class Cala {

    public String name;
    public String money;
    public String years;
    public String size;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }


    @Override
    public String toString() {
        return "Cala{" +
                "name='" + name + '\'' +
                ", money='" + money + '\'' +
                ", years='" + years + '\'' +
                ", size='" + size + '\'' +
                '}';
    }
}
