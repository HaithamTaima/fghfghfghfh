package com.example.fainl_android.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fainl_android.Dialog.CustomDialog;
import com.example.fainl_android.Interface.OnClickItem;
import com.example.fainl_android.R;
import com.example.fainl_android.RoomDB.Tables.Review;
import com.example.fainl_android.databinding.CustomItemBinding;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

   private List<Review> list;
   private OnClickItem onClickItem;
   private Activity activity;

    public List<Review> getList() {
        return list;
    }

    public void setList(List<Review> list) {
        this.list = list;
        notifyDataSetChanged();
    }

//    public ListAdapter(List<Review> list, OnClickItem onClickItem) {
//        this.list = list;
//        this.onClickItem=onClickItem;
//    }


    public ListAdapter(List<Review> list, OnClickItem onClickItem, Activity activity) {
        this.list = list;
        this.onClickItem = onClickItem;
        this.activity = activity;
    }

    public OnClickItem getOnClickItem() {
        return onClickItem;
    }

    public void setOnClickItem(OnClickItem onClickItem) {
        this.onClickItem = onClickItem;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_item,parent,false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        Review review=list.get(position);
        holder.bind(review);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder {
        CustomItemBinding binding;
        Review re;
        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=CustomItemBinding.bind(itemView);
        }

        void bind(Review review){
            re=review;
            binding.Category.setText(review.getCategory());
            binding.Date.setText(review.getDate());
            binding.NameBook.setText(review.getNameBook());
            binding.NamePerson.setText(review.getName());

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickItem.OnClick(re);
                }
            });

//            binding.cancel.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onClickItem.onDelete(re);
//                }
//            });
            binding.cancel.setOnClickListener(view -> {
                CustomDialog customDialog = new CustomDialog(activity,
                        () -> {
                            onClickItem.onDelete(re);
                        });
                customDialog.show();
            });
        }
    }
}
