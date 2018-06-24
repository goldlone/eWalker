package cn.goldlone.car.view.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cn.goldlone.car.R;
import cn.goldlone.car.utils.Contact;
import cn.goldlone.car.view.BaseActivity;

/**
 * @author : Created by CN on 2018/6/24 23:49
 */
public class PhoneActivity extends BaseActivity implements View.OnClickListener {

    private TextView tv_phone_phone_number;
    private Button btn_phone_1;
    private Button btn_phone_2;
    private Button btn_phone_3;
    private Button btn_phone_4;
    private Button btn_phone_5;
    private Button btn_phone_6;
    private Button btn_phone_7;
    private Button btn_phone_8;
    private Button btn_phone_9;
    private Button btn_phone_11;
    private Button btn_phone_0;
    private Button btn_phone_12;
    private Button btn_phone_call;
    private Button btn_phone_delete;

    private StringBuffer phoneNumberBuffer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        initToolBar();
        initView();
        phoneNumberBuffer = new StringBuffer();
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
        tv_phone_phone_number = (TextView) findViewById(R.id.tv_phone_phone_number);
        btn_phone_1 = (Button) findViewById(R.id.btn_phone_1);
        btn_phone_2 = (Button) findViewById(R.id.btn_phone_2);
        btn_phone_3 = (Button) findViewById(R.id.btn_phone_3);
        btn_phone_4 = (Button) findViewById(R.id.btn_phone_4);
        btn_phone_5 = (Button) findViewById(R.id.btn_phone_5);
        btn_phone_6 = (Button) findViewById(R.id.btn_phone_6);
        btn_phone_7 = (Button) findViewById(R.id.btn_phone_7);
        btn_phone_8 = (Button) findViewById(R.id.btn_phone_8);
        btn_phone_9 = (Button) findViewById(R.id.btn_phone_9);
        btn_phone_0 = (Button) findViewById(R.id.btn_phone_0);
        btn_phone_11 = (Button) findViewById(R.id.btn_phone_11);
        btn_phone_12 = (Button) findViewById(R.id.btn_phone_12);
        btn_phone_call = (Button) findViewById(R.id.btn_phone_call);
        btn_phone_delete = (Button) findViewById(R.id.btn_phone_delete);

        btn_phone_1.setOnClickListener(this);
        btn_phone_2.setOnClickListener(this);
        btn_phone_3.setOnClickListener(this);
        btn_phone_4.setOnClickListener(this);
        btn_phone_5.setOnClickListener(this);
        btn_phone_6.setOnClickListener(this);
        btn_phone_7.setOnClickListener(this);
        btn_phone_8.setOnClickListener(this);
        btn_phone_9.setOnClickListener(this);
        btn_phone_0.setOnClickListener(this);
        btn_phone_11.setOnClickListener(this);
        btn_phone_12.setOnClickListener(this);
        btn_phone_call.setOnClickListener(this);
        btn_phone_delete.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_phone_0:
                appendPhoneNumber("0");
                break;
            case R.id.btn_phone_1:
                appendPhoneNumber("1");
                break;
            case R.id.btn_phone_2:
                appendPhoneNumber("2");
                break;
            case R.id.btn_phone_3:
                appendPhoneNumber("3");
                break;
            case R.id.btn_phone_4:
                appendPhoneNumber("4");
                break;
            case R.id.btn_phone_5:
                appendPhoneNumber("5");
                break;
            case R.id.btn_phone_6:
                appendPhoneNumber("6");
                break;
            case R.id.btn_phone_7:
                appendPhoneNumber("7");
                break;
            case R.id.btn_phone_8:
                appendPhoneNumber("8");
                break;
            case R.id.btn_phone_9:
                appendPhoneNumber("9");
                break;
            case R.id.btn_phone_11:
                appendPhoneNumber("*");
                break;
            case R.id.btn_phone_12:
                appendPhoneNumber("#");
                break;
            case R.id.btn_phone_call:
                // 拨号
//                Toast.makeText(this, "拨打电话", Toast.LENGTH_SHORT).show();
                Contact contact = new Contact(this, phoneNumberBuffer.toString());
                contact.call();
                break;
            case R.id.btn_phone_delete:
                // 清除
                if(phoneNumberBuffer.length()>0) {
                    phoneNumberBuffer.deleteCharAt(phoneNumberBuffer.length() - 1);
                    tv_phone_phone_number.setText(phoneNumberBuffer.toString());
                }
                break;
        }
    }

    private void appendPhoneNumber(String code) {
        phoneNumberBuffer.append(code);
        Log.e("TAG", phoneNumberBuffer.toString());
        tv_phone_phone_number.setText(phoneNumberBuffer.toString());
    }
}
