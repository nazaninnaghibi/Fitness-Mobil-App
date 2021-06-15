package com.example.fitness.view.adapter;

import android.view.LayoutInflater;

import androidx.navigation.Navigation;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitness.R;
import com.example.fitness.databinding.NewestDietBinding;
import com.example.fitness.model.ModelFitnessDiet;
import com.example.fitness.view.fragment.FitnessDietFragmentDirections;

import java.util.List;

public class AdapterFitnessDiet extends RecyclerView.Adapter<AdapterFitnessDiet.viewholder> {
    List<ModelFitnessDiet> list;

    public AdapterFitnessDiet(List<ModelFitnessDiet> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.newest_diet, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final ModelFitnessDiet modelIndex = list.get(position);
        holder.binding.setData(modelIndex);
        holder.itemView.setOnClickListener(view -> Navigation.findNavController(view).navigate(FitnessDietFragmentDirections.actionFitnessDietFragmentToFitnessDietDetailsFragment(modelIndex)));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class viewholder extends RecyclerView.ViewHolder {
        NewestDietBinding binding;

        public viewholder(@NonNull NewestDietBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
