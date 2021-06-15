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
import com.example.fitness.databinding.FragmentFavoriteExerciseBinding;
import com.example.fitness.model.ModelExerciseOptions;
import com.example.fitness.utils.Repository;
import com.example.fitness.view.adapter.AdapterShowFavoriteExercise;
import com.example.fitness.viewmodel.ViewModelShowFavoriteExercise;

import java.util.List;


public class FavoriteExerciseFragment extends Fragment {
    FragmentFavoriteExerciseBinding binding;
    NavController navController;
    AdapterShowFavoriteExercise adapterShowFavoriteExercise;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = FragmentFavoriteExerciseBinding.inflate(inflater, container, false);
            navController = Navigation.findNavController(requireActivity(), R.id.fragment);
            ViewModelShowFavoriteExercise viewmodel = new ViewModelProvider(this).get(ViewModelShowFavoriteExercise.class);
            String token = Repository.Shared_Read(requireActivity());
            viewmodel.ShowFavorite(token);
            binding.back.setOnClickListener(view -> NavHostFragment.findNavController(this).popBackStack());
            viewmodel.mutableLiveDataPost.observe(requireActivity(), this::onChanged);

        }

        return binding.getRoot();
    }

    private void onChanged(List<ModelExerciseOptions> modelExerciseOptions) {
        binding.RecyclerViewShowFavoriteExercise.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapterShowFavoriteExercise = new AdapterShowFavoriteExercise(modelExerciseOptions, requireContext());
        binding.RecyclerViewShowFavoriteExercise.setAdapter(adapterShowFavoriteExercise);
        if (adapterShowFavoriteExercise.getItemCount() == 0) {
            binding.noExercise.setVisibility(View.VISIBLE);
            binding.Description.setOnClickListener(view -> navController.navigate(R.id.exerciseFragment));
        } else {
            binding.noExercise.setVisibility(View.GONE);
        }
    }
}