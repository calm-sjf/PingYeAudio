package com.cn.cae;

import android.app.KeyguardManager;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.os.PowerManager;
import android.util.Log;

public class CAEMainService extends Service {

    final static String TAG = "CAEService ";

    final static String SCREE_ON_ACTION = "com.cn.cae.screen.on";

    final static String SCREE_OFF_ACTION = "com.cn.cae.screen.off";

    public Intent bIntent = new Intent();

    @Override
    public void onCreate(){
        super.onCreate();
        Log.d(TAG,"CAEMainService onCreate");

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_SCREEN_ON);
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(mReceiver,intentFilter);
    }

    BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.d(TAG,"action = "+intent.getAction());
            if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
                Log.d(TAG,"action_off");
                bIntent.setAction(SCREE_OFF_ACTION);
                sendBroadcast(bIntent);
            }
            if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
                Log.d(TAG,"action_on ");
                wakeUpAndUnlock();
                bIntent.setAction(SCREE_ON_ACTION);
                sendBroadcast(bIntent);
            }
        }
    };

    public  void wakeUpAndUnlock(){
        Log.d(TAG,"wakeupAndUnlock");
        KeyguardManager km= (KeyguardManager)getSystemService(KEYGUARD_SERVICE);
        KeyguardManager.KeyguardLock kl = km.newKeyguardLock("unLock");
        //解锁
        kl.disableKeyguard();
        //获取电源管理器对象
        PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        //获取PowerManager.WakeLock对象,后面的参数|表示同时传入两个值,最后的是LogCat里用的Tag
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_DIM_WAKE_LOCK,"bright");
        //点亮屏幕
        wl.acquire();
        //释放
        wl.release();
    }

    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mReceiver);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}