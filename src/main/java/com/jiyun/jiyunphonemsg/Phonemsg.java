package com.jiyun.jiyunphonemsg;

/**
 * 腾讯云短信验证码参数
 */

public class Phonemsg {
  public static final int appid = 1400185124;
  public static final String appkey = "2ee06f49ad3dd476f9d8905ad2a424a3";
  //需要发送短信的手机号码
  public static final String[] phoneNumbers = {"18435998750"};
    // 短信模板ID，需要在短信应用中申请
  public static final int templateId = 280144;
    // 签名
  public static final String smsSign = "控江文创时记AR";
}
