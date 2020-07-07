package com.example.b.p;

import com.example.b.Bean;
import com.example.b.m.MainModel;
import com.example.b.net.BaseCallBack;
import com.example.b.v.MainView;

public class MainPresenter {
    private MainView view;
    private final MainModel model;

    public MainPresenter(MainView view) {
        this.view = view;
        model = new MainModel();
    }

    public void getData() {
        model.getData(new BaseCallBack<Bean>() {
            @Override
            public void onSuccess(Bean bean) {
                view.setData(bean);
            }

            @Override
            public void onFail(String errot) {

            }
        });
    }
}
