package com.example.b.net;

public interface BaseCallBack<T> {
    void onSuccess(T t);

    void onFail(String errot);
}
