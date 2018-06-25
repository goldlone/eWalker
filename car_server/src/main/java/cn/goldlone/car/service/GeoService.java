package cn.goldlone.car.service;

import cn.goldlone.car.mapper.GeoMapper;
import cn.goldlone.car.model.GeoInfo;
import cn.goldlone.car.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Created by CN on 2018/6/25 19:17 .
 */
@Service
public class GeoService {

    @Autowired
    private GeoMapper gm;

    /**
     *
     * @param info
     */
    public void receiveGeoInfo(GeoInfo info) {
        gm.addGeoInfo(info);
    }

    /**
     *
     * @param userId
     * @return
     */
    public GeoInfo getRecentlyLocation(String userId) {
        return gm.selectRecentlyLocation(userId);
    }
}
