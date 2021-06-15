package com.example.fitness.view.adapter;


import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitness.R;
import com.example.fitness.databinding.ItemsfavoriteExerciseuserBinding;
import com.example.fitness.model.ModelExerciseOptions;
import com.example.fitness.view.fragment.UserProfileFragmentDirections;

import java.util.List;



public class AdapterShowFavoriteExerciseUser extends RecyclerView.Adapter<AdapterShowFavoriteExerciseUser.viewholder> {
    List<ModelExerciseOptions> list;

    public AdapterShowFavoriteExerciseUser(List<ModelExerciseOptions> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.itemsfavorite_exerciseuser, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final ModelExerciseOptions modelShowFavoriteExercise = list.get(position);
        holder.binding.setData(modelShowFavoriteExercise);
        holder.itemView.setOnClickListener(view -> Navigation.findNavController(view).navigate(UserProfileFragmentDirections.
                actionUserProfileFragmentToExerciseDetailsFragment(modelShowFavoriteExercise)));

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class viewholder extends RecyclerView.ViewHolder {
        ItemsfavoriteExerciseuserBinding binding;

        public viewholder(@NonNull ItemsfavoriteExerciseuserBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
