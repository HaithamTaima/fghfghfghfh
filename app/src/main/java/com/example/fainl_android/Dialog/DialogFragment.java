package com.example.fainl_android.Dialog;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fainl_android.R;

public class DialogFragment extends androidx.fragment.app.DialogFragment {

    onokclicklistener onokclicklistener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof onokclicklistener){
            onokclicklistener = (onokclicklistener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onokclicklistener = null;
    }

    public DialogFragment() {}

    public static DialogFragment newInstance() {
        DialogFragment fragment = new DialogFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);
        EditText ed_name ;
        EditText ed_address;
        Button button_ok;

        ed_name = view.findViewById(R.id.Edit_name);

        ed_address = view.findViewById(R.id.Edit_address);
        button_ok = view.findViewById(R.id.Button_Ok);


        button_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onokclicklistener.OnOkClicked(ed_name.getText().toString(),ed_address.getText().toString());
                dismiss();
            }
        });
    }




    public interface onokclicklistener {
        void OnOkClicked(String name,String address);
    }

}