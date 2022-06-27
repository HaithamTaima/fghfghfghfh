package com.example.fainl_android.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.fainl_android.Interface.UserInfoBoolean;
import com.example.fainl_android.Interface.UserInfoObject;
import com.example.fainl_android.RoomDB.MyViewModel;
import com.example.fainl_android.RoomDB.Tables.UserInfo;
import com.example.fainl_android.databinding.ActivityLoginBinding;

public class LoginActivity extends AppCompatActivity {

    ActivityLoginBinding binding;
    MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        String email = binding.loginEtEmailID.getText().toString();
        String password = binding.loginEtPasswordID.getText().toString();

        binding.loginSubmitButtonID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(binding.loginEtEmailID.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Please Enter Email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(binding.loginEtPasswordID.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "Please Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }


                viewModel.Login(binding.loginEtEmailID.getText().toString(),
                        binding.loginEtPasswordID.getText().toString(), new UserInfoBoolean() {
                            @Override
                            public void UserBoolean(boolean item) {
                                Log.d("ttt", item + "");
                            }
                        });


                viewModel.LoginInfo(binding.loginEtEmailID.getText().toString(),
                        binding.loginEtPasswordID.getText().toString(), new UserInfoObject() {
                            @Override
                            public void UserObject(UserInfo user) {

                                try {
                                    if (user.getEmail() != null) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                                                Toast.makeText(LoginActivity.this, "Welcome Mr." + user.getName(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(LoginActivity.this, "المستخدم غير موجود", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                }
                            }
                        });
            }
        });

        binding.Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getBaseContext(), RegActivity.class));
            }
        });

    }
}