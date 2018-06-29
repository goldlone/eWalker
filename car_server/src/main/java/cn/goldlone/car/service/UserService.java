package cn.goldlone.car.service;

import cn.goldlone.car.mapper.UserMapper;
import cn.goldlone.car.model.Result;
import cn.goldlone.car.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Created by CN on 2018/6/28 23:58 .
 */
@Service
public class UserService {

    @Autowired
    private UserMapper um;

    public User selectUser(String phone) {
        return um.selectUser(phone);
    }

    public int addUser(User user) {
        return um.addUser(user);
    }

}
