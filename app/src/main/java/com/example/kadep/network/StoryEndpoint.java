package com.example.kadep.network;

import com.example.kadep.models.LoginResponse;
import com.example.kadep.models.LogoutResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface StoryEndpoint {
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginResponse> login (
            @Field("username")String username,
            @Field("password")String password
    );

    @POST("api/logout")
    Call<LogoutResponse> logout(
            @Header("Authorization") String token
    );

}
