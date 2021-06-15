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
import com.example.fitness.databinding.FragmentLoginBinding;
import com.example.fitness.model.Status;
import com.example.fitness.utils.Repository;
import com.example.fitness.viewmodel.ViewModelLogin;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

import es.dmoral.toasty.Toasty;


public class LoginFragment extends Fragment {

    FragmentLoginBinding binding;
    NavController navController;
    String ok = "ok";
    String user = "User not available or Wrong username or password";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        navController = Navigation.findNavController(requireActivity(), R.id.fragment);
        ViewModelLogin viewmodel = new ViewModelProvider(this).get(ViewModelLogin.class);
        binding.setData(viewmodel);
        ChipNavigationBar navigationBar = requireActivity().findViewById(R.id.navigation_bar);
        navigationBar.setVisibility(View.GONE);
        viewmodel.mutableLiveDataLogin.observe(requireActivity(), this::onChanged);
        viewmodel.mutableLiveDataRegister.observe(requireActivity(), aBoolean -> {
            if (aBoolean) {
                navController.navigate(R.id.action_loginFragment_to_registerFragment);
            }
        });
        return binding.getRoot();
    }

    private void onChanged(Status status) {
        if (status.getStatus().equals(ok)) {
            Repository.Shared_write(requireActivity(), status.getToken());
            navController.navigate(R.id.action_loginFragment_to_fitnessDietFragment);
        } else if (status.getStatus().equals(user)) {
            Toasty.error(requireActivity(), R.string.not_available, Toast.LENGTH_SHORT, true).show();
        }
    }
}