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
import android.widget.Toast;

import com.example.fitness.R;
import com.example.fitness.databinding.FragmentFavoriteBinding;
import com.example.fitness.model.ModelFitnessDiet;
import com.example.fitness.utils.Repository;
import com.example.fitness.view.adapter.AdapterShowFavorite;
import com.example.fitness.viewmodel.ViewModelShowFavorite;

import java.util.List;


public class FavoriteFragment extends Fragment {
    FragmentFavoriteBinding binding;
    NavController navController;
    AdapterShowFavorite adapterShowFavorite;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = FragmentFavoriteBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragment);
        ViewModelShowFavorite viewmodel = new ViewModelProvider(this).get(ViewModelShowFavorite.class);
        String token = Repository.Shared_Read(requireActivity());
        viewmodel.ShowFavorite(token);
        binding.back.setOnClickListener(view -> NavHostFragment.findNavController(this).popBackStack());
        viewmodel.mutableLiveDataPost.observe(requireActivity(), this::onChanged);

        return binding.getRoot();
    }


    private void onChanged(List<ModelFitnessDiet> modelShowFavorites) {
        binding.RecyclerViewShowFavorite.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapterShowFavorite = new AdapterShowFavorite(modelShowFavorites, requireContext());
        binding.RecyclerViewShowFavorite.setAdapter(adapterShowFavorite);
        if (adapterShowFavorite.getItemCount() == 0) {
            binding.noDiet.setVisibility(View.VISIBLE);
            binding.Description.setOnClickListener(view -> navController.navigate(R.id.fitnessDietFragment));
        } else {
            binding.noDiet.setVisibility(View.GONE);
        }
    }
}