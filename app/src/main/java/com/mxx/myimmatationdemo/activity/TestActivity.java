package com.mxx.myimmatationdemo.activity;

import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mxx.myimmatationdemo.R;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by 98179 on 2019/9/4.
 */

public class TestActivity extends BaseActivity {

    private ListView listView;
    private ListView listView2;


    private List<String> data = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initData() {
        super.initData();
        //字符串组合

        data.add("nihao");
        data.add("nihao1");
        data.add("nihao2");
        data.add("nihao3");
        data.add("nihao4");
        data.add("nihao5");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");
        data.add("nihao6");

    }

    @Override
    protected void initView() {
        super.initView();
        listView = findViewById(R.id.list1);
        listView2 = findViewById(R.id.list2);

        ArrayAdapter<String> arrayAdapter
                = new ArrayAdapter<String>(TestActivity.this,R.layout.item_test,R.id.tv_test,data);

        ArrayAdapter<String> arrayAdapter2
                = new ArrayAdapter<String>(TestActivity.this,R.layout.item_test,R.id.tv_test,data);

        listView.setAdapter(arrayAdapter);
        listView2.setAdapter(arrayAdapter2);

    }




}
