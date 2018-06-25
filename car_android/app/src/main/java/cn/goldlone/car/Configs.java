package cn.goldlone.car;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Created by CN on 2018/6/25 16:15
 */
public class Configs {

    /** 用户信息 **/
    public static String userId = "18435187057";

    /** 基本配置 **/
    // 服务器IP
    public static final String SERVER_IP = "http://192.168.1.104:8080/car/";
//    public static final String SERVER_IP = "http://192.168.43.143:8080/car/";

    /** 求救设置 **/
    // 是否处于求救状态
    public static boolean isHelping = false;
    // 录像时长(秒)
    public static long recordTimes = 10;
    // 间隔多长时间发送一次位置信息(秒)
    public static int intervalGeo = 3;
    // 紧急联系人列表
    public static List<String> contacts = new ArrayList<>();



}
