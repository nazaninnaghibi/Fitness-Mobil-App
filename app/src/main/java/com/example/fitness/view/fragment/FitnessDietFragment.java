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
import com.example.fitness.databinding.FragmentFitnessDietBinding;
import com.example.fitness.view.adapter.AdapterDietAll;
import com.example.fitness.view.adapter.AdapterFitnessDiet;
import com.example.fitness.viewmodel.ViewModelFitnessDiet;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;


public class FitnessDietFragment extends Fragment {

    FragmentFitnessDietBinding binding;
    NavController navController;
    AdapterFitnessDiet adapterFitnessDiet;
    AdapterDietAll adapterDietAll;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = FragmentFitnessDietBinding.inflate(inflater, container, false);
            navController = Navigation.findNavController(requireActivity(), R.id.fragment);
            ViewModelFitnessDiet viewmodel = new ViewModelProvider(this).get(ViewModelFitnessDiet.class);
            viewmodel.Fitnessdiet();
            ChipNavigationBar navigationBar = requireActivity().findViewById(R.id.navigation_bar);
            navigationBar.setVisibility(View.VISIBLE);
            binding.back.setOnClickListener(view -> NavHostFragment.findNavController(this).popBackStack());
            viewmodel.mutableLiveDataPost.observe(requireActivity(), modelFitnessDiets -> {
                adapterFitnessDiet = new AdapterFitnessDiet(modelFitnessDiets);
                binding.RecyclerViewFitnessDiet.setAdapter(adapterFitnessDiet);
            });
            viewmodel.mutableLiveDataPost.observe(requireActivity(), modelFitnessDiets -> {
                adapterDietAll = new AdapterDietAll(modelFitnessDiets);
                binding.RecyclerViewFitnessDietItem.setAdapter(adapterDietAll);
            });
        }

        return binding.getRoot();
    }

}