package com.ai.bdex.dataexchange.apigateway.dao.model;

public class AipClientScopeKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_scope.CLIENT_ID
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    private String clientId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_aip_client_scope.SCOPE_ID
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    private String scopeId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_scope.CLIENT_ID
     *
     * @return the value of t_aip_client_scope.CLIENT_ID
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_scope.CLIENT_ID
     *
     * @param clientId the value for t_aip_client_scope.CLIENT_ID
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_aip_client_scope.SCOPE_ID
     *
     * @return the value of t_aip_client_scope.SCOPE_ID
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    public String getScopeId() {
        return scopeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_aip_client_scope.SCOPE_ID
     *
     * @param scopeId the value for t_aip_client_scope.SCOPE_ID
     *
     * @mbg.generated Tue May 23 16:25:05 CST 2017
     */
    public void setScopeId(String scopeId) {
        this.scopeId = scopeId == null ? null : scopeId.trim();
    }
}