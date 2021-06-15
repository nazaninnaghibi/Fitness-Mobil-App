package com.example.fitness.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.fitness.R;
import com.example.fitness.databinding.FragmentExerciseOptionBinding;
import com.example.fitness.model.ModelExerciseOptions;
import com.example.fitness.view.adapter.AdapterExerciseOptions;
import com.example.fitness.viewmodel.ViewModelExerciseOptions;

import java.util.List;


public class ExerciseOptionFragment extends Fragment {
    FragmentExerciseOptionBinding binding;
    NavController navController;
    AdapterExerciseOptions adapterExerciseOptions;
    ExerciseOptionFragmentArgs args;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = FragmentExerciseOptionBinding.inflate(inflater, container, false);
            navController = Navigation.findNavController(requireActivity(), R.id.fragment);
            ViewModelExerciseOptions viewmodel = new ViewModelProvider(this).get(ViewModelExerciseOptions.class);
            assert getArguments() != null;
            args = ExerciseOptionFragmentArgs.fromBundle(getArguments());
            viewmodel.ExerciseOptions(args.getModelExercise().getId());
            binding.back.setOnClickListener(view -> NavHostFragment.findNavController(this).popBackStack());
            viewmodel.mutableLiveDataPost.observe(requireActivity(), this::onChanged);

        }

        return binding.getRoot();
    }

    private void onChanged(List<ModelExerciseOptions> modelExerciseOptions) {
        binding.RecyclerViewExerciseOptions.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapterExerciseOptions = new AdapterExerciseOptions(modelExerciseOptions);
        binding.RecyclerViewExerciseOptions.setAdapter(adapterExerciseOptions);

    }
}