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
import com.example.fitness.databinding.FragmentSearchBinding;
import com.example.fitness.utils.Repository;
import com.example.fitness.view.adapter.AdapterShowAllUser;

import com.example.fitness.viewmodel.ViewModelShowAllUser;


public class SearchFragment extends Fragment {

    FragmentSearchBinding binding;
    NavController navController;
    AdapterShowAllUser adapterShowAllUser;




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = FragmentSearchBinding.inflate(inflater, container, false);
            navController = Navigation.findNavController(requireActivity(), R.id.fragment);
            ViewModelShowAllUser viewmodel = new ViewModelProvider(this).get(ViewModelShowAllUser.class);
            String token = Repository.Shared_Read(requireActivity());
            viewmodel.User(token);
            binding.back.setOnClickListener(view -> NavHostFragment.findNavController(this).popBackStack());
            viewmodel.mutableLiveDataPost.observe(requireActivity(), modelShowAllUsers -> {
                adapterShowAllUser = new AdapterShowAllUser(modelShowAllUsers, token, requireContext());
                binding.RecyclerViewSuggested.setAdapter(adapterShowAllUser);
            });

        }

        return binding.getRoot();
    }


}