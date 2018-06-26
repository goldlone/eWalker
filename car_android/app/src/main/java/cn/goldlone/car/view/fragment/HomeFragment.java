package cn.goldlone.car.view.fragment;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import cn.goldlone.car.R;
import cn.goldlone.car.view.activity.HomeActivity;
import cn.goldlone.car.view.activity.MineActivity;
import cn.goldlone.car.view.activity.PhoneActivity;
import cn.goldlone.car.view.activity.VoiceActivity;

/**
 * @author : Created by CN on 2018/6/9 23:30
 */
public class HomeFragment extends Fragment implements View.OnClickListener {
    private View rootView;

    private LinearLayout btn_home_voice;
    private LinearLayout btn_home_phone;
    private LinearLayout btn_home_help;
    private LinearLayout btn_home_person;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        return rootView;
    }

    @Override
    public void onStart() {
        initView();
        super.onStart();
    }

    private void initView() {
        btn_home_voice = (LinearLayout) rootView.findViewById(R.id.btn_home_voice);
        btn_home_phone = (LinearLayout) rootView.findViewById(R.id.btn_home_phone);
        btn_home_help = (LinearLayout) rootView.findViewById(R.id.btn_home_help);
        btn_home_person = (LinearLayout) rootView.findViewById(R.id.btn_home_person);
        btn_home_voice.setOnClickListener(this);
        btn_home_phone.setOnClickListener(this);
        btn_home_help.setOnClickListener(this);
        btn_home_person.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_home_voice:
//                Toast.makeText(getActivity(), "语音提示", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), VoiceActivity.class));
                break;
            case R.id.btn_home_phone:
//                Toast.makeText(getActivity(), "打电话", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), PhoneActivity.class));
                break;
            case R.id.btn_home_help:
//                Toast.makeText(getActivity(), "求救", Toast.LENGTH_SHORT).show();
                ((HomeActivity)getActivity()).startHelp();
                break;
            case R.id.btn_home_person:
//                Toast.makeText(getActivity(), "个人中心", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getContext(), MineActivity.class));
                break;
        }
    }
}
