package com.ai.bdex.dataexchange.apigateway;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestName;

import com.ai.bdex.dataexchange.apigateway.util.cfca.utils.EncryptUtils;
import com.ai.bdex.dataexchange.apigateway.util.cfca.utils.HashUtils;
import com.ai.bdex.dataexchange.apigateway.util.cfca.utils.JsonUtils;
import com.ai.bdex.dataexchange.apigateway.util.cfca.utils.SSLProtocolSocketFactory;

/**
 * @Description: CFCA数据服务测试Demo, 仅供参考<br>
 *               测试步骤<br>
 *               1、修改cfca.dataservice.demo.service.CFCATransactionTest.USER_ACCOUNT_ID<br>
 *               2、调用cfca.dataservice.demo.service.CFCATransactionTest.applyKey()，将申请到的秘钥替换掉cfca.dataservice.demo.service.CFCATransactionTest.USER_ACCOUNT_KEY
 *               <br>
 *               3、根据需求接口编写相应测试用例，可参考
 *               cfca.dataservice.demo.service.CFCATransactionTest.testCF203b0001()、cfca.dataservice.demo.service.CFCATransactionTest.testCF209b0015()
 */
public class CFCATransactionTest {
    public final static String UTF_8 = "UTF-8";
    /**
     * 加密密钥的密钥
     */
    private static final String HOME_KEY = "SDGr4234gG465356";

    /** 生产环境地址 */
    // private String urlPrefix = "https://dtserv.cfca.com.cn/dataservice";

    /** 测试环境地址 */
    private String urlPrefix = "https://210.74.42.39/dataservice";

    /** 异步获取结果，默认休眠时间 */
    private int defaultSleepTime = 5000;

    /** 异步轮训结果尝试次数 */
    private int asyncGetResultTryCount = 3;

    /** 用户账号 */
    private static final String USER_ACCOUNT_ID = "**********";

    /** 用户秘钥 */
    private static final String USER_ACCOUNT_KEY = "V1dnjVpZbl38LKQX";

    @Rule
    public TestName name = new TestName();

    @Before
    public void before() {
        Protocol protocol = new Protocol("https", new SSLProtocolSocketFactory(), 443);
        Protocol.registerProtocol("https", protocol);
    }

    @Test
    @SuppressWarnings("rawtypes")
    public void applyKey() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("lastKey", USER_ACCOUNT_KEY); // lastKey第一次申请密钥时可随意输入，第二次申请传入之前申请到的密钥明文
        params.put("lastKeyGenTime", FastDateFormat.getInstance("yyyyMMddHHmmss").format(new Date()));
        params.put("validationTime", "0");
        params.put("keyType", "0");
        Map responseBody = testNotEncryptSync("CF00000001", "apply-for-key.json", params);
        if (responseBody != null) {
            String newKey = (String) ((Map) responseBody.get("result")).get("newKey");
            String key = EncryptUtils.decryptWithAES(newKey, HOME_KEY); // 替换USER_ACCOUNT_KEY
            System.out.println("申请到的用户秘钥：" + key + "，请替换USER_ACCOUNT_KEY, 并妥善保存该密钥");
        }
    }

    @Test
    public void testCF203b0001() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("personName", "张三");
        params.put("identityType", "0");
        params.put("identityNumber", "421081199304173997");
        testEncryptSync(name.getMethodName().substring(4), "check-P2Pblacklist-id.json", params);
    }

    @Test
    public void testCF209b0015() throws Exception {
        Map<String, String> params = new HashMap<>();
        params.put("personName", "张三");
        params.put("identityType", "0");
        params.put("identityNumber", "421081199304173997");
        params.put("cardNumber", "629999466730946790");
        params.put("cellPhoneNumber", "18812188399");
        testEncryptAsync(name.getMethodName().substring(4), "yearly-payment-sum.json", params);
    }

    /**
     * 测试明文传输同步返回交易
     * 
     * @param transactionCode
     *            接口编码
     * @param urlSuffix
     *            接口url后缀部分，如申请秘钥接口为apply-for-key.json
     * @param params
     *            请求接口body部分参数
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public Map testNotEncryptSync(String transactionCode, String urlSuffix, Map<String, String> params) throws Exception {
        return test(transactionCode, urlSuffix, params, false, true);
    }

    /**
     * 测试加密传输同步返回交易
     * 
     * 
     * @param transactionCode
     *            接口编码
     * @param urlSuffix
     *            接口url后缀部分，如申请秘钥接口为apply-for-key.json
     * @param params
     *            请求接口body部分参数
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public Map testEncryptSync(String transactionCode, String urlSuffix, Map<String, String> params) throws Exception {
        return test(transactionCode, urlSuffix, params, true, true);
    }

    /**
     * 测试明文传输异步返回交易
     * 
     * @param transactionCode
     *            接口编码
     * @param urlSuffix
     *            接口url后缀部分，如申请秘钥接口为apply-for-key.json
     * @param params
     *            请求接口body部分参数
     * @param sleepTime
     *            获取结果休眠时间, 默认5秒钟
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public Map testNotEncryptedAsync(String transactionCode, String urlSuffix, Map<String, String> params, int... sleepTime) throws Exception {
        return test(transactionCode, urlSuffix, params, false, false, sleepTime);
    }

    /**
     * 测试加密传世异步返回交易
     * 
     * @param transactionCode
     *            接口编码
     * @param urlSuffix
     *            接口url后缀部分，如申请秘钥接口为apply-for-key.json
     * @param params
     *            请求接口body部分参数
     * 
     * @param sleepTime
     *            获取结果休眠时间, 默认5秒钟
     * @return
     * @throws Exception
     */
    @SuppressWarnings("rawtypes")
    public Map testEncryptAsync(String transactionCode, String urlSuffix, Map<String, String> params, int... sleepTime) throws Exception {
        return test(transactionCode, urlSuffix, params, true, false, sleepTime);
    }

    /**
     * 测试交易类
     * 
     * @param transactionCode
     *            接口编码
     * @param urlSuffix
     *            接口url后缀部分，如申请秘钥接口为apply-for-key.json
     * @param params
     *            请求接口body部分参数
     * @param encryptFlag
     *            是否加密接口 true为加密接口，false为明文接口
     * @param syncFlag
     *            是否为同步接口，true为同步接口，false为异步接口
     * @param sleepTime
     *            获取结果休眠时间, 默认5秒钟
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public Map test(String transactionCode, String urlSuffix, Map<String, String> params, boolean encryptFlag, boolean syncFlag, int... sleepTime)
            throws Exception {
        System.out.println("------------------提交任务----------------------");
        long start = System.currentTimeMillis();

        Map request = new LinkedHashMap<String, Object>();

        Map head = new LinkedHashMap();
        head.put("transactionID", UUID.randomUUID().toString().replace("-", ""));
        head.put("transactionCode", transactionCode);
        head.put("action", "0");
        head.put("chanel", "0");
        head.put("userAccountID", USER_ACCOUNT_ID);
        head.put("authorizationID", "11111111"); // 业务查询授权号
        request.put("head", head);

        Map body = new HashMap<String, String>(params);
        String bodyJson = JsonUtils.obj2Json(body);
        System.out.println("encrypt source:" + bodyJson);
        request.put("body", encryptFlag ? EncryptUtils.encryptWithAES(bodyJson, USER_ACCOUNT_KEY) : body);
        String temp = JsonUtils.obj2Json(request);
        String hashSource = temp.substring(1, temp.length() - 1);
        System.out.println("hashSource:" + hashSource);
        request.put("hashValue", HashUtils.hashWithSHA256(hashSource));
        String urlStr = urlPrefix + "/" + urlSuffix;
        System.out.println(urlStr);
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(urlStr);
        String requestStr = JsonUtils.obj2Json(request);
        System.out.println("request:" + requestStr);

        StringRequestEntity requestEntity = new StringRequestEntity(requestStr, "application/json", UTF_8);
        postMethod.getParams().setContentCharset(UTF_8);
        postMethod.setRequestEntity(requestEntity);
        httpClient.executeMethod(postMethod);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("response code:" + postMethod.getStatusCode());
        String responseStr = postMethod.getResponseBodyAsString();
        System.out.println("response:" + responseStr);
        postMethod.releaseConnection();
        Map response = JsonUtils.json2Obj(responseStr, Map.class);
        Map responseBody = null;
        if ("00000".equals(((Map) response.get("head")).get("code"))) {
            responseBody = encryptFlag ? JsonUtils.json2Obj(EncryptUtils.decryptWithAES((String) response.get("body"), USER_ACCOUNT_KEY), Map.class)
                    : (Map) (response.get("body"));
            System.out.println("response body:" + responseBody);
        } else {
            return null;
        }
        if (syncFlag) {
            return responseBody;
        }
        String orderID = (String) responseBody.get("orderID"); // 异步接口提交任务回执

        responseBody = null;

        responseBody = asyncGetResult(transactionCode, urlSuffix, encryptFlag, orderID, sleepTime);
        return responseBody;
    }

    /**
     * 异步轮询结果
     * 
     * @param transactionCode
     *            接口编码
     * @param urlSuffix
     *            接口url后缀部分，如申请秘钥接口为apply-for-key.json
     * @param encryptFlag
     *            是否加密接口 true为加密接口，false为明文接口
     * @param orderID
     *            异步提交任务回执
     * @param sleepTime
     *            获取结果休眠时间, 默认5秒钟
     * @return
     * @throws Exception
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    private Map asyncGetResult(String transactionCode, String urlSuffix, boolean encryptFlag, String orderID, int... sleepTime) throws Exception {

        Map responseBody = null;
        HttpClient httpClient = new HttpClient();

        while ((responseBody == null || responseBody.get("result") == null) && (asyncGetResultTryCount-- > 0)) {

            Thread.sleep(sleepTime != null && sleepTime.length > 0 ? sleepTime[0] : defaultSleepTime);
            long start = System.currentTimeMillis();

            System.out.println("------------------获取结果----------------------");
            Map request = new LinkedHashMap<String, Object>();
            Map head = new HashMap<>();
            head.put("transactionID", UUID.randomUUID().toString().replace("-", ""));
            head.put("transactionCode", transactionCode);
            head.put("action", "1");
            head.put("chanel", "0");
            head.put("userAccountID", USER_ACCOUNT_ID);
            head.put("authorizationID", "11111111"); // 业务查询授权号
            request.put("head", head);

            Map body = new HashMap<String, String>();
            body.put("orderID", orderID);
            String bodyJson = JsonUtils.obj2Json(body);
            System.out.println("body source:" + bodyJson);
            request.put("body", encryptFlag ? EncryptUtils.encryptWithAES(bodyJson, USER_ACCOUNT_KEY) : body);
            String temp = JsonUtils.obj2Json(request);
            String hashSource = temp.substring(1, temp.length() - 1);
            System.out.println("hashSource:" + hashSource);
            request.put("hashValue", HashUtils.hashWithSHA256(hashSource));
            String urlStr = urlPrefix + "/" + urlSuffix;
            System.out.println(urlStr);
            PostMethod postMethod = new PostMethod(urlStr);
            String requestStr = JsonUtils.obj2Json(request);
            System.out.println("request:" + requestStr);

            StringRequestEntity requestEntity = new StringRequestEntity(requestStr, "application/json", UTF_8);
            postMethod.getParams().setContentCharset(UTF_8);
            postMethod.setRequestEntity(requestEntity);
            httpClient.executeMethod(postMethod);
            System.out.println("response code:" + postMethod.getStatusCode());
            String responseStr = postMethod.getResponseBodyAsString();
            postMethod.releaseConnection();

            System.out.println("response:" + responseStr);
            Map response = JsonUtils.json2Obj(responseStr, Map.class);
            if ("00000".equals(((Map) response.get("head")).get("code"))) {
                responseBody = encryptFlag ? JsonUtils.json2Obj(EncryptUtils.decryptWithAES((String) response.get("body"), USER_ACCOUNT_KEY), Map.class)
                        : (Map) (response.get("body"));
                System.out.println("response body:" + responseBody);
            } else {
                System.out.println("查询失败");
                break;
            }
            System.out.println("+++++++++++++++++++++++++++++++++");
            System.out.println(System.currentTimeMillis() - start);
        }
        return responseBody;
    }
}