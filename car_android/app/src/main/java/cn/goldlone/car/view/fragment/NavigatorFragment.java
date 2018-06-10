package cn.goldlone.car.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.SupportMapFragment;
import com.amap.api.maps.TextureMapView;
import com.amap.api.maps.model.MyLocationStyle;

import cn.goldlone.car.R;


/**
 * @author : Created by CN on 2018/6/9 23:30
 */
public class NavigatorFragment extends SupportMapFragment {
    private View rootView;
    private TextureMapView mMapView;
    private AMap aMap = null;

//    private static NavigatorFragment fragment = null;
//    public static NavigatorFragment newInstance() {
//        if (fragment == null) {
//            synchronized (NavigatorFragment.class) {
//                if (fragment == null) {
//                    fragment = new NavigatorFragment();
//                }
//            }
//        }
//        return fragment;
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_navigator, container, false);
        mMapView = (TextureMapView) rootView.findViewById(R.id.map);
        mMapView.onCreate(savedInstanceState);
        // 初始化地图控制器对象
        if (aMap == null) {
            aMap = mMapView.getMap();
            initMap();
        } else {
            aMap.clear();
            aMap = mMapView.getMap();
            initMap();
        }
//        // 初始化地图控制器对象
//        if (aMap == null) {
//            aMap = mMapView.getMap();
//        } else {
//            if (rootView.getParent() != null) {
//                ((ViewGroup) rootView.getParent()).removeView(rootView);
//            }
//        }
        return rootView;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    private void initMap() {
        MyLocationStyle myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_MAP_ROTATE);
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        aMap.setMapType(AMap.MAP_TYPE_NAVI);
    }

    @Override
    public void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
            mMapView.onDestroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        try {
            mMapView.onPause();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
