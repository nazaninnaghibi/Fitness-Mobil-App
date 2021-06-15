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
import com.example.fitness.databinding.FragmentFavoriteAllBinding;
import com.example.fitness.model.ModelFitnessDiet;
import com.example.fitness.utils.Repository;
import com.example.fitness.view.adapter.AdapterShowFavoriteLimit;
import com.example.fitness.viewmodel.ViewModelShowFavoriteLimit;
import com.jem.fliptabs.FlipTab;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class FavoriteAllFragment extends Fragment {
    FragmentFavoriteAllBinding binding;
    NavController navController;
    AdapterShowFavoriteLimit adapterShowFavoriteLimit;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentFavoriteAllBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragment);
        ViewModelShowFavoriteLimit viewmodel = new ViewModelProvider(this).get(ViewModelShowFavoriteLimit.class);
        String token = Repository.Shared_Read(requireActivity());
        viewmodel.ShowFavorite(token);
        binding.back.setOnClickListener(view -> NavHostFragment.findNavController(this).popBackStack());
        viewmodel.mutableLiveDataPost.observe(requireActivity(), this::onChanged);
        binding.FlipTab.setTabSelectedListener(new FlipTab.TabSelectedListener() {
            @Override
            public void onTabSelected(boolean b, @NotNull String s) {
                navController.navigate(R.id.favoriteExerciseFragment);
            }

            @Override
            public void onTabReselected(boolean b, @NotNull String s) {
                navController.navigate(R.id.favoriteFragment);
            }
        });


        return binding.getRoot();
    }


    private void onChanged(List<ModelFitnessDiet> modelShowFavorites) {
        adapterShowFavoriteLimit = new AdapterShowFavoriteLimit(modelShowFavorites, requireContext());
        binding.RecyclerViewFitnessDietItem.setAdapter(adapterShowFavoriteLimit);
        if (adapterShowFavoriteLimit.getItemCount() == 0) {
            binding.noDiet.setVisibility(View.VISIBLE);
            binding.noDiet.setOnClickListener(view -> navController.navigate(R.id.fitnessDietFragment));

        } else {
            binding.noDiet.setVisibility(View.GONE);
        }
    }
}