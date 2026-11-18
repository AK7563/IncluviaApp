package com.example.incluviaapp.presentation.feature.auth.ui;

import android.os.Bundle;

import androidx.core.view.WindowCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.incluviaapp.R;
import com.example.incluviaapp.core.base.BaseActivity;
import com.example.incluviaapp.data.response.auth.UserResponse;
import com.example.incluviaapp.databinding.ActivityAuthBinding;
import com.example.incluviaapp.presentation.common.state.DataUiState;
import com.example.incluviaapp.presentation.feature.auth.viewModel.AuthViewModel;
import com.example.incluviaapp.presentation.feature.auth.viewModel.TestModel;

import java.util.List;

public class AuthActivity extends BaseActivity<ActivityAuthBinding, AuthViewModel> {

    @Override
    public ActivityAuthBinding getViewBinding() {
        return ActivityAuthBinding.inflate(getLayoutInflater());
    }

    @Override
    public AuthViewModel getViewModel() {
        return new ViewModelProvider(this).get(AuthViewModel.class);
    }

    protected void setupUi() {
        super.setupUi();
        viewModel.getData();
    }

    protected void observeViewModel(){
        super.observeViewModel();
        viewModel.testModel.observe(this, state ->{
            switch (state){
                case DataUiState.Idle<List<UserResponse>> x -> viewModel.getData();
                case DataUiState.Error<List<UserResponse>> x -> viewModel.getData();
                case DataUiState.Loading<List<UserResponse>> x -> viewModel.getData();
                case DataUiState.Success<List<UserResponse>> x -> viewModel.getData();
            }
        });
    }
}