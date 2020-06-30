package com.example.a.presenter;

import android.util.Log;

import com.example.a.bean.HomeBean;
import com.example.a.model.HomeModel;
import com.example.a.net.BaseCallBack;
import com.example.a.view.HomeView;

public class HomePresenter {
    private HomeView mView;
    private final HomeModel model;

    public HomePresenter(HomeView mView) {
        this.mView = mView;
        model = new HomeModel();
    }

    public void getData() {
        model.getData(new BaseCallBack<HomeBean>() {
            @Override
            public void onSuccess(HomeBean homeBean) {
                mView.setData(homeBean);
            }

            @Override
            public void onFiled(String error) {
                Log.d("TAG", "onFiled: "+error);
            }
        });
    }
}
