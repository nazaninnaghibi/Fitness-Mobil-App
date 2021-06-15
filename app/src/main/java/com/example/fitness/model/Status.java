package com.example.fitness.model;

import com.google.gson.annotations.SerializedName;

public class Status {
    @SerializedName("status")
    String status;
    @SerializedName("token")
    String token;

    public String getStatus() {
        return status;
    }

    public String getToken() {
        return token;
    }


}
