package cn.goldlone.car.mapper;

import cn.goldlone.car.model.ShortUrlMapping;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Created by CN on 2018/6/26 9:39 .
 */
@Mapper
public interface ShortUrlMapper {
    // 记录映射关系
    public int addMapping(ShortUrlMapping urlMapper);

    // 查询映射关系
    public ShortUrlMapping selectMapping(String shortUrl);


}
