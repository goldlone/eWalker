package cn.goldlone.car.view.activity;

import android.Manifest;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseIntArray;
import android.view.KeyEvent;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import cn.goldlone.car.R;
import cn.goldlone.car.view.BaseActivity;
import cn.goldlone.car.view.fragment.HealthFragment;
import cn.goldlone.car.view.fragment.HomeFragment;
import cn.goldlone.car.view.fragment.MusicFragment;
import cn.goldlone.car.view.fragment.NavigatorFragment;

public class HomeActivity extends BaseActivity implements View.OnClickListener,SurfaceHolder.Callback {

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
        Accessibility();
        initView();
        initFragment1();
//        initFragment2();
//        initFragment3();
        activeTitle(1);

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


    // 隐藏所有的fragment
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


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_VOLUME_UP:
                if((System.currentTimeMillis() - firstClickUpVolume) < 500) {
                    Toast.makeText(this, "触发求救", Toast.LENGTH_SHORT).show();
                    startHideVideos();
                }
                firstClickUpVolume = System.currentTimeMillis();
                break;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * 启动隐秘录像，定时设置为10秒
     */
    public void startHideVideos() {
        startRecord();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                stopRecord();
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 10*1000);
    }

    // 第一次按键时间
    private long firstClickUpVolume = 0;
    // 隐秘录像
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private boolean isRecording = false;//标记是否已经在录制
    private MediaRecorder mRecorder;//音视频录制类
    private Camera mCamera = null;//相机
    private Camera.Size mSize = null;//相机的尺寸
    //    private int mCameraFacing = Camera.CameraInfo.CAMERA_FACING_BACK;//默认后置摄像头
    private int mCameraFacing = Camera.CameraInfo.CAMERA_FACING_FRONT;//默认前置摄像头
    private static final SparseIntArray orientations = new SparseIntArray();//手机旋转对应的调整角度
    static final int  REQUEST_CODE_ASK_CALL_PHONE=122;
    static {
        orientations.append(Surface.ROTATION_0, 90);
        orientations.append(Surface.ROTATION_90, 0);
        orientations.append(Surface.ROTATION_180, 270);
        orientations.append(Surface.ROTATION_270, 180);
    }

    private void setWindow() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);// 设置全屏
        // 设置竖屏显示
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        // 选择支持半透明模式,在有surfaceview的activity中使用。
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
    }

    private void initVideoViews() {
        mSurfaceView = (SurfaceView) findViewById(R.id.hideSurfaceView);
        SurfaceHolder holder = mSurfaceView.getHolder();// 取得holder
        holder.setFormat(PixelFormat.TRANSPARENT);
        holder.setKeepScreenOn(true);
        holder.addCallback(this); // holder加入回调接口
    }

    /**
     * 初始化相机
     */
    private void initCamera() {
        if (Camera.getNumberOfCameras() == 2) {
            mCamera = Camera.open(mCameraFacing);
        } else {
            mCamera = Camera.open();
        }

        CameraSizeComparator sizeComparator = new CameraSizeComparator();
        Camera.Parameters parameters = mCamera.getParameters();

        if (mSize == null) {
            List<Camera.Size> vSizeList = parameters.getSupportedPreviewSizes();
            Collections.sort(vSizeList, sizeComparator);

            for (int num = 0; num < vSizeList.size(); num++) {
                Camera.Size size = vSizeList.get(num);

                if (size.width >= 800 && size.height >= 480) {
                    this.mSize = size;
                    break;
                }
            }
            mSize = vSizeList.get(0);

            List<String> focusModesList = parameters.getSupportedFocusModes();

            //增加对聚焦模式的判断
            if (focusModesList.contains(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_CONTINUOUS_VIDEO);
            } else if (focusModesList.contains(Camera.Parameters.FOCUS_MODE_AUTO)) {
                parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            }
            mCamera.setParameters(parameters);
        }
        int rotation = getWindowManager().getDefaultDisplay().getRotation();
        int orientation = orientations.get(rotation);
        mCamera.setDisplayOrientation(orientation);
    }

    @Override
    public void onPause() {
        releaseCamera();
        super.onPause();
    }

    /**
     * 开始录制
     */
    private void startRecord() {
        initCamera();
        if (mRecorder == null) {
            mRecorder = new MediaRecorder(); // 创建MediaRecorder
        }
        if (mCamera != null) {
            mCamera.stopPreview();
            mCamera.unlock();
            mRecorder.setCamera(mCamera);
        }
        try {
            // 设置音频采集方式
            mRecorder.setAudioSource(MediaRecorder.AudioSource.CAMCORDER);
            //设置视频的采集方式
            mRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
            //设置文件的输出格式
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);//aac_adif， aac_adts， output_format_rtp_avp， output_format_mpeg2ts ，webm
            //设置audio的编码格式
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            //设置video的编码格式
            mRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);
            //设置录制的视频编码比特率
            mRecorder.setVideoEncodingBitRate(1024 * 1024);
            //设置录制的视频帧率,注意文档的说明:
            mRecorder.setVideoFrameRate(30);
            //设置要捕获的视频的宽度和高度
            mSurfaceHolder.setFixedSize(320, 240);//最高只能设置640x480
            mRecorder.setVideoSize(320, 240);//最高只能设置640x480
            //设置记录会话的最大持续时间（毫秒）
            mRecorder.setMaxDuration(60 * 1000);
            mRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
            String path = getExternalCacheDir().getPath();
            if (path != null) {
                File dir = new File(path + "/videos");
                if (!dir.exists()) {
                    dir.mkdir();
                }
                path = dir + "/" + System.currentTimeMillis() + ".mp4";
                //设置输出文件的路径
                mRecorder.setOutputFile(path);
                //准备录制
                mRecorder.prepare();
                //开始录制
                mRecorder.start();
                isRecording = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 停止录制
     */
    private void stopRecord() {
        Log.i("隐秘录像", "停止录像");
        try {
            if(mRecorder!=null) {
                //停止录制
                mRecorder.stop();
                //重置
                mRecorder.reset();
            }
            releaseCamera();
        } catch (Exception e) {
            e.printStackTrace();
        }
        isRecording = false;
    }

    /**
     * 释放MediaRecorder
     */
    private void releaseMediaRecorder() {
        if (mRecorder != null) {
            mRecorder.release();
            mRecorder = null;
        }
    }

    /**
     * 释放相机资源
     */
    private void releaseCamera() {
        try {
            if (mCamera != null) {
                mCamera.stopPreview();
                mCamera.setPreviewCallback(null);
                mCamera.unlock();
                mCamera.release();
            }
        } catch (RuntimeException e) {
            e.printStackTrace();
        } finally {
            mCamera = null;
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
        Log.e("TAG", "surfaceChanged.....");
        // 将holder，这个holder为开始在onCreate里面取得的holder，将它赋给mSurfaceHolder
        mSurfaceHolder = holder;
        if (mCamera == null) {
            return;
        }
        try {
            //设置显示
            mCamera.setPreviewDisplay(holder);
            mCamera.startPreview();
        } catch (Exception e) {
            e.printStackTrace();
            releaseCamera();
            finish();
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        Log.e("TAG", "surfaceCreated.....");
        // 将holder，这个holder为开始在onCreate里面取得的holder，将它赋给mSurfaceHolder
        mSurfaceHolder = holder;
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.e("TAG", "surfaceDestroyed.....");
        // surfaceDestroyed的时候同时对象设置为null
        if (isRecording && mCamera != null) {
            mCamera.lock();
        }
        mSurfaceView = null;
        mSurfaceHolder = null;
        releaseMediaRecorder();
        releaseCamera();
    }

    private class CameraSizeComparator implements Comparator<Camera.Size> {
        public int compare(Camera.Size lhs, Camera.Size rhs) {
            if (lhs.width == rhs.width) {
                return 0;
            } else if (lhs.width > rhs.width) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    /**
     * 对于6.0以后的机器动态权限申请
     */
    public void Accessibility() {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            int checkCallPhonePermission2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int checkCallPhonePermission3 = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
            if (checkCallPhonePermission != PackageManager.PERMISSION_GRANTED &&checkCallPhonePermission2 != PackageManager.PERMISSION_GRANTED && checkCallPhonePermission3 != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO}, REQUEST_CODE_ASK_CALL_PHONE);
                return;
            } else {
                initVideoViews();
            }
        } else {
            initVideoViews();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_CODE_ASK_CALL_PHONE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    // Permission Granted
                    initVideoViews();

                } else {
                    // Permission Denied
                    Toast.makeText(this, "CALL_PHONE Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

}
