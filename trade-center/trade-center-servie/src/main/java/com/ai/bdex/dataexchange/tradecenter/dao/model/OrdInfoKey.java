package com.ai.bdex.dataexchange.tradecenter.dao.model;

public class OrdInfoKey {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_info.SUB_ORDER
     *
     * @mbg.generated Wed May 24 13:17:04 CST 2017
     */
    private String subOrder;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_ord_info.AGENT_PRICE
     *
     * @mbg.generated Wed May 24 13:17:04 CST 2017
     */
    private Long agentPrice;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_info.SUB_ORDER
     *
     * @return the value of t_ord_info.SUB_ORDER
     *
     * @mbg.generated Wed May 24 13:17:04 CST 2017
     */
    public String getSubOrder() {
        return subOrder;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_info.SUB_ORDER
     *
     * @param subOrder the value for t_ord_info.SUB_ORDER
     *
     * @mbg.generated Wed May 24 13:17:04 CST 2017
     */
    public void setSubOrder(String subOrder) {
        this.subOrder = subOrder == null ? null : subOrder.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_ord_info.AGENT_PRICE
     *
     * @return the value of t_ord_info.AGENT_PRICE
     *
     * @mbg.generated Wed May 24 13:17:04 CST 2017
     */
    public Long getAgentPrice() {
        return agentPrice;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_ord_info.AGENT_PRICE
     *
     * @param agentPrice the value for t_ord_info.AGENT_PRICE
     *
     * @mbg.generated Wed May 24 13:17:04 CST 2017
     */
    public void setAgentPrice(Long agentPrice) {
        this.agentPrice = agentPrice;
    }
}