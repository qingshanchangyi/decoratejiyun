package com.jiyun.jiyunlogin;

import lombok.Data;

/**
 * Created by SCH on 2018/12/25 0025.
 *
 * 微信用户登陆凭证
 */
@Data
public class TokenJson {
    private String access_token;
    private String refresh_token;
    private int expires_in;
    private String openid;
    private String uid;
    private String code;
    private String sessionKey;
    private String encryptedData;
    private String iv;
    private Long buserid;//所属B端用户Id
}
