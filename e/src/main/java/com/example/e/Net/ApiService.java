package com.example.e.Net;

import com.example.e.Bean.Bean;
import com.example.e.Bean.TabBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Lenovo on 2020/5/11.
 */

public interface ApiService {
    String url = "https://www.wanandroid.com/";

    @GET("project/tree/json")
    Observable<TabBean> getTab();

    @GET("project/list/{page}/json?")
//=294
    Observable<Bean> getData(@Path("page") int page, @Query("cid") int cid);
}
