package cn.goldlone.car.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

import cn.goldlone.car.MyApplication;
import cn.goldlone.car.R;
import cn.goldlone.car.view.BaseActivity;

/**
 * @author : Created by CN on 2018/6/28 17:19
 */
public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                finish();
                if(MyApplication.getLoginStatus()) {
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                } else {
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                }
            }
        };
        timer.schedule(timerTask, 1000);
    }
}