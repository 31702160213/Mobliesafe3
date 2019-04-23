package com.example.administrator.mobliesafe2;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        correctSIM();
    }

    public void correctSIM() {
        //检查SIM卡是否发生变化
        SharedPreferences sp = getSharedPreferences("config", Context.MODE_PRIVATE);
        //获取防盗保护的状态
        boolean protecting = sp.getBoolean("protecting", true);
        if (protecting) {
            //得到绑定的sim卡串号
            String bindsim = sp.getString("sim", "");
            //得到手机现在的sim卡串号
            TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
            //为了测试在手机序列号上dafa已模拟SIM卡被更换的情况
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            String realsim = tm.getSimSerialNumber();
            if (bindsim.equals(realsim)){
                Log.i("","sim卡未发生变化，还是你的手机");
            }else{
                Log.i("","SIM卡变化了");
                //由于系统版本的原因，这里的发短信可能与其他手机版本不兼容
                String safenumber = sp.getString("safephone", "");
                if (!TextUtils.isEmpty(safenumber)){
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(safenumber,null,"你的亲友手机的SIM卡已经被更换！",null,null);
                }
            }
        }
    }
}

