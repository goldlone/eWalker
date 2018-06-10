package cn.goldlone.car.utils;

import okhttp3.*;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Created by CN on 2018/6/8 18:09 .
 */
public class HealthUtils {

    /**
     * 获取血压分析简报
     * @param username 用户id
     * @param highValue 高压
     * @param lowValue 低压
     * @param disease 病史
     * @return 简报内容
     */
    public static String analyzeBloodPressure(String username,
                                              int highValue,
                                              int lowValue,
                                              String disease) {
        String host = "http://dtbp.market.alicloudapi.com";
        String path = "/alicloudapi/dailyTest/bloodPressure";
        String method = "POST";
        String appcode = "77b8539a40304986ab6753ac4ce99787";

        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(disease==null)
            disease = "";

        // post方式提交的数据
        FormBody formBody = new FormBody.Builder()
                .add("id", ""+System.currentTimeMillis())
                .add("memberId", username)
                .add("testTime", sdf.format(new Date()))
                .add("highValue", ""+highValue)
                .add("lowValue", ""+lowValue)
                .add("disease", disease)
                .build();

        final Request request = new Request.Builder()
                .url(host+path)
                .post(formBody)
                .addHeader("Authorization", "APPCODE "+appcode)
                .build();

        // 创建 Call
        Call call = okHttpClient.newCall(request);
        try {
            Response response = call.execute();
            System.out.println(response.toString());
            if(response.code()==200) {
                try {
                    String result = response.body().string();
                    System.out.println(result);
                    return result;
                } catch (NullPointerException e) {
                    System.out.println("结果为null");
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }


    /**
     * 参考API示例实现
     * @param username
     * @param highValue
     * @param lowValue
     * @param disease
     * @return
     */
    public static String analyzeBloodPressure2(String username,
                                               int highValue,
                                               int lowValue,
                                               String disease) {
        String host = "http://dtbp.market.alicloudapi.com";
        String path = "/alicloudapi/dailyTest/bloodPressure";
        String method = "POST";
        String appcode = "77b8539a40304986ab6753ac4ce99787";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        //根据API的要求，定义相对应的Content-Type
        headers.put("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        Map<String, String> querys = new HashMap<String, String>();
        Map<String, String> bodys = new HashMap<String, String>();
        bodys.put("id", ""+System.currentTimeMillis());
        bodys.put("memberId", username);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bodys.put("testTime", sdf.format(new Date()));
        bodys.put("highValue", ""+highValue);
        bodys.put("lowValue", ""+lowValue);
        if(disease==null)
            bodys.put("disease", "");
        else
            bodys.put("disease", disease);


        try {
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            System.out.println(EntityUtils.toString(response.getEntity()));
            return EntityUtils.toString(response.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String... args) {
        analyzeBloodPressure("gold1", 113, 96, "");
    }

}
