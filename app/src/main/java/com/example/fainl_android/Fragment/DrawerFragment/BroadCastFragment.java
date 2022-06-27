package com.example.fainl_android.Fragment.DrawerFragment;

import android.Manifest;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.fainl_android.Receiver.BootReceiver;
import com.example.fainl_android.databinding.FragmentBroadcastBinding;

import java.util.Map;

public class BroadCastFragment extends Fragment {

    private FragmentBroadcastBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentBroadcastBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        SharedPreferences preferences = getActivity().getSharedPreferences("mmm", 0);

        ActivityResultLauncher<String[]> aboodLauncher = registerForActivityResult(new
                        ActivityResultContracts.RequestMultiplePermissions(),
                new ActivityResultCallback<Map<String, Boolean>>() {
                    @Override
                    public void onActivityResult(Map<String, Boolean> result) {
                        if (result.get(Manifest.permission.READ_CALL_LOG) &&
                                result.get(Manifest.permission.RECEIVE_SMS) &&
                                result.get(Manifest.permission.READ_PHONE_STATE)) {

                            BootReceiver receiver = new BootReceiver();
                            IntentFilter filter = new IntentFilter();
                            filter.addAction("android.intent.action.PHONE_STATE");
                            filter.addAction("android.provider.Telephony.SMS_RECEIVED");

                            getActivity().registerReceiver(receiver, filter);
                        }
                    }
                });
        String message = preferences.getString("message", "no last message");
        binding.TV.setText(message);


        String call = preferences.getString("PhoneKey", "no last Call");
        binding.tVCall.setText(call);


        aboodLauncher.launch(new String[]{
                Manifest.permission.READ_CALL_LOG
                , Manifest.permission.RECEIVE_SMS
                , Manifest.permission.READ_PHONE_STATE
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}