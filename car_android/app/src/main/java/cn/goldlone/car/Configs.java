package cn.goldlone.car;

import android.content.SharedPreferences;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : Created by CN on 2018/6/25 16:15
 */
public class Configs {

    /** 用户信息 **/
    public static String userId;

    /** 基本配置 **/
    // 服务器IP
//    public static final String SERVER_IP = "http://192.168.1.104:8080/car/";
    public static final String SERVER_IP = "http://172.25.154.37:8080/car/";
//    public static final String SERVER_IP = "http://192.168.43.143:8080/car/";

    /** 求救设置 **/
    // 是否处于求救状态
    public static boolean isHelping = false;
    // 录像时长(秒)
    public static int recordTimes;
    // 间隔多长时间发送一次位置信息(秒)
    public static int intervalGeo;
    // 紧急联系人列表
    public static Set<String> contacts;


}
