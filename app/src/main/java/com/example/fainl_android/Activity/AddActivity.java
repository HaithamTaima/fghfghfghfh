package com.example.fainl_android.Activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.fainl_android.RoomDB.MyViewModel;
import com.example.fainl_android.RoomDB.Tables.Review;
import com.example.fainl_android.databinding.ActivityAddBinding;


public class AddActivity extends AppCompatActivity {

    private ActivityAddBinding binding;
    private Review review;
    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);


        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(binding.NameBook.getText().toString())) {
                    Toast.makeText(AddActivity.this, "Please Enter Name Book", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(binding.Category.getText().toString())) {
                    Toast.makeText(AddActivity.this, "Please Enter Category", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(binding.Date.getText().toString())) {
                    Toast.makeText(AddActivity.this, "Please Enter Date", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(binding.NamePerson.getText().toString())) {
                    Toast.makeText(AddActivity.this, "Please Enter NamePerson", Toast.LENGTH_SHORT).show();
                    return;
                }

                String name_book = binding.NameBook.getText().toString();
                String category = binding.Category.getText().toString();
                String date = binding.Date.getText().toString();
                String NamePerson = binding.NamePerson.getText().toString();

                review = new Review(name_book, NamePerson, date, category);

                viewModel.Insert(review);

                finish();
            }
        });
    }
}