package com.peterstev.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiGenerator {

    public static ApiInterface getApiInterface() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonmock.hackerrank.com/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ApiInterface.class);
    }
}
