package com.ai.bdex.dataexchange.apigateway.dubbo.interfaces;

/**
 * Created by chenjy on 2017/5/17.
 */
public interface ICheckCreditRSV {
    /**
     * 获取移动手机号三要素认证
     * @author chenjy
     * @param checkName
     * @param checkPSN
     * @param checkPhone
     * @return
     */
    public int getChinaMobilePoint(String checkName, String checkPSN, String checkPhone) throws Exception;

    /**
     * 获取联通手机号三要素认证
     * @author chenjy
     * @param checkName
     * @param checkPSN
     * @param checkPhone
     * @return
     */
    public int getChinaUnicomPoint(String checkName, String checkPSN, String checkPhone)throws Exception;

    /**
     * 获取电信手机号三要素认证
     * @author chenjy
     * @param checkName
     * @param checkPSN
     * @param checkPhone
     * @return
     */
    public int getChinaTelecomPoint(String checkName, String checkPSN, String checkPhone)throws Exception;

    /**
     * 获取不良信息信用
     * @param checkName
     * @param checkPSN
     * @return
     */
    public int getBadInfoPoint(String checkName, String checkPSN)throws Exception;

    /**
     * 网贷黑名单
     * @param paramType
     * @param checkName
     * @param checkPSN
     * @return
     */
    public int getBlacklistPoint(String paramType,String checkName, String checkPSN)throws Exception;

    /**
     * 个人黑名单综合查询(风险清单查询)
     * @param checkName
     * @param checkPSN
     * @param checkPhone
     * @return
     */
    public int getRiskistPoint(String checkName, String checkPSN, String checkPhone)throws Exception;
}
