package com.ai.bdex.dataexchange.apigateway.dubbo.impl;

import com.ai.bdex.dataexchange.apigateway.dubbo.interfaces.ICheckCreditRSV;
import com.ai.bdex.dataexchange.apigateway.util.HttpUtils;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chenjy on 2017/5/18.
 */
@Service("iCheckCreditRSV")
public class CheckCreditRSVImpl implements ICheckCreditRSV {

    private final String CM_KEY ="85c99ccd726354f2d28cdb17d98ccefd";
    private final String CM_URL ="http://api.chinadatapay.com/communication/personal/330";

    private final String CU_KEY = "96e7c544b971108c8bc6191b88d9cbb5";
    private final String CU_URL = "http://api.chinadatapay.com/communication/personal/331";

    private final String CT_KEY = "408bc8389e8ca7a5a5f4216d8cf15a5b";
    private final String CT_URL ="http://api.chinadatapay.com/communication/personal/1727";

    private final String BI_KEY = "9110d3116d549e1deed471f15c6be39f";
    private final String BI_URL = "http://api.chinadatapay.com/government/safety/1676";

    private final String BL_KEY = "f53edf51cbcb4a0bb322373dea506e28";
    private final String BL_URL = "http://api.chinadatapay.com/government/safety/1760";

    private final String RISKIST_KEY = "7288f4432844c9dd674f0cd8d097e215";
    private final String RISKIST_URL = "http://api.chinadatapay.com/government/safety/1790";



    /**
     * http://api.chinadatapay.com/communication/personal/330?key=申请的key值&NAME=姓名&PHONE=手机号&IDCARD=身份证号
     * @param checkName
     * @param checkPSN
     * @param checkPhone
     * @return
     */
    @Override
    public int getChinaMobilePoint(String checkName, String checkPSN, String checkPhone)throws Exception {
        Map<String, String> querys = new HashMap<String, String> ();
        Map<String, String> headers = new HashMap<String, String> ();
        querys.put("key",CM_KEY);
        querys.put("NAME",checkName);
        querys.put("PHONE",checkPhone);
        querys.put("IDCARD",checkPSN);
        String result = "";
        try {
            HttpResponse response = HttpUtils.doPost(CM_URL, "", "POST", headers, querys, "");
            result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
            return getCmPoint(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    private int getCmPoint(String result) {
        JSONObject object = JSONObject.parseObject(result);
        if("10000".equals(object.getString("code"))){
            if("T".equals(object.getJSONObject("data").getString("result"))){
                return 10;
            }
        }
        return 0;
    }

    /**
     * http://api.chinadatapay.com/communication/personal/331?key=申请的key值&NAME=姓名&PHONE=手机号&IDCARD=身份证号
     * @param checkName
     * @param checkPSN
     * @param checkPhone
     * @return
     * @throws Exception
     */
    @Override
    public int getChinaUnicomPoint(String checkName, String checkPSN, String checkPhone) throws Exception{
        Map<String, String> querys = new HashMap<String, String> ();
        Map<String, String> headers = new HashMap<String, String> ();
        querys.put("key",CU_KEY);
        querys.put("NAME",checkName);
        querys.put("PHONE",checkPhone);
        querys.put("IDCARD",checkPSN);
        String result = "";
        try {
            HttpResponse response = HttpUtils.doPost(CU_URL, "", "POST", headers, querys, "");
            result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
            return getCUPoint(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    private int getCUPoint(String result) {
        JSONObject object = JSONObject.parseObject(result);
        if("10000".equals(object.getString("code"))){
            if("00".equals(object.getJSONObject("data").getString("result"))){
                return 10;
            }
        }
        return 0;
    }

    /**
     * http://api.chinadatapay.com/communication/personal/1727?key=您申请的key&idCardNumber=身份证号码&mobile=手机号
     * @param checkName
     * @param checkPSN
     * @param checkPhone
     * @return
     * @throws Exception
     */
    @Override
    public int getChinaTelecomPoint(String checkName, String checkPSN, String checkPhone) throws Exception{
        Map<String, String> querys = new HashMap<String, String> ();
        Map<String, String> headers = new HashMap<String, String> ();
        querys.put("key",CT_KEY);
        querys.put("mobile",checkPhone);
        querys.put("idCardNumber",checkPSN);
        String result = "";
        try {
            HttpResponse response = HttpUtils.doPost(CT_URL, "", "POST", headers, querys, "");
            result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
            return getCTPoint(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    private int getCTPoint(String result) {
        JSONObject object = JSONObject.parseObject(result);
        if("10000".equals(object.getString("code"))){
            if("T".equals(object.getJSONObject("data").getString("result"))){
                return 10;
            }
        }
        return 0;
    }

    /**
     * 不良信息信用
     * http://api.chinadatapay.com/government/safety/1676?key=申请的key值&name=姓名&idCard=身份证号
     * @deprecated
     * @param checkName
     * @param checkPSN
     * @return
     * @throws Exception
     *
     */
    @Override
    public int getBadInfoPoint(String checkName, String checkPSN) throws Exception{
        Map<String, String> querys = new HashMap<String, String> ();
        Map<String, String> headers = new HashMap<String, String> ();
        querys.put("key",BI_KEY);
        querys.put("name",checkName);
        querys.put("idCard",checkPSN);
        String result = "";
        try {
            HttpResponse response = HttpUtils.doPost(BI_URL, "", "POST", headers, querys, "");
            result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
            return getBIPoint(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    private int getBIPoint(String result) {
        JSONObject object = JSONObject.parseObject(result);
        if("10001".equals(object.getString("code"))){
                return 30;
        }
        return 0;
    }
    private int getBLPoint(String result) {
        JSONObject object = JSONObject.parseObject(result);
        if("10001".equals(object.getString("code"))){
            return 30;
        }
        return 0;
    }

    private int getRiskistPoint(String result) {
        JSONObject object = JSONObject.parseObject(result);
        if("10001".equals(object.getString("code"))){
            return 30;
        }
        return 0;
    }

    /**
     * 网贷黑名单
     * http://api.chinadatapay.com/government/safety/1760?key=您申请的key值&paramType=类型（1或2）传1时，paramValue必须传主体名称； 传2时，paramValue必须传个人身份证号&paramValue=主体名称（个人身份证号）
     * @deprecated
     * @param paramType
     * @param checkName
     * @param checkPSN
     * @return
     * @throws Exception
     */
    @Override
    public int getBlacklistPoint(String paramType, String checkName, String checkPSN) throws Exception{
        Map<String, String> querys = new HashMap<String, String> ();
        Map<String, String> headers = new HashMap<String, String> ();
        querys.put("key",BL_KEY);
        querys.put("paramType",paramType);
        querys.put("paramValue",checkPSN);
        String result = "";
        try {
            HttpResponse response = HttpUtils.doPost(BL_URL, "", "POST", headers, querys, "");
            result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
            return getBLPoint(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    /**
     * 风险清单查询
     * http://api.chinadatapay.com/government/safety/1790?key=您购买的key值&name=姓名&idCard=身份证号码&mobile=手机号码&idType=证件类型&queryCause=查询原因
     * @param checkName
     * @param checkPSN
     * @param checkPhone
     * @return
     * @throws Exception
     */
    @Override
    public int getRiskistPoint(String checkName, String checkPSN, String checkPhone) throws Exception{
        Map<String, String> querys = new HashMap<String, String> ();
        Map<String, String> headers = new HashMap<String, String> ();
        querys.put("key",RISKIST_KEY);
        querys.put("name",checkName);
        querys.put("idCard",checkPSN);
        querys.put("mobile",checkPhone);
        querys.put("idType","101");
        querys.put("queryCause","10");
        String result = "";
        try {
            HttpResponse response = HttpUtils.doPost(RISKIST_URL, "", "POST", headers, querys, "");
            result = EntityUtils.toString(response.getEntity());
            System.out.println(result);
            return getRiskistPoint(result);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public static void main(String[] args)throws Exception{
        CheckCreditRSVImpl  impl = new CheckCreditRSVImpl();
//        impl.getChinaMobilePoint("陈驹远","450103198112042016","18978889432");
//        impl.getChinaUnicomPoint("陈驹远","450103198112042016","18978889432");
        impl.getChinaTelecomPoint("陈驹远","450103198112042016","18978889432");
        impl.getBadInfoPoint("450103198112042016","陈驹远");
//        impl.getBlacklistPoint("2","","450103198112042016");
//        impl.getRiskistPoint("陈驹远","450103198112042016","18978889432");

//        String t = "{\"code\":\"10000\",\"message\":\"成功\",\"data\":{\"result\":\"T\",\"phone\":\"15994328468\",\"Message1\":\"信息验证通过\",\"name\":\"陈驹远\",\"idnumber\":\"450103198112042016\",\"serialno\":\"14950865834189300\"},\"seqNo\":\"2QVS38821705181349\"}";
//        JSONObject json = JSON.parseObject(t);
//        System.out.println(json.getString("code"));
//        System.out.println(json.getJSONObject("data").getString("result"));

    }
}
