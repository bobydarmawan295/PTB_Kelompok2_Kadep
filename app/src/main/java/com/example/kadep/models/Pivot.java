package com.example.kadep.models;

import com.google.gson.annotations.SerializedName;

public class Pivot{

    @SerializedName("notes")
    private Object notes;

    @SerializedName("reviewer_id")
    private int reviewerId;

    @SerializedName("recomendation")
    private Object recomendation;

    @SerializedName("id")
    private int id;

    @SerializedName("position")
    private Object position;

    @SerializedName("thesis_seminar_id")
    private int thesisSeminarId;

    public Object getNotes(){
        return notes;
    }

    public int getReviewerId(){
        return reviewerId;
    }

    public Object getRecomendation(){
        return recomendation;
    }

    public int getId(){
        return id;
    }

    public Object getPosition(){
        return position;
    }

    public int getThesisSeminarId(){
        return thesisSeminarId;
    }
}