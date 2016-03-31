package com.example.jiashuaishuai.myapplicationretrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by jiashuaishuai on 2016/3/8.
 */
public interface PhoneService {
    @GET("/apistore/mobilenumber/mobilenumber")
    Call<PhoneResult> getPhoneResultCall(@Header("apikey") String apikey, @Query("phone") String phone);
}
