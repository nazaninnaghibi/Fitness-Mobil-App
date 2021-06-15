package com.example.fitness.model;



import com.google.gson.annotations.SerializedName;


public class ModelDetails {
    @SerializedName("id")
    String id;
    @SerializedName("Description")
    String Description;

    @SerializedName("image")
    String image;

    @SerializedName("aLatitude")
    Double aLatitude;

    @SerializedName("aLongitude")
    Double aLongitude;

    @SerializedName("z")
    Double z;

    public String getId() {
        return id;
    }

    public String getDescription() {
        return Description;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Double getaLatitude() {
        return aLatitude;
    }

    public Double getaLongitude() {
        return aLongitude;
    }

    public Double getZ() {
        return z;
    }
}
