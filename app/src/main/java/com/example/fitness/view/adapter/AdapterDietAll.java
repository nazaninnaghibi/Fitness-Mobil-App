package com.example.fitness.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitness.R;
import com.example.fitness.databinding.DietItemBinding;
import com.example.fitness.model.ModelFitnessDiet;
import com.example.fitness.view.fragment.FitnessDietFragmentDirections;

import java.util.List;

public class AdapterDietAll extends RecyclerView.Adapter<AdapterDietAll.viewholder> {
    List<ModelFitnessDiet> list;

    public AdapterDietAll(List<ModelFitnessDiet> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AdapterDietAll.viewholder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.diet_item, parent, false));

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
        DietItemBinding binding;

        public viewholder(@NonNull DietItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
