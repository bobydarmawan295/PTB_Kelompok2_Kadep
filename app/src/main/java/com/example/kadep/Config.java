package com.example.kadep;

import com.example.kadep.network.StoryEndpoint;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {

    private String API_BASE_URL = "http://ptb-api.husnilkamil.my.id/";
    public static final Integer SUCCESS_RESULT = 1;
    public static final Integer FAILURE_RESULT = 0;

    public static StoryEndpoint configRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://ptb-api.husnilkamil.my.id/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build();
        StoryEndpoint route = retrofit.create(StoryEndpoint.class);
        return route;
    }

}
