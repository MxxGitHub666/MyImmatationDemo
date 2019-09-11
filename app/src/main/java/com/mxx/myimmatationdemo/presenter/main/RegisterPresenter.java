package com.mxx.myimmatationdemo.presenter.main;

import android.text.TextUtils;
import android.util.Log;

import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.MyApp;
import com.mxx.myimmatationdemo.base.presenter.BasePresenter;
import com.mxx.myimmatationdemo.contract.main.RegisterContract;
import com.mxx.myimmatationdemo.core.DataManager;
import com.mxx.myimmatationdemo.core.bean.main.login.LoginData;
import com.mxx.myimmatationdemo.utils.RxUtils;
import com.mxx.myimmatationdemo.widget.BaseObserver;

import javax.inject.Inject;

public class RegisterPresenter extends BasePresenter<RegisterContract.View> implements RegisterContract.Presenter {

    private DataManager mDataManager;

    @Inject
    public RegisterPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void getRegisterData(String username, String password, String rePassword) {
        if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password) || TextUtils.isEmpty(rePassword)) {
            mView.showSnackBar(MyApp.getInstance().getString(R.string.account_password_null_tint));
            return;
        }
        if (!password.equals(rePassword)) {
            mView.showSnackBar(MyApp.getInstance().getString(R.string.password_not_same));
            return;
        }
        addSubscribe(mDataManager.getRegisterData(username, password, rePassword)
                .compose(RxUtils.rxSchedulerHelper())
                .compose(RxUtils.handleResult())
                .filter(loginResponse -> !TextUtils.isEmpty(username)
                        && !TextUtils.isEmpty(password)
                        && !TextUtils.isEmpty(rePassword))
                .subscribeWith(new BaseObserver<LoginData>(mView,
                        MyApp.getInstance().getString(R.string.register_fail)) {
                    @Override
                    public void onNext(LoginData loginData) {
                        Log.e("loginData",loginData.toString());
                        mView.showRegisterSuccess();
                    }
                }));
    }
}
