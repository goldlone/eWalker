package cn.goldlone.car.service;

import cn.goldlone.car.mapper.ShortUrlMapper;
import cn.goldlone.car.model.ShortUrlMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Created by CN on 2018/6/26 9:49 .
 */
@Service
public class ShortUrlMappingService {

    @Autowired
    private ShortUrlMapper shortUrlMapper;

    /**
     * 记录映射关系
     * @param urlMapping
     * @return
     */
    public int addMapping(ShortUrlMapping urlMapping) {
        return shortUrlMapper.addMapping(urlMapping);
    }

    /**
     * 查询映射关系
     * @param shortUrl
     * @return
     */
    public ShortUrlMapping selectMapping(String shortUrl) {
        return shortUrlMapper.selectMapping(shortUrl);
    }

}
