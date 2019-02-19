package com.jiyun.common;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jiyun.jiyunlogin.TokenJson;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.codehaus.xfire.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.InvalidParameterSpecException;
import java.util.Arrays;
@Component
public class WeChatMethod {

   @Autowired
   private Constant constant;

    private static final String BASE_PATH = "https://api.weixin.qq.com/sns";
   /* public static void main(String[] args) throws Exception {

        System.out.println(getResultByWxCode("021MvTcE1DMm070ql2dE1szVcE1MvTcO"));
    }*/
    /**
     * 小程序 code  获取 openid和session_key
     *
     * @param code
     * @return
     */
    /*根据临时code获取openId和sessionId*/
    public TokenJson getResultByWxCode(String code) throws Exception {
        System.out.println(constant.getWECHAT_APPID()+constant.getWX_WEB_SECRET()+"appid和secret==");
        String getWeChatUserUrl = BASE_PATH + "/jscode2session?appid=" + constant.getWECHAT_APPID() + "&secret=" + constant.getWX_WEB_SECRET() + "&js_code=" + code + "&grant_type=authorization_code";
        JSONObject tokenJsonObject = HttpRequestService.get(getWeChatUserUrl);
        System.out.println(tokenJsonObject+"---->tokenJsonObject");
        return formatJSONObjectByWxTokenJson(tokenJsonObject);
    }

  /*根据openid和sessionId获取微信用户信息*/
    public static WechatUser getWeChatUserByData(String openid, String sessionKey, String encryptedData, String iv) throws Exception {
        JSONObject jsonObject = getUserInfo(encryptedData, sessionKey, iv);
        WechatUser weUser = formatJSONObjectByWx(jsonObject);
        weUser.setOpenid(openid);
        return weUser;
    }

    private static WechatUser formatJSONObjectByWx(JSONObject weChatUserJSONObject) {
        String nickname = weChatUserJSONObject.getString("nickName");
        int gender = weChatUserJSONObject.getInteger("gender");
        String province = weChatUserJSONObject.getString("province");
        String city = weChatUserJSONObject.getString("city");
        String country = weChatUserJSONObject.getString("country");
        String headimgurl = weChatUserJSONObject.getString("avatarUrl");
        String unionid = weChatUserJSONObject.getString("unionId");
        WechatUser weChatUser = new WechatUser();
        weChatUser.setNickname(nickname);
        weChatUser.setSex(gender);
        weChatUser.setProvince(province);
        weChatUser.setCity(city);
        weChatUser.setCountry(country);
        weChatUser.setHeadImgUrl(headimgurl);
        weChatUser.setUnionid(unionid);
        return weChatUser;
    }




    private static TokenJson formatJSONObjectByWxTokenJson(JSONObject tokenJsonObject) {
        String sessionKey = tokenJsonObject.getString("session_key");
        String openId = tokenJsonObject.getString("openid");
        String uuid = tokenJsonObject.getString("unionid");

        TokenJson tokenJson = new TokenJson();
        tokenJson.setOpenid(openId);
        tokenJson.setSessionKey(sessionKey);
        tokenJson.setUid(uuid);
        return tokenJson;
    }
    









    /**
     * 解密用户敏感数据获取用户信息
     *
     * @param sessionKey    数据进行加密签名的密钥
     * @param encryptedData 包括敏感数据在内的完整用户信息的加密数据
     * @param iv            加密算法的初始向量
     * @return
     * @author zhy
     */
    private static JSONObject getUserInfo(String encryptedData, String sessionKey, String iv) {
        // 被加密的数据  
        byte[] dataByte = Base64.decode(encryptedData);
        // 加密秘钥  
        byte[] keyByte = Base64.decode(sessionKey);
        // 偏移量  
        byte[] ivByte = Base64.decode(iv);
        try {
            // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
            int base = 16;
            if (keyByte.length % base != 0) {
                int groups = keyByte.length / base + (keyByte.length % base != 0 ? 1 : 0);
                byte[] temp = new byte[groups * base];
                Arrays.fill(temp, (byte) 0);
                System.arraycopy(keyByte, 0, temp, 0, keyByte.length);
                keyByte = temp;
            }
            // 初始化  
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding", "BC");
            SecretKeySpec spec = new SecretKeySpec(keyByte, "AES");
            AlgorithmParameters parameters = AlgorithmParameters.getInstance("AES");
            parameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, spec, parameters);// 初始化  
            byte[] resultByte = cipher.doFinal(dataByte);
            if (null != resultByte && resultByte.length > 0) {
                String result = new String(resultByte, "UTF-8");
                return JSON.parseObject(result);
            }
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchPaddingException e) {
            System.out.println(e.getMessage());
        } catch (InvalidParameterSpecException e) {
            System.out.println(e.getMessage());
        } catch (IllegalBlockSizeException e) {
            System.out.println(e.getMessage());
        } catch (BadPaddingException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedEncodingException e) {
            System.out.println(e.getMessage());
        } catch (InvalidKeyException e) {
            System.out.println(e.getMessage());
        } catch (InvalidAlgorithmParameterException e) {
            System.out.println(e.getMessage());
        } catch (NoSuchProviderException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }


}
