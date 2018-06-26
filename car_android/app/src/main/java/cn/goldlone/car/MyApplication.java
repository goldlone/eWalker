package cn.goldlone.car;

import android.app.Application;
import android.content.SharedPreferences;
import android.util.Log;

import java.util.HashSet;
import java.util.Set;

/**
 * @author : Created by CN on 2018/6/26 09:09
 */
public class MyApplication extends Application {

    private static SharedPreferences sharedPreferences = null;
    private static SharedPreferences.Editor editor = null;

    public static final String USER_ID = "userId";
    public static final String RECORD_TIMES = "recordTimes";
    public static final String INTERVAL_GEO = "intervalGeo";
    public static final String CONTACTS = "contacts";

    @Override
    public void onCreate() {
        super.onCreate();

        sharedPreferences = getSharedPreferences("eWalker", MODE_PRIVATE);
        initConfigs();

        // 初始化紧急联系人
        String contact1 = "18435187057";
//        String contact2 = "18435187057";
        Configs.contacts.add(contact1);
        setContacts(Configs.contacts);
    }

    /**
     * 初始化个人配置
     */
    private void initConfigs() {
        Configs.userId = sharedPreferences.getString(USER_ID, "18435187057");
        Configs.recordTimes = sharedPreferences.getInt(RECORD_TIMES, 10);
        Configs.intervalGeo = sharedPreferences.getInt(INTERVAL_GEO, 3);
        Configs.contacts = sharedPreferences.getStringSet(CONTACTS, new HashSet<String>());

        Log.e(USER_ID, Configs.userId);
        Log.e(RECORD_TIMES, ""+Configs.recordTimes);
        Log.e(INTERVAL_GEO, ""+Configs.intervalGeo);
    }

    /**
     * 修改用户ID
     * @param userId
     */
    public static void setUserId(String userId) {
        Configs.userId = userId;
        editor = sharedPreferences.edit();
        editor.putString(USER_ID, userId);
        editor.apply();
        editor.commit();
    }

    /**
     * 修改录像时长(秒)
     * @param recordTimes
     */
    public static void setRecordTimes(int recordTimes) {
        Configs.recordTimes = recordTimes;
        editor = sharedPreferences.edit();
        editor.putInt(RECORD_TIMES, recordTimes);
        editor.apply();
        editor.commit();
    }

    /**
     * 间隔多长时间发送一次位置信息(秒)
     * @param intervalGeo
     */
    public static void setIntervalGeo(int intervalGeo) {
        Configs.intervalGeo = intervalGeo;
        editor = sharedPreferences.edit();
        editor.putInt(INTERVAL_GEO, intervalGeo);
        editor.apply();
        editor.commit();
    }

    /**
     * 修改紧急联系人列表
     * @param contacts
     */
    public static void setContacts(Set<String> contacts) {
        Configs.contacts = null;
        editor = sharedPreferences.edit();
        editor.putStringSet(CONTACTS, contacts);
        editor.apply();
        editor.commit();
    }

}
