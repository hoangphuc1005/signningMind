package com.MentalHealth.mental.serverapi;

public class ApiUtils {

    public static final String BASE_URL = "http://nhatthanh2805.000webhostapp.com/api/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
