package cn.goldlone.car.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.codec.digest.DigestUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
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
 * @author : Created by CN on 2018/6/28 17:16
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private EditText et_login_phone;
    private EditText et_login_password;
    private Button btn_login_login;
    private TextView tv_login_forget;
    private TextView tv_login_register;

    private String phone;
    private String password;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    private void initView() {
        et_login_phone = (EditText) findViewById(R.id.et_login_phone);
        et_login_password = (EditText) findViewById(R.id.et_login_password);
        btn_login_login = (Button) findViewById(R.id.btn_login_login);
        tv_login_forget = (TextView) findViewById(R.id.tv_login_forget);
        tv_login_register = (TextView) findViewById(R.id.tv_login_register);

        btn_login_login.setOnClickListener(this);
        tv_login_forget.setOnClickListener(this);
        tv_login_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login_login:
                // 校验手机号和密码的有效性
                phone = et_login_phone.getText().toString();
                password = et_login_password.getText().toString();

                Log.e("TAG", phone);
                Log.e("TAG", password);

                if("".equals(phone)) {
                    Toast.makeText(this, "请输入有效的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if("".equals(password)) {
                    Toast.makeText(this, "请输入有效的密码", Toast.LENGTH_SHORT).show();
                    return;
                }
                // 登录验证
                actionLogin();
                break;
            case R.id.tv_login_forget:
                Toast.makeText(this, "开发小哥正在努力中...", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_login_register:
//                Toast.makeText(this, "开发小哥正在努力中...", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, RegisterActivity.class));
                break;
        }
    }

    public void actionLogin() {
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
                .url(Configs.SERVER_IP+"/user/login")
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
                            // 登录成功
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    MyApplication.setUserId(phone);
                                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                    finish();
                                    MyApplication.setLoginStatus(true);
                                }
                            });
                        } else {
                            final String msg = json.getString("msg");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
