package com.ai.bdex.dataexchange.busi.gds;

import com.ai.bdex.dataexchange.tradecenter.dubbo.interfaces.gds.IGdsInfoRSV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yx on 2017/4/18.
 */
@Controller
@RequestMapping(value = "/goods")
public class GdsController {

    private static final Logger log = LoggerFactory.getLogger(GdsController.class);

    @Autowired
    private IGdsInfoRSV iGdsInfoRSV;

    @RequestMapping(value = "/pageInit")
    public String pageInit(){



        return "/gds/gdsInfo";
    }
}
