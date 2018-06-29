package cn.goldlone.car.utils;

import cn.goldlone.car.model.Result;

/**
 * @author Created by CN on 2018/6/8 17:26 .
 */
public class ResultUtils {
    // 成功状态码
    public static final int SUCCESS = 1001;

    // 失败状态码
    // 出现异常
    public static final int EXCEPTION = 2000;
    // 无结果
    public static final int RESULT_EMPTY = 2001;
    // 无实时位置
    public static final int NOT_NOW_LOCATION = 2002;
    // 解析JSON失败
    public static final int JSON_ANALYZE_FAIL = 2003;
    // 操作失败
    public static final int ACTION_FAIL = 2004;
    // 用户已存在
    public static final int USER_EXISTS = 2005;

    public static Result success(Object data, String msg) {
        return new Result(SUCCESS, msg, data);
    }

    public static Result error(int code, String msg) {
        return new Result(code, msg, null);
    }


}
