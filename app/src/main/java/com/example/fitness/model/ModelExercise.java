package com.example.fitness.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import androidx.annotation.Keep;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.fitness.R;
import com.google.gson.annotations.SerializedName;

@Keep
public class ModelExercise implements Parcelable {
    @SerializedName("id")
    String id;
    @SerializedName("title")
    String title;
    @SerializedName("image")
    String image;

    protected ModelExercise(Parcel in) {
        id = in.readString();
        title = in.readString();
        image = in.readString();
    }

    public static final Creator<ModelExercise> CREATOR = new Creator<ModelExercise>() {
        @Override
        public ModelExercise createFromParcel(Parcel in) {
            return new ModelExercise(in);
        }

        @Override
        public ModelExercise[] newArray(int size) {
            return new ModelExercise[size];
        }
    };

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public static RequestOptions options = new RequestOptions()
            .centerCrop()
            .placeholder(R.drawable.progress_animation)
            .error(R.drawable.ic_baseline_error_24)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .priority(Priority.HIGH)
            .dontAnimate()
            .dontTransform();

    @BindingAdapter("image")
    static public void image(ImageView view, String url) {
        Glide.with(view).load(url).apply(options).into(view);

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
    }
}
