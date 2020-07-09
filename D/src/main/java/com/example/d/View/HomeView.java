package com.example.d.View;

import com.example.d.Bean.DataBean;
import com.example.d.Bean.FoodBean;

import java.util.List;

/**
 * Created by Lenovo on 2020/5/10.
 */

public interface HomeView {
    void setData(FoodBean bean);

    void setQuery(List<DataBean> datasBeans);

    void showToast(String msg);

    void renewal();
}
