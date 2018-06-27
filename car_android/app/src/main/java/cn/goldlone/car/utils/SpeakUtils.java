package cn.goldlone.car.utils;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;

/**
 * @author : Created by CN on 2018/6/27 13:37
 */
public class SpeakUtils {

    private final String TAG = "语音工具类";
    private Context context;

    public SpeakUtils(Context context) {
        this.context = context;
        initSpeech();
    }


    /**
     * 初始化语音引擎
     */
    private void initSpeech() {
        // 请勿在 “ =”与 appid 之间添加任务空字符或者转义符
        SpeechUtility.createUtility( this.context, SpeechConstant.APPID + "=5b322ecd" );
    }


    /**
     * 语音播报
     * @param text
     */
    public void speakText(String text) {
        // 1. 创建 SpeechSynthesizer 对象 , 第二个参数： 本地合成时传 InitListener
        SpeechSynthesizer mTts = SpeechSynthesizer.createSynthesizer( this.context, null);
        // 2.合成参数设置，详见《 MSC Reference Manual》 SpeechSynthesizer 类
        // 设置发音人（更多在线发音人，用户可参见 附录 13.2
        mTts.setParameter(SpeechConstant. VOICE_NAME, "xiaoqi" ); // 设置发音人
        mTts.setParameter(SpeechConstant. SPEED, "50" );// 设置语速
        mTts.setParameter(SpeechConstant. VOLUME, "80");// 设置音量，范围 0~100
        mTts.setParameter(SpeechConstant. ENGINE_TYPE, SpeechConstant.TYPE_CLOUD); //设置云端
        // 设置合成音频保存位置（可自定义保存位置），保存在 “./sdcard/iflytek.pcm”
        // 保存在 SD 卡需要在 AndroidManifest.xml 添加写 SD 卡权限
        // 仅支持保存为 pcm 和 wav 格式， 如果不需要保存合成音频，注释该行代码
//        mTts.setParameter(SpeechConstant. TTS_AUDIO_PATH, "./sdcard/iflytek.pcm" );
        // 3.开始合成
        mTts.startSpeaking(text, new MySynthesizerListener()) ;
    }


    class MySynthesizerListener implements SynthesizerListener {

        @Override
        public void onSpeakBegin() {
            Log.i(TAG, " 开始播放 ");
        }

        @Override
        public void onSpeakPaused() {
            Log.i(TAG, " 暂停播放 ");
        }

        @Override
        public void onSpeakResumed() {
            Log.i(TAG, " 继续播放 ");
//            showTip(" 继续播放 ");
        }

        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos ,
                                     String info) {
            // 合成进度
        }

        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {
            // 播放进度
        }

        @Override
        public void onCompleted(SpeechError error) {
            if (error == null) {
                Log.i(TAG, " 播放完成 ");
            } else if (error != null ) {
                Toast.makeText(context, error.getPlainDescription( true), Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onEvent(int eventType, int arg1 , int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话 id，当业务出错时将会话 id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话 id为null
            //if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //     String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //     Log.d(TAG, "session id =" + sid);
            //}
        }
    }
}
