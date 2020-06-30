package com.example.a.net;

public interface BaseCallBack<T> {
    void onSuccess(T t);

    void onFiled(String error);
}
