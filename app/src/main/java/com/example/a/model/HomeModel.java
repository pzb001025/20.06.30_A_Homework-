package com.example.a.model;

import com.example.a.bean.HomeBean;
import com.example.a.net.ApiService;
import com.example.a.net.BaseCallBack;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeModel {
    public void getData(BaseCallBack<HomeBean> callBack) {
        new Retrofit.Builder()
                .baseUrl(ApiService.mUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<HomeBean>() {
                    @Override
                    public void onNext(HomeBean homeBean) {
                        callBack.onSuccess(homeBean);
                    }

                    @Override
                    public void onError(Throwable t) {
                        callBack.onFiled(t.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
