package com.mxx.myimmatationdemo.fragment.three;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.adapter.WeatherAdapter;
import com.mxx.myimmatationdemo.bean.TechnologyBean;
import com.mxx.myimmatationdemo.bean.WeatherBean;
import com.mxx.myimmatationdemo.fragment.BaseFragment;
import com.mxx.myimmatationdemo.fragment.LazyLoadFragment;
import com.mxx.myimmatationdemo.myinterface.RequestCallBack;
import com.mxx.myimmatationdemo.utils.GlideUtils;
import com.mxx.myimmatationdemo.utils.RequestMethod;
import com.mxx.myimmatationdemo.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * @author geyifeng
 * @date 2017/5/12
 */
public class MineThreeFragment extends LazyLoadFragment {
    private ImageView iv_bg;
    private TextView tv_addr;
    private TextView tv_temperature;
    private TextView tv_warn;
    private RecyclerView rv_content;
    private String city;
    private String wendu;
    private String ganmao;
    private List<WeatherBean> weatherBeanList =new ArrayList<WeatherBean>();
    private WeatherAdapter weatherAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_one_mine;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        super.initView();
        iv_bg = mActivity.findViewById(R.id.iv_bg);
        tv_warn = mActivity.findViewById(R.id.tv_warn);
        tv_addr = mActivity.findViewById(R.id.tv_addr);
        tv_temperature = mActivity.findViewById(R.id.tv_temperature);
        rv_content = mActivity.findViewById(R.id.rv_content);

        GlideUtils.load("http://106.14.135.179/ImmersionBar/phone/31.jpeg",iv_bg);

        LinearLayoutManager layoutManager = new LinearLayoutManager(mActivity);
        rv_content.setLayoutManager(layoutManager);

    }

    @Override
    protected void initData() {
        super.initData();
        RequestMethod.get("http://wthrcdn.etouch.cn/weather_mini?city=深圳", new RequestCallBack() {


            @Override
            public void success(List data) {

            }

            @Override
            public void success(String data) {
                Log.i("data=",data);
                try {
                    JSONObject jsonObj = new JSONObject(data).getJSONObject("data");
                    city =jsonObj.getString("city");
                    ganmao =jsonObj.getString("ganmao");
                    wendu =jsonObj.getString("wendu");
                    Log.i("jsonObj=",jsonObj.toString());
                    JSONArray jsonArray = jsonObj.getJSONArray("forecast");
                    for(int i= 0;i<jsonArray.length();i++){
                        WeatherBean weatherBean = new WeatherBean();
                        JSONObject jsonObject = (JSONObject) jsonArray.get(i);
                        weatherBean.setData(jsonObject.getString("date"));
                        weatherBean.setFl(jsonObject.getString("fengli"));
                        weatherBean.setFx(jsonObject.getString("fengxiang"));
                        weatherBean.setLow(jsonObject.getString("low"));
                        weatherBean.setType(jsonObject.getString("type"));
                        weatherBean.setHigh(jsonObject.getString("high"));
                        Log.i("weatherBean=",weatherBean.toString());
                        weatherBeanList.add(weatherBean);
                    }

                    tv_temperature.setText(wendu);
                    tv_addr.setText(city);
                    tv_warn.setText("警告:"+ganmao);

                    weatherAdapter = new WeatherAdapter(R.layout.item_weather,weatherBeanList);
                    rv_content.setAdapter(weatherAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void fail(Exception e) {

            }
        });
    }

    @Override
    protected void loadData() {

    }
}
