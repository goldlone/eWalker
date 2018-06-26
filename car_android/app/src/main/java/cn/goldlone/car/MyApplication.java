package cn.goldlone.car;

import android.app.Application;
import android.util.Log;

/**
 * @author : Created by CN on 2018/6/26 09:09
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化紧急联系人
        String contact1 = "18435187057";
//        String contact2 = "18435187057";
        Configs.contacts.add(contact1);

    }
}
