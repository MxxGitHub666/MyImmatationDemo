package com.mxx.myimmatationdemo.widget;

import android.text.TextUtils;
import android.util.Log;

import com.bumptech.glide.load.HttpException;
import com.mxx.myimmatationdemo.R;
import com.mxx.myimmatationdemo.app.MyApp;
import com.mxx.myimmatationdemo.base.view.AbstractView;
import com.mxx.myimmatationdemo.core.http.exception.ServerException;

import io.reactivex.observers.ResourceObserver;

public class BaseObserver<T> extends ResourceObserver<T> {
    private AbstractView mView;
    private String mErrorMsg;
    private boolean isShowError = true;

    protected  BaseObserver(AbstractView view){
        this.mView = view;
    }


    protected BaseObserver(AbstractView view, String errorMsg){
        this.mView = view;
        this.mErrorMsg = errorMsg;
    }

    protected BaseObserver(AbstractView view, boolean isShowError){
        this.mView = view;
        this.isShowError = isShowError;
    }

    protected BaseObserver(AbstractView view, String errorMsg, boolean isShowError){
        this.mView = view;
        this.mErrorMsg = errorMsg;
        this.isShowError = isShowError;
    }

    @Override
    public void onComplete() {

    }


    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {
        if (mView == null) {
            return;
        }
        if (mErrorMsg != null && !TextUtils.isEmpty(mErrorMsg)) {
            mView.showErrorMsg(mErrorMsg);
        } else if (e instanceof ServerException) {
            mView.showErrorMsg(e.toString());
        } else if (e instanceof HttpException) {
            mView.showErrorMsg(MyApp.getInstance().getString(R.string.http_error));
        } else {
            mView.showErrorMsg(MyApp.getInstance().getString(R.string.unKnown_error));
            Log.d("BaseObserver",e.toString());
        }
        if (isShowError) {
            mView.showError();
        }
    }
}
