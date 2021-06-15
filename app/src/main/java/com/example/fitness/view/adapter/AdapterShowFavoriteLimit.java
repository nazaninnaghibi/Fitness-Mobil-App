package com.example.fitness.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitness.R;
import com.example.fitness.databinding.ItemsfavoritelimitBinding;
import com.example.fitness.model.ModelFitnessDiet;
import com.example.fitness.view.fragment.FavoriteAllFragmentDirections;

import java.util.List;

public class AdapterShowFavoriteLimit extends RecyclerView.Adapter<AdapterShowFavoriteLimit.viewholder> {
    List<ModelFitnessDiet> list;
    Context context;


    public AdapterShowFavoriteLimit(List<ModelFitnessDiet> list,Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.itemsfavoritelimit, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final ModelFitnessDiet modelShowFavorite = list.get(position);
        holder.binding.setData(modelShowFavorite);
        holder.itemView.setOnClickListener(view -> Navigation.findNavController(view).navigate(FavoriteAllFragmentDirections.actionFavoriteAllFragmentToFitnessDietDetailsFragment(modelShowFavorite)));

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    static class viewholder extends RecyclerView.ViewHolder {
        ItemsfavoritelimitBinding binding;

        public viewholder(@NonNull ItemsfavoritelimitBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
