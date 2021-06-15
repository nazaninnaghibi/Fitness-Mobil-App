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
import com.example.fitness.databinding.FragmentUserProfileBinding;

import com.example.fitness.model.ModelExerciseOptions;
import com.example.fitness.model.ModelFitnessDiet;
import com.example.fitness.view.adapter.AdapterShowFavoriteExerciseUser;
import com.example.fitness.view.adapter.AdapterShowFavoriteUser;
import com.example.fitness.viewmodel.ViewModelShowFavorite;
import com.example.fitness.viewmodel.ViewModelShowFavoriteExercise;

import java.util.List;


public class UserProfileFragment extends Fragment {
    FragmentUserProfileBinding binding;
    NavController navController;
    UserProfileFragmentArgs args;
    String username, age, height, weight, token;
    AdapterShowFavoriteUser adapterShowFavoriteUser;
    AdapterShowFavoriteExerciseUser adapterShowFavoriteExerciseUser;
    String Female = "Female";
    String Male = "Male";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (binding == null) {
            binding = FragmentUserProfileBinding.inflate(inflater, container, false);
            navController = Navigation.findNavController(requireActivity(), R.id.fragment);
            ViewModelShowFavorite viewmodel = new ViewModelProvider(this).get(ViewModelShowFavorite.class);
            ViewModelShowFavoriteExercise viewModelShowFavoriteExercise = new ViewModelProvider(this).get(ViewModelShowFavoriteExercise.class);
            Arguments();
            viewmodel.ShowFavorite(token);
            viewModelShowFavoriteExercise.ShowFavorite(token);
            binding.back.setOnClickListener(view -> NavHostFragment.findNavController(this).popBackStack());
            viewmodel.mutableLiveDataPost.observe(requireActivity(), this::onChanged);
            viewModelShowFavoriteExercise.mutableLiveDataPost.observe(requireActivity(), this::onChangedExercise);


        }

        return binding.getRoot();
    }

    public void Arguments() {
        assert getArguments() != null;
        args = UserProfileFragmentArgs.fromBundle(getArguments());
        username = args.getModelShowUser().getUsername();
        token = args.getModelShowUser().getToken();
        age = args.getModelShowUser().getBirthday();
        height = args.getModelShowUser().getHeight();
        weight = args.getModelShowUser().getWeight();
        binding.username.setText(username);
        binding.heightText.setText(height);
        binding.ageText.setText(age);
        binding.weightText.setText(weight);
        if (args.getModelShowUser().getGender() == null)
            binding.image.setImageResource(R.drawable.user);
        else if (args.getModelShowUser().getGender().equals(Female))
            binding.image.setImageResource(R.drawable.ic_woman);
        else if (args.getModelShowUser().getGender().equals(Male))
            binding.image.setImageResource(R.drawable.ic_man);


    }

    private void onChanged(List<ModelFitnessDiet> modelFitnessDiets) {
        binding.RecyclerViewShowFavorite.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapterShowFavoriteUser = new AdapterShowFavoriteUser(modelFitnessDiets);
        binding.RecyclerViewShowFavorite.setAdapter(adapterShowFavoriteUser);
        binding.commentDiet.setVisibility(adapterShowFavoriteUser.getItemCount() == 0 ? View.VISIBLE : View.GONE);
    }

    private void onChangedExercise(List<ModelExerciseOptions> modelExerciseOptions) {
        binding.RecyclerViewShowFavoriteExercise.setLayoutManager(new LinearLayoutManager(requireActivity()));
        adapterShowFavoriteExerciseUser = new AdapterShowFavoriteExerciseUser(modelExerciseOptions);
        binding.RecyclerViewShowFavoriteExercise.setAdapter(adapterShowFavoriteExerciseUser);
        binding.exerciseComment.setVisibility(adapterShowFavoriteExerciseUser.getItemCount() == 0 ? View.VISIBLE : View.GONE);

    }
}