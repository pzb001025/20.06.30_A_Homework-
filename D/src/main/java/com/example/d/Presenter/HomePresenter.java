package com.example.d.Presenter;

import com.example.d.Bean.DataBean;
import com.example.d.Bean.FoodBean;
import com.example.d.Model.HomeModel;
import com.example.d.Net.NetCallBack;
import com.example.d.View.HomeView;

import java.util.List;

/**
 * Created by Lenovo on 2020/5/10.
 */

public class HomePresenter {
    private HomeView homeView;
    private final HomeModel model;

    public HomePresenter(HomeView homeView) {
        this.homeView = homeView;
        model = new HomeModel();
    }

    public void setData() {
        model.setData(new NetCallBack<FoodBean>() {
            @Override
            public void onSuccess(FoodBean bean) {
                homeView.setData(bean);
            }

            @Override
            public void onFail(String error) {
                homeView.showToast(error);
            }
        });
    }

    public void query() {
        model.query(new NetCallBack<List<DataBean>>() {
            @Override
            public void onSuccess(List<DataBean> bean) {
                homeView.setQuery(bean);
            }

            @Override
            public void onFail(String error) {
                homeView.showToast(error);
            }
        });
    }

    public void insert(DataBean dataBean) {
        model.insert(dataBean, new NetCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                homeView.showToast(bean);
            }

            @Override
            public void onFail(String error) {
                homeView.showToast(error);
            }
        });
    }

    public void delete(DataBean dataBean) {
        model.delete(dataBean, new NetCallBack<String>() {
            @Override
            public void onSuccess(String bean) {
                homeView.showToast(bean);
                homeView.renewal();
            }

            @Override
            public void onFail(String error) {
                homeView.showToast(error);
            }
        });
    }
}
