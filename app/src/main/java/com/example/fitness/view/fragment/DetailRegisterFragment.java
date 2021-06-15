package com.example.fitness.view.fragment;


import android.annotation.SuppressLint;
import android.os.Bundle;

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
import com.example.fitness.databinding.FragmentDetailRegisterBinding;
import com.example.fitness.model.Status;
import com.example.fitness.utils.Repository;
import com.example.fitness.viewmodel.ViewModelDetails;


import es.dmoral.toasty.Toasty;


public class DetailRegisterFragment extends Fragment {
    FragmentDetailRegisterBinding binding;
    NavController navController;
    String token;
    String Female = "Female";
    String Male = "Male";
    String ok = "ok";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("NonConstantResourceId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDetailRegisterBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragment);
        ViewModelDetails viewmodel = new ViewModelProvider(this).get(ViewModelDetails.class);
        token = Repository.Shared_Read(requireActivity());
        viewmodel.token = token;
        binding.setUpdate(viewmodel);
        binding.birthday.setOnValueChangedListener((picker, oldVal, newVal) -> viewmodel.date_of_birth = newVal);
        binding.weight.setOnValueChangedListener((picker, oldVal, newVal) -> viewmodel.weight = newVal);
        binding.height.setOnValueChangedListener((picker, oldVal, newVal) -> viewmodel.height = newVal);
        binding.male.setOnClickListener(view -> {
            binding.male.setChecked(true);
            binding.image.setImageResource(R.drawable.ic_man);
            viewmodel.gender = Male;
        });

        binding.female.setOnClickListener(view -> {
            binding.female.setChecked(true);
            binding.image.setImageResource(R.drawable.ic_woman);
            viewmodel.gender = Female;
        });
        viewmodel.mutableLiveDataUpdate.observe(requireActivity(), this::onChanged);
        return binding.getRoot();
    }

    private void onChanged(Status status) {
        if (status.getStatus().equals(ok)) {
            Toasty.success(requireActivity(), R.string.successfully, Toast.LENGTH_SHORT, true).show();
            navController.navigate(R.id.action_detailRegisterFragment_to_fitnessDietFragment);
        } else {
            Toasty.error(requireActivity(), R.string.error_update, Toast.LENGTH_SHORT, true).show();
        }
    }
}