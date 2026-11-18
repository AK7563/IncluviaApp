package com.example.incluviaapp;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.WindowCompat;

import com.example.incluviaapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        WindowCompat.enableEdgeToEdge(getWindow());
        View view = binding.getRoot();
        setContentView(view);

        binding.btnNext.setOnClickListener(onNextClicked());

    }

    private View.OnClickListener onNextClicked(){
        return v -> System.out.println("AAAAAAAAAAAAA");
    }
}