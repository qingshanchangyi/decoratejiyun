package com.jiyun.common;

import io.swagger.annotations.ApiParam;
import lombok.Data;

import java.io.Serializable;


/**
 * Created by YUAN on 2016/9/3.
 */
@Data
public class WechatUser implements Serializable{


    private Long id;
    private Long buserid;//对应商管B端用户Id
    @ApiParam("普通用户的标识，对当前开发者帐号唯一")
    private String openid;
    private String nickname;
    @ApiParam("普通用户性别，1 为男性，2 为女性")
    private int sex;
    private String province;
    private String city;
    private String country;
    @ApiParam("用户微信头像")
    private String headImgUrl;
    /*@ApiParam("用户微信头像保存到本地后名称")
    private String headImgName*/;
    @ApiParam("用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的 unionid 是唯一的")
    private String unionid;


}
