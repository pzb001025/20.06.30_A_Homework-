package com.example.e.Presenter;

import com.example.e.Bean.Bean;
import com.example.e.Bean.TabBean;
import com.example.e.Model.MainModel;
import com.example.e.Net.NetCallBack;
import com.example.e.View.MainView;

/**
 * Created by Lenovo on 2020/5/11.
 */

public class MainPresenter {
    private MainView mainView;
    private final MainModel model;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        model = new MainModel();
    }

    public void setTab() {
        model.setTab(new NetCallBack<TabBean>() {
            @Override
            public void onSuccess(TabBean bean) {
                mainView.setTab(bean);
            }

            @Override
            public void onFail(String error) {
                mainView.showToast(error);
            }
        });
    }

    public void setData(int page, int id) {
        model.setData(page, id, new NetCallBack<Bean>() {
            @Override
            public void onSuccess(Bean bean) {
                mainView.setData(bean);
            }

            @Override
            public void onFail(String error) {
                mainView.showToast(error);
            }
        });
    }
}
