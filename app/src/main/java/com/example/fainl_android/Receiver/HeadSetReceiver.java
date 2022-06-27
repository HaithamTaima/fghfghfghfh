package com.example.fainl_android.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.fainl_android.Service.SoundService;


public class HeadSetReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {

        int state = intent.getIntExtra("state", -1);
        if (intent.getAction().equals("android.intent.action.HEADSET_PLUG")) {
            switch (state) {
                case 0:
                    context.stopService(new Intent(context, SoundService.class));
                    Toast.makeText(context, "السماعات غير متصلة", Toast.LENGTH_SHORT).show();
                    break;
                case 1:
                    context.startService(new Intent(context, SoundService.class));
                    Toast.makeText(context, "السماعات متصلة", Toast.LENGTH_SHORT).show();
                    Toast.makeText(context, "تم تشغيل الموسيقى", Toast.LENGTH_LONG).show();
                    break;
                default:
                    Toast.makeText(context, "ما ندري", Toast.LENGTH_SHORT).show();
                    break;
            }
        }
    }

}

