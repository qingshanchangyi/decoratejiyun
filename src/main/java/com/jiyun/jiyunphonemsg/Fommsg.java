package com.jiyun.jiyunphonemsg;

import lombok.Data;

import java.lang.reflect.Array;

/**
 * Created by SCH on 2018/12/25 0025.
 *
 * 微信用户登陆凭证
 */
@Data
public class Fommsg {
    private String wxappid;
    private String preid;
    private String openid;
    private String record;
}
