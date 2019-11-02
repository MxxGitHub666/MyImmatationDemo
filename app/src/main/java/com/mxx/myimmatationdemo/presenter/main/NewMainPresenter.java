package com.mxx.myimmatationdemo.presenter.main;

import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.MyApp;
import com.mxx.myimmatationdemo.base.presenter.BasePresenter;
import com.mxx.myimmatationdemo.component.RxBus;
import com.mxx.myimmatationdemo.contract.main.NewMainContract;
import com.mxx.myimmatationdemo.core.DataManager;
import com.mxx.myimmatationdemo.core.bean.main.login.LoginData;
import com.mxx.myimmatationdemo.core.event.AutoLoginEvent;
import com.mxx.myimmatationdemo.core.event.LoginEvent;
import com.mxx.myimmatationdemo.core.event.NightModeEvent;
import com.mxx.myimmatationdemo.core.http.Cookies.CookiesManager;
import com.mxx.myimmatationdemo.utils.RxUtils;
import com.mxx.myimmatationdemo.widget.BaseObserver;
import com.mxx.myimmatationdemo.widget.BaseSubscribe;

import javax.inject.Inject;

public class NewMainPresenter extends BasePresenter<NewMainContract.View> implements NewMainContract.Presenter {

    private DataManager mDataManager;

    @Inject
    NewMainPresenter(DataManager dataManager) {
        super(dataManager);
        this.mDataManager = dataManager;
    }

    @Override
    public void attachView(NewMainContract.View view) {
        super.attachView(view);
        registerEvent();
    }

    private void registerEvent(){
        addSubscribe(RxBus.getDefault().toFlowable(NightModeEvent.class)
        .compose(RxUtils.rxFlSchedulerHelper())
        .map(NightModeEvent::isNightMode)
        .subscribeWith(new BaseSubscribe<Boolean>(mView,MyApp.getInstance().getString(R.string.failed_to_cast_mode)){
            @Override
            public void onNext(Boolean aBoolean) {
                mView.useNightMode(aBoolean);
            }

            @Override
            public void onError(Throwable e) {
                super.onError(e);
                registerEvent();
            }
        }));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
        .filter(LoginEvent::isLogin)
        .subscribe(loginEvent -> mView.showLoginView()));

        addSubscribe(RxBus.getDefault().toFlowable(LoginEvent.class)
                .filter(loginEvent -> !loginEvent.isLogin())
                .subscribe(logoutEvent -> mView.showLogoutView()));
        addSubscribe(RxBus.getDefault().toFlowable(AutoLoginEvent.class)
                .subscribe(autoLoginEvent -> mView.showAutoLoginView()));

    }

    @Override
    public void setCurrentPage(int page) {
        mDataManager.setCurrentPage(page);
    }

    @Override
    public void setNightModeState(boolean b) {
        mDataManager.setNightModeState(b);
    }

    @Override
    public void logout() {
        addSubscribe(mDataManager.logout()
        .compose(RxUtils.rxSchedulerHelper())
        .compose(RxUtils.handleLogoutResult())
        .subscribeWith(new BaseObserver<LoginData>(mView,
                MyApp.getInstance().getString(R.string.logout_fail)){
            @Override
            public void onNext(LoginData loginData) {
                setLoginAccount("");
                setLoginPassword("");
                setLoginStatus(false);
                CookiesManager.clearAllCookies();
                RxBus.getDefault().post(new LoginEvent(false));
                mView.showLogoutSuccess();
            }
        }));
    }
}
