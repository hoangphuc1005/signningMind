package com.MentalHealth.mental.serverapi;

import android.os.Message;

import com.MentalHealth.mental.home.login.model.LoginModel;
import com.MentalHealth.mental.home.model.SOAnswersResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SOService {
    @FormUrlEncoded
    @POST("login")
    Call<LoginModel> loginApp(
            @Field("account") String account,
            @Field("password") String password,
            @Field("device_id") String deviceID);
}