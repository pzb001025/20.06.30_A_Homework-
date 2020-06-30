package com.example.a.net;

import com.example.a.bean.HomeBean;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;

public interface ApiService {
    String mUrl = " https://www.wanandroid.com/";
    String downUrl = "https://alissl.ucdl.pp.uc.cn/";

    @GET("project/list/1/json?cid=294")
    Flowable<HomeBean> getData();

    @GET("fs08/2017/05/02/7/106_64d3e3f76babc7bce131650c1c21350d.apk")
    Flowable<ResponseBody> download();
}
