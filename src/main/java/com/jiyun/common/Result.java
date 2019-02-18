package com.jiyun.common;

import java.time.LocalDateTime;

/**
 * Created by Huoo
 * On 2018/9/17 10:32
 *
 * 平台服务统一返回信息封装
 */
public class Result<T> {

    private Integer code;
    private String msg;
    private  T data;
    private String time;
    public Integer getCode() {
        return code;
    }

    public Result() {
        setTime(LocalDateTime.now().toString());
    }

    public Result setCode(Integer resultCode) {
        this.code = resultCode;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public Result setData(T data) {
        this.data = data;
        return this;
    }
    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
