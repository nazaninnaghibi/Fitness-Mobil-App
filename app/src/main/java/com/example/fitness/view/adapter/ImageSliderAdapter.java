package com.example.fitness.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitness.R;
import com.example.fitness.databinding.ItemssliderBinding;
import com.example.fitness.model.Slider;

import java.util.List;

public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewHolder> {
    private final  List<Slider> slider;
    private LayoutInflater layoutInflater;

    public ImageSliderAdapter(List<Slider> slider) {
        this.slider = slider;
    }

    @NonNull
    @Override
    public ImageSliderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemssliderBinding sliderImageBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.itemsslider, parent, false
        );
        return new ImageSliderViewHolder(sliderImageBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderViewHolder holder, int position) {
        holder.bindSliderImage(slider.get(position).getImage());
    }

    @Override
    public int getItemCount() {
        return slider.size();
    }

    static class ImageSliderViewHolder extends RecyclerView.ViewHolder {
        private final ItemssliderBinding sliderImageBinding;

        public ImageSliderViewHolder(ItemssliderBinding sliderImageBinding) {
            super(sliderImageBinding.getRoot());
            this.sliderImageBinding = sliderImageBinding;
        }

        public void bindSliderImage(String imageURL) {
            sliderImageBinding.setImageurl(imageURL);
        }


    }

}
