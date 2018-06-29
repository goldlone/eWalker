package cn.goldlone.car;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Environment;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import cn.goldlone.car.model.HelpContact;
import cn.goldlone.car.utils.ACache;
import cn.goldlone.car.utils.Contact;

/**
 * @author : Created by CN on 2018/6/26 09:09
 */
public class MyApplication extends Application {

    private static ACache aCache;

    public static final String USER_ID = "userId";
    public static final String RECORD_TIMES = "recordTimes";
    public static final String INTERVAL_GEO = "intervalGeo";
    public static final String CONTACTS = "contacts";

    @Override
    public void onCreate() {
        super.onCreate();

        File rootPath = new File(Environment.getExternalStorageDirectory(), "eWalker");
        if(!rootPath.exists())
            rootPath.mkdir();
        File cache = new File(rootPath.getAbsolutePath()+"/cache");
        Configs.CACHE_FILE = cache.getAbsolutePath();
        aCache = ACache.get(new File(Configs.CACHE_FILE));

        initConfigs();
    }

    /**
     * 初始化个人配置
     */
    private void initConfigs() {
        // 用户ID
        Configs.userId = aCache.getAsString(USER_ID);
        if(Configs.userId == null)
            Configs.userId = "";
        // 录像时长
        Object recordTimes = aCache.getAsObject(RECORD_TIMES);
        if(recordTimes == null)
            Configs.recordTimes = 10;
        else
            Configs.recordTimes = (int)recordTimes;
        // 发送位置信息间隔
        Object intervalGeo = aCache.getAsObject(INTERVAL_GEO);
        if(intervalGeo == null)
            Configs.intervalGeo = 3;
        else
            Configs.intervalGeo = (int)intervalGeo;
        // 紧急联系人
        Configs.contacts = new HashSet<>();
        JSONArray jsonArray = aCache.getAsJSONArray(CONTACTS);
        if(jsonArray != null) {
            JSONObject json = null;
            HelpContact contact = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                try {
                    System.out.println("【"+i+"】"+jsonArray.get(i));
                    json = jsonArray.getJSONObject(i);
                    contact = new HelpContact(json.optString("name"), json.optString("phone"));
                    Configs.contacts.add(contact);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

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
        aCache.put(USER_ID, userId);
    }

    /**
     * 修改录像时长(秒)
     * @param recordTimes
     */
    public static void setRecordTimes(int recordTimes) {
        Configs.recordTimes = recordTimes;
        aCache.put(RECORD_TIMES, recordTimes);
    }

    /**
     * 间隔多长时间发送一次位置信息(秒)
     * @param intervalGeo
     */
    public static void setIntervalGeo(int intervalGeo) {
        Configs.intervalGeo = intervalGeo;
        aCache.put(INTERVAL_GEO, intervalGeo);
    }

    /**
     * 修改紧急联系人列表
     * @param contacts
     */
    public static void setContacts(Set<HelpContact> contacts) {
        Configs.contacts = contacts;
        JSONArray jsonArray = new JSONArray();
        JSONObject json = null;
        for(HelpContact c: contacts) {
            json = new JSONObject();
            try {
                json.put("name", c.getName());
                json.put("phone", c.getPhone());
                jsonArray.put(json);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        System.out.println("【JSON】"+jsonArray.toString());
        aCache.put(CONTACTS, jsonArray);
    }

    public static boolean getLoginStatus() {
        Object obj = aCache.getAsObject("loginStatus");
        if(obj == null)
            return false;
        return (Boolean) obj;
    }

    public static void setLoginStatus(boolean status) {
        aCache.put("loginStatus", status);
    }

}
