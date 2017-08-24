package com.suwonsmartapp.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver {

    public static final String MY_ACTION = "com.suwonsmartapp.broadcastreceiver.action.MY_ACTION";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (Intent.ACTION_POWER_CONNECTED.equals(intent.getAction())) {
            Toast.makeText(context, "전원 연결 됨", Toast.LENGTH_SHORT).show();
        } else if (Intent.ACTION_POWER_DISCONNECTED.equals(intent.getAction())) {
            Toast.makeText(context, "전원 뽑힘!!", Toast.LENGTH_SHORT).show();
        } else if (MY_ACTION.equals(intent.getAction())) {
            Toast.makeText(context, "나만의 액션이다", Toast.LENGTH_SHORT).show();
        }
    }
}
