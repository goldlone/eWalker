package cn.goldlone.car.view.fragment;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import cn.goldlone.car.R;
import cn.goldlone.car.manager.HeartLineChartManager;
import cn.goldlone.car.utils.SpeakUtils;

/**
 * @author : Created by CN on 2018/6/9 23:30
 */
public class HealthFragment extends Fragment {

    private View rootView;
    private Thread loop1;
    private TextView tv_health_heart;
    private TextView tv_health_pulse;
    private TextView tv_health_exam_result;
    private StringBuffer heathResultStrBuffer;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_health, container, false);
        return rootView;
    }

    private HeartLineChartManager dynamicLineChartManager1;
    private SpeakUtils speakUtils;

    @Override
    public void onStart() {
        super.onStart();
        speakUtils = new SpeakUtils(getContext());
        initView();

        LineChart mChart1 = (LineChart) rootView.findViewById(R.id.dynamic_chart1);
        dynamicLineChartManager1 = new HeartLineChartManager(mChart1, "脉搏波", Color.RED);
        dynamicLineChartManager1.setYAxis(1000, 0, 20);

        updatePulseData();
        speakUtils.speakText("正在收集数据，请耐心等待体检结果");

        // 播报健康报告
        TimerTask timerTask1 = new TimerTask() {
            @Override
            public void run() {
                // 您好，您6月09日19:02检测血压113/96mmHg：血压偏高，需警惕。请养成并坚持健康的生活方式，经常监测血压，以预防高血压的发生
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm");
                heathResultStrBuffer = new StringBuffer();
                heathResultStrBuffer.append("\u3000\u3000")
                        .append("您好，您于")
                        .append(sdf.format(new Date()))
                        .append("检测血压")
                        .append(tv_health_pulse.getText())
                        .append("mmHg，血压偏高，需警惕。请养成并坚持健康的生活方式，经常监测血压，以预防高血压的发生");
                speakUtils.speakText(heathResultStrBuffer.toString());
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_health_exam_result.setText(heathResultStrBuffer.toString());
                    }
                });
            }
        };
        // 更新健康信息数值
        TimerTask timerTask2 = new TimerTask() {
            @Override
            public void run() {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv_health_heart.setText("82");
                        tv_health_pulse.setText("113/96");
                    }
                });
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask1, 10*1000);
        timer.schedule(timerTask2, 5*1000);
    }

    public void initView() {
        tv_health_heart = (TextView) rootView.findViewById(R.id.tv_health_heart);
        tv_health_pulse = (TextView) rootView.findViewById(R.id.tv_health_pulse);
        tv_health_exam_result = (TextView) rootView.findViewById(R.id.tv_health_exam_result);
    }

    /**
     * 更新脉搏数据
     */
    public void updatePulseData() {
        // 死循环模拟数据
        loop1 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Integer> list = new ArrayList<>();
//                list.add();
//                final int[] data = {696, 625, 625, 626, 625, 625, 625, 545, 424, 315, 315, 231, 182, 166, 169, 180};
                final int[] data = {300, 350, 325, 340, 342, 800, 700, 300};
                while (true) {
                    for(int i=0; i<data.length; i++) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            Log.e("TAG", "线程被打断");
                            e.printStackTrace();
                        }
                        if (getActivity() == null) {
                            return;
                        }
                        try {
                            dynamicLineChartManager1.addEntry(data[i]);
//                            getActivity().runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    dynamicLineChartManager1.addEntry((int) (Math.random() * 100));
//                                    dynamicLineChartManager1.addEntry(data[i]);
//                                list.add((int) (Math.random() * 50) + 10);
//                                list.add((int) (Math.random() * 80) + 10);
//                                list.add((int) (Math.random() * 100));
//                                dynamicLineChartManager2.addEntry(list);
//                                list.clear();
//                                }
//                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });
        loop1.start();
    }

    @Override
    public void onResume() {
        super.onResume();
        if(loop1!=null && loop1.isInterrupted()) {
            loop1.notify();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(loop1!=null) {
            loop1.interrupt();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(loop1!=null) {
            loop1.interrupt();
            loop1 = null;
        }
    }

}
