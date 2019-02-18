package com.jiyun.common;

/**
 * 异常错误描述
 */
public enum ExceptionEnum {

    UNKNOW_ERROR(-1,"系统错误，请联系管理员"),
    USER_NOT_FIND(101,"用户不存在"),
    FORBIDDEN_Code(403,"禁止访问"),
    PHONE_CODE_ERROR(402,"验证码错误"),
    PASSWORD_ERROR(405,"密码错误"),
    RE_PAS_NOT_SAME(406,"两次密码输入不一样"),
    UP_LOAD_FAIL(407,"上传图片失败，图片路径错误"),
    ERROR_TRY_AGAIN(350,"请在尝试,本次操作不扣除任何费用"),
    PARAMS_EXIST_NULL(30000,"字段存在空值"),
    TOKEN_DATA_VALIDA_ERROR(30001,"token非法"),
    ILLEGAL_Data(40000,"非法数据"),
    ERROR_CAUSE_BY_WECHATE(50000,"微信支付服务错误"),
    Error_CAUSE_BY_ALIPAY(60000,"支付宝服务错误");

    private Integer code;

    private String msg;

     ExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
