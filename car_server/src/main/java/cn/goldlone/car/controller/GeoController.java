package cn.goldlone.car.controller;

import cn.goldlone.car.model.GeoInfo;
import cn.goldlone.car.model.Result;
import cn.goldlone.car.service.GeoService;
import cn.goldlone.car.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * @author Created by CN on 2018/6/25 16:42 .
 */
@RestController
public class GeoController extends BaseController {

    private int times = 0;

    @Autowired
    private GeoService gs;

    /**
     * 接收位置信息
     * @param userId 用户ID
     * @param latitude 纬度
     * @param longitude 经度
     * @param address 地址
     * @param time 时间
     */
    @PostMapping("/geo/receive")
    public void receiveGeoInfo(String userId,
                                 String latitude,
                                 String longitude,
                                 String address,
                                 String time) {

        GeoInfo info = new GeoInfo(userId, Double.parseDouble(latitude), Double.parseDouble(longitude), address, time);
        gs.receiveGeoInfo(info);
    }

    /**
     * 获取该汽车最近的位置
     * @param userId 用户ID
     * @return
     */
    @GetMapping("/geo/location/recently")
    public Result getRecentlyLocation(String userId) {
        times++;
        System.out.println(times);
        System.out.println("用户ID："+userId);
//        if(times%2==0)
//            return ResultUtils.success(new GeoInfo("18435187057", 37.8002, 112.588, "山西省太原市小店区书海路92号靠近山西大学", "1529925931061"), "成功");
//        return ResultUtils.success(new GeoInfo("18435187057", 37.8102, 112.598, "山西省太原市小店区书海路92号靠近山西大学", "1529925931061"), "成功");
        GeoInfo info = gs.getRecentlyLocation(userId);
        if(info == null) {
            return ResultUtils.error(ResultUtils.RESULT_EMPTY, "查询结果为空");
        } else {
            return ResultUtils.success(info, "获取成功");
        }
    }

}
