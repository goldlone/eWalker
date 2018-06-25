package cn.goldlone.car.controller;

import cn.goldlone.car.model.GeoInfo;
import cn.goldlone.car.model.Result;
import cn.goldlone.car.service.GeoService;
import cn.goldlone.car.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Created by CN on 2018/6/25 16:42 .
 */
@RestController
public class GeoController extends BaseController {

    @Autowired
    private GeoService gs;

    @PostMapping("/geo/receive")
    public void receiveGeoInfo(String userId,
                                 String latitude,
                                 String longitude,
                                 String address,
                                 String time) {

        GeoInfo info = new GeoInfo(userId, Double.parseDouble(latitude), Double.parseDouble(longitude), address, time);
        gs.receiveGeoInfo(info);
    }

    @GetMapping("/geo/location/recently")
    public Result getRecentlyLocation() {
        return ResultUtils.success(new GeoInfo("18435187057", 37.8002, 112.588, "山西省太原市小店区书海路92号靠近山西大学", "1529925931061"), "成功");
    }

}
