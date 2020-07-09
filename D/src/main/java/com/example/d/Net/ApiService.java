package com.example.d.Net;

import com.example.d.Bean.FoodBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by Lenovo on 2020/5/10.
 */

public interface ApiService {
    String url = "http://www.qubaobei.com/";

    @GET("ios/cf/dish_list.php?stage_id=1&limit=20&page=1")
    Observable<FoodBean> getData();
}
