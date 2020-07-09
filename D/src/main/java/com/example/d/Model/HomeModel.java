package com.example.d.Model;

import com.example.d.Bean.DataBean;
import com.example.d.Bean.FoodBean;
import com.example.d.Net.ApiService;
import com.example.d.Net.BaseApp;
import com.example.d.Net.NetCallBack;
import com.example.d.db.DataBeanDao;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Lenovo on 2020/5/10.
 */

public class HomeModel {

    private final DataBeanDao mDao;

    public HomeModel() {
        mDao = BaseApp.getInstance().getDaoSession().getDataBeanDao();
    }

    public void setData(final NetCallBack<FoodBean> callBack) {
        new Retrofit.Builder()
                .baseUrl(ApiService.url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class)
                .getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<FoodBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(FoodBean bean) {
                        if (bean != null) {
                            callBack.onSuccess(bean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        callBack.onFail(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    public void query(NetCallBack<List<DataBean>> netCallBack) {
        final List<DataBean> all = mDao.loadAll();
        if (all != null && all.size() > 0) {
            netCallBack.onSuccess(all);
        } else {
            netCallBack.onFail("没有数据");
        }
    }

    public void insert(DataBean dataBean, NetCallBack<String> netCallBack) {
        final long l = mDao.insertOrReplace(dataBean);
        netCallBack.onSuccess("添加成功" + l);
    }

    public void delete(DataBean dataBean, NetCallBack<String> netCallBack) {
        mDao.delete(dataBean);
        netCallBack.onSuccess("删除成功");
    }
}
