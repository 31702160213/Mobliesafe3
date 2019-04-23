package com.example.administrator.mobliesafe2.chapter02.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.administrator.mobliesafe2.App;

/**监听开机启动的广播接受者，主要用于检查SIM卡是否被更换，如果被更换则发送短信给安全号码*/
public class BootCompleteReciever extends BroadcastReceiver {
 private static final String  TAG = BootCompleteReciever.class.getSimpleName();
    @Override
    public void onReceive(Context context, Intent intent) {
        ((App) context.getApplicationContext()).correctSIM(); //初始化
    }
}
