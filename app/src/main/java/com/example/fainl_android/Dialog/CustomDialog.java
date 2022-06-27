package com.example.fainl_android.Dialog;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Window;

import com.example.fainl_android.R;
import com.example.fainl_android.databinding.LayoutDialogBinding;


public class CustomDialog extends Dialog {

    public Activity activity;
    OnClearClickListener onClearClickListener;

    public CustomDialog(Activity activity,OnClearClickListener onClearClickListener) {
        super(activity);
        this.activity = activity;
        this.onClearClickListener = onClearClickListener;
    }

    public interface OnClearClickListener {
        void onClearClickListener();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutDialogBinding binding = LayoutDialogBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        getWindow().getAttributes().windowAnimations = R.style.DialogScaleAnimation;
        binding.button.setOnClickListener(view -> {
            onClearClickListener.onClearClickListener();
            dismiss();
        });
        binding.button2.setOnClickListener(view -> dismiss());
    }
}