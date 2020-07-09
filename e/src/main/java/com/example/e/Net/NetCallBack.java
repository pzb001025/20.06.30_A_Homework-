package com.example.e.Net;

/**
 * Created by Lenovo on 2020/5/11.
 */

public interface NetCallBack<T> {
    void onSuccess(T bean);

    void onFail(String error);
}
