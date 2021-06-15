package com.example.fitness.model;

import com.google.gson.annotations.SerializedName;


public class ModelUserInfo {

    @SerializedName("username")
    String username;
    @SerializedName("weight")
    Integer weight;
    @SerializedName("height")
    Integer height;
    @SerializedName("birthday")
    Integer birthday;
    @SerializedName("gender")
    String gender;
    @SerializedName("description")
    String description;


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getBirthday() {
        return birthday;
    }



    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
