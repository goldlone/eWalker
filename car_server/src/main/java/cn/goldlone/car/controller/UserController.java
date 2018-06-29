package cn.goldlone.car.controller;

import cn.goldlone.car.model.Result;
import cn.goldlone.car.model.User;
import cn.goldlone.car.service.UserService;
import cn.goldlone.car.utils.ResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by CN on 2018/6/28 23:21 .
 */
@RestController
public class UserController extends BaseController {

    @Autowired
    private UserService us;

    @PostMapping("/user/login")
    public Result login(String phone, String password) {
        User user = us.selectUser(phone);
        if(user == null)
            return ResultUtils.error(ResultUtils.RESULT_EMPTY, "用户不存在");
        else if(user.getPassword().equals(password))
            return ResultUtils.success(null, "登录成功");
        else
            return ResultUtils.error(ResultUtils.ACTION_FAIL, "密码错误");
    }

    @PostMapping("/user/add")
    public Result addUser(String phone, String password) {
        User user = new User(phone, password);
        if(us.selectUser(phone)!=null)
            return ResultUtils.error(ResultUtils.USER_EXISTS, "用户已存在");
        if(us.addUser(user) > 0) {
            return ResultUtils.success(null, "注册成功");
        } else {
            return ResultUtils.error(ResultUtils.ACTION_FAIL, "注册失败");
        }
    }

}
