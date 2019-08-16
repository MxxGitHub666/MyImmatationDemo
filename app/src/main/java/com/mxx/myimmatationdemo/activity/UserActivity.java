package com.mxx.myimmatationdemo.activity;

import com.mxx.myimmatationdemo.R;


/**
 * Created by 98179 on 2019/6/26.
 */

public class UserActivity extends BaseActivity {



    @Override
    protected int getLayoutId() {

        return R.layout.activity_user;
    }

    @Override
    protected void initData() {
        super.initData();
//        ImageView img = findViewById(R.id.iv_01);
//        ActivityUserBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_user);
//        UserBean userBean = new UserBean("xiaoxia", "mo");
////        userBean.setImg(img,"https://profile.csdnimg.cn/D/5/1/1_qq_40798435",R.mipmap.ic_launcher);
//        binding.setUser(userBean);

//        binding.setHandler(new ClickHandler(this, binding));

    }
}
