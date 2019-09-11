package com.mxx.myimmatationdemo.utils;


import com.mxx.myimmatationdemo.core.bean.BaseResponse;
import com.mxx.myimmatationdemo.core.http.exception.OtherException;

import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RxUtils {

    /**
     * 统一线程处理
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<T, T> rxSchedulerHelper() {
        return observable -> observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }


    /**
     * 统一返回结果处理
     * @param <T> 指定的泛型类型
     * @return ObservableTransformer
     */
    public static <T> ObservableTransformer<BaseResponse<T>, T> handleResult() {
        return httpResponseObservable ->
                httpResponseObservable.flatMap((Function<BaseResponse<T>, Observable<T>>) baseResponse -> {
                    if(baseResponse.getErrorCode() == BaseResponse.SUCCESS
                            && baseResponse.getData() != null
                            && CommonUtils.isNetworkConnected()) {
                        return createData(baseResponse.getData());
                    } else {
                        return Observable.error(new OtherException());
                    }
                });
    }
    /**
     * 得到 Observable
     * @param <T> 指定的泛型类型
     * @return Observable
     */
    private static <T> Observable<T> createData(final T t) {
        return Observable.create(emitter -> {
            try {
                emitter.onNext(t);
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });
    }


}
