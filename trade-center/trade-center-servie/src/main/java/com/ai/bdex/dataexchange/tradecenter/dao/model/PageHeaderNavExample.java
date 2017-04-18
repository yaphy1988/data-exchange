package com.ai.bdex.dataexchange.tradecenter.dao.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PageHeaderNavExample {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    protected String orderByClause;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    protected boolean distinct;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    protected List<Criteria> oredCriteria;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public PageHeaderNavExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public String getOrderByClause() {
        return orderByClause;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public boolean isDistinct() {
        return distinct;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
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
     * This method corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
     */
    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
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

        public Criteria andNavIdIsNull() {
            addCriterion("NAV_ID is null");
            return (Criteria) this;
        }

        public Criteria andNavIdIsNotNull() {
            addCriterion("NAV_ID is not null");
            return (Criteria) this;
        }

        public Criteria andNavIdEqualTo(Integer value) {
            addCriterion("NAV_ID =", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdNotEqualTo(Integer value) {
            addCriterion("NAV_ID <>", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdGreaterThan(Integer value) {
            addCriterion("NAV_ID >", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("NAV_ID >=", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdLessThan(Integer value) {
            addCriterion("NAV_ID <", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdLessThanOrEqualTo(Integer value) {
            addCriterion("NAV_ID <=", value, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdIn(List<Integer> values) {
            addCriterion("NAV_ID in", values, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdNotIn(List<Integer> values) {
            addCriterion("NAV_ID not in", values, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdBetween(Integer value1, Integer value2) {
            addCriterion("NAV_ID between", value1, value2, "navId");
            return (Criteria) this;
        }

        public Criteria andNavIdNotBetween(Integer value1, Integer value2) {
            addCriterion("NAV_ID not between", value1, value2, "navId");
            return (Criteria) this;
        }

        public Criteria andNavNameIsNull() {
            addCriterion("NAV_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNavNameIsNotNull() {
            addCriterion("NAV_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNavNameEqualTo(String value) {
            addCriterion("NAV_NAME =", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameNotEqualTo(String value) {
            addCriterion("NAV_NAME <>", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameGreaterThan(String value) {
            addCriterion("NAV_NAME >", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAV_NAME >=", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameLessThan(String value) {
            addCriterion("NAV_NAME <", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameLessThanOrEqualTo(String value) {
            addCriterion("NAV_NAME <=", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameLike(String value) {
            addCriterion("NAV_NAME like", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameNotLike(String value) {
            addCriterion("NAV_NAME not like", value, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameIn(List<String> values) {
            addCriterion("NAV_NAME in", values, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameNotIn(List<String> values) {
            addCriterion("NAV_NAME not in", values, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameBetween(String value1, String value2) {
            addCriterion("NAV_NAME between", value1, value2, "navName");
            return (Criteria) this;
        }

        public Criteria andNavNameNotBetween(String value1, String value2) {
            addCriterion("NAV_NAME not between", value1, value2, "navName");
            return (Criteria) this;
        }

        public Criteria andNavLinkIsNull() {
            addCriterion("NAV_LINK is null");
            return (Criteria) this;
        }

        public Criteria andNavLinkIsNotNull() {
            addCriterion("NAV_LINK is not null");
            return (Criteria) this;
        }

        public Criteria andNavLinkEqualTo(String value) {
            addCriterion("NAV_LINK =", value, "navLink");
            return (Criteria) this;
        }

        public Criteria andNavLinkNotEqualTo(String value) {
            addCriterion("NAV_LINK <>", value, "navLink");
            return (Criteria) this;
        }

        public Criteria andNavLinkGreaterThan(String value) {
            addCriterion("NAV_LINK >", value, "navLink");
            return (Criteria) this;
        }

        public Criteria andNavLinkGreaterThanOrEqualTo(String value) {
            addCriterion("NAV_LINK >=", value, "navLink");
            return (Criteria) this;
        }

        public Criteria andNavLinkLessThan(String value) {
            addCriterion("NAV_LINK <", value, "navLink");
            return (Criteria) this;
        }

        public Criteria andNavLinkLessThanOrEqualTo(String value) {
            addCriterion("NAV_LINK <=", value, "navLink");
            return (Criteria) this;
        }

        public Criteria andNavLinkLike(String value) {
            addCriterion("NAV_LINK like", value, "navLink");
            return (Criteria) this;
        }

        public Criteria andNavLinkNotLike(String value) {
            addCriterion("NAV_LINK not like", value, "navLink");
            return (Criteria) this;
        }

        public Criteria andNavLinkIn(List<String> values) {
            addCriterion("NAV_LINK in", values, "navLink");
            return (Criteria) this;
        }

        public Criteria andNavLinkNotIn(List<String> values) {
            addCriterion("NAV_LINK not in", values, "navLink");
            return (Criteria) this;
        }

        public Criteria andNavLinkBetween(String value1, String value2) {
            addCriterion("NAV_LINK between", value1, value2, "navLink");
            return (Criteria) this;
        }

        public Criteria andNavLinkNotBetween(String value1, String value2) {
            addCriterion("NAV_LINK not between", value1, value2, "navLink");
            return (Criteria) this;
        }

        public Criteria andNavOrderIsNull() {
            addCriterion("NAV_ORDER is null");
            return (Criteria) this;
        }

        public Criteria andNavOrderIsNotNull() {
            addCriterion("NAV_ORDER is not null");
            return (Criteria) this;
        }

        public Criteria andNavOrderEqualTo(Integer value) {
            addCriterion("NAV_ORDER =", value, "navOrder");
            return (Criteria) this;
        }

        public Criteria andNavOrderNotEqualTo(Integer value) {
            addCriterion("NAV_ORDER <>", value, "navOrder");
            return (Criteria) this;
        }

        public Criteria andNavOrderGreaterThan(Integer value) {
            addCriterion("NAV_ORDER >", value, "navOrder");
            return (Criteria) this;
        }

        public Criteria andNavOrderGreaterThanOrEqualTo(Integer value) {
            addCriterion("NAV_ORDER >=", value, "navOrder");
            return (Criteria) this;
        }

        public Criteria andNavOrderLessThan(Integer value) {
            addCriterion("NAV_ORDER <", value, "navOrder");
            return (Criteria) this;
        }

        public Criteria andNavOrderLessThanOrEqualTo(Integer value) {
            addCriterion("NAV_ORDER <=", value, "navOrder");
            return (Criteria) this;
        }

        public Criteria andNavOrderIn(List<Integer> values) {
            addCriterion("NAV_ORDER in", values, "navOrder");
            return (Criteria) this;
        }

        public Criteria andNavOrderNotIn(List<Integer> values) {
            addCriterion("NAV_ORDER not in", values, "navOrder");
            return (Criteria) this;
        }

        public Criteria andNavOrderBetween(Integer value1, Integer value2) {
            addCriterion("NAV_ORDER between", value1, value2, "navOrder");
            return (Criteria) this;
        }

        public Criteria andNavOrderNotBetween(Integer value1, Integer value2) {
            addCriterion("NAV_ORDER not between", value1, value2, "navOrder");
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

        public Criteria andRemarkIsNull() {
            addCriterion("REMARK is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("REMARK is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("REMARK =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("REMARK <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("REMARK >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("REMARK >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("REMARK <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("REMARK <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("REMARK like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("REMARK not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("REMARK in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("REMARK not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("REMARK between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("REMARK not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIsNull() {
            addCriterion("CREATE_STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIsNotNull() {
            addCriterion("CREATE_STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdEqualTo(String value) {
            addCriterion("CREATE_STAFF_ID =", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotEqualTo(String value) {
            addCriterion("CREATE_STAFF_ID <>", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdGreaterThan(String value) {
            addCriterion("CREATE_STAFF_ID >", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_STAFF_ID >=", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdLessThan(String value) {
            addCriterion("CREATE_STAFF_ID <", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdLessThanOrEqualTo(String value) {
            addCriterion("CREATE_STAFF_ID <=", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdLike(String value) {
            addCriterion("CREATE_STAFF_ID like", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotLike(String value) {
            addCriterion("CREATE_STAFF_ID not like", value, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdIn(List<String> values) {
            addCriterion("CREATE_STAFF_ID in", values, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotIn(List<String> values) {
            addCriterion("CREATE_STAFF_ID not in", values, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdBetween(String value1, String value2) {
            addCriterion("CREATE_STAFF_ID between", value1, value2, "createStaffId");
            return (Criteria) this;
        }

        public Criteria andCreateStaffIdNotBetween(String value1, String value2) {
            addCriterion("CREATE_STAFF_ID not between", value1, value2, "createStaffId");
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

        public Criteria andUpdateStaffIdIsNull() {
            addCriterion("UPDATE_STAFF_ID is null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdIsNotNull() {
            addCriterion("UPDATE_STAFF_ID is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdEqualTo(String value) {
            addCriterion("UPDATE_STAFF_ID =", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdNotEqualTo(String value) {
            addCriterion("UPDATE_STAFF_ID <>", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdGreaterThan(String value) {
            addCriterion("UPDATE_STAFF_ID >", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_STAFF_ID >=", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdLessThan(String value) {
            addCriterion("UPDATE_STAFF_ID <", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_STAFF_ID <=", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdLike(String value) {
            addCriterion("UPDATE_STAFF_ID like", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdNotLike(String value) {
            addCriterion("UPDATE_STAFF_ID not like", value, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdIn(List<String> values) {
            addCriterion("UPDATE_STAFF_ID in", values, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdNotIn(List<String> values) {
            addCriterion("UPDATE_STAFF_ID not in", values, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdBetween(String value1, String value2) {
            addCriterion("UPDATE_STAFF_ID between", value1, value2, "updateStaffId");
            return (Criteria) this;
        }

        public Criteria andUpdateStaffIdNotBetween(String value1, String value2) {
            addCriterion("UPDATE_STAFF_ID not between", value1, value2, "updateStaffId");
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
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_page_header_nav
     *
     * @mbg.generated do_not_delete_during_merge Tue Apr 18 20:36:16 CST 2017
     */
    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    /**
     * This class was generated by MyBatis Generator.
     * This class corresponds to the database table t_page_header_nav
     *
     * @mbg.generated Tue Apr 18 20:36:16 CST 2017
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