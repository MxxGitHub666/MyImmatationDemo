package com.mxx.myimmatationdemo.presenter.main;

import android.text.TextUtils;
import android.util.Log;

import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.MyApp;
import com.mxx.myimmatationdemo.base.presenter.BasePresenter;
import com.mxx.myimmatationdemo.component.RxBus;
import com.mxx.myimmatationdemo.contract.main.LoginContract;
import com.mxx.myimmatationdemo.core.DataManager;
import com.mxx.myimmatationdemo.core.bean.main.login.LoginData;
import com.mxx.myimmatationdemo.core.event.LoginEvent;
import com.mxx.myimmatationdemo.utils.RxUtils;
import com.mxx.myimmatationdemo.widget.BaseObserver;

import javax.inject.Inject;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private DataManager mDataManager;
    @Inject
    public LoginPresenter(DataManager dataManager) {
        super(dataManager);
        mDataManager = dataManager;
    }

    @Override
    public void getLoginData(String username, String password) {
        if(TextUtils.isEmpty(username)||TextUtils.isEmpty(password)){
            mView.showSnackBar(MyApp.getInstance().getString(R.string.account_password_null_tint));
            return;
        }
        addSubscribe(mDataManager.getLoginData(username,password)
                    .compose(RxUtils.rxSchedulerHelper())
                    .compose(RxUtils.handleResult())
                    .subscribeWith(new BaseObserver<LoginData>(mView,MyApp.getInstance().getString(R.string.login_fail)){
                        @Override
                        public void onNext(LoginData loginData) {
                            setLoginAccount(loginData.getUsername());
                            setLoginPassword(loginData.getPassword());
                            setLoginStatus(true);
                            Log.e("getLoginStatus : ",getLoginStatus()+"");
                            RxBus.getDefault().post(new LoginEvent(true));
                            mView.showLoginSuccess();
                        }
                    }));
    }
}
