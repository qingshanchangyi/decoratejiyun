package com.jiyun.jiyunlogin;

import com.alibaba.fastjson.JSONObject;
import com.jiyun.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2018/12/25 0025.
 */
@Service("MiniUserService")
public class MiniUserService{
    @Autowired
    private WeChatMethod weChatMethod;

    public Result getByWxCode(TokenJson tokenJson) throws Exception {
        JSONObject jsonObject = new JSONObject();
        String code = tokenJson.getCode();
        String encryptedData = tokenJson.getEncryptedData();
        String iv = tokenJson.getIv();
        if(isBlank(code)){
            return ResultGenerator.error(1001,"wxCode不能为空!");
        }
        TokenJson objcect = weChatMethod.getResultByWxCode(code);
        String openid = objcect.getOpenid();
        String sessionKey = objcect.getSessionKey();
        WechatUser weChatUser = WeChatMethod.getWeChatUserByData(openid, sessionKey, encryptedData, iv);
        System.out.println("weChatUser=="+weChatUser);
        weChatUser.setNickname(EmojiFilter.filterEmoji(weChatUser.getNickname()));
        weChatUser.setId(RandomNumberUtil.randomLong(8));
        weChatUser.setBuserid(tokenJson.getBuserid());


        WechatUser obj = new WechatUser();
        obj.setOpenid(openid);
        jsonObject.put("openId",openid);
      /*  WechatUser selectuser = wechatDao.selectWxuser(obj);  //先到数据库中查询是否存在用户。存在则返回用户一个token和openId
        if (selectuser==null){
            wechatDao.insertWxuser(weChatUser);//不存在则录入微信用户信息
            String tminstoreToken = TokensUtil.createToken(weChatUser.getId());
            redisService.insert(tminstoreToken,weChatUser.getId(),2L, TimeUnit.HOURS);
            jsonObject.put("openId",openid);
            jsonObject.put("wxuserId",weChatUser.getId());
            jsonObject.put("tminstoreToken",tminstoreToken);
        }else {
            // String tminstoreToken =RandomNumberUtil.generateToken();
            //redisService.insert(tminstoreToken,tminstoreToken,Integer.parseInt(constant.getTOKEN_EXPIRED_TIME()), TimeUnit.MINUTES);
            String tminstoreToken = TokensUtil.createToken(selectuser.getId());
            redisService.insert(tminstoreToken,selectuser.getId(),2L, TimeUnit.HOURS);
            jsonObject.put("openId",openid);
            jsonObject.put("wxuserId",wechatDao.selectWxuser(obj).getId());
            jsonObject.put("tminstoreToken",tminstoreToken);
        }
*/

        return ResultGenerator.genSuccessResult(jsonObject,"微信登陆成功");
    }


    /*判断字符串是否为空得方法*/
    public  boolean isBlank(String str) {
        if (null == str || "".equals(str)) {
            return true;
        }
        return false;
    }
}
