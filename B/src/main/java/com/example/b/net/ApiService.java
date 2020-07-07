package com.example.b.net;

import com.example.b.Bean;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface ApiService {
    String url = "https://www.wanandroid.com/";
    @GET("project/list/1/json?cid=294")
    Flowable<Bean> getData();
}
