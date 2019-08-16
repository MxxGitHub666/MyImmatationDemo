package com.mxx.myimmatationdemo.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.RelativeLayout;

import com.mxx.myimmatationdemo.R;

/**
 * Created by 98179 on 2019/6/18.
 */

public class SlipActivity extends BaseActivity {
    // 抽屉菜单对象
    private ActionBarDrawerToggle drawerbar;
    public DrawerLayout drawerLayout;
    private RelativeLayout main_left_drawer_layout, main_right_drawer_layout;

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.a;
    }

    @Override
    protected void initView() {
        super.initView();
        drawerLayout = (DrawerLayout) findViewById(R.id.main_drawer_layout);
        //设置菜单内容之外其他区域的背景色
        drawerLayout.setScrimColor(Color.TRANSPARENT);

        //左边菜单
        main_left_drawer_layout = (RelativeLayout) findViewById(R.id.main_left_drawer_layout);
        //右边菜单
        main_right_drawer_layout = (RelativeLayout) findViewById(R.id.main_right_drawer_layout);
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void setListener() {
        super.setListener();
        drawerbar = new ActionBarDrawerToggle(this, drawerLayout, R.mipmap.ic_launcher, R.string.open, R.string.close) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        drawerLayout.setDrawerListener(drawerbar);
    }


    //左边菜单开关事件
    public void openLeftLayout(View view) {
        if (drawerLayout.isDrawerOpen(main_left_drawer_layout)) {
            drawerLayout.closeDrawer(main_left_drawer_layout);
        } else {
            drawerLayout.openDrawer(main_left_drawer_layout);
        }
    }

    // 右边菜单开关事件
    public void openRightLayout(View view) {
        if (drawerLayout.isDrawerOpen(main_right_drawer_layout)) {
            drawerLayout.closeDrawer(main_right_drawer_layout);
        } else {
            drawerLayout.openDrawer(main_right_drawer_layout);
        }
    }
}
