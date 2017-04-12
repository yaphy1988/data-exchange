package com.ai.bdex.dataexchange.busi.demo.controller;

import com.ai.paas.util.CacheUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yafei
 * @since 2017/4/3
 */
@RestController
@RequestMapping(value = "/demo")
public class DemoRestController {
    @RequestMapping(value = "test",method = RequestMethod.GET,produces={"application/json"})
    public String getTest(){
        Object testValue = CacheUtil.getItem("ABC");
        return "test";
    }
}
