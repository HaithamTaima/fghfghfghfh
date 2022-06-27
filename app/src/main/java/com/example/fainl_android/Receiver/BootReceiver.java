package com.example.fainl_android.Receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.telephony.PhoneStateListener;
import android.telephony.SmsMessage;
import android.telephony.TelephonyManager;
import android.widget.Toast;


public class BootReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(final Context context, final Intent intent) {
        SharedPreferences preferences = context.getSharedPreferences("mmm", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        if (intent.getAction().equals("android.intent.action.PHONE_STATE")) {

            TelephonyManager manager = (TelephonyManager) context.getSystemService(context.TELEPHONY_SERVICE);

            manager.listen(new PhoneStateListener() {
                @Override
                public void onCallStateChanged(int state, String phoneNumber) {
                    super.onCallStateChanged(state, phoneNumber);

                    if (!phoneNumber.isEmpty()) {
                        editor.putString("PhoneKey", phoneNumber);
                        editor.apply();

                    }
                    Toast.makeText(context, "phoneNumber = " + phoneNumber, Toast.LENGTH_SHORT).show();


                }
            }, PhoneStateListener.LISTEN_CALL_STATE);
        } else if (intent.getAction().equals("android.provider.Telephony.SMS_RECEIVED")) {
            Bundle bundle = intent.getExtras();
            if (bundle != null) {
                Object[] objects = (Object[]) bundle.get("pdus");
                SmsMessage[] messages = new SmsMessage[objects.length];
                for (int x = 0; x < messages.length; x++) {
                    messages[x] = SmsMessage.createFromPdu((byte[]) objects[x]);
                }

                Toast.makeText(context, "" + messages[0].getMessageBody(), Toast.LENGTH_SHORT).show();

                editor.putString("message", messages[0].getMessageBody());
                editor.apply();

            }


        }


    }

}

