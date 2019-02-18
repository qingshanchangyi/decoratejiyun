package com.jiyun.common;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * Created by SCH on 2018/12/25 0025.
 */
@Component
//@ConfigurationProperties(prefix = "wechat")
@PropertySource("application.properties")
@Data
public class Constant {
    @Value("${wechat.appId}")
    private String WECHAT_APPID;  //  微信公众平台appid

    @Value("${wechat.appsecretId}")
    private String WX_WEB_SECRET;  // 微信公众平台secretid

    @Value("${wechat.tokentime}")
    private String TOKEN_EXPIRED_TIME;  // 微信登陆凭证有效时长

    @Value("${wechat.filepath}")
    private String FILE_PATH;  // 识别文件储存路径

}
