package cn.goldlone.car.model;

/**
 * 返回数据格式
 * @author Created by CN on 2018/6/8 17:24 .
 */
public class Result<T> {
    // 返回码
    private int code;
    // 返回消息
    private String msg;
    // 返回数据
    private T data;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
