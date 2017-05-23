package com.ai.bdex.dataexchange.busi.demo;

import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.ICheckCreditRSV;
import com.ai.bdex.dataexchange.common.util.CheckPhoneUtil;
import com.ai.bdex.dataexchange.usercenter.dubbo.dto.LoginInfoDTO;
import com.ai.paas.utils.InetTool;
import com.alibaba.boot.dubbo.annotation.DubboConsumer;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

import static org.apache.solr.client.solrj.impl.XMLResponseParser.log;

/**
 *  Created by chenjy on 2017/5/17.
 */
@Controller
@RequestMapping("/checkCredit")
public class CheckCreditController {

    private static  final  String IS_CM_PHONE = "cm";//中国移动
    private static  final  String IS_CU_PHONE = "cu";//中国联通
    private static  final  String IS_CT_PHONE = "ct";//中国电信

    @DubboConsumer(timeout = 30000)
    private ICheckCreditRSV iCheckCreditRSV;

    @RequestMapping(value="/pageInit")
    public String pageInit(Model model){
        return "/demo/checkCredit";
    }

    @RequestMapping(value="/check",method= RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> check(HttpServletRequest request,HttpServletResponse response, HttpSession session){
        Map<String,Object> rMap = new HashMap<String,Object>();
        LoginInfoDTO loginInfo = null;
        String staffId = null;
        String ip = InetTool.getClientAddr(request);

        String checkName = request.getParameter("checkName");
        String checkPSN = request.getParameter("checkPSN");
        String checkPhone = request.getParameter("checkPhone");
        if (StringUtils.isBlank(checkName)) {
            rMap.put("success", false);
            rMap.put("errorMsg", "姓名不能为空");
            return rMap;
        }
        if (StringUtils.isBlank(checkPSN)) {
            rMap.put("success", false);
            rMap.put("errorMsg", "身份证号不能为空");
            return rMap;
        }
        if (StringUtils.isBlank(checkPhone)) {
            rMap.put("success", false);
            rMap.put("errorMsg", "手机号码不能为空");
            return rMap;
        }
        //判断手机归属运营商
       String phoneFlag  = "";
        if(CheckPhoneUtil.isChinaMobilePhoneNum(checkPhone)){
            phoneFlag = IS_CM_PHONE;
        }else if (CheckPhoneUtil.isChinaUnicomPhoneNum(checkPhone)){
            phoneFlag = IS_CU_PHONE;
        } else if (CheckPhoneUtil.isChinaTelecomPhoneNum(checkPhone)){
            phoneFlag = IS_CT_PHONE;
        }else{
            rMap.put("success", false);
            rMap.put("errorMsg", "手机号码格式不正确");
            return rMap;
        }
        int point = 0;
        if(IS_CM_PHONE.equals(phoneFlag)){
            point = getChinaMobilePoint(checkName,checkPSN,checkPhone);
        }else if(IS_CU_PHONE.equals(phoneFlag)){
            point = this.getChinaUnicomPoint(checkName,checkPSN,checkPhone);
        }else if(IS_CT_PHONE.equals(phoneFlag)){
            point = this.getChinaTelecomPoint(checkName,checkPSN,checkPhone);
        }
        if (0==point){
            rMap.put("success", false);
            rMap.put("errorMsg", "手机号码实名认证不通过");
            return rMap;
        }


        try {
            point = point + this.getRiskistPoint(checkName,checkPSN,checkPhone);
            rMap.put("success", true);
            rMap.put("point", point);
        } catch (Exception e) {
            log.error("异常", e);
            rMap.put("success", false);
            rMap.put("errorMsg",e.getMessage());
        }
        return rMap;
    }


    private int getChinaMobilePoint(String checkName, String checkPSN, String checkPhone) {
        try {
           return iCheckCreditRSV.getChinaMobilePoint(checkName,checkPSN,checkPhone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  0;
    }

    private int getChinaUnicomPoint(String checkName, String checkPSN, String checkPhone) {
        try {
            return iCheckCreditRSV.getChinaUnicomPoint(checkName,checkPSN,checkPhone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  0;
    }

    private int getChinaTelecomPoint(String checkName, String checkPSN, String checkPhone) {
        try {
            return iCheckCreditRSV.getChinaTelecomPoint(checkName,checkPSN,checkPhone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  0;
    }

//    private int getBadInfoPoint(String checkName, String checkPSN, String checkPhone) {
//        try {
//            return iCheckCreditRSV.getBadInfoPoint(checkName,checkPSN);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return  0;
//    }
//    private int getBlacklistPoint(String checkName, String checkPSN, String checkPhone) {
//        try {
//            return iCheckCreditRSV.getBlacklistPoint("2","",checkPSN);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return  0;
//    }

    private int getRiskistPoint(String checkName, String checkPSN, String checkPhone) {
        try {
            return iCheckCreditRSV.getRiskistPoint(checkName,checkPSN,checkPhone);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  0;
    }
}
