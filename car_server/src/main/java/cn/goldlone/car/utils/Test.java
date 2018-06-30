package cn.goldlone.car.utils;

import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Created by CN on 2018/6/25 17:34 .
 */
public class Test {
    public static void main(String... args) {
        System.out.println(post("http://192.168.1.106:8080/car/geo/receive"));
    }

    /**
     * 发送POST请求
     * @param url
     * @return
     */
    public static String post(String url) {
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
        FormBody formBody = new FormBody.Builder()
                .add("latitude", "37.79745")
                .add("longitude", "112.590737")
                .add("address", "山西省太原市小店区俊良路654号靠近山西大学管理科学与工程研究所")
                .add("time", "8888888888888888")
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        Call call = okHttpClient.newCall(request);

        try {
            Response response = call.execute();
            System.out.println(response.toString());
            if(response.code()==200) {
                try {
                    String result = response.body().string();
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
}
