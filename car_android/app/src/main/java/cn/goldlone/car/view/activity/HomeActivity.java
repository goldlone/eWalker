package cn.goldlone.car.view.activity;

import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.goldlone.car.R;
import cn.goldlone.car.view.BaseActivity;
import cn.goldlone.car.view.fragment.HealthFragment;
import cn.goldlone.car.view.fragment.HomeFragment;
import cn.goldlone.car.view.fragment.MusicFragment;
import cn.goldlone.car.view.fragment.NavigatorFragment;

public class HomeActivity extends BaseActivity implements View.OnClickListener {

    private HomeFragment homeFragment;
    private HealthFragment healthFragment;
    private NavigatorFragment navigatorFragment;
    private MusicFragment musicFragment;

    private LinearLayout nav_home;
    private LinearLayout nav_health;
    private LinearLayout nav_navigator;
    private LinearLayout nav_music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initFragment1();
    }

    private void initView() {
        nav_home = findViewById(R.id.nav_home);
        nav_health = findViewById(R.id.nav_health);
        nav_navigator = findViewById(R.id.nav_navigator);
        nav_music = findViewById(R.id.nav_music);
        nav_home.setOnClickListener(this);
        nav_health.setOnClickListener(this);
        nav_navigator.setOnClickListener(this);
        nav_music.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.nav_home:
                initFragment1();
                activeTitle(1);
                break;
            case R.id.nav_health:
                initFragment2();
                activeTitle(2);
                break;
            case R.id.nav_navigator:
                initFragment3();
                activeTitle(3);
                break;
            case R.id.nav_music:
                initFragment4();
                activeTitle(4);
                break;
        }
    }

    // 显示主页
    private void initFragment1() {
        //开启事务，fragment的控制是由事务来实现的
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

//        //第一种方式（add），初始化fragment并添加到事务中，如果为null就new一个
//        if(homeFragment == null){
//            homeFragment = new HomeFragment();
//            transaction.add(R.id.main_content, homeFragment);
//        }
//        //隐藏所有fragment
//        hideFragment(transaction);
//        // 显示需要显示的fragment
//        transaction.show(homeFragment);

        // 第二种方式(replace)，初始化fragment
        if(homeFragment == null) {
            homeFragment = new HomeFragment();
        }
        transaction.replace(R.id.main_content, homeFragment);

        // 提交事务
        transaction.commit();
    }
    // 显示体检
    private void initFragment2() {
        FragmentTransaction transaction = super.getSupportFragmentManager().beginTransaction();
        if(healthFragment == null){
            healthFragment = new HealthFragment();
        }
        transaction.replace(R.id.main_content, healthFragment);
        transaction.commit();
    }
    // 显示导航
    private void initFragment3() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(navigatorFragment == null) {
            navigatorFragment = new NavigatorFragment();
//            navigatorFragment = NavigatorFragment.newInstance();
        }
        transaction.replace(R.id.main_content, navigatorFragment);
        transaction.commit();
    }
    // 显示音乐
    private void initFragment4() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if(musicFragment == null){
            musicFragment = new MusicFragment();
        }
        transaction.replace(R.id.main_content, musicFragment);
        transaction.commit();
    }



    //隐藏所有的fragment
    private void hideFragment(FragmentTransaction transaction){
        if(homeFragment != null){
            transaction.hide(homeFragment);
        }
        if(healthFragment != null){
            transaction.hide(healthFragment);
        }
    }

    // 取消激活状态
    private void activeTitle(int index) {
        // 主页
        if(index == 1) {
            ImageView imageView = (ImageView) nav_home.getChildAt(0);
            imageView.setImageResource(R.mipmap.home_active);
            TextView textView = (TextView) nav_home.getChildAt(1);
            textView.setTextColor(getResources().getColor(R.color.titleActive));
        } else {
            ImageView imageView = (ImageView) nav_home.getChildAt(0);
            imageView.setImageResource(R.mipmap.home);
            TextView textView = (TextView) nav_home.getChildAt(1);
            textView.setTextColor(getResources().getColor(R.color.title));
        }
        // 体检
        if(index == 2) {
            ImageView imageView = (ImageView) nav_health.getChildAt(0);
            imageView.setImageResource(R.mipmap.health_active);
            TextView textView = (TextView) nav_health.getChildAt(1);
            textView.setTextColor(getResources().getColor(R.color.titleActive));
        } else {
            ImageView imageView = (ImageView) nav_health.getChildAt(0);
            imageView.setImageResource(R.mipmap.health);
            TextView textView = (TextView) nav_health.getChildAt(1);
            textView.setTextColor(getResources().getColor(R.color.title));
        }
        // 导航
        if(index == 3) {
            ImageView imageView = (ImageView) nav_navigator.getChildAt(0);
            imageView.setImageResource(R.mipmap.nav_active);
            TextView textView = (TextView) nav_navigator.getChildAt(1);
            textView.setTextColor(getResources().getColor(R.color.titleActive));
        } else {
            ImageView imageView = (ImageView) nav_navigator.getChildAt(0);
            imageView.setImageResource(R.mipmap.nav);
            TextView textView = (TextView) nav_navigator.getChildAt(1);
            textView.setTextColor(getResources().getColor(R.color.title));
        }
        // 音乐
        if(index == 4) {
            ImageView imageView = (ImageView) nav_music.getChildAt(0);
            imageView.setImageResource(R.mipmap.music_active);
            TextView textView = (TextView) nav_music.getChildAt(1);
            textView.setTextColor(getResources().getColor(R.color.titleActive));
        } else {
            ImageView imageView = (ImageView) nav_music.getChildAt(0);
            imageView.setImageResource(R.mipmap.music);
            TextView textView = (TextView) nav_music.getChildAt(1);
            textView.setTextColor(getResources().getColor(R.color.title));
        }
    }


}
