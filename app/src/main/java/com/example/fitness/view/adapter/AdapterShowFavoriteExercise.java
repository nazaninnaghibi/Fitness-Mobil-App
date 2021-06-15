package com.example.fitness.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitness.R;
import com.example.fitness.databinding.ItemsfavoriteexerciseBinding;
import com.example.fitness.model.ModelExerciseOptions;
import com.example.fitness.model.Status;
import com.example.fitness.utils.Api;
import com.example.fitness.utils.Repository;
import com.example.fitness.view.fragment.FavoriteExerciseFragmentDirections;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class AdapterShowFavoriteExercise extends RecyclerView.Adapter<AdapterShowFavoriteExercise.viewholder> {
    List<ModelExerciseOptions> list;
    Context context;

    public AdapterShowFavoriteExercise(List<ModelExerciseOptions> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.itemsfavoriteexercise, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final ModelExerciseOptions modelShowFavoriteExercise = list.get(position);
        holder.binding.setData(modelShowFavoriteExercise);
        holder.itemView.setOnClickListener(view -> Navigation.findNavController(view).navigate(FavoriteExerciseFragmentDirections.actionFavoriteExerciseFragmentToExerciseDetailsFragment(modelShowFavoriteExercise)));
        holder.binding.delete.setOnClickListener(view -> Repository.INSTACNCE.CustomResponse(Api.Compation.invoke().DeleteFavoriteExercise(modelShowFavoriteExercise.getIdfavoriteexercise()), object -> {
            Status status = (Status) object;
            if (status.getStatus().equals("ok")) {
                list.remove(position);
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, list.size());
                Toasty.success(context, R.string.successfully_removed, Toast.LENGTH_SHORT, true).show();

            } else {
                Toasty.error(context, R.string.try_again, Toast.LENGTH_SHORT, true).show();
            }
        }));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    static class viewholder extends RecyclerView.ViewHolder {
        ItemsfavoriteexerciseBinding binding;

        public viewholder(@NonNull ItemsfavoriteexerciseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
