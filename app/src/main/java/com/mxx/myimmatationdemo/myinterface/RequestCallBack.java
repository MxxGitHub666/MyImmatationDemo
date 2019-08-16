package com.mxx.myimmatationdemo.myinterface;

import com.mxx.myimmatationdemo.bean.TechnologyBean;

import java.util.List;


/**
 * Created by 98179 on 2019/6/28.
 */

public interface RequestCallBack<T> {

    void success(List<T> data);
    void success(String data);
    void fail(Exception e);
}
