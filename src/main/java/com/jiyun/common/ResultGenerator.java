package com.jiyun.common;

import com.jiyun.common.ExceptionEnum;
import com.jiyun.common.Result;
import com.jiyun.common.ResultCodeEnum;

/**
 * Created by Huoo
 * On 2018/9/17 10:34
 * 返回结果生成类
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "成功";
    private static final String DEFAULT_FAIL_MESSAGE = "失败";

    //成功
    public static Result genSuccessResult(String params) {
        return new Result()
                .setCode(ResultCodeEnum.SUCCESS.code)
                .setMsg(params+DEFAULT_SUCCESS_MESSAGE);
    }
    public static <T> Result<T> genSuccessResult(T data,String params) {
        return new Result()
                .setCode(ResultCodeEnum.SUCCESS.code)
                .setMsg(params+DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }
    public static <T> Result<T> genInfo(T data,String params) {
        return new Result()
                .setCode(ResultCodeEnum.SUCCESS.code)
                .setMsg(params)
                .setData(data);
    }
    public static <T> Result<T> genInfoAndData(T data,String params,Integer code) {
        return new Result()
                .setCode(code)
                .setMsg(params)
                .setData(data);
    }
    public static <T> Result<T> genPre(T data) {
        return new Result()
                .setCode(ResultCodeEnum.SUCCESS.code)
                .setMsg(DEFAULT_SUCCESS_MESSAGE)
                .setData(data);
    }
    public static <T> Result<T> getDataByFail(T data) {
        return new Result()
                .setCode(ResultCodeEnum.SUCCESS.code)
                .setData(data);
    }
    public static <T> Result<T> getMsgByFail(String msg) {
        return new Result()
                .setCode(ResultCodeEnum.SUCCESS.code)
                .setMsg(msg)
                .setData("");
    }
    public static Result genFailResult(String message) {
        return new Result()
                .setCode(ResultCodeEnum.FAIL.code)
                .setMsg(message);
    }

    public static Result genUnauthorizedResult() {
        return new Result()
                .setCode(ResultCodeEnum.UNAUTHORIZED.code)
                .setMsg("权限不足！");
    }

    /**
     * 返回异常信息，在已知的范围内
     * @param exceptionEnum
     * @return
     */
    public static Result error(ExceptionEnum exceptionEnum){
        Result result = new Result();
        result.setCode(exceptionEnum.getCode());
        result.setData("");
        result.setMsg(exceptionEnum.getMsg());
        return result;
    }
    /**
     * 自定义错误信息
     * @param code
     * @param msg
     * @return
     */
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(null);
        return result;
    }

    /**
     * 自定义错误信息
     * @param msg
     * @return
     */
    public static Result genFailMsg(String msg){
        return new Result()
                .setCode(ResultCodeEnum.NOT_FOUND.code)
                .setMsg(msg)
                .setData("");
    }
}
