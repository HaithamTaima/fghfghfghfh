package com.example.fainl_android.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fainl_android.R;

import java.util.ArrayList;

public class ServiceProAdapter extends RecyclerView.Adapter<ServiceProAdapter.MyViewHolder> {
    ArrayList<String> strings;
    int selectedPosition = 0;
    Context context;

    public ServiceProAdapter(Context context, ArrayList<String> strings) {
        this.strings = strings;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.test_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textView.setText(strings.get(position));
        if (selectedPosition == position)
            holder.textView.setBackgroundColor(context.getResources().getColor(android.R.color.background_light));
        else
            holder.textView.setBackgroundColor(context.getResources().getColor(android.R.color.transparent));
    }

    public void setSelectedPosition(int selectedPosition) {
        this.selectedPosition = selectedPosition;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return strings.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.text);
        }
    }

}
