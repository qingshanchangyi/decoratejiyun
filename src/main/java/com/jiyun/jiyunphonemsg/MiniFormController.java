package com.jiyun.jiyunphonemsg;

import com.alibaba.fastjson.JSONObject;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.jiyun.common.Result;
import com.jiyun.jiyunlogin.MiniUserService;
import com.jiyun.jiyunlogin.TokenJson;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.jiyun.jiyunphonemsg.Phonemsg.*;

/**
 * Created by SCH on 2018/12/25 0025.
 */
@RestController
public class MiniFormController {

    @RequestMapping(value = "/miniProgram/formsubmit", method = RequestMethod.POST)
   public Result getByWxCode(Fommsg fommsg) throws Exception {
        System.out.println(fommsg);
        System.out.println(fommsg.getRecord());
        JSONObject jsStr = JSONObject.parseObject(fommsg.getRecord());
        //System.out.println(jsStr.getString("I6ImUl_6"));
        String username = jsStr.getString("I6ImUl_6");
        String phone = jsStr.getString("PyOq3j_6");
        String type = jsStr.getString("jafMUx_6");
        String duoxuan = jsStr.getString("CUEE6e_6");
        String date = jsStr.getString("IRXIMO_6");
        String msg = jsStr.getString("B7v8Dd_6");
        String[] params = {username,phone,type,date};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
        SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
        SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
                templateId, params, smsSign, "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
        System.out.println(result);
        return null;
   }

}
