package com.example.fitness.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.fitness.R;
import com.example.fitness.databinding.FragmentProfileBinding;
import com.example.fitness.model.Status;
import com.example.fitness.utils.Repository;
import com.example.fitness.viewmodel.ViewModelFollowing;
import com.example.fitness.viewmodel.ViewModelUpdateProfile;
import com.example.fitness.viewmodel.ViewModelUserInfo;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;


import es.dmoral.toasty.Toasty;


public class ProfileFragment extends Fragment {
    public String token;
    FragmentProfileBinding binding;
    NavController navController;
    String zero = "0";
    String Female = "Female";
    String Male = "Male";
    String ok = "ok";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        OnBackPressedCallback callback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback);

    }


    @SuppressLint("NonConstantResourceId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragment);
        ViewModelUserInfo viewmodel = new ViewModelProvider(this).get(ViewModelUserInfo.class);
        ViewModelUpdateProfile viewModelDetails = new ViewModelProvider(this).get(ViewModelUpdateProfile.class);
        ViewModelFollowing viewModelFollowing = new ViewModelProvider(this).get(ViewModelFollowing.class);
        token = Repository.Shared_Read(requireActivity());
        viewmodel.UserInfo(token);
        viewModelDetails.token = token;
        binding.setUpdate(viewModelDetails);
        viewModelFollowing.Following(token);
        ChipNavigationBar navigationBar = requireActivity().findViewById(R.id.navigation_bar);
        navigationBar.setVisibility(View.VISIBLE);
        binding.MaterialCardViewFavorite.setOnClickListener(view -> navController.navigate(R.id.action_profileFragment_to_favoriteAllFragment));
        binding.back.setOnClickListener(view -> requireActivity().finish());
        viewmodel.mutableinfo.observe(requireActivity(), modelUserInfos -> {
            binding.username.setText(modelUserInfos.get(0).getUsername());
            binding.description.setText(modelUserInfos.get(0).getDescription());
            binding.weight.setValue(modelUserInfos.get(0).getWeight());
            binding.height.setValue(modelUserInfos.get(0).getHeight());
            binding.birthday.setValue(modelUserInfos.get(0).getBirthday());
            binding.birthday.setOnValueChangedListener((picker, oldVal, newVal) -> {
                if (modelUserInfos.get(0).getBirthday() == newVal)
                    viewModelDetails.date_of_birth = modelUserInfos.get(0).getBirthday();
                else
                    viewModelDetails.date_of_birth = newVal;

            });
            binding.height.setOnValueChangedListener((picker, oldVal, newVal) -> {
                if (modelUserInfos.get(0).getHeight() == newVal)
                    viewModelDetails.height = modelUserInfos.get(0).getHeight();
                else
                    viewModelDetails.height = newVal;

            });
            binding.weight.setOnValueChangedListener((picker, oldVal, newVal) -> {
                if (modelUserInfos.get(0).getWeight() == newVal)
                    viewModelDetails.weight = modelUserInfos.get(0).getWeight();
                else
                    viewModelDetails.weight = newVal;

            });

            if (modelUserInfos.get(0).getGender() == null) {
                binding.male.setChecked(true);
                binding.image.setImageResource(R.drawable.ic_man);
                viewModelDetails.gender = Male;
            } else {
                switch (modelUserInfos.get(0).getGender()) {
                    case "Male":
                        binding.male.setChecked(true);
                        binding.image.setImageResource(R.drawable.ic_man);
                        viewModelDetails.gender = Male;

                        break;
                    case "Female":
                        binding.female.setChecked(true);
                        binding.image.setImageResource(R.drawable.ic_woman);
                        viewModelDetails.gender = Female;
                        break;

                }
            }

        });
        viewModelFollowing.mutableLiveData.observe(requireActivity(), following -> {
            if (following.getFollowing().equals(zero)) {
                binding.textFollow.setText(zero);
            } else {
                binding.textFollow.setText(following.getFollowing());
            }
        });
        binding.Following.setOnClickListener(view -> navController.navigate(R.id.action_profileFragment_to_followingFragment));
        binding.singOut.setOnClickListener(view -> {
            Repository.Shared_exit(requireContext());
            navController.navigate(R.id.loginFragment);
        });

        binding.male.setOnClickListener(view -> {
            binding.male.setChecked(true);
            binding.image.setImageResource(R.drawable.ic_man);
            viewModelDetails.gender = Male;
        });

        binding.female.setOnClickListener(view -> {
            binding.female.setChecked(true);
            binding.image.setImageResource(R.drawable.ic_woman);
            viewModelDetails.gender = Female;
        });
        viewModelDetails.mutableLiveDataUpdate.observe(requireActivity(), this::onChanged);
        return binding.getRoot();
    }


    private void onChanged(Status status) {
        if (status.getStatus().equals(ok)) {
            Toasty.success(requireActivity(), R.string.successfully, Toast.LENGTH_SHORT, true).show();
        } else {
            Toasty.error(requireActivity(), R.string.error_update, Toast.LENGTH_SHORT, true).show();
        }
    }
}