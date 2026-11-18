package com.example.incluviaapp.core.base;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.WindowCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewbinding.ViewBinding;

import com.example.incluviaapp.databinding.ActivityMainBinding;
import com.example.incluviaapp.presentation.feature.auth.viewModel.AuthViewModel;

import kotlinx.coroutines.flow.Flow;

public abstract class BaseActivity<VB extends ViewBinding, VM extends BaseViewModel> extends FragmentActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = getViewBinding();
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(binding.getRoot());
        viewModel = getViewModel();
        observeViewModel();
        setupUi();
        setupClickListeners();
    }

    protected VB binding;
    protected VM viewModel;

    public abstract VB getViewBinding();
    public abstract VM getViewModel();

    protected void setupUi(){}
    protected void observeViewModel(){}
    protected void setupClickListeners(){}


}
