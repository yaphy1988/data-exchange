package com.ai.bdex.dataexchange.usercenter.dao.model;

public class BaseLoginUrl extends BaseLoginUrlKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_base_login_url.status
     *
     * @mbg.generated Mon May 15 10:07:23 CST 2017
     */
    private String status;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_base_login_url.unlogin_access
     *
     * @mbg.generated Mon May 15 10:07:23 CST 2017
     */
    private String unloginAccess;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_base_login_url.url_desc
     *
     * @mbg.generated Mon May 15 10:07:23 CST 2017
     */
    private String urlDesc;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_base_login_url.status
     *
     * @return the value of t_base_login_url.status
     *
     * @mbg.generated Mon May 15 10:07:23 CST 2017
     */
    public String getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_base_login_url.status
     *
     * @param status the value for t_base_login_url.status
     *
     * @mbg.generated Mon May 15 10:07:23 CST 2017
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_base_login_url.unlogin_access
     *
     * @return the value of t_base_login_url.unlogin_access
     *
     * @mbg.generated Mon May 15 10:07:23 CST 2017
     */
    public String getUnloginAccess() {
        return unloginAccess;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_base_login_url.unlogin_access
     *
     * @param unloginAccess the value for t_base_login_url.unlogin_access
     *
     * @mbg.generated Mon May 15 10:07:23 CST 2017
     */
    public void setUnloginAccess(String unloginAccess) {
        this.unloginAccess = unloginAccess == null ? null : unloginAccess.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_base_login_url.url_desc
     *
     * @return the value of t_base_login_url.url_desc
     *
     * @mbg.generated Mon May 15 10:07:23 CST 2017
     */
    public String getUrlDesc() {
        return urlDesc;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_base_login_url.url_desc
     *
     * @param urlDesc the value for t_base_login_url.url_desc
     *
     * @mbg.generated Mon May 15 10:07:23 CST 2017
     */
    public void setUrlDesc(String urlDesc) {
        this.urlDesc = urlDesc == null ? null : urlDesc.trim();
    }
}