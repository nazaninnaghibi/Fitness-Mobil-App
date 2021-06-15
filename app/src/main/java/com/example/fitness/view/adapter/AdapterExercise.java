package com.example.fitness.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.example.fitness.R;
import com.example.fitness.databinding.ItemexerciseBinding;
import com.example.fitness.model.ModelExercise;
import com.example.fitness.view.fragment.ExerciseFragmentDirections;

import java.util.List;

public class AdapterExercise extends RecyclerView.Adapter<AdapterExercise.viewholder> {
    List<ModelExercise> list;

    public AdapterExercise(List<ModelExercise> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.itemexercise, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final ModelExercise modelExercise = list.get(position);
        holder.binding.setData(modelExercise);
        holder.itemView.setOnClickListener(view -> Navigation.findNavController(view).navigate(ExerciseFragmentDirections.actionExerciseFragmentToExerciseOptionFragment(modelExercise)));
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    static class viewholder extends RecyclerView.ViewHolder {
        ItemexerciseBinding binding;

        public viewholder(@NonNull ItemexerciseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
