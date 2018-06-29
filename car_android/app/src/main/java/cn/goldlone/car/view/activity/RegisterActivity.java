package cn.goldlone.car.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import cn.goldlone.car.Configs;
import cn.goldlone.car.MyApplication;
import cn.goldlone.car.R;
import cn.goldlone.car.utils.EncryptUtils;
import cn.goldlone.car.view.BaseActivity;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author : Created by CN on 2018/6/28 17:19
 */
public class RegisterActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_register_phone;
    private EditText et_register_password;
    private EditText et_register_repeat_password;
    private Button btn_register_login;
    private TextView tv_register_back;

    private String phone;
    private String password;
    private String repeatPassword;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initView();
    }

    private void initView() {
        et_register_phone = (EditText) findViewById(R.id.et_register_phone);
        et_register_password = (EditText) findViewById(R.id.et_register_password);
        et_register_repeat_password = (EditText) findViewById(R.id.et_register_repeat_password);
        btn_register_login = (Button) findViewById(R.id.btn_register_login);
        tv_register_back = (TextView) findViewById(R.id.tv_register_back);

        btn_register_login.setOnClickListener(this);
        tv_register_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_register_login:
                phone = et_register_phone.getText().toString();
                password = et_register_password.getText().toString();
                repeatPassword = et_register_repeat_password.getText().toString();

                if("".equals(phone)) {
                    showTips("手机号不可为空");
                    return;
                }
                if("".equals(password) || "".equals(repeatPassword)) {
                    showTips("密码不可为空");
                    return;
                }
                if(!password.equals(repeatPassword)) {
                    showTips("两次输入密码的不一致");
                    return;
                }
                // 进行注册
                actionRegister();
                break;
            case R.id.tv_register_back:
                finish();
                break;
        }
    }

    private void showTips(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }


    public void actionRegister() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
        FormBody formBody = new FormBody.Builder()
                .add("phone", phone)
                .add("password", EncryptUtils.sha256Encrypt(password))
                .build();
        final Request request = new Request.Builder()
                .url(Configs.SERVER_IP+"/user/add")
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(e.getMessage());
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.code() == 200) {
                    try {
                        JSONTokener jt = new JSONTokener(response.body().string());
                        JSONObject json = new JSONObject(jt);
                        int code = json.getInt("code");
                        if(code == 1001) {
                            // 注册成功
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    showTips("注册成功");
                                    finish();
                                }
                            });
                        } else {
                            final String msg = json.getString("msg");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegisterActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "注册失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
