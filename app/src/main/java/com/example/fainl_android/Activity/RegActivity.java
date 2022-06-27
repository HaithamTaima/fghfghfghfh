package com.example.fainl_android.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.fainl_android.RoomDB.MyViewModel;
import com.example.fainl_android.RoomDB.Tables.UserInfo;
import com.example.fainl_android.databinding.ActivityRegBinding;

public class RegActivity extends AppCompatActivity {

    ActivityRegBinding binding;
    private MyViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityRegBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel=new ViewModelProvider(this).get(MyViewModel.class);

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegActivity.this, MainActivity.class));
            }
        });
        binding.registerSubmitButtonID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strFName = binding.regEtFNameID.getText().toString();
                String strAddress = binding.regEtAddressID.getText().toString();
                String strEmail = binding.regEtEmailID.getText().toString();
                String strPassword = binding.regEtPasswordID.getText().toString();

                UserInfo userInfo = new UserInfo(strEmail, strFName,strAddress, strPassword);
                viewModel.InsertUserInfo(userInfo);

                binding.regEtFNameID.setText("");
                binding.regEtAddressID.setText("");
                binding.regEtEmailID.setText("");
                binding.regEtPasswordID.setText("");

                Toast.makeText(RegActivity.this, "Registration Success", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegActivity.this, MainActivity.class));
            }
        });
    }
}