package com.example.fitness.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fitness.R;
import com.example.fitness.databinding.SearchRecycler2Binding;
import com.example.fitness.model.ModelShowAllUser;
import com.example.fitness.model.Status;
import com.example.fitness.utils.Api;
import com.example.fitness.utils.Repository;
import com.example.fitness.view.fragment.SearchFragmentDirections;

import java.util.List;

import es.dmoral.toasty.Toasty;

public class AdapterShowAllUser extends RecyclerView.Adapter<AdapterShowAllUser.viewholder> {
    List<ModelShowAllUser> modelShowAllUsers;
    String token;
    Context context;

    public AdapterShowAllUser(List<ModelShowAllUser> modelShowAllUsers, String token, Context context) {
        this.modelShowAllUsers = modelShowAllUsers;
        this.token = token;
        this.context = context;

    }

    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewholder(DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.search_recycler2, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        final ModelShowAllUser modelShowAllUser = modelShowAllUsers.get(position);
        holder.binding.setData(modelShowAllUser);
        holder.itemView.setOnClickListener(view -> Navigation.findNavController(view).navigate(SearchFragmentDirections.actionSearchFragmentToUserProfileFragment(modelShowAllUser)));
        holder.binding.follow.setOnClickListener(view -> Repository.INSTACNCE.CustomResponse(Api.Compation.invoke().Follow(token, modelShowAllUser.getToken()), object -> {
            Status status = (Status) object;
            switch (status.getStatus()) {
                case "ok":
                    Toasty.success(context, R.string.successfully_followed, Toast.LENGTH_SHORT, true).show();
                    holder.binding.textFollow.setText(R.string.followed);
                    holder.binding.tik.setVisibility(View.VISIBLE);
                    break;
                case "error":
                    Toasty.error(context, R.string.error, Toast.LENGTH_SHORT, true).show();
                    holder.binding.textFollow.setText(R.string.followed);
                    break;
                case "400 Bad Request":
                    Toasty.error(context, R.string.bad_request, Toast.LENGTH_SHORT, true).show();
                    break;
            }
        }));
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
        Repository.INSTACNCE.CustomResponse(Api.Compation.invoke().CheckFollow(token, modelShowAllUser.getToken()), object -> {
            Status status = (Status) object;
            switch (status.getStatus()) {
                case "200 OK":
                    holder.binding.textFollow.setText(R.string.followed);
                    holder.binding.tik.setVisibility(View.VISIBLE);
                    break;
                case "400 Bad Request":
                    holder.binding.textFollow.setText(R.string.follow);
                    holder.binding.tik.setVisibility(View.GONE);
                    break;
            }
        });
    }


    @Override
    public int getItemCount() {
        return modelShowAllUsers.size();
    }

    static class viewholder extends RecyclerView.ViewHolder {
        SearchRecycler2Binding binding;

        public viewholder(@NonNull SearchRecycler2Binding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
