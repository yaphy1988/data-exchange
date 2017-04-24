package com.ai.bdex.dataexchange.aipcenter.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AipServiceUsedLogExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    public AipServiceUsedLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andUsedIdIsNull() {
            addCriterion("USED_ID is null");
            return (Criteria) this;
        }

        public Criteria andUsedIdIsNotNull() {
            addCriterion("USED_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUsedIdEqualTo(String value) {
            addCriterion("USED_ID =", value, "usedId");
            return (Criteria) this;
        }

        public Criteria andUsedIdNotEqualTo(String value) {
            addCriterion("USED_ID <>", value, "usedId");
            return (Criteria) this;
        }

        public Criteria andUsedIdGreaterThan(String value) {
            addCriterion("USED_ID >", value, "usedId");
            return (Criteria) this;
        }

        public Criteria andUsedIdGreaterThanOrEqualTo(String value) {
            addCriterion("USED_ID >=", value, "usedId");
            return (Criteria) this;
        }

        public Criteria andUsedIdLessThan(String value) {
            addCriterion("USED_ID <", value, "usedId");
            return (Criteria) this;
        }

        public Criteria andUsedIdLessThanOrEqualTo(String value) {
            addCriterion("USED_ID <=", value, "usedId");
            return (Criteria) this;
        }

        public Criteria andUsedIdLike(String value) {
            addCriterion("USED_ID like", value, "usedId");
            return (Criteria) this;
        }

        public Criteria andUsedIdNotLike(String value) {
            addCriterion("USED_ID not like", value, "usedId");
            return (Criteria) this;
        }

        public Criteria andUsedIdIn(List<String> values) {
            addCriterion("USED_ID in", values, "usedId");
            return (Criteria) this;
        }

        public Criteria andUsedIdNotIn(List<String> values) {
            addCriterion("USED_ID not in", values, "usedId");
            return (Criteria) this;
        }

        public Criteria andUsedIdBetween(String value1, String value2) {
            addCriterion("USED_ID between", value1, value2, "usedId");
            return (Criteria) this;
        }

        public Criteria andUsedIdNotBetween(String value1, String value2) {
            addCriterion("USED_ID not between", value1, value2, "usedId");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNull() {
            addCriterion("SERVICE_ID is null");
            return (Criteria) this;
        }

        public Criteria andServiceIdIsNotNull() {
            addCriterion("SERVICE_ID is not null");
            return (Criteria) this;
        }

        public Criteria andServiceIdEqualTo(String value) {
            addCriterion("SERVICE_ID =", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotEqualTo(String value) {
            addCriterion("SERVICE_ID <>", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThan(String value) {
            addCriterion("SERVICE_ID >", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdGreaterThanOrEqualTo(String value) {
            addCriterion("SERVICE_ID >=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThan(String value) {
            addCriterion("SERVICE_ID <", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLessThanOrEqualTo(String value) {
            addCriterion("SERVICE_ID <=", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdLike(String value) {
            addCriterion("SERVICE_ID like", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotLike(String value) {
            addCriterion("SERVICE_ID not like", value, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdIn(List<String> values) {
            addCriterion("SERVICE_ID in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotIn(List<String> values) {
            addCriterion("SERVICE_ID not in", values, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdBetween(String value1, String value2) {
            addCriterion("SERVICE_ID between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andServiceIdNotBetween(String value1, String value2) {
            addCriterion("SERVICE_ID not between", value1, value2, "serviceId");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("VERSION is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("VERSION is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(String value) {
            addCriterion("VERSION =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(String value) {
            addCriterion("VERSION <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(String value) {
            addCriterion("VERSION >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(String value) {
            addCriterion("VERSION >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(String value) {
            addCriterion("VERSION <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(String value) {
            addCriterion("VERSION <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLike(String value) {
            addCriterion("VERSION like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotLike(String value) {
            addCriterion("VERSION not like", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<String> values) {
            addCriterion("VERSION in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<String> values) {
            addCriterion("VERSION not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(String value1, String value2) {
            addCriterion("VERSION between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(String value1, String value2) {
            addCriterion("VERSION not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("STATUS is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andClientIdIsNull() {
            addCriterion("CLIENT_ID is null");
            return (Criteria) this;
        }

        public Criteria andClientIdIsNotNull() {
            addCriterion("CLIENT_ID is not null");
            return (Criteria) this;
        }

        public Criteria andClientIdEqualTo(String value) {
            addCriterion("CLIENT_ID =", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotEqualTo(String value) {
            addCriterion("CLIENT_ID <>", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThan(String value) {
            addCriterion("CLIENT_ID >", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdGreaterThanOrEqualTo(String value) {
            addCriterion("CLIENT_ID >=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThan(String value) {
            addCriterion("CLIENT_ID <", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLessThanOrEqualTo(String value) {
            addCriterion("CLIENT_ID <=", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdLike(String value) {
            addCriterion("CLIENT_ID like", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotLike(String value) {
            addCriterion("CLIENT_ID not like", value, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdIn(List<String> values) {
            addCriterion("CLIENT_ID in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotIn(List<String> values) {
            addCriterion("CLIENT_ID not in", values, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdBetween(String value1, String value2) {
            addCriterion("CLIENT_ID between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andClientIdNotBetween(String value1, String value2) {
            addCriterion("CLIENT_ID not between", value1, value2, "clientId");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNull() {
            addCriterion("ACCESS_TOKEN is null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIsNotNull() {
            addCriterion("ACCESS_TOKEN is not null");
            return (Criteria) this;
        }

        public Criteria andAccessTokenEqualTo(String value) {
            addCriterion("ACCESS_TOKEN =", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotEqualTo(String value) {
            addCriterion("ACCESS_TOKEN <>", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThan(String value) {
            addCriterion("ACCESS_TOKEN >", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenGreaterThanOrEqualTo(String value) {
            addCriterion("ACCESS_TOKEN >=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThan(String value) {
            addCriterion("ACCESS_TOKEN <", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLessThanOrEqualTo(String value) {
            addCriterion("ACCESS_TOKEN <=", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenLike(String value) {
            addCriterion("ACCESS_TOKEN like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotLike(String value) {
            addCriterion("ACCESS_TOKEN not like", value, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenIn(List<String> values) {
            addCriterion("ACCESS_TOKEN in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotIn(List<String> values) {
            addCriterion("ACCESS_TOKEN not in", values, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenBetween(String value1, String value2) {
            addCriterion("ACCESS_TOKEN between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andAccessTokenNotBetween(String value1, String value2) {
            addCriterion("ACCESS_TOKEN not between", value1, value2, "accessToken");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andRequestMsgIsNull() {
            addCriterion("REQUEST_MSG is null");
            return (Criteria) this;
        }

        public Criteria andRequestMsgIsNotNull() {
            addCriterion("REQUEST_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andRequestMsgEqualTo(String value) {
            addCriterion("REQUEST_MSG =", value, "requestMsg");
            return (Criteria) this;
        }

        public Criteria andRequestMsgNotEqualTo(String value) {
            addCriterion("REQUEST_MSG <>", value, "requestMsg");
            return (Criteria) this;
        }

        public Criteria andRequestMsgGreaterThan(String value) {
            addCriterion("REQUEST_MSG >", value, "requestMsg");
            return (Criteria) this;
        }

        public Criteria andRequestMsgGreaterThanOrEqualTo(String value) {
            addCriterion("REQUEST_MSG >=", value, "requestMsg");
            return (Criteria) this;
        }

        public Criteria andRequestMsgLessThan(String value) {
            addCriterion("REQUEST_MSG <", value, "requestMsg");
            return (Criteria) this;
        }

        public Criteria andRequestMsgLessThanOrEqualTo(String value) {
            addCriterion("REQUEST_MSG <=", value, "requestMsg");
            return (Criteria) this;
        }

        public Criteria andRequestMsgLike(String value) {
            addCriterion("REQUEST_MSG like", value, "requestMsg");
            return (Criteria) this;
        }

        public Criteria andRequestMsgNotLike(String value) {
            addCriterion("REQUEST_MSG not like", value, "requestMsg");
            return (Criteria) this;
        }

        public Criteria andRequestMsgIn(List<String> values) {
            addCriterion("REQUEST_MSG in", values, "requestMsg");
            return (Criteria) this;
        }

        public Criteria andRequestMsgNotIn(List<String> values) {
            addCriterion("REQUEST_MSG not in", values, "requestMsg");
            return (Criteria) this;
        }

        public Criteria andRequestMsgBetween(String value1, String value2) {
            addCriterion("REQUEST_MSG between", value1, value2, "requestMsg");
            return (Criteria) this;
        }

        public Criteria andRequestMsgNotBetween(String value1, String value2) {
            addCriterion("REQUEST_MSG not between", value1, value2, "requestMsg");
            return (Criteria) this;
        }

        public Criteria andResponseMsgIsNull() {
            addCriterion("RESPONSE_MSG is null");
            return (Criteria) this;
        }

        public Criteria andResponseMsgIsNotNull() {
            addCriterion("RESPONSE_MSG is not null");
            return (Criteria) this;
        }

        public Criteria andResponseMsgEqualTo(String value) {
            addCriterion("RESPONSE_MSG =", value, "responseMsg");
            return (Criteria) this;
        }

        public Criteria andResponseMsgNotEqualTo(String value) {
            addCriterion("RESPONSE_MSG <>", value, "responseMsg");
            return (Criteria) this;
        }

        public Criteria andResponseMsgGreaterThan(String value) {
            addCriterion("RESPONSE_MSG >", value, "responseMsg");
            return (Criteria) this;
        }

        public Criteria andResponseMsgGreaterThanOrEqualTo(String value) {
            addCriterion("RESPONSE_MSG >=", value, "responseMsg");
            return (Criteria) this;
        }

        public Criteria andResponseMsgLessThan(String value) {
            addCriterion("RESPONSE_MSG <", value, "responseMsg");
            return (Criteria) this;
        }

        public Criteria andResponseMsgLessThanOrEqualTo(String value) {
            addCriterion("RESPONSE_MSG <=", value, "responseMsg");
            return (Criteria) this;
        }

        public Criteria andResponseMsgLike(String value) {
            addCriterion("RESPONSE_MSG like", value, "responseMsg");
            return (Criteria) this;
        }

        public Criteria andResponseMsgNotLike(String value) {
            addCriterion("RESPONSE_MSG not like", value, "responseMsg");
            return (Criteria) this;
        }

        public Criteria andResponseMsgIn(List<String> values) {
            addCriterion("RESPONSE_MSG in", values, "responseMsg");
            return (Criteria) this;
        }

        public Criteria andResponseMsgNotIn(List<String> values) {
            addCriterion("RESPONSE_MSG not in", values, "responseMsg");
            return (Criteria) this;
        }

        public Criteria andResponseMsgBetween(String value1, String value2) {
            addCriterion("RESPONSE_MSG between", value1, value2, "responseMsg");
            return (Criteria) this;
        }

        public Criteria andResponseMsgNotBetween(String value1, String value2) {
            addCriterion("RESPONSE_MSG not between", value1, value2, "responseMsg");
            return (Criteria) this;
        }

        public Criteria andResponseTimeIsNull() {
            addCriterion("RESPONSE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andResponseTimeIsNotNull() {
            addCriterion("RESPONSE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andResponseTimeEqualTo(Date value) {
            addCriterion("RESPONSE_TIME =", value, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeNotEqualTo(Date value) {
            addCriterion("RESPONSE_TIME <>", value, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeGreaterThan(Date value) {
            addCriterion("RESPONSE_TIME >", value, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("RESPONSE_TIME >=", value, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeLessThan(Date value) {
            addCriterion("RESPONSE_TIME <", value, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeLessThanOrEqualTo(Date value) {
            addCriterion("RESPONSE_TIME <=", value, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeIn(List<Date> values) {
            addCriterion("RESPONSE_TIME in", values, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeNotIn(List<Date> values) {
            addCriterion("RESPONSE_TIME not in", values, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeBetween(Date value1, Date value2) {
            addCriterion("RESPONSE_TIME between", value1, value2, "responseTime");
            return (Criteria) this;
        }

        public Criteria andResponseTimeNotBetween(Date value1, Date value2) {
            addCriterion("RESPONSE_TIME not between", value1, value2, "responseTime");
            return (Criteria) this;
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated do_not_delete_during_merge Mon Apr 24 10:27:08 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_aip_service_used_log
     *
     * @mbg.generated Mon Apr 24 10:27:08 CST 2017
     */
    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}