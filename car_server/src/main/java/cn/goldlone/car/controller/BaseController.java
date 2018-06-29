package cn.goldlone.car.controller;

import cn.goldlone.car.model.Result;
import cn.goldlone.car.utils.ResultUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Created by CN on 2018/6/8 17:23 .
 */
@RestController
public class BaseController {

    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        return ResultUtils.error(ResultUtils.EXCEPTION, "【异常】"+e.getMessage());
    }

}
