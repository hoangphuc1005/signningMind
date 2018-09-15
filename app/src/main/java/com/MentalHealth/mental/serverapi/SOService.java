package com.MentalHealth.mental.serverapi;


import com.MentalHealth.mental.az.model.AZModel;
import com.MentalHealth.mental.az.model.AZModelDetail;
import com.MentalHealth.mental.gamemini.model.LevelQuiz;
import com.MentalHealth.mental.gamemini.model.QuizModelDetail;
import com.MentalHealth.mental.home.login.model.LoginModel;
import com.MentalHealth.mental.infonew.model.InfoNew;
import com.MentalHealth.mental.library.model.DoccumentDetail;
import com.MentalHealth.mental.library.model.DoccumentModel;
import com.MentalHealth.mental.library.model.VideoModel;
import com.MentalHealth.mental.monthinfo.model.AllDayMonthModel;
import com.MentalHealth.mental.monthinfo.model.DayDetail;
import com.MentalHealth.mental.monthinfo.model.MonthDetailModel;
import com.MentalHealth.mental.sos.model.SOSDataModel;
import com.MentalHealth.mental.sos.model.SOSModel;
import com.MentalHealth.mental.sos.model.SOSModelDetail;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface SOService {
    @FormUrlEncoded
    @POST("login")
    Call<LoginModel> loginApp(
            @Field("account") String account,
            @Field("password") String password,
            @Field("device_id") String device_id);

    @GET("news/{id}")
    Call<DoccumentDetail> getInfoNewDetail(@Path("id") String id);

    @GET("news")
    Call<InfoNew> getInfoNew();

    @FormUrlEncoded
    @POST("logtime")
    Call<LoginModel> loginTime(
            @Field("user_id") String user_id,
            @Field("action") String action);

    @GET("videos")
    Call<VideoModel> getAllVideo();

    @GET("documents")
    Call<DoccumentModel> getAllDocument();

    @GET("documents/{id}")
    Call<DoccumentDetail> getDocumentDetail(@Path("id") String id);

    @GET("subject")
    Call<AZModel> getAllAZ();

    @GET("diseases/{id}")
    Call<List<AZModelDetail>> getAZDetail(@Path("id") String id);

    @GET("levels")
    Call<LevelQuiz> getAllLevel();

    @GET("days/{user_id}")
    Call<AllDayMonthModel> getAllDay(@Path("user_id") String user_id);

    @FormUrlEncoded
    @POST("update_day")
    Call<LoginModel> UpdateSateDays(
            @Field("user_id") String user_id,
            @Field("day_id") String day_id);

    @GET("day/{id}")
    Call<List<DayDetail>> getOneDay(@Path("id") String id);

    @GET("sos/{id}")
    Call<SOSModelDetail> getSOSDetail(@Path("id") String id);

    @GET("sos")
    Call<SOSModel> getSOSInfo();

    @GET("questions/{id}")
    Call<QuizModelDetail> getQuizInfo(@Path("id") String id);
    //http://mucilaginous-commit.000webhostapp.com/api/search/{key_word}

}