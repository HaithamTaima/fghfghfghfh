package com.example.fainl_android.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fainl_android.databinding.ActivityShowsBinding;

public class ShowsActivity extends AppCompatActivity {


    ActivityShowsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityShowsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.maps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), MapsActivity.class);
                startActivity(intent);
            }
        });
        binding.RWN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), ProductActivity.class);
                startActivity(intent);
            }
        });

    }
}