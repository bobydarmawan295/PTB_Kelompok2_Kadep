package com.example.kadep.models;

import com.google.gson.annotations.SerializedName;

public class LoginResponse{

    @SerializedName("authorisation")
    private Authorisation authorisation;

    @SerializedName("user")
    private User user;

    @SerializedName("status")
    private String status;

    public Authorisation getAuthorisation(){
        return authorisation;
    }

    public User getUser(){
        return user;
    }

    public String getStatus(){
        return status;
    }
}