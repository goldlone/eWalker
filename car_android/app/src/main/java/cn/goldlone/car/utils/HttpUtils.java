package cn.goldlone.car.utils;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.goldlone.car.Configs;
import cn.goldlone.car.model.GeoInfo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * @author : Created by CN on 2018/6/25 17:01
 */
public class HttpUtils {

    /**
     * 发送POST请求
     * @param url
     * @param data
     * @return
     */
    public static String postJSON(String url, String data) {
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
        RequestBody requestBody = FormBody.create(MediaType.parse("application/json; charset=utf-8"), data);

        final Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
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
     * 发送位置信息
     * @param info
     * @return
     */
    public static void postGeoInfo(GeoInfo info) {
        String url = Configs.serverIP+"geo/receive";
        OkHttpClient okHttpClient  = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.SECONDS)
                .writeTimeout(3, TimeUnit.SECONDS)
                .readTimeout(3, TimeUnit.SECONDS)
                .build();
        FormBody formBody = new FormBody.Builder()
                .add("userId", info.getUserId())
                .add("latitude", ""+info.getLatitude())
                .add("longitude", ""+info.getLongitude())
                .add("address", info.getAddress())
                .add("time", ""+info.getTime())
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e("HTTP:发送位置信息", "发送失败");
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if(response.code()==200) {
                    Log.i("HTTP:发送位置信息", response.body().toString());
                }
            }
        });
    }


}
