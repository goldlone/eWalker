package cn.goldlone.car.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.amap.api.navi.AmapNaviPage;
import com.amap.api.navi.AmapNaviParams;
import com.amap.api.navi.INaviInfoCallback;
import com.amap.api.navi.model.AMapNaviLocation;

import cn.goldlone.car.R;
import cn.goldlone.car.view.BaseActivity;

/**
 * @author : Created by CN on 2018/6/23 16:51
 */
public class NavActivity extends BaseActivity implements INaviInfoCallback {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        AmapNaviPage.getInstance().showRouteActivity(this, new AmapNaviParams(null), NavActivity.this);
    }

    /**
     * 导航初始化失败时的回调函数
     */
    @Override
    public void onInitNaviFailure() {

    }

    /**
     * 导航播报信息回调函数。
     * @param s 语音播报文字
     */
    @Override
    public void onGetNavigationText(String s) {

    }

    /**
     * 当GPS位置有更新时的回调函数。
     * @param aMapNaviLocation
     */
    @Override
    public void onLocationChange(AMapNaviLocation aMapNaviLocation) {

    }

    @Override
    public void onArriveDestination(boolean b) {

    }

    @Override
    public void onStartNavi(int i) {

    }

    @Override
    public void onCalculateRouteSuccess(int[] ints) {

    }

    @Override
    public void onCalculateRouteFailure(int i) {

    }

    @Override
    public void onStopSpeaking() {

    }

    @Override
    public void onReCalculateRoute(int i) {

    }

    @Override
    public void onExitPage(int i) {

    }

    @Override
    public void onStrategyChanged(int i) {

    }

    @Override
    public View getCustomNaviBottomView() {
        return null;
    }

    @Override
    public View getCustomNaviView() {
        return null;
    }

    @Override
    public void onArrivedWayPoint(int i) {

    }
}
