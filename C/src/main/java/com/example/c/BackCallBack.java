package com.example.c;

public interface BackCallBack<T> {
    void onSuccess(T t);
    void onFail(String msg);
}
