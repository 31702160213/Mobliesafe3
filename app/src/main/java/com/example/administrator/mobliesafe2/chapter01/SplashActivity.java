package com.example.administrator.mobliesafe2.chapter01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.example.administrator.mobliesafe2.R;
import com.example.administrator.mobliesafe2.chapter01.utils.MyUtils;
import com.example.administrator.mobliesafe2.chapter01.utils.VersionUpdateUtils;

public class SplashActivity extends AppCompatActivity {
    /**应用版本号*/
    private TextView mVersionTV;
    /**本地版本号*/
    private String mVersion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置该Activity没用标题栏，在加载布局之前调用
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);
        mVersion=MyUtils.getVersion(getApplicationContext());
        initView();

        final VersionUpdateUtils updateUtils = new VersionUpdateUtils(mVersion, SplashActivity.this);
        new Thread(){
            @Override
            public void run() {
             //   super.run();
                //获取服务器版本号
                updateUtils.getCloudVersion();
            };
        }.start();

    }


    
    /**初始化控件*/
    private void initView() {
        mVersionTV=(TextView)findViewById(R.id.tv_splash_version);
        mVersionTV.setText("版本号"+mVersion);
    }
}
