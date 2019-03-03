package com.MentalHealth.mental.serverapi;

public class ApiUtils {

    private static final String BASE_URL = "http://mucilaginous-commit.000webhostapp.com/api/";
    public static final String BASE_URL_LINK_DOWNLOAD = "http://mucilaginous-commit.000webhostapp.com/api/pdf/";

    public static SOService getSOService() {
        return RetrofitClient.getClient(BASE_URL).create(SOService.class);
    }
}
