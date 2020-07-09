package com.example.e.View;

import com.example.e.Bean.Bean;
import com.example.e.Bean.TabBean;

/**
 * Created by Lenovo on 2020/5/11.
 */

public interface MainView {
    void setTab(TabBean bean);

    void setData(Bean bean);

    void showToast(String msg);
}
