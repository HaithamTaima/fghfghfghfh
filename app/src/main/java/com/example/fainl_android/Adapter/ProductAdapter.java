package com.example.fainl_android.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fainl_android.Class.Product_class;
import com.example.fainl_android.Interface.on_item_click_listener;
import com.example.fainl_android.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductAdapterHolder> {

    ArrayList<Product_class> productClasses;
    on_item_click_listener listener;

    public ProductAdapter(ArrayList<Product_class> productClasses, on_item_click_listener listener) {
        this.productClasses = productClasses;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ProductAdapterHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.customi_item,parent,false);
        ProductAdapterHolder holder = new ProductAdapterHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapterHolder holder, int position) {

        Product_class productClass = productClasses.get(position);
        holder.bind(productClass);
    }

    @Override
    public int getItemCount() {
        return productClasses.size();
    }

    class ProductAdapterHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView Id, Name, Description, Count, Price;
        Product_class productClass;
        public ProductAdapterHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.image_view);
            Name = itemView.findViewById(R.id.name);
            Description = itemView.findViewById(R.id.desc);
            Count = itemView.findViewById(R.id.count);
            Price = itemView.findViewById(R.id.price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClicked(productClass);
                }
            });
        }
        void bind(Product_class productClass){
            this.productClass = productClass;
            iv.setImageResource(productClass.getImage());
            Name.setText(productClass.getName());
            Description.setText(productClass.getDescription());
            Count.setText(String.valueOf(productClass.getCount()));
            Price.setText(String.valueOf(productClass.getPrice()));
        }
    }
}
