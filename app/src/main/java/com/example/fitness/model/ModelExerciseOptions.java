package com.example.fitness.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.Keep;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;

@Keep
public class ModelExerciseOptions implements Parcelable {
    @SerializedName("id")
    String id;
    @SerializedName("title")
    String title;
    @SerializedName("image")
    String image;

    @SerializedName("idexercise")
    String idexercise;

    @SerializedName("idfavoriteexercise")
    String idfavoriteexercise;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getIdexercise() {
        return idexercise;
    }

    public String getIdfavoriteexercise() {
        return idfavoriteexercise;
    }

    protected ModelExerciseOptions(Parcel in) {
        id = in.readString();
        title = in.readString();
        image = in.readString();
        idexercise = in.readString();
        idfavoriteexercise = in.readString();
    }

    public static final Creator<ModelExerciseOptions> CREATOR = new Creator<ModelExerciseOptions>() {
        @Override
        public ModelExerciseOptions createFromParcel(Parcel in) {
            return new ModelExerciseOptions(in);
        }

        @Override
        public ModelExerciseOptions[] newArray(int size) {
            return new ModelExerciseOptions[size];
        }
    };

    @BindingAdapter("gif")
    static public void image(ImageView view, String url) {
        Glide.with(view).asGif().load(url).into(view);
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(id);
        parcel.writeString(title);
        parcel.writeString(image);
        parcel.writeString(idexercise);
        parcel.writeString(idfavoriteexercise);
    }
}
