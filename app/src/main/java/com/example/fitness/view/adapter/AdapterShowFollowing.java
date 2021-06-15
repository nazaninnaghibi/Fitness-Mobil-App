package com.example.fitness.view.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitness.R;
import com.example.fitness.databinding.ItemFollowingBinding;
import com.example.fitness.model.ModelShowAllUser;
import com.example.fitness.view.fragment.FollowingFragmentDirections;


import java.util.List;


public class AdapterShowFollowing extends RecyclerView.Adapter<AdapterShowFollowing.viewholder> {
    List<ModelShowAllUser> modelShowAllUsers;
    String token;

    public AdapterShowFollowing(List<ModelShowAllUser> modelShowAllUsers, String token) {
        this.modelShowAllUsers = modelShowAllUsers;
        this.token = token;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_following, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final ModelShowAllUser modelShowAllUser = modelShowAllUsers.get(position);
        holder.binding.setData(modelShowAllUser);
        if (modelShowAllUser.getGender() == null) {
            holder.binding.image.setImageResource(R.drawable.user);
            holder.binding.gender.setText(R.string.no_gender);
        } else {
            switch (modelShowAllUser.getGender()) {
                case "Female":
                    holder.binding.image.setImageResource(R.drawable.ic_woman);
                    holder.binding.gender.setText(modelShowAllUser.getGender());
                    break;
                case "Male":
                    holder.binding.image.setImageResource(R.drawable.ic_man);
                    holder.binding.gender.setText(modelShowAllUser.getGender());
                    break;
            }
        }
        holder.itemView.setOnClickListener(view -> Navigation.findNavController(view).navigate(FollowingFragmentDirections.actionFollowingFragmentToUserProfileFragment(modelShowAllUser)));
        holder.binding.call.setOnClickListener(v -> Navigation.findNavController(v).navigate(R.id.callFragment));

    }


    @Override
    public int getItemCount() {
        return modelShowAllUsers.size();
    }

    static class viewholder extends RecyclerView.ViewHolder {
        ItemFollowingBinding binding;

        public viewholder(@NonNull ItemFollowingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
