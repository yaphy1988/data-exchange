package com.ai.bdex.dataexchange.apigateway.dao.model;

public class AipApiDataKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_api_data.service_id
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    private String serviceId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_api_data.version_id
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    private String versionId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_api_data.request_md5
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    private String requestMd5;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_api_data.service_id
     *
     * @return the value of t_aip_api_data.service_id
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    public String getServiceId() {
        return serviceId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_api_data.service_id
     *
     * @param serviceId the value for t_aip_api_data.service_id
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    public void setServiceId(String serviceId) {
        this.serviceId = serviceId == null ? null : serviceId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_api_data.version_id
     *
     * @return the value of t_aip_api_data.version_id
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    public String getVersionId() {
        return versionId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_api_data.version_id
     *
     * @param versionId the value for t_aip_api_data.version_id
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    public void setVersionId(String versionId) {
        this.versionId = versionId == null ? null : versionId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_api_data.request_md5
     *
     * @return the value of t_aip_api_data.request_md5
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    public String getRequestMd5() {
        return requestMd5;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_api_data.request_md5
     *
     * @param requestMd5 the value for t_aip_api_data.request_md5
     *
     * @mbg.generated Tue May 23 19:58:12 CST 2017
     */
    public void setRequestMd5(String requestMd5) {
        this.requestMd5 = requestMd5 == null ? null : requestMd5.trim();
    }
}