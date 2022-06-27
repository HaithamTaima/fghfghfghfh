package com.example.fainl_android.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.fainl_android.Fragment.BottomFragment.RecyclerFragment;
import com.example.fainl_android.RoomDB.MyViewModel;
import com.example.fainl_android.RoomDB.Tables.Review;
import com.example.fainl_android.databinding.ActivityEditBinding;


public class EditActivity extends AppCompatActivity {

    private ActivityEditBinding binding;
    private int Id;
    private MyViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        Intent intent = getIntent();
        Id = intent.getIntExtra(RecyclerFragment.ID, -1);


        viewModel.getItem(Id).observe(this, new Observer<Review>() {
            @Override
            public void onChanged(Review review) {
                binding.Category.setText(review.getCategory());
                binding.Date.setText(review.getDate());
                binding.NamePerson.setText(review.getName());
                binding.NameBook.setText(review.getNameBook());
            }
        });


        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.NamePerson.getText().toString();
                String NameBook = binding.NameBook.getText().toString();
                String Date = binding.Date.getText().toString();
                String Category = binding.Category.getText().toString();

                Review review = new Review();
                review.setId(Id);
                review.setCategory(Category);
                review.setName(name);
                review.setNameBook(NameBook);
                review.setDate(Date);
                viewModel.UpdateProducts(review);
                finish();
            }
        });
    }
}