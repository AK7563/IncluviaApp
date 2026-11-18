package com.example.incluviaapp.presentation.feature.test.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.example.incluviaapp.R;
import com.example.incluviaapp.core.base.BaseActivity;
import com.example.incluviaapp.data.response.testing.TestingResponse;
import com.example.incluviaapp.databinding.ActivityTestingBinding;
import com.example.incluviaapp.presentation.common.state.DataUiState;
import com.example.incluviaapp.presentation.feature.test.viewModel.TestingViewModel;

public class TestingActivity extends BaseActivity<ActivityTestingBinding, TestingViewModel> {
    public ActivityTestingBinding getViewBinding() {
        return ActivityTestingBinding.inflate(getLayoutInflater());
    }

    public TestingViewModel getViewModel() {
        return new ViewModelProvider(this).get(TestingViewModel.class);
    }

    protected void setupUi() {
        super.setupUi();
        viewModel.getMetar();
    }

    protected void setupClickListeners() {
        super.setupClickListeners();
    }

    protected void observeViewModel() {
        super.observeViewModel();
        viewModel.testingModel.observe(this, state ->{
            switch (state){
                case DataUiState.Error<TestingResponse> v -> {
                    //Cover error handling
                }
                case DataUiState.Idle<TestingResponse> v -> {
                    //Cover idle handling
                }
                case DataUiState.Loading<TestingResponse> v -> {
                    //Cover loading handling
                }
                case DataUiState.Success<TestingResponse> v -> {
                    System.out.println(v.getData());
                    binding.testingResult.setText(v.getData().icaoId());
                }
            }
        });
    }
}