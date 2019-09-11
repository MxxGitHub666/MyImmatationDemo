package com.mxx.myimmatationdemo.ui.main.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.jakewharton.rxbinding2.view.RxView;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.Constants;
import com.mxx.myimmatationdemo.base.activity.Base2Activity;
import com.mxx.myimmatationdemo.contract.main.LoginContract;
import com.mxx.myimmatationdemo.presenter.main.LoginPresenter;
import com.mxx.myimmatationdemo.utils.CommonUtils;
import com.mxx.myimmatationdemo.utils.StatusBarUtil;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 98179 on 2019/9/6.
 */

public class LoginActivity extends Base2Activity<LoginPresenter> implements LoginContract.View {
    @BindView(R.id.login_group)
    RelativeLayout mLoginGroup;
    @BindView(R.id.login_toolbar)
    Toolbar mToolbar;
    @BindView(R.id.login_account_edit)
    EditText mAccountEdit;
    @BindView(R.id.login_password_edit)
    EditText mPasswordEdit;
    @BindView(R.id.login_btn)
    Button mLoginBtn;
    @BindView(R.id.login_register_btn)
    Button mRegisterBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initToolbar() {
        StatusBarUtil.immersive(this);
        StatusBarUtil.setPaddingSmart(this,mToolbar);
        mToolbar.setNavigationOnClickListener(v -> onBackPressedSupport());
    }

    @Override
    protected void initEventAndData() {
        subscribeLoginClickEvent();
    }

    @Override
    public void showLoginSuccess() {
        CommonUtils.showMessage(this,getString(R.string.login_success));
        onBackPressedSupport();
    }
    @OnClick({R.id.login_register_btn})
    void onClick(View v){
        switch (v.getId()){
            case R.id.login_register_btn:
                startRegisterPager();
                break;
                default:
                    break;
        }
    }

    private void startRegisterPager(){
        ActivityOptions options = ActivityOptions.makeScaleUpAnimation(mRegisterBtn,
                mRegisterBtn.getWidth() / 2,
                mRegisterBtn.getHeight() / 2,
                0 ,
                0);
        startActivity(new Intent(this, RegisterActivity.class), options.toBundle());
    }

    private void subscribeLoginClickEvent() {
        mPresenter.addRxBindingSubscribe(RxView.clicks(mLoginBtn)
                .throttleFirst(Constants.CLICK_TIME_AREA, TimeUnit.MILLISECONDS)
                .filter(o -> mPresenter != null)
                .subscribe(o -> mPresenter.getLoginData(
                        mAccountEdit.getText().toString().trim(),
                        mPasswordEdit.getText().toString().trim())));
    }
}
