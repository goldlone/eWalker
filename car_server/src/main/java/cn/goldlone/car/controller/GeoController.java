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

    boolean times = false;

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
        System.out.println(userId+" "+latitude+" "+longitude+" "+time+" "+address);
        System.out.println("*********************\n");
        GeoInfo info = new GeoInfo(userId, Double.parseDouble(latitude), Double.parseDouble(longitude), address, String.valueOf(System.currentTimeMillis()));
        gs.receiveGeoInfo(info);
    }

    /**
         * 获取该汽车最近的位置
     * @param userId 用户ID
     * @return
     */
    @GetMapping("/geo/location/recently")
    public Result getRecentlyLocation(String userId) {
        System.out.println("用户ID："+userId);
        GeoInfo info = gs.getRecentlyLocation(userId);
        System.out.println(info);
        if(info == null) {
            return ResultUtils.error(ResultUtils.RESULT_EMPTY, "查询结果为空");
        } else {
            if(System.currentTimeMillis()-Long.parseLong(info.getTime()) < 20000) {
                return ResultUtils.success(info, "获取成功");
            } else
                return ResultUtils.error(ResultUtils.NOT_NOW_LOCATION, "未启动APP或未处于求救状态，无法获取实时位置");
        }
//        if(times) {
//            times = false;
//            return ResultUtils.success(new GeoInfo(userId, 37.8002, 112.588, "山西省太原市小店区书海路92号靠近山西大学", "1529925931061"), "");
//        } else {
//            times = true;
//            return ResultUtils.success(new GeoInfo(userId, 37.7974, 112.591, "山西省太原市小店区俊良路654号靠近山西大学管理科学与工程研究所", "1529980107496"), "");
//        }
    }

}
