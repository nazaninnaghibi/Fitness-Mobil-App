package com.example.fitness.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.example.fitness.R;
import com.example.fitness.databinding.ActivityMainBinding;
import com.example.fitness.utils.Repository;
import com.gruveo.sdk.model.CallEndReason;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    NavController navController;
    ActivityMainBinding binding;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        assert navHostFragment != null;
        navController = navHostFragment.getNavController();
        String status = Repository.Shared_Read(MainActivity.this);
        if (status == null) {
            navController.navigate(R.id.registerFragment);
        } else {
            navController.navigate(R.id.profileFragment);
        }
        binding.navigationBar.setOnItemSelectedListener(id -> {
            switch (id) {
                case R.id.item_exercise:
                    navController.navigate(R.id.exerciseFragment);
                    break;
                case R.id.item_diet:
                    navController.navigate(R.id.fitnessDietFragment);
                    break;
                case R.id.item_profile:
                    navController.navigate(R.id.profileFragment);
                    break;
                case R.id.item_search:
                    navController.navigate(R.id.searchFragment);
                    break;
            }
        });
    }

}