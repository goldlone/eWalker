package cn.goldlone.car.mapper;

import cn.goldlone.car.model.GeoInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Created by CN on 2018/6/25 19:05 .
 */
@Mapper
public interface GeoMapper {
    // 存储位置信息
    public int addGeoInfo(GeoInfo info);
    // 查询该汽车最近的位置
    public GeoInfo selectRecentlyLocation(String userId);
}
