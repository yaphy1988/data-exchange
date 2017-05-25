package com.ai.bdex.dataexchange.apigateway.dao.model;

import java.util.Date;

public class AipApiData extends AipApiDataKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_api_data.requestStr
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    private String requeststr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_api_data.responseStr
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    private String responsestr;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_api_data.create_time
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    private Date createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_api_data.status
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    private String status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_api_data.requestStr
     *
     * @return the value of t_aip_api_data.requestStr
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    public String getRequeststr() {
        return requeststr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_api_data.requestStr
     *
     * @param requeststr the value for t_aip_api_data.requestStr
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    public void setRequeststr(String requeststr) {
        this.requeststr = requeststr == null ? null : requeststr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_api_data.responseStr
     *
     * @return the value of t_aip_api_data.responseStr
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    public String getResponsestr() {
        return responsestr;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_api_data.responseStr
     *
     * @param responsestr the value for t_aip_api_data.responseStr
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    public void setResponsestr(String responsestr) {
        this.responsestr = responsestr == null ? null : responsestr.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_api_data.create_time
     *
     * @return the value of t_aip_api_data.create_time
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_api_data.create_time
     *
     * @param createTime the value for t_aip_api_data.create_time
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_api_data.status
     *
     * @return the value of t_aip_api_data.status
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_api_data.status
     *
     * @param status the value for t_aip_api_data.status
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}