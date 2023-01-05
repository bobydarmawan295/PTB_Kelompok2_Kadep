package com.example.kadep.models;

import com.google.gson.annotations.SerializedName;

public class FormPengujiSidangResponse{

    @SerializedName("reviewer")
    private Reviewer reviewer;

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private String status;

    public Reviewer getReviewer(){
        return reviewer;
    }

    public String getMessage(){
        return message;
    }

    public String getStatus(){
        return status;
    }
}