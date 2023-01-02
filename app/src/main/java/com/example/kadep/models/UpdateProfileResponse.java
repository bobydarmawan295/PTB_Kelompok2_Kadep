package com.example.kadep.models;

import com.google.gson.annotations.SerializedName;

public class UpdateProfileResponse{

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public String getMessage(){
        return message;
    }

    public String getStatus(){
        return status;
    }
}