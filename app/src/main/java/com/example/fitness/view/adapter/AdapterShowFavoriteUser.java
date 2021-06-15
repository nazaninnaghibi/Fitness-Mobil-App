package com.example.fitness.view.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitness.R;

import com.example.fitness.databinding.ItemsfavoriteuserBinding;
import com.example.fitness.model.ModelFitnessDiet;
import com.example.fitness.view.fragment.UserProfileFragmentDirections;

import java.util.List;


public class AdapterShowFavoriteUser extends RecyclerView.Adapter<AdapterShowFavoriteUser.viewholder> {
    List<ModelFitnessDiet> list;


    public AdapterShowFavoriteUser(List<ModelFitnessDiet> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.itemsfavoriteuser, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final ModelFitnessDiet modelShowFavorite = list.get(position);
        holder.binding.setData(modelShowFavorite);
        holder.itemView.setOnClickListener(view -> Navigation.findNavController(view).
                navigate(UserProfileFragmentDirections.actionUserProfileFragmentToFitnessDietDetailsFragment(modelShowFavorite)));

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class viewholder extends RecyclerView.ViewHolder {
        ItemsfavoriteuserBinding binding;

        public viewholder(@NonNull ItemsfavoriteuserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
