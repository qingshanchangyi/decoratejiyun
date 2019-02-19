package com.jiyun.jiyunlogin;

import com.jiyun.common.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by SCH on 2018/12/25 0025.
 */
@RestController
public class MiniUserController {
    @Resource(name = "MiniUserService")
    private MiniUserService miniUserService;

    @RequestMapping(value = "/miniProgram/user/code", method = RequestMethod.POST)
   public Result getByWxCode(TokenJson tokenJson) throws Exception {
        System.out.println(tokenJson);
        return miniUserService.getByWxCode(tokenJson);
   }

}
