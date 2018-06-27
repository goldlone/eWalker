package cn.goldlone.car.view.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import cn.goldlone.car.Configs;
import cn.goldlone.car.MyApplication;
import cn.goldlone.car.R;
import cn.goldlone.car.view.BaseActivity;

/**
 * @author : Created by CN on 2018/6/26 13:56
 */
public class MineActivity extends BaseActivity implements View.OnClickListener {

    private LinearLayout ll_mine_help_status;
    private LinearLayout ll_mine_contacts;
    private LinearLayout ll_mine_video_length;
    private LinearLayout ll_mine_send_location_interval;
    private LinearLayout ll_mine_usually_location;
    private LinearLayout ll_mine_update_password;
    private LinearLayout ll_mine_logout;
    private LinearLayout ll_mine_exit;

    // 设置求救录像时长对话框
    private AlertDialog videoLengthDialog = null;
    // 设置发送位置信息的时间间隔对话框
    private AlertDialog locationIntervalDialog = null;
    private SeekBar seekBar = null;
    private TextView seekBarValue = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);

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
        ll_mine_help_status = (LinearLayout) findViewById(R.id.ll_mine_help_status);
        ll_mine_contacts = (LinearLayout) findViewById(R.id.ll_mine_contacts);
        ll_mine_video_length = (LinearLayout) findViewById(R.id.ll_mine_video_length);
        ll_mine_send_location_interval = (LinearLayout) findViewById(R.id.ll_mine_send_location_interval);
        ll_mine_usually_location = (LinearLayout) findViewById(R.id.ll_mine_usually_location);
        ll_mine_update_password = (LinearLayout) findViewById(R.id.ll_mine_update_password);
        ll_mine_logout = (LinearLayout) findViewById(R.id.ll_mine_logout);
        ll_mine_exit = (LinearLayout) findViewById(R.id.ll_mine_exit);

        ll_mine_help_status.setOnClickListener(this);
        ll_mine_contacts.setOnClickListener(this);
        ll_mine_video_length.setOnClickListener(this);
        ll_mine_send_location_interval.setOnClickListener(this);
        ll_mine_usually_location.setOnClickListener(this);
        ll_mine_update_password.setOnClickListener(this);
        ll_mine_logout.setOnClickListener(this);
        ll_mine_exit.setOnClickListener(this);

        if(!Configs.isHelping) {
            ll_mine_help_status.setVisibility(View.GONE);
        } else {
            ll_mine_help_status.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_mine_help_status:
                Toast.makeText(this, "已关闭", Toast.LENGTH_SHORT).show();
                ll_mine_help_status.setVisibility(View.GONE);
                Configs.isHelping = false;
                break;
            case R.id.ll_mine_contacts:
                startActivity(new Intent(this, ContactActivity.class));
                break;
            case R.id.ll_mine_video_length:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("请设置时间范围");
                builder.setView(R.layout.dialog_seekbar);
                builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        Configs.recordTimes = Integer.parseInt(seekBarValue.getText().toString());
                        MyApplication.setRecordTimes(Configs.recordTimes);
                        Toast.makeText(MineActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MineActivity.this, "已取消", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.create();
                videoLengthDialog = builder.show();
                seekBar = videoLengthDialog.findViewById(R.id.sb_dialog_seek_bar);
                seekBarValue = videoLengthDialog.findViewById(R.id.tv_dialog_seek_bar_value);
                seekBarValue.setText(String.valueOf(Configs.recordTimes));
                seekBar.setProgress((Configs.recordTimes-10)*2);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                        progress = progress/2 + 10;
                        seekBarValue.setText(String.valueOf(progress));
                    }
                    // 开始拖动
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }
                    // 结束拖动
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                break;
            case R.id.ll_mine_send_location_interval:
                AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
                builder2.setTitle("请设置时间间隔");
                builder2.setView(R.layout.dialog_seekbar);
                builder2.setPositiveButton("确认", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        Configs.intervalGeo = Integer.parseInt(seekBarValue.getText().toString());
                        MyApplication.setIntervalGeo(Configs.intervalGeo);
                        Toast.makeText(MineActivity.this, "设置成功", Toast.LENGTH_SHORT).show();
                    }
                });
                builder2.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MineActivity.this, "已取消", Toast.LENGTH_SHORT).show();
                    }
                });
                builder2.create();
                locationIntervalDialog = builder2.show();
                seekBar = locationIntervalDialog.findViewById(R.id.sb_dialog_seek_bar);
                seekBarValue = locationIntervalDialog.findViewById(R.id.tv_dialog_seek_bar_value);
                seekBarValue.setText(String.valueOf(Configs.intervalGeo));
                seekBar.setProgress((Configs.intervalGeo-3)*20);
                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                        progress = progress/20 + 3;
                        seekBarValue.setText(String.valueOf(progress));
                    }
                    // 开始拖动
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }
                    // 结束拖动
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });
                break;
            case R.id.ll_mine_usually_location:

                break;
            case R.id.ll_mine_update_password:

                break;
            case R.id.ll_mine_logout:

                break;
            case R.id.ll_mine_exit:
                finish();
                System.exit(0);
                break;
        }
    }
}
