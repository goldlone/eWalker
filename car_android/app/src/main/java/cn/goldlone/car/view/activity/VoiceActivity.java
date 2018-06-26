package cn.goldlone.car.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import cn.goldlone.car.R;
import cn.goldlone.car.view.BaseActivity;

/**
 * @author : Created by CN on 2018/6/26 17:27
 */
public class VoiceActivity extends BaseActivity implements View.OnClickListener{

    private FloatingActionButton fb_wake_voice_assistant;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice);

        initToolBar();
        initView();
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initView() {
        fb_wake_voice_assistant = (FloatingActionButton) findViewById(R.id.fb_wake_voice_assistant);
        fb_wake_voice_assistant.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fb_wake_voice_assistant:
                Toast.makeText(this, "你说，我在听!", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
