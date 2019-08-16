package com.mxx.myimmatationdemo.fragment.three;

import android.os.Bundle;
import android.widget.TextView;

import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.fragment.BaseFragment;
import com.mxx.myimmatationdemo.fragment.LazyLoadFragment;


/**
 * @author geyifeng
 * @date 2019-05-05 17:35
 */
public class NewsFragment extends LazyLoadFragment {


    private TextView tvContent;

    private String mTitle = "";

    public static NewsFragment newInstance(String title) {
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mTitle = arguments.getString("title");
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initView() {
        super.initView();
        tvContent = mActivity.findViewById(R.id.tv_content);
        tvContent.setText(mTitle);

    }

    @Override
    protected void loadData() {

    }
}
