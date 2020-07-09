package com.example.d.Net;

/**
 * Created by Lenovo on 2020/5/10.
 */

public interface NetCallBack<T> {
    void onSuccess(T bean);

    void onFail(String error);
}
