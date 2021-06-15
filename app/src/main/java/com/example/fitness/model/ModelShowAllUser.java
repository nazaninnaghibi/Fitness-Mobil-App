package com.example.fitness.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import com.google.gson.annotations.SerializedName;
@Keep
public class ModelShowAllUser implements Parcelable {
    @SerializedName("username")
    String username;

    @SerializedName("weight")
    String weight;

    @SerializedName("height")
    String height;

    @SerializedName("birthday")
    String birthday;

    @SerializedName("gender")
    String gender;

    @SerializedName("description")
    String description;

    @SerializedName("token")
    String token;

    protected ModelShowAllUser(Parcel in) {
        username = in.readString();
        weight = in.readString();
        height = in.readString();
        birthday = in.readString();
        gender = in.readString();
        description = in.readString();
        token = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeString(weight);
        dest.writeString(height);
        dest.writeString(birthday);
        dest.writeString(gender);
        dest.writeString(description);
        dest.writeString(token);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ModelShowAllUser> CREATOR = new Creator<ModelShowAllUser>() {
        @Override
        public ModelShowAllUser createFromParcel(Parcel in) {
            return new ModelShowAllUser(in);
        }

        @Override
        public ModelShowAllUser[] newArray(int size) {
            return new ModelShowAllUser[size];
        }
    };

    public String getToken() {
        return token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getBirthday() {
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

    public void setToken(String token) {
        this.token = token;
    }
}
