package cn.goldlone.car.mapper;

import cn.goldlone.car.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Created by CN on 2018/6/28 23:24 .
 */
@Mapper
public interface UserMapper {

    public int addUser(User user);

    public User selectUser(String phone);

}
