package com.myblog.test;

import com.alibaba.fastjson.JSONObject;
import com.myblog.common.AshOneAnnotation;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lmd
 * @version 1.0.0
 * @date 2020/12/5
 */
@RestController
@RequestMapping(value = "/ashone")
public class TestController {
    @AshOneAnnotation
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public JSONObject getThing(@RequestBody JSONObject jsonObject) {
        return JSONObject.parseObject("{\"message\":\"SUCCESS\",\"code\":200}");
    }
}
