package com.ai.bdex.dataexchange.busi.order.controller;



import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.HtmlUtils;
import org.thymeleaf.util.StringUtils;

import com.ai.bdex.dataexchange.busi.gds.entity.GdsInfoVO;
import com.ai.bdex.dataexchange.util.ObjectCopyUtil;
import com.ai.bdex.dataexchange.util.StringUtil;
import com.ai.paas.util.ImageUtil;
import com.ai.paas.util.MongoFileUtil;
import com.ai.paas.utils.CollectionUtil;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import com.alibaba.dubbo.common.utils.CollectionUtils;
import com.alibaba.dubbo.rpc.service.GenericException;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/orderManage")
public class orderManageController {
    /**
     * 记录日志
     */
    private final Log logger = LogFactory.getLog(getClass());

    
    /**
     * 我的数据
     * @param model
     * @param gdsInfoVO
     * @return
     * @throws Exception
     */
    @RequestMapping("/myData")
    public String myData(Model model,GdsInfoVO gdsInfoVO) throws Exception{
        return "mydata";
    }
    
}
