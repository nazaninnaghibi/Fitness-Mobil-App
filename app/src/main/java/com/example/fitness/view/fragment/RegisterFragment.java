package com.example.fitness.view.fragment;

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
import com.example.fitness.databinding.FragmentRegisterBinding;
import com.example.fitness.model.Status;
import com.example.fitness.utils.Repository;
import com.example.fitness.viewmodel.ViewModelRegister;

import es.dmoral.toasty.Toasty;


public class RegisterFragment extends Fragment {
    FragmentRegisterBinding binding;
    NavController navController;
    String ok = "ok";
    String username = "This username is available";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRegisterBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragment);
        binding.login.setOnClickListener(view -> navController.navigate(R.id.action_registerFragment_to_loginFragment));
        ViewModelRegister viewmodel = new ViewModelProvider(this).get(ViewModelRegister.class);
        binding.setData(viewmodel);
        viewmodel.mutableLiveDataRegister.observe(requireActivity(), this::onChanged);
        return binding.getRoot();
    }


    private void onChanged(Status status) {
        if (status.getStatus().equals(ok)) {
            Repository.Shared_write(requireActivity(), status.getToken());
            navController.navigate(R.id.action_registerFragment_to_detailRegisterFragment);
        } else if (status.getStatus().equals(username)) {
            Toasty.error(requireActivity(), R.string.is_username, Toast.LENGTH_SHORT, true).show();
        }
    }
}