package com.example.kadep.models;

import com.google.gson.annotations.SerializedName;

public class Reviewer{

    @SerializedName("examiner_id")
    private String examinerId;

    @SerializedName("updated_at")
    private String updatedAt;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("position")
    private String position;

    @SerializedName("thesis_trial_id")
    private String thesisTrialId;

    @SerializedName("status")
    private int status;

    public String getExaminerId(){
        return examinerId;
    }

    public String getUpdatedAt(){
        return updatedAt;
    }

    public String getCreatedAt(){
        return createdAt;
    }

    public String getPosition(){
        return position;
    }

    public String getThesisTrialId(){
        return thesisTrialId;
    }

    public int getStatus(){
        return status;
    }
}