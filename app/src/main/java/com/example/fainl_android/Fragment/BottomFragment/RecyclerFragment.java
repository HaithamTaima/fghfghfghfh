package com.example.fainl_android.Fragment.BottomFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.fainl_android.Activity.AddActivity;
import com.example.fainl_android.Activity.EditActivity;
import com.example.fainl_android.Adapter.ListAdapter;
import com.example.fainl_android.Interface.OnClickItem;
import com.example.fainl_android.RoomDB.MyViewModel;
import com.example.fainl_android.RoomDB.Tables.Review;
import com.example.fainl_android.databinding.FragmentRecyclerBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerFragment extends Fragment implements OnClickItem {

    private MyViewModel viewModel;
    private ListAdapter adapter;
    public static final String ID = "Id";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        com.example.fainl_android.databinding.FragmentRecyclerBinding binding = FragmentRecyclerBinding.inflate(inflater, container, false);
        viewModel = new ViewModelProvider(this).get(MyViewModel.class);

        adapter = new ListAdapter(new ArrayList<>(), this,getActivity());

        viewModel.getAll().observe(getActivity(), new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                adapter.setList(reviews);
            }
        });

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getActivity(), AddActivity.class));
            }
        });

        binding.rv.setAdapter(adapter);
        binding.rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.rv.setHasFixedSize(true);
        return binding.getRoot();
    }

    @Override
    public void OnClick(Review review) {
        Intent intent = new Intent(getActivity(), EditActivity.class);
        intent.putExtra(ID, review.getId());
        startActivity(intent);
    }

    @Override
    public void onDelete(Review review) {
        viewModel.DeleteProducts(review.getId());
    }
}