package com.example.fitness.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitness.R;
import com.example.fitness.databinding.FragmentFollowingBinding;
import com.example.fitness.utils.Repository;
import com.example.fitness.view.adapter.AdapterShowFollowing;
import com.example.fitness.viewmodel.ViewModelShowFollowing;


public class FollowingFragment extends Fragment {
    FragmentFollowingBinding binding;
    NavController navController;
    AdapterShowFollowing adapterShowFollowing;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = FragmentFollowingBinding.inflate(inflater, container, false);
            navController = Navigation.findNavController(requireActivity(), R.id.fragment);
            ViewModelShowFollowing viewmodel = new ViewModelProvider(this).get(ViewModelShowFollowing.class);
            String token = Repository.Shared_Read(requireActivity());
            viewmodel.Following(token);
            binding.back.setOnClickListener(view -> NavHostFragment.findNavController(this).popBackStack());
            viewmodel.mutableLiveDataPost.observe(requireActivity(), modelShowAllUsers -> {
                adapterShowFollowing = new AdapterShowFollowing(modelShowAllUsers, token);
                binding.RecyclerViewShowFollowing.setAdapter(adapterShowFollowing);
                if (adapterShowFollowing.getItemCount() == 0) {
                    binding.noFollowing.setVisibility(View.VISIBLE);
                    binding.noFollowing.setOnClickListener(view -> navController.navigate(R.id.searchFragment));
                } else {
                    binding.noFollowing.setVisibility(View.GONE);
                }
            });

        }

        return binding.getRoot();
    }
}