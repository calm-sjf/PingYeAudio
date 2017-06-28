package com.cn.cae;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Created by shen on 17-6-21.
 */

public class CAEBootReceiver extends BroadcastReceiver {
    final static String TAG = "CAEBootReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d(TAG,"action="+intent.getAction());
        if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
            Log.d(TAG,"startService");
            context.startService(new Intent(context, CAEMainService.class));
            Intent bIntent = new Intent(context,MainActivity.class);
            bIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(bIntent);
        }
    }
}