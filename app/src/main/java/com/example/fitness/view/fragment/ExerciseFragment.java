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
import com.example.fitness.databinding.FragmentExerciseBinding;
import com.example.fitness.view.adapter.AdapterExercise;
import com.example.fitness.viewmodel.ViewModelExercise;


public class ExerciseFragment extends Fragment {
    FragmentExerciseBinding binding;
    NavController navController;
    AdapterExercise adapterExercise;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = FragmentExerciseBinding.inflate(inflater, container, false);
            navController = Navigation.findNavController(requireActivity(), R.id.fragment);
            ViewModelExercise viewmodel = new ViewModelProvider(this).get(ViewModelExercise.class);
            viewmodel.Exercise();
            binding.back.setOnClickListener(view -> NavHostFragment.findNavController(this).popBackStack());
            viewmodel.mutableLiveDataPost.observe(requireActivity(), modelExercises -> {
                adapterExercise = new AdapterExercise(modelExercises);
                binding.RecyclerViewExercise.setAdapter(adapterExercise);
                binding.RecyclerViewExercisePopular.setAdapter(adapterExercise);
            });
        }

        return binding.getRoot();
    }
}