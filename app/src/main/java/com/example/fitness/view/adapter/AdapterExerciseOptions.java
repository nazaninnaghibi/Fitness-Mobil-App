package com.example.fitness.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitness.R;
import com.example.fitness.databinding.ItemexerciseoptionBinding;
import com.example.fitness.model.ModelExerciseOptions;
import com.example.fitness.view.fragment.ExerciseOptionFragmentDirections;

import java.util.List;

public class AdapterExerciseOptions extends RecyclerView.Adapter<AdapterExerciseOptions.viewholder> {
    List<ModelExerciseOptions> list;

    public AdapterExerciseOptions(List<ModelExerciseOptions> list) {
        this.list = list;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.itemexerciseoption, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final ModelExerciseOptions modelExerciseOptions = list.get(position);
        holder.binding.setData(modelExerciseOptions);
        holder.itemView.setOnClickListener(view -> Navigation.findNavController(view).navigate(ExerciseOptionFragmentDirections.actionExerciseOptionFragmentToExerciseDetailsFragment(modelExerciseOptions)));

    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    static class viewholder extends RecyclerView.ViewHolder {
        ItemexerciseoptionBinding binding;

        public viewholder(@NonNull ItemexerciseoptionBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
